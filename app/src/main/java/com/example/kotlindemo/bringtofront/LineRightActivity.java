package com.example.kotlindemo.bringtofront;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlindemo.R;

import kotlinx.coroutines.GlobalScope;

/**
 * @author:zhangshijie
 * @Date:2022/3/28
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class LineRightActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bring_to_front_layout);



        TextView textView = findViewById(R.id.tv_title_xxxx);
        textView.setText("我叫上世界");
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Layout layout = textView.getLayout();
                Log.e("zsj", "onCreate: "+layout.getLineRight(0) );
            }
        });

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        x -= textView.getTotalPaddingLeft();
                        y -= textView.getTotalPaddingTop();

                        x += textView.getScrollX();
                        y += textView.getScrollY();

                        Layout layout = textView.getLayout();
                        int line = layout.getLineForVertical(y);
                        int off = layout.getOffsetForHorizontal(line, x);

                        Log.e("zsj", "onTouch: +x"+x+"---right"+layout.getLineRight(line) );
                        break;
                }
                return false;
            }
        });

    }
}
