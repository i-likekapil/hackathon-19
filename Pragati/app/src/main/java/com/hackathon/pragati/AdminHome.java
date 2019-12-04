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

import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;

public class AdminHome extends AppCompatActivity {

    //klklkl
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

    }
    public void exit(View view)
    {
        firebaseAuth.signOut();
        startActivity(new Intent(AdminHome.this,SplashScreen.class));
        finish();
    }

    public void addNewP(View v)
    {
        startActivity(new Intent(this,AddNewProject.class));
    }
    public void opU(View v)
    {
        startActivity(new Intent(this,MainActivity.class));
    }
    public void gotoP(View v)
    {
        Intent n=new Intent(this,ProjectHome.class);
        n.putExtra("projectID","ProjectDKRana");
        startActivity(n);
    }


    public void perform(String s){
        final LinearLayout t = findViewById(R.id.tt);
        firestore.document("Admins/"+s).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Map document=task.getResult().getData();
                    Set<String> keys= document.keySet();
                    ArrayList k2 = new ArrayList(keys);

                    ArrayList values= new ArrayList(document.values());
                    for(int i=0;i<keys.size();i++) {
                        TextView V = new TextView(AdminHome.this);//(TextView) inflater.inflate(R.layout.cartext, t, false);
                        V.setText(k2.get(i)+" : "+values.get(i));
                        t.addView(V);
                    }

                    Button b=new Button(AdminHome.this);
                    b.setText("Graph dekho ji");
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(AdminHome.this,TestGraph.class));
                        }
                    });
                    t.addView(b);
                }
                else{
                    Toast.makeText(AdminHome.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
