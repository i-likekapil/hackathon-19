package com.hackathon.pragati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import static com.hackathon.pragati.SplashScreen.appUser;
import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;
import static com.hackathon.pragati.SplashScreen.storageRef;

public class ProjectHome extends AppCompatActivity {

    TextView prN,prID,prTyp,cities,arSer,strtDt,endDt,bdgtAll,bdgtUs,likes,dislikes,sDesc,lDesc,status,web,prHd,vid,lastUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_home);
        prN=findViewById(R.id.project_namev);
        prID=findViewById(R.id.project_idv);
        prTyp=findViewById(R.id.project_typev);
        cities=findViewById(R.id.citiesv);
        arSer=findViewById(R.id.area_servedv);
        strtDt=findViewById(R.id.start_datev);
        endDt=findViewById(R.id.end_datev);
        bdgtAll=findViewById(R.id.bgtAv);
        bdgtUs=findViewById(R.id.bgtUv);
        likes=findViewById(R.id.upvotesv);
        dislikes=findViewById(R.id.downvotesv);
        sDesc=findViewById(R.id.des_shortv);
        lDesc=findViewById(R.id.des_longv);
        status=findViewById(R.id.statusv);
        web=findViewById(R.id.webviewv);
        prHd=findViewById(R.id.project_headv);
        vid=findViewById(R.id.vidviewv);
        //lastUpdate=findViewById(R.id.lastUpdatev);




        perform(getIntent().getStringExtra("project_id"));
    }

    public void webView(String v){
        if(v.equals("")||v==null)return;
        Intent i=new Intent(ProjectHome.this,WebActivity.class);
        i.putExtra("link",v);
        startActivity(i);
    }

    public void perform(final String s) {
        final LinearLayout t = findViewById(R.id.tt);
        ImageView img = findViewById(R.id.profile);
        read(img, s + "001");
        firestore.document("Projects/" + s).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    final Map document = task.getResult().getData();

                    prN.setText(document.get("project_name").toString());
                    prID.setText(document.get("project_id").toString());
                    prTyp.setText(document.get("type").toString());
                    cities.setText(document.get("cities").toString());
//                    lastUpdate.setText(document.get("last_update").toString());
                    arSer.setText(document.get("area").toString());
                    strtDt.setText(document.get("start_date").toString());
                    endDt.setText(document.get("end_date").toString());
                    bdgtAll.setText(document.get("budget_allocated").toString());
                    bdgtUs.setText(document.get("budget_used").toString());
                    prHd.setText(document.get("project_head").toString());
                    likes.setText(document.get("likes").toString());
                    dislikes.setText(document.get("dislikes").toString());
                    sDesc.setText(document.get("short_description").toString());
                    lDesc.setText(document.get("long_description").toString());
                    status.setText(document.get("status").toString());
                    web.setText(document.get("website").toString());
                    vid.setText(document.get("video").toString());

                    web.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            webView(web.getText().toString());
                        }
                    });

                    vid.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            webView(vid.getText().toString());
                        }
                    });

                    if(appUser.equals("constructor"))
                    {
                        Button btn=new Button(ProjectHome.this);
                        btn.setText("Add Updates");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                update(document.get("project_id").toString());
                            }
                        });

                    }


                    Set<String> keys = document.keySet();
                    ArrayList k2 = new ArrayList(keys);

                    ArrayList values = new ArrayList(document.values());
                    for (int i = 0; i < keys.size(); i++) {
                        TextView V = new TextView(ProjectHome.this);//(TextView) inflater.inflate(R.layout.cartext, t, false);
                        V.setText(k2.get(i) + " : " + values.get(i));
                        t.addView(V);
                    }

                    Button butt=new Button(ProjectHome.this);
                    butt.setText("Give Updates");
                    butt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            update(s);
                        }
                    });
                    t.addView(butt);

