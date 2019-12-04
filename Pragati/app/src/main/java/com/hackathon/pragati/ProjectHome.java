package com.hackathon.pragati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;

public class ProjectHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_home);

        perform(getIntent().getStringExtra("projectID"));
    }

    public void perform(String s){
        final LinearLayout t = findViewById(R.id.tt);
        firestore.document("Projects/"+s).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Map document=task.getResult().getData();
                    Set<String> keys= document.keySet();
                    ArrayList k2 = new ArrayList(keys);

                    ArrayList values= new ArrayList(document.values());
                    for(int i=0;i<keys.size();i++) {
                        TextView V = new TextView(ProjectHome.this);//(TextView) inflater.inflate(R.layout.cartext, t, false);
                        V.setText(k2.get(i)+" : "+values.get(i));
                        t.addView(V);
                    }

                    Button b=new Button(ProjectHome.this);
                    b.setText("Graph dekho ji");
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(ProjectHome.this,TestGraph.class));
                        }
                    });
                    t.addView(b);
                }
                else{
                    Toast.makeText(ProjectHome.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public  void update(final String prID)
    {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Update");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);
        builder.setPositiveButton("Notify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String x = input.getText().toString();
                Map<String,Object> text=new HashMap<>();
                text.put(getNowTimeMillis(),x);
                firestore.document("Projects/"+prID+"/Updates/"+getTodayDate()).set(text, SetOptions.merge())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){

                                    Toast.makeText(ProjectHome.this, "Updated ;)", Toast.LENGTH_SHORT).show();}
                                else
                                    Toast.makeText(ProjectHome.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    public static String getTodayDate() {
        Calendar c = Calendar.getInstance();
        int mm = (int) (c.get(Calendar.MONTH)) + 1;
        int dd = c.get(Calendar.DATE);
        int yyyy = c.get(Calendar.YEAR);
        return (dd < 10 ? "0" + dd : dd + "") + "-" + (mm < 10 ? "0" + mm : mm + "") + "-" + yyyy;
    }

    public String getNowTime() {
        int hh = Calendar.getInstance(TimeZone.getDefault()).getTime().getHours();
        int mm = Calendar.getInstance(TimeZone.getDefault()).getTime().getMinutes();
        return (hh < 10 ? ("0" + hh) : hh) + ":" + (mm < 10 ? ("0" + mm) : mm);
    }
    public static String getNowTimeMillis() {
        int hh = Calendar.getInstance(TimeZone.getDefault()).getTime().getHours();
        int mm = Calendar.getInstance(TimeZone.getDefault()).getTime().getMinutes();
        int ss = Calendar.getInstance(TimeZone.getDefault()).getTime().getSeconds();
        //int mi = Calendar.getInstance(TimeZone.getDefault()).getTime().get;
        return (hh < 10 ? ("0" + hh) : hh) + ":" + (mm < 10 ? ("0" + mm) : mm)+":"+ (ss < 10 ? ("0" + ss) : ss);//+ (mi < 10 ? ("0" + mi) : mi);
    }



}
/*
    public void perform(){
        final LinearLayout t = findViewById(R.id.usersList);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        firestore.collection("RUsers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    List<DocumentSnapshot> documents=task.getResult().getDocuments();
                    for(DocumentSnapshot document:documents)
                    {
                        ConstraintLayout k = (ConstraintLayout) inflater.inflate(R.layout.ruser, t, false);
                        TextView tv1 = (TextView) k.getChildAt(1);
                        tv1.setText(document.get("Name").toString());
                        TextView tv2 = (TextView) k.getChildAt(3);
                        tv2.setText(document.get("Phone").toString());
                        TextView tv3 = (TextView) k.getChildAt(5);
                        tv3.setText(document.get("Email").toString());
                        TextView tv4 = (TextView) k.getChildAt(7);
                        tv4.setText(document.get("Address").toString());
                        LinearLayout ll=(LinearLayout)k.getChildAt(8);
                        ArrayList<String> cars= (ArrayList<String>) document.get("VIDArray");
                        for(final String car:cars) {
                            TextView carV = (TextView) inflater.inflate(R.layout.cartext, ll, false);
                            carV.setText(car);
                            carV.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i=new Intent(RUsersList.this,CarPage.class);
                                    i.putExtra("vehicleID",car);
                                    startActivity(i);
                                }
                            });
                            ll.addView(carV);
                        }
                        t.addView(k);
                    }
                }
                else{
                    Toast.makeText(RUsersList.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
 */