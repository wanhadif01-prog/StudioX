package com.studiox.app;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private ImageView xLogo;
    private EditText etEmail, etPassword;
    private Button btnLogin, btnGoogle, btnApple;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        xLogo = findViewById(R.id.xLogo);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnApple = findViewById(R.id.btnApple);
        tvRegister = findViewById(R.id.tvRegister);

        Animation floatAnim = AnimationUtils.loadAnimation(this, R.anim.float_x);
        xLogo.startAnimation(floatAnim);

        ObjectAnimator glowAnim = ObjectAnimator.ofFloat(xLogo, "alpha", 0.7f, 1.0f);
        glowAnim.setDuration(1200);
        glowAnim.setRepeatMode(ValueAnimator.REVERSE);
        glowAnim.setRepeatCount(ValueAnimator.INFINITE);
        glowAnim.setInterpolator(new LinearInterpolator());
        glowAnim.start();

        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(xLogo, "rotation", -3f, 3f);
        rotateAnim.setDuration(2000);
        rotateAnim.setRepeatMode(ValueAnimator.REVERSE);
        rotateAnim.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnim.start();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();
                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Sila isi emel & kata laluan", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Log masuk berjaya!", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Membuka Google Sign-In...", Toast.LENGTH_SHORT).show();
            }
        });

        btnApple.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Membuka Apple Sign-In...", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Buka skrin Daftar...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
