package com.example.livecricketscore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.livecricketscore.BottomNavActivity;
import com.example.livecricketscore.R;
import com.example.livecricketscore.loginbll.LoginBLL;
import com.example.livecricketscore.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {

    private EditText etMailID, etPassword;
    private Button btnLogin,btnLRegister;
    private CheckBox chkRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMailID = findViewById(R.id.etMailID);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLRegister = findViewById(R.id.btnLRegister);
        chkRemember = findViewById(R.id.chkRemember);

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

    public void SaveUsers() {
        SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("email", etMailID.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.commit();

        Toast.makeText(this, "User Saved", Toast.LENGTH_SHORT).show();
    }
}
