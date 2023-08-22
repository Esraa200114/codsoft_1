package com.example.flashlightapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch flashlightSwitch;
    TextView hint;
    CameraManager cameraManager;
    String cameraID, result;
    ConstraintLayout constraintLayout;
    boolean hasCameraFlash = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.palm_springs_sp));

        flashlightSwitch = findViewById(R.id.on_off_switch);
        hint = findViewById(R.id.hint_tv);
        constraintLayout = findViewById(R.id.constraint_layout);

        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        flashlightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    flashLight(isChecked);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void flashLight(boolean isChecked) throws CameraAccessException {

        if (hasCameraFlash) {

            cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            cameraID = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraID, isChecked);
            result = isChecked ? "ON" : "OFF";

            Toast.makeText(getApplicationContext(), "Flashlight is " + result, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "No flashlight available on your device", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            flashLight(false);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            flashLight(false);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}