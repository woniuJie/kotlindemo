package com.example.kotlindemo.reflect;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.Method;

public class InstrumentationProxy extends Instrumentation {
    Instrumentation mInstrumentation;
    public InstrumentationProxy(Instrumentation instrumentation){
        this.mInstrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options){
        Log.e("zsj", "execStartActivity: hook成功");

        try{
            Method execStartActivity = Instrumentation.class.getDeclaredMethod("execStartActivity",Context.class,IBinder.class,
                    IBinder.class,Activity.class,Intent.class,int.class,Bundle.class);

            return (ActivityResult) execStartActivity.invoke(mInstrumentation,who,contextThread,token,target,intent,requestCode,options);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
