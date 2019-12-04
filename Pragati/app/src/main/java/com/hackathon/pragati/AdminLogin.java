package com.hackathon.pragati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        getSupportActionBar().hide();

    }

    public void logAdmin(View v){

        String email=((EditText)(findViewById(R.id.inp_name))).getText().toString().trim();
        String pass=((EditText)(findViewById(R.id.inp_pass))).getText().toString().trim();
        if(!(email.contains("@")&&email.contains(".")))
        {
            Toast.makeText(this, "Invalid Email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.length()<8)
        {
            Toast.makeText(this, "Invalid Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AdminLogin.this, "SignIn successful!", Toast.LENGTH_SHORT).show();
                    Intent i;
                    if(firebaseAuth.getCurrentUser().getEmail().equals(""))
                        i = new Intent(AdminLogin.this, ConstructorHome.class);
                    else
                        i = new Intent(AdminLogin.this, AdminHome.class);
                    startActivity(i);
                    finish();

                }
                else
                    Toast.makeText(AdminLogin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void newAdmin(View v){
        Intent i = new Intent(AdminLogin.this, NewAdmin.class);
        startActivity(i);
        finish();
    }
}
