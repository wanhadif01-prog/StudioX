package com.studiox.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirm = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name)) { etName.setError("Nama diperlukan"); return; }
        if (TextUtils.isEmpty(email)) { etEmail.setError("Emel diperlukan"); return; }
        if (TextUtils.isEmpty(password)) { etPassword.setError("Kata laluan diperlukan"); return; }
        if (password.length() < 6) { etPassword.setError("Minimum 6 aksara"); return; }
        if (!password.equals(confirm)) { etConfirmPassword.setError("Kata laluan tak sama"); return; }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build();
                        user.updateProfile(profileUpdates);

                        user.sendEmailVerification()
                            .addOnCompleteListener(verifyTask -> {
                                Toast.makeText(RegisterActivity.this,
                                    "Akaun berjaya! Sila semak emel untuk pengesahan.",
                                    Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                                finish();
                            });
                    }
                } else {
                    Toast.makeText(RegisterActivity.this,
                        "Daftar gagal: " + task.getException().getMessage(),
                        Toast.LENGTH_LONG).show();
                }
            });
    }
}
