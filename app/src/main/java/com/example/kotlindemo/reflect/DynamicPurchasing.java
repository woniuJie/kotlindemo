package com.example.kotlindemo.reflect;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicPurchasing implements InvocationHandler {
    private Object object;
    public DynamicPurchasing(Object ob){
        this.object = ob;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object,args);
        if(method.getName().equals("buy")){
            Log.e("zsj", "invoke: " );
        }
        return result;
    }
}