/*
                    Button b = new Button(ProjectHome.this);
                    b.setText("Graph dekho ji");
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(ProjectHome.this, TestGraph.class));
                        }
                    });
                    t.addView(b);
                    Button butt=new Button(ProjectHome.this);
                    butt.setText("Give Updates");
                    butt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            update(s);
                        }
                    });
*/
              }
                else {
                    Toast.makeText(ProjectHome.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void update(final String prID) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Update");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String x = input.getText().toString();
                Map<String, Object> text = new HashMap<>();
                text.put(getNowTimeMillis(), x);
                firestore.document("Projects/" + prID + "/Updates/" + getTodayDate()).set(text, SetOptions.merge())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(ProjectHome.this, "Updated ;)", Toast.LENGTH_SHORT).show();
                                } else
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
        return (hh < 10 ? ("0" + hh) : hh) + ":" + (mm < 10 ? ("0" + mm) : mm) + ":" + (ss < 10 ? ("0" + ss) : ss);//+ (mi < 10 ? ("0" + mi) : mi);
    }

    Bitmap bit;

    public void read(final ImageView dp, final String filename) {
        //if (path == null) {
        final long OO = 1024 * 1024;
        storageRef.child(filename).getBytes(OO).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                dp.setImageBitmap(bit);
                System.out.println("Online se hua READ ");

                //firestore.document("faculty/" + FirebaseAuth.getInstance().getCurrentUser().getEmail()).update("img_path", saveToInternalStorage(bit,filename));

            }
        });


    }

    //-------------------------------Graph ka karya----------------------------------------
    GraphView graphView;
    public DataPoint dataPoint[],dp1[];
    public  void  graphdekh(View view)
    {
        graphView=findViewById(R.id.graph1);
        if(graphView.getVisibility()==View.VISIBLE)
            graphView.setVisibility(View.GONE);
        else if(graphView.getVisibility()==View.GONE)
            graphView.setVisibility(View.VISIBLE);
        int[] a={0,9,12,15,19,26,29,34};//red
        int[] b={0,8,14,18,21,28,33,38};
        dataPoint=new DataPoint[a.length];
        int[] a1={0,11,15,19,23,29,39,45};//blue
        int[] b1={0,10,17,21,24,32,37,42};
        dp1=new DataPoint[a1.length];
        for(int i=0;i<a.length;i++)
        {
            dataPoint[i]= new DataPoint(a[i],b[i]);
            dp1[i]= new DataPoint(a1[i],b1[i]);
        }
        LineGraphSeries<DataPoint> lineGraphSeries = new LineGraphSeries<>(dataPoint);
        LineGraphSeries<DataPoint> lineGraphSeries1 = new LineGraphSeries<>(dp1);
        lineGraphSeries.setColor(Color.RED);
        lineGraphSeries.setThickness(5);
        lineGraphSeries1.setColor(Color.BLUE);
        lineGraphSeries1.setThickness(5);
        Viewport viewport=graphView.getViewport();
        viewport.setXAxisBoundsManual(true);
        viewport.setYAxisBoundsManual(true);
        viewport.setMaxX(50);
        viewport.setMaxY(50);
        graphView.addSeries(lineGraphSeries);
        graphView.addSeries(lineGraphSeries1);


    }

    TextView tupd,tcom;
    LinearLayout tupdL,tcomL;

    public void viewupdate(View view) {
        tupd = findViewById(R.id.updatesv);
        tupdL = findViewById(R.id.updateLL);
        if (tupdL.getVisibility() == View.VISIBLE)
            tupdL.setVisibility(View.GONE);
        else if (tupdL.getVisibility() == View.GONE)
            tupdL.setVisibility(View.VISIBLE);
        show("Updates");

    }
    public void viewcomment(View view) {
        tcom = findViewById(R.id.commentv);
        tcomL = findViewById(R.id.commentLL);
        if (tcomL.getVisibility() == View.VISIBLE)
            tcomL.setVisibility(View.GONE);
        else if (tcomL.getVisibility() == View.GONE)
            tcomL.setVisibility(View.VISIBLE);
        show("Comments");
    }











    ArrayList<String> time, msg;String date;








    public  void show(String s){

        final LinearLayout main;
        if(s.equals("Updates"))
        main=findViewById(R.id.updateLL);
        else
            main=findViewById(R.id.commentLL);

        firestore.collection("Projects/"+prID.getText().toString()+"/"+s).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> tas) {

                List<DocumentSnapshot> kk=tas.getResult().getDocuments();

                if (tas.isSuccessful() && tas.getResult() != null && tas.getResult().getDocuments() != null) {
                    //findViewById(R.id.noNoti).setVisibility(View.GONE);
                    //findViewById(R.id.yesNoti).setVisibility(View.VISIBLE);
                    for(DocumentSnapshot doc:kk)
                    {
                    Map m = doc.getData();


                    LayoutInflater inflater = (LayoutInflater) ProjectHome.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


                    time = new ArrayList<>(m.keySet());
                    String []ar= time.toArray(new String[time.size()]);
                    msg = new ArrayList<String>(m.values());

                    CardView card=(CardView)inflater.inflate(R.layout.notif_card, main, false);

                    ConstraintLayout mainLay=(ConstraintLayout)card.getChildAt(0);
                    TextView vt=(TextView) mainLay.getChildAt(0);
                    System.out.println(card+"  xx  "+vt);
                    vt.setText(doc.getId());


                    Arrays.sort(ar);

                    int n=time.size()-1;
                    System.out.println(msg.get(n));

                    LinearLayout t= (LinearLayout) mainLay.getChildAt(1);


                    while(n>=0) {

                        int k=time.indexOf(ar[n]);
                        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.notif_content, t, false);
                        TextView tv = (TextView) constraintLayout.getChildAt(0);
                        tv.setText(time.get(k).substring(0,5));
                        tv = (TextView) constraintLayout.getChildAt(1);
                        tv.setText(msg.get(k));

                        t.addView(constraintLayout);
                        n=n-1;
                    }
                    t.setVisibility(View.VISIBLE);
                    main.addView(card);


                }

              }

        }});
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