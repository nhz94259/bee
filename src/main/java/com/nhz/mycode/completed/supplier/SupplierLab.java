package com.nhz.mycode.completed.supplier;

import java.util.function.Supplier;

public class SupplierLab {

    private int age = 1;

    SupplierLab(){
        System.out.println(age);
    }
    public static void main(String[] args) {
        //创建Supplier容器，声明为TestSupplier类型，此时并不会调用对象的构造方法，即不会创建对象
        Supplier<SupplierLab> sup= SupplierLab::new;
        System.out.println("--------");
        //调用get()方法，此时会调用对象的构造方法，即获得到真正对象
        SupplierLab supplierLab1 = sup.get();
        SupplierLab supplierLab2 = sup.get();
        //每次get都会调用构造方法，即获取的对象不同
        System.out.println(supplierLab1.hashCode());
        System.out.println(supplierLab2.hashCode());
    }
}
