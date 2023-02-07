package com.example.kotlindemo.reflect;

import java.lang.reflect.Proxy;

public class ParentDemo {
    private String parentName;
    public String parentAge;
    protected String parentGender;
    String parentLive;
    private void printParentName(){
        IShop liuwangshu = new LiuWangShu();
        DynamicPurchasing dynamicPurchasing = new DynamicPurchasing(liuwangshu);
        ClassLoader loader = liuwangshu.getClass().getClassLoader();
        IShop pur = (IShop) Proxy.newProxyInstance(loader,new Class[]{IShop.class},dynamicPurchasing);
        pur.buy();
    }
}
