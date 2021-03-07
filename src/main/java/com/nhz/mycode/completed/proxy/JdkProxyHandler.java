package com.nhz.mycode.completed.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.stream.Stream;

public class JdkProxyHandler implements InvocationHandler {

    public static void main(String[] args)  {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //声明被代理的对象
        Programmer pg = new Programmer();
        //声明被代理类
        JdkProxyHandler handler = new JdkProxyHandler(pg);
        //获取类加载器
        ClassLoader loader = pg.getClass().getClassLoader();
        //获取被代理的所有方法
        Class[] interfaces = pg.getClass().getInterfaces();

        /*  ClassLoader loader, 类加载器
            Class<?>[] interfaces 被代理的类一定是某个接口的实现 ,
            InvocationHandler  代理类
         */
        //使用Proxy构建代理器Proxy
        Worker workerProxy1=(Worker) Proxy.newProxyInstance(loader,interfaces, handler);
        Worker workerProxy2=(Worker) Proxy.newProxyInstance(loader,new Class[] { Worker.class }, handler);
        //使用被代理的对象执行方法
        workerProxy1.hello("nhz");
        workerProxy1.goodbye();
    }
/*---------------------------------------（以下是实验代码）--------------------------------------*/
    //1.被代理的对象
    private Object target =null;

    /**
     * 2.构造代理
     * 将真实对象和代理对象建立联系，通过真实对象来返回一个代理对象（猜测该代理对象可能为真实对象的子类）
     * @param target 真实对象
     * @return 代理对象
     */
    public  JdkProxyHandler(Object target){
        this.target=target;
    }

    /**3.封装调用的前后逻辑 代理方法逻辑
     * @param proxy 代理对象
     * @param method 当前调度方法
     * @param args 当前方法参数
     * @return  代理结果返回
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行前逻辑");
        System.out.println("方法："+method.getName());
        if (args != null) {
            Stream.of(args).forEach(e-> System.out.println("参数："+e.toString()));
        }
        Object obj=method.invoke(target, args);
        System.out.println("执行后逻辑\n");
        return obj;
    }

    /*---------------------------------------（一些接口的声明）--------------------------------------*/
    public static class Programmer implements Worker {
        @Override
        public void hello(String name) {
            System.out.println("hello "+name);
        }

        @Override
        public void goodbye() {
            System.out.println("goodbye");
        }
    }
    public interface Worker {
        void hello(String name);
        void goodbye();
    }
}