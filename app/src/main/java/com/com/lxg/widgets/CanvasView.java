package com.com.lxg.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.logging.Logger;

/**
 * Created by lxg on 18/08/2017.
 */

public class CanvasView extends View implements SensorEventListener {
    private SensorManager sManager;
    private Sensor mSensorAccelerometer;

    private Canvas ctx = null;

    // -10 到 10 的一个边界
    private float X = 0,diffX = 0;
    private float Y = 0,diffY = 0;
    private float Z = 0,diffZ = 0;

    private Paint paint1 = new Paint();
    private Paint paint2 = new Paint();

    Display display;
    int dWidth,dHeight,cx,cy,cr1,cr2; //

    float computeX,computeY;


    public CanvasView(Context context) {
        super(context);
        init(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        sManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mSensorAccelerometer = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sManager.registerListener(this,mSensorAccelerometer,SensorManager.SENSOR_DELAY_UI);

        WindowManager vm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = vm.getDefaultDisplay();

        //设备宽度
        dWidth = display.getWidth();
        dHeight = display.getHeight();
        //大小圆半径
        cr1 = 300;
        cr2 = 80;
        //圆心
        cx = dWidth / 2;
        cy = dHeight / 2 - (cr1 / 2);
        computeX = cx;
        computeY = cy;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ctx = canvas;
        this.computePosition();
        this.drawBoundary();
        this.drawDirection();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float [] values = event.values;

        diffX = values[0] - X;
        diffY = values[1] - Y;
        diffZ = values[2] - Z;

        X = values[0];
        Y = values[1];
        Z = values[2];


        this.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     *
     * */
    void drawBoundary(){
        paint1.setStyle(Paint.Style.FILL);
        ctx.drawCircle(cx,cy,cr1,paint1);
    }
    void drawDirection(){

        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.WHITE);
        ctx.drawCircle(computeX,computeY,cr2,paint2);
    }
    //计算坐标
    void computePosition(){
        //向右
        int max = cr1 - cr2;
        //每一格的大小
        float unit = max / 10;
        if(computeY <= (cy+max) && computeY >= (cy-max)){
            computeY = cy + ((int) (unit * Y *10)) / 10;
        }else if(computeY > (cy+max)){
            computeY = cy+max;
        }else if(computeY < (cy-max)){
            computeY = cy-max;
        }

        if(computeX <= (cx+max) && computeX >= (cx-max)){
            computeX = cx + ((int) (unit * X * 10)) / 10;
        }else if(computeX > (cx+max)){
            computeX = cx+max;
        }else if(computeX < (cx-max)){
            computeX = cx-max;
        }

    }
}
