package com.simplesoftware.testeauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Cadastrar extends AppCompatActivity {

    EditText et_emailCadastrar, et_passwordCadastrar, et_passwordConfirm;
    CheckBox chkbox;
    Button btn_fazerCadastro;
    FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        mAuth = FirebaseAuth.getInstance();
        et_emailCadastrar = findViewById(R.id.et_emailCadastrar);
        et_passwordCadastrar = findViewById(R.id.et_passwordCadastrar);
        et_passwordConfirm = findViewById(R.id.et_passwordConfirm);
        btn_fazerCadastro = findViewById(R.id.btn_fazerCadastro);
        chkbox = findViewById(R.id.chk_mostrarCadastrar);
        progressBar = findViewById(R.id.progressBarCadastrar);

        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    et_passwordCadastrar.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_passwordConfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else{
                    et_passwordCadastrar.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_passwordConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

    public void CadastrarUsuario(View v) {
        String cadastroEmail = et_emailCadastrar.getText().toString();
        String cadastroSenha = et_passwordCadastrar.getText().toString();
        String cadastroSenhaConfirm = et_passwordConfirm.getText().toString();

        try {

            if (cadastroSenha.equals(cadastroSenhaConfirm)) {
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(cadastroEmail, cadastroSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Cadastrar.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(Cadastrar.this, "" + error, Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                });

            } else {
                Toast.makeText(Cadastrar.this, "Senhas não conferem", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }

        } catch (Exception e) {
            Toast.makeText(Cadastrar.this, "Favor preencher todos os campos", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void Voltar(View v) {
        onBackPressed();
    }

}