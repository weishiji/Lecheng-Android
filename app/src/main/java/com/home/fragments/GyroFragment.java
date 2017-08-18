package com.home.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lxg.lecheng.R;

/**
 * Created by lxg on 17/08/2017.
 */

public class GyroFragment extends Fragment implements SensorEventListener {
    TextView outputTxt;
    View view;
    private Context context;
    private SensorManager sManager;
    private Sensor mSensorAccelerometer;

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        sManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        mSensorAccelerometer = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sManager.registerListener(this,mSensorAccelerometer,SensorManager.SENSOR_DELAY_UI);

        view = inflater.inflate(R.layout.gyro, container, false);

        getOutputTxt();
        return view;
    }

    @Override
    public void onStop() {
        sManager.unregisterListener(this);
        super.onStop();
    }

    private void getOutputTxt(){
        outputTxt = (TextView)view.findViewById(R.id.gyro_output);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] value = event.values;

        outputTxt.setText("X:" + value[0] + "\nY:" + value[1] + "\nZ:" + value[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
