package com.example.livecricketscore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.livecricketscore.R;
import com.example.livecricketscore.broadcast.BroadCastReceiver;
import com.example.livecricketscore.createchannel.CreateChannel;
import com.example.livecricketscore.loginbll.LoginBLL;
import com.example.livecricketscore.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {

    private EditText etMailID, etPassword;
    private Button btnLogin,btnLRegister;
    public NotificationManagerCompat notificationManagerCompat;
    public SensorManager sensorManager;
    private CheckBox chkRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        etMailID = findViewById(R.id.etMailID);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLRegister = findViewById(R.id.btnLRegister);
        chkRemember = findViewById(R.id.chkRemember);

        sensorGyro();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
        btnLRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }


    private void sensorGyro() {

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if (event.values[1] < 0) {
                    checkUser();
                    finish();

                } else if (event.values[1] > 0) {

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null) {
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }


    private void checkUser(){

        SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        String username = sharedPreferences.getString("email","");
        String password = sharedPreferences.getString("password","");


        StrictModeClass.StrictMode();

        LoginBLL loginBLL = new LoginBLL();

        if (loginBLL.checkUser(etMailID.getText().toString(), etPassword.getText().toString())) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, BottomNavActivity.class);
            startActivity(intent);
            if (chkRemember.isChecked()){
                SaveUsers();
            }
        }

        else {
            Toast.makeText(this, "Login Unsucessful", Toast.LENGTH_SHORT).show();
        }
    }

    private void notifiy() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.cricket)
                .setContentTitle("Live Cricket Score")
                .setContentText("Login success :" + etMailID.getText().toString())
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    BroadCastReceiver broadcastReceiver = new BroadCastReceiver(this);

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);
    }


    public void SaveUsers() {
        SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("email", etMailID.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.commit();

        Toast.makeText(this, "User Saved", Toast.LENGTH_SHORT).show();
    }
}
