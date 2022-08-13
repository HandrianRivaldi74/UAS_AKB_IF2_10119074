package com.example.uas_akb_if2_10119074;
/**
 * Nama : Handrian Rivaldi
 * Kelas : IF2
 * NIM :10119074
 * Email : handrianr28@gmail.com
 * **/
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText signupEmail, signupPass;
    Button btnCreateSignup;
    TextView btnCreateLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signupEmail = findViewById(R.id.editText);
        signupPass = findViewById(R.id.editText2);
        btnCreateSignup = findViewById(R.id.btnCreate);
        btnCreateLogin = (TextView) findViewById(R.id.btnLogin);

        fAuth = FirebaseAuth.getInstance();

//        if(fAuth.getCurrentUser() != null){
//            Toast.makeText(this, "Kode Berjalan", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

        btnCreateSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signupEmail.getText().toString().trim();
                String pass = signupPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    signupEmail.setError("Masukkan Email !");
                }
                if (TextUtils.isEmpty(pass)){
                    signupPass.setError("Masukkan Pass. ");
                }
                if (pass.length()<6){
                    signupPass.setError("Pass harus lebih dari 6 karakter");
                }else {
                    fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Kamu Terdaftar", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                            } else {
                                Toast.makeText(RegisterActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        btnCreateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
