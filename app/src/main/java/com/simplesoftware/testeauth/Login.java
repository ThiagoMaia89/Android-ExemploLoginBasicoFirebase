package com.simplesoftware.testeauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText et_email, et_password;
    CheckBox chk_mostrar;
    Button btn_Login, btn_Cadastrar;
    FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        chk_mostrar = findViewById(R.id.chk_mostrar);
        btn_Login = findViewById(R.id.btn_Login);
        btn_Cadastrar = findViewById(R.id.btn_Cadastrar);
        progressBar = findViewById(R.id.progressBar);

        chk_mostrar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else{
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

    public void fazerLogin(View v) {
        String loginEmail = et_email.getText().toString();
        String loginSenha = et_password.getText().toString();

        try {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(loginEmail, loginSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(Login.this, Tela_Principal.class));
                        finish();
                    } else {
                        String error = task.getException().getMessage();
                        Toast.makeText(Login.this, "" + error, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });
        } catch (Exception e){
            Toast.makeText(Login.this, "Favor preencher todos os campos", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    public void irCadastro(View view) {
        startActivity(new Intent(Login.this, Cadastrar.class));
    }
}