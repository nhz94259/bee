package com.nhz.mycode.completed.source;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransmittableThreadLocalRead<T> extends InheritableThreadLocal<T> {
    /**
     * 所用的TransmittableThreadLocalRead 就是 TransmittableThreadLocal
     *
     * */
    private static final Logger logger = Logger.getLogger(TransmittableThreadLocalRead.class.getName());

    /**
     *  当任务对象被常见你的时候，会根据父线程的值进行计算出当前 {transmittable thread-local}的值
     *  This method is called from {@link TtlRunnable} or {@link TtlCallable} when it create, before the task is started.
     *  这个方法通常是被在任务执行之前调用，如 {@link TtlRunnable} or {@link TtlCallable} 类型任务创建时候进行调用
     *  此方法仅返回其来父线程值的引用，如果需要不同的行为，则应重写
     */
    protected T copy(T parentValue) {
        return parentValue;
    }

    /**
     * 执行（{@link TtlRunnable} / {@ link TtlCallable}）任务对象之前的回调方法。
     */
    protected void beforeExecute() {
    }

    /**
     * 执行（{@link TtlRunnable} / {@ link TtlCallable}）任务对象之后的回调方法。
     */
    protected void afterExecute() {
    }

    /**
     * see {@link InheritableThreadLocal#get()}
     */
    @Override
    public final T get() {
        T value = super.get();
        if (null != value) addValue();
        return value;
    }

    /**
     * see {@link InheritableThreadLocal#set}
     */
    @Override
    public final void set(T value) {
        super.set(value);
        // may set null to remove value
        if (null == value) removeValue();
        else addValue();
    }

    /**
     * see {@link InheritableThreadLocal#remove()}
     */
    @Override
    public final void remove() {
        removeValue();
        super.remove();
    }

    private void superRemove() {
        super.remove();
    }

    private T copyValue() {
        return copy(get());
    }

    // 注意 holder 这个对象:
    // 1 .holder的值类型Map<TransmittableThreadLocal<?>, ?> (WeakHashMap implementation),
    // 2. WeakHashMap 支持null 值.
    private static InheritableThreadLocal<Map<TransmittableThreadLocalRead<?>, ?>> holder =
            new InheritableThreadLocal<Map<TransmittableThreadLocalRead<?>, ?>>() {
                @Override
                protected Map<TransmittableThreadLocalRead<?>, ?> initialValue() {
                    return new WeakHashMap<TransmittableThreadLocalRead<?>, Object>();
                }
                @Override
                protected Map<TransmittableThreadLocalRead<?>, ?> childValue(Map<TransmittableThreadLocalRead<?>, ?> parentValue) {
                    return new WeakHashMap<TransmittableThreadLocalRead<?>, Object>(parentValue);
                }
            };
    // holder 的存取值方法
    private void addValue() {
        if (!holder.get().containsKey(this)) {
            holder.get().put(this, null); // WeakHashMap supports null value.
        }
    }
    private void removeValue() {
        holder.get().remove(this);
    }
    // 执行 回调方法
    //  可以重写 beforeExecute() afterExecute()来实现自定义逻辑
    private static void doExecuteCallback(boolean isBefore) {
        for (Map.Entry<TransmittableThreadLocalRead<?>, ?> entry : holder.get().entrySet()) {
            TransmittableThreadLocalRead<?> threadLocal = entry.getKey();
            try {
                if (isBefore) threadLocal.beforeExecute();
                else threadLocal.afterExecute();
            } catch (Throwable t) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.log(Level.WARNING, "TTL exception when " + (isBefore ? "beforeExecute" : "afterExecute") + ", cause: " + t.toString(), t);
                }
            }
        }
    }

    /**
     * 调试方法
     * 打印holder
     */
    static void dump(@Nullable String title) {
        if (title != null && title.length() > 0) {
            System.out.printf("Start TransmittableThreadLocal[%s] Dump...\n", title);
        } else {
            System.out.println("Start TransmittableThreadLocal Dump...");
        }

        for (Map.Entry<TransmittableThreadLocalRead<?>, ?> entry : holder.get().entrySet()) {
            final TransmittableThreadLocalRead<?> key = entry.getKey();
            System.out.println(key.get());
        }
        System.out.println("TransmittableThreadLocal Dump end!");
    }

    /**
     *  调试方法
     */
    static void dump() {
        dump(null);
    }

    /**
     *  内部类 {@link TransmittableThreadLocalRead.Transmitter} Transmitter
     *  通过静态方法  {@link #capture()}  {@link #replay(Object)}  {@link #restore(Object)}
     *  传递所有当前线程的 {@link TransmittableThreadLocalRead} 值 ，给其他线程
     *
     * <p>
     * {@link TransmittableThreadLocalRead.Transmitter} 是内部操作的API，给架构\中间件用来集成
     *
     * 一般情况下，我的代码中几乎不会用到它
     *
     * 如下是案例代码：
     * <pre><code>
     * ///////////////////////////////////////////////////////////////////////////
     * // in thread A, capture all TransmittableThreadLocal values of thread A
     * ///////////////////////////////////////////////////////////////////////////
     *  在线程A中，捕获所用线程A的TransmittableThreadLocal值
     *
     * Object captured = Transmitter.capture(); // 第一步 (1)
     *
     * ///////////////////////////////////////////////////////////////////////////
     * // in thread B
     * ///////////////////////////////////////////////////////////////////////////
     *
     * //  all TransmittableThreadLocal values from thread A
     * //  replay线程A中的所有Transmittable ThreadLocal值
     * Object backup = Transmitter.replay(captured); // 第二步 (2)
     * try {
     *     // 在线程B中，使用的TransmittableThreadLocal值在你的业务逻辑中
     *     // your biz logic, run with the TransmittableThreadLocal values of thread B
     *     System.out.println("Hello");
     *     // ...
     *     return "World";
     * } finally {
     *     // restore the TransmittableThreadLocal of thread B when replay
     *     // 在调用replay时，恢复线程B的TransmittableThreadLocal的值
     *     Transmitter.restore(backup); (3)
     * }
     * </code></pre>
     * <p>
     * 请参阅的实现代码 {@link TtlRunnable} and {@link TtlCallable}  得到更多实际代码案例
     * <hr>
     *  当然，{@link #replay(Object)} and {@link #restore(Object)}的操作，可以通过工具方法来简化
     * Of course, {@link #replay(Object)} and {@link #restore(Object)} operation can be simplified
     * {@link #runCallableWithCaptured(Object, Callable)} or {@link #runSupplierWithCaptured(Object, Supplier)}
     *
     *
     * by util methods {@link #runCallableWithCaptured(Object, Callable)} or {@link #runSupplierWithCaptured(Object, Supplier)}
     * and the adorable {@code Java 8 lambda syntax}.
     * <p>
     *
     * 如下是案例代码：
     * <pre><code>
     * ///////////////////////////////////////////////////////////////////////////
     * // in thread A, capture all TransmittableThreadLocal values of thread A
     * ///////////////////////////////////////////////////////////////////////////
     *
     * Object captured = Transmitter.capture(); // (1)
     *
     * ///////////////////////////////////////////////////////////////////////////
     * // in thread B
     * ///////////////////////////////////////////////////////////////////////////
     *
     * String result = runSupplierWithCaptured(captured, () -> {
     *      // 拿着线程A的 captured $TransmittableThreadLocal ，执行你的业务逻辑
     *      // your biz logic, run with the TransmittableThreadLocal values of thread A
     *      System.out.println("Hello");
     *      ...
     *      return "World";
     * }); // (2) + (3)
     * </code></pre>
     * <p>
     *  提供2个util方法的原因是：不同的{@code throws Exception}类型，以便满足您的业务逻辑
     * The reason of providing 2 util methods is the different {@code throws Exception} type so as to satisfy your biz logic({@code lambda}):
     * <ol>
     * <li>{@link #runCallableWithCaptured(Object, Callable)}: {@code throws Exception}</li>
     * <li>{@link #runSupplierWithCaptured(Object, Supplier)}: No {@code throws}</li>
     * </ol>
     * <p>
     *  如果你需要这种特性
     * If you need the different {@code throws Exception} type,
     * 可以自定义工具方法
     * you can define your own util method(function interface({@code lambda})) with your own {@code throws Exception} type.
     *
     * @author Yang Fang (snoop dot fy at gmail dot com)
     * @author Jerry Lee (oldratlee at gmail dot com)
     * @see TtlRunnable
     * @see TtlCallable
     * @since 2.3.0
     */
    public static class Transmitter {
        /**
         * Capture all {@link TransmittableThreadLocalRead} values in current thread.
         *  获取holder中的值 ，并#threadLocal.copyValue() copy一份副本返回
         * @return the captured {@link TransmittableThreadLocalRead} values
         * @since 2.3.0
         */
        @Nonnull
        public static Object capture() {
            Map<TransmittableThreadLocalRead<?>, Object> captured = new HashMap<TransmittableThreadLocalRead<?>, Object>();
            for (TransmittableThreadLocalRead<?> threadLocal : holder.get().keySet()) {
                captured.put(threadLocal, threadLocal.copyValue());
            }
            return captured;
        }

        /**
         *
         * Replay the captured {@link TransmittableThreadLocalRead} values from {@link #capture()},
         * and return the backup {@link TransmittableThreadLocalRead} values in current thread before replay.
         *  参数captured:是从其他线程捕获得到的captured ttl值
         * @param captured captured {@link TransmittableThreadLocalRead} values from other thread from {@link #capture()}
         * @return the backup {@link TransmittableThreadLocalRead} values before replay
         * @see #capture()
         * @since 2.3.0
         *  当前的holder值备份并返回
         */
        @Nonnull
        public static Object replay(@Nonnull Object captured) {
            @SuppressWarnings("unchecked")
            Map<TransmittableThreadLocalRead<?>, Object> capturedMap = (Map<TransmittableThreadLocalRead<?>, Object>) captured;
            Map<TransmittableThreadLocalRead<?>, Object> backup = new HashMap<TransmittableThreadLocalRead<?>, Object>();
            //将当前的holder备份到 backup 中，返回
            for (Iterator<? extends Map.Entry<TransmittableThreadLocalRead<?>, ?>>
                 iterator  = holder.get().entrySet().iterator(); iterator.hasNext(); )
            {
                Map.Entry<TransmittableThreadLocalRead<?>, ?> next = iterator.next();
                TransmittableThreadLocalRead<?> threadLocal = next.getKey();

                // backup 备份操作
                backup.put(threadLocal, threadLocal.get());
                //  捕获的 参数captured 中不存在
                // clear the TTL values that is not in captured
                // avoid the extra TTL values after replay when run task
                if (!capturedMap.containsKey(threadLocal)) {
                    //capturedMap 不包含 holder 中的值，就将holder中此key对象的删除
                    iterator.remove();
                    //避免内存泄漏调用remove方法
                    threadLocal.superRemove();
                }
            }
            //
            // set values to captured TTL
            setTtlValuesTo(capturedMap);

            // call beforeExecute callback
            // 执行前，回调
            doExecuteCallback(true);

            return backup;
        }

        /**
         * Clear all {@link TransmittableThreadLocalRead} values in current thread,
         * and return the backup {@link TransmittableThreadLocalRead} values in current thread before clear.
         *
         * @return the backup {@link TransmittableThreadLocalRead} values before clear
         * @since 2.9.0
         */
        @Nonnull
        public static Object clear() {
            return replay(Collections.emptyMap());
        }

        /**
         *  Restore()方法 ,用来恢复调用 {@link #replay(Object)}/{@link #clear()}
         *      返回的备份值backup {@link TransmittableThreadLocalRead}
         * @param backup {@link TransmittableThreadLocalRead} 来自{@link #replay(Object)}方法的返回
         * @see #replay(Object)
         * @see #clear()
         * @since 2.3.0
         */
        public static void restore(@Nonnull Object backup) {
            @SuppressWarnings("unchecked")
            //类型强转
            Map<TransmittableThreadLocalRead<?>, Object> backupMap = (Map<TransmittableThreadLocalRead<?>, Object>) backup;
            // 调用 afterExecute 执行方法内部定义的回调函数
            //此处可以定义自己的逻辑，不定义的话就什么没做
            doExecuteCallback(false);
            //遍历 holder
            for (Iterator<? extends Map.Entry<TransmittableThreadLocalRead<?>, ?>> iterator
                 = holder.get().entrySet().iterator(); iterator.hasNext(); )
            {
                Map.Entry<TransmittableThreadLocalRead<?>, ?> next = iterator.next();
                //获取这个类型<?>的TransmittableThreadLocalRead
                TransmittableThreadLocalRead<?> threadLocal = next.getKey();

                /*  清除不在备份中的TransmittableThreadLocal值
                 *   避免 restore() 后,出现多余的TTL值
                 */
                // 判断holder 中的值是否在 父级backupMap(backup)备份中
                if (!backupMap.containsKey(threadLocal)) {
                    // todo
                    // 如果在父线程的ttl backup 副本中不存在，从holder中移除
                    iterator.remove();
                    threadLocal.superRemove();
                }
            }

            // 恢复 TTL values
            setTtlValuesTo(backupMap);
        }

        //todo mei kan dong
        private static void setTtlValuesTo(@Nonnull Map<TransmittableThreadLocalRead<?>, Object> ttlValues) {
            for (Map.Entry<TransmittableThreadLocalRead<?>, Object> entry : ttlValues.entrySet()) {
                @SuppressWarnings("unchecked")
                TransmittableThreadLocalRead<Object> threadLocal = (TransmittableThreadLocalRead<Object>) entry.getKey();
                threadLocal.set(entry.getValue());
            }
        }

        /**
         * Util method for simplifying {@link #replay(Object)} and {@link #restore(Object)} operation.
         *
         * @param captured captured {@link TransmittableThreadLocalRead} values from other thread from {@link #capture()}
         * @param bizLogic biz logic
         * @param <R>      the return type of biz logic
         * @return the return value of biz logic
         * @see #capture()
         * @see #replay(Object)
         * @see #restore(Object)
         * @since 2.3.1
         */
        public static <R> R runSupplierWithCaptured(@Nonnull Object captured, @Nonnull Supplier<R> bizLogic) {
            //备份
            Object backup = replay(captured);
            try {
                //执行业务逻辑
                return bizLogic.get();
            } finally {
                //返回前将ttl值回复一下
                restore(backup);
            }
        }

        /**
         * Util method for simplifying {@link #clear()} and {@link #restore(Object)} operation.
         *
         * @param bizLogic biz logic
         * @param <R>      the return type of biz logic
         * @return the return value of biz logic
         * @see #clear()
         * @see #restore(Object)
         * @since 2.9.0
         */
        public static <R> R runSupplierWithClear(@Nonnull Supplier<R> bizLogic) {

            Object backup = clear();
            try {
                //对象的返回
                return bizLogic.get();
            } finally {
                //回复 TTL
                restore(backup);
            }
        }

        /**
         * Util method for simplifying {@link #replay(Object)} and {@link #restore(Object)} operation.
         *
         * @param captured captured {@link TransmittableThreadLocalRead} values from other thread from {@link #capture()}
         * @param bizLogic biz logic
         * @param <R>      the return type of biz logic
         * @return the return value of biz logic
         * @throws Exception exception threw by biz logic
         * @see #capture()
         * @see #replay(Object)
         * @see #restore(Object)
         * @since 2.3.1
         */
        public static <R> R runCallableWithCaptured(@Nonnull Object captured, @Nonnull Callable<R> bizLogic) throws Exception {
            Object backup = replay(captured);
            try {
                return bizLogic.call();
            } finally {
                restore(backup);
            }
        }

        /**
         * Util method for simplifying {@link #clear()} and {@link #restore(Object)} operation.
         *
         * @param bizLogic biz logic
         * @param <R>      the return type of biz logic
         * @return the return value of biz logic
         * @throws Exception exception threw by biz logic
         * @see #clear()
         * @see #restore(Object)
         * @since 2.9.0
         */
        public static <R> R runCallableWithClear(@Nonnull Callable<R> bizLogic) throws Exception {
            Object backup = clear();
            try {
                return bizLogic.call();
            } finally {
                restore(backup);
            }
        }

        private Transmitter() {
            throw new InstantiationError("Must not instantiate this class");
        }
    }
}
