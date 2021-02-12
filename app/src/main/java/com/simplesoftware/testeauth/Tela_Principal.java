package com.simplesoftware.testeauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Tela_Principal extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button btn_Logout;
    TextView saudacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__principal);

        mAuth = FirebaseAuth.getInstance();
        btn_Logout = findViewById(R.id.btn_Logout);
        saudacao = findViewById(R.id.saudacao);

        saudacao.setText("Olá, " + mAuth.getCurrentUser().getEmail());

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(Tela_Principal.this, Login.class));
            }
        });
    }
}