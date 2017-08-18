package com.example.lxg.lecheng;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.com.lxg.widgets.CanvasView;

/**
 * Created by lxg on 18/08/2017.
 */

public class CanvasActivity extends Activity {
    CanvasView cv;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.gyro);


    }

}

