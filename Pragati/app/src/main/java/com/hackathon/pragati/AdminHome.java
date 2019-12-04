package com.hackathon.pragati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static com.hackathon.pragati.SplashScreen.appUserMap;
import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;

public class AdminHome extends AppCompatActivity {

    //klklkl
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        perform();
    }

    public void exit(View view) {
        firebaseAuth.signOut();
        startActivity(new Intent(AdminHome.this, SplashScreen.class));
        finish();
    }

    public void addNewP(View v) {
        startActivity(new Intent(this, AddNewProject.class));
    }

    public void opU(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void gotoP(View v) {
        Intent n = new Intent(this, ProjectHome.class);
        n.putExtra("projectID", "ProjectID");
        startActivity(n);
    }


    public void perform() {

        Map document = appUserMap;

        TextView name = findViewById(R.id.name);
        name.setText(document.get("name").toString());
        TextView org = findViewById(R.id.designation);
        org.setText(document.get("pos").toString());
        TextView pos = findViewById(R.id.location);
        pos.setText(document.get("org").toString());





    }

}