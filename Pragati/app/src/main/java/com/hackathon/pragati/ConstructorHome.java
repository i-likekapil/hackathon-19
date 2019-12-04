package com.hackathon.pragati;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Nullable;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.Nullable;

import static com.hackathon.pragati.SplashScreen.appUserMap;
import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;


public class ConstructorHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constructor_home);
        perform();
    }

    public void perform(){

        Map document = appUserMap;

        TextView nae = findViewById(R.id.name);
        nae.setText(document.get("name").toString());
        TextView org = findViewById(R.id.designation);
        org.setText(document.get("pos").toString());
        TextView pos = findViewById(R.id.location);
        pos.setText(document.get("org").toString());


        String name=firebaseAuth.getCurrentUser().getEmail();
        final LinearLayout t = findViewById(R.id.tt);
        t.removeAllViews();
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        firestore.collection("Projects").whereEqualTo("constructor",name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    List<DocumentSnapshot> documents = task.getResult().getDocuments();
                    for (final DocumentSnapshot document : documents) {
                        CardView cd = (CardView) inflater.inflate(R.layout.prcard, t, false);
                        ConstraintLayout k = (ConstraintLayout) cd.getChildAt(0);
                        TextView tv1 = (TextView) k.getChildAt(0);
                        tv1.setText(document.get("project_name").toString());

                        ImageView main= (ImageView) k.getChildAt(1);
                        TextView tv2 = (TextView) k.getChildAt(2);
                        tv2.setText(tv2.getText() + " : " + document.get("area").toString());
                        TextView tv3 = (TextView) k.getChildAt(3);
                        tv3.setText(tv3.getText() + " : " + document.get("status").toString());
//                        LinearLayout ll=(LinearLayout)k.getChildAt(8);
                        ArrayList<String> cities = (ArrayList<String>) document.get("cities");

                        // for(final String car:cars) {
                        //    TextView carV = new TextView(MainActivity.this);
                        //    carV.setText(car);
//                        String photoPath=document.get("photoMainPath").toString();
                        //                      String photoFile=xdocument.get("photoMainFile").toString();

                        //                    read(main,photoPath,photoFile);
                        final ImageView pic= (ImageView) k.getChildAt(1);
                        final ImageView like= (ImageView) k.getChildAt(4);
                        final ImageView dislike= (ImageView) k.getChildAt(5);
                        final ImageView comment= (ImageView) k.getChildAt(6);
                        final EditText cmntTxt=(EditText)k.getChildAt(7);
                        final ImageView send= (ImageView) k.getChildAt(8);


                        comment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cmntTxt.setVisibility(View.VISIBLE);
                                send.setVisibility(View.VISIBLE);
                            }
                        });


                        send.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String cmnt=cmntTxt.getText().toString();
                                if(cmnt.equals("")){
                                    Toast.makeText(ConstructorHome.this, "Enter Comment First", Toast.LENGTH_SHORT).show();
                                }

                                //addComment(document.get("project_id"),cmnt);
                                cmntTxt.setText("");
                            }
                        });

                        pic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(ConstructorHome.this, ProjectHome.class);
                                i.putExtra("project_id", document.get("project_id").toString());
                                startActivity(i);
                            }
                        });

                        if(document.contains("pics")) {
                            Map<String, String> pics = (Map<String, String>) document.get("pics");
                            List<String> picKeys = new ArrayList<>(pics.keySet());

                            String path = picKeys.get(0);
                            String filename = pics.get(path);
                            //read(pic, path, filename);

                        }

                        t.addView(cd);
                    }
                }

                else{
                    Toast.makeText(ConstructorHome.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}


/*

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Map<String, Object> batchMap[];

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int[] SideShowImgs = {R.drawable.mainback6, R.drawable.mainback2, R.drawable.mainback4, R.drawable.mainback5, R.drawable.mainback3};
    private ArrayList<Integer> SlideShowArray = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.nav_view);

        init();

        navigationView.setNavigationItemSelectedListener(this);

        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
                .build();
        WorkManager.getInstance().enqueue(uploadWorkRequest);


        ;// Heyy.. NOtification me gadbad hai.. Maine NandGram se Rajnagar select kiya toh database me update hua ki reached at nandgram..
        //Previous value aa rahi hai..


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            //  Snackbar.make(findViewById(android.R.id.content), "Internet Connection available...hata do ise ab", Snackbar.LENGTH_SHORT).show();
        } else
            Snackbar.make(findViewById(android.R.id.content), "Internet Connection NOT available", Snackbar.LENGTH_INDEFINITE).show();

        /*if(firebaseUser!=null)
        new UserDetails();
        System.out.println("FACULTY FIRST LOGIN SE" +faculty_first_name);
*\
TextView tv = findViewById(R.id.textView13);
        tv.setText(appUser.getFirst_name() + " " + appUser.getLast_name());
                tv = findViewById(R.id.set_designation);
                tv.setText(appUser.getPhone() + " | " + appUser.getUserame());
                View headerView = navigationView.getHeaderView(0);
                tv = (TextView) headerView.findViewById(R.id.dpn);
                tv.setText(appUser.getFirst_name() + " " + appUser.getLast_name());
                tv = (TextView) headerView.findViewById(R.id.dpnn);
                tv.setText(appUser.getPhone() + " | " + appUser.getUserame());


                //tv = findViewById(R.id.set_email);
                //tv.setText(logged_inUser.getUserame());
                //tv = findViewById(R.id.set_phone);
                //tv.setText(logged_inUser.getPhone());*\


                if (!appUser.getType().equalsIgnoreCase("Admin")) {
                findViewById(R.id.admin_panel).setVisibility(View.GONE);
                System.out.println(appUser.getType());
                }

                read(appUser.getImg_path());


                //setBatchData(0);


                //addCard(1);


                all_batches();


                {

                int k = 0;
        /*       final int noOfBatches=faculty_batches.length;

        batchMap=new Map[noOfBatches];
        for(int i=0;i<faculty_batches.length;i++)
            System.out.println(faculty_batches[i]);


        System.out.println("nBatch "+noOfBatches);

        for(int i=0;i<noOfBatches;i++)
        {
            final int finalI = i;
            final String finS=faculty_batches[i].trim();
            System.out.println("i = "+finalI+"   s= "+finS + "tttt");
            Task<DocumentSnapshot> tas = firestore.document("batches/"+finS).get();





            tas.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if(task.isSuccessful()){
               DocumentSnapshot documentSnapshot=task.getResult();
               System.out.println("DocumentSnapshot   "+documentSnapshot.getData());
                    batchMap[finalI]=documentSnapshot.getData();






               }
            else System.out.println("Kat gaya fir se");


                    System.out.print("YE MAP READ HUA HAI " +task.getResult().getData());

                }



            });

                    tas.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    System.out.println("ye baat hai  "+e.getMessage());
                }
            });
            if(finalI==noOfBatches-1){
                System.out.println("BatchMaap1..."+batchMap[0]+"\nBatchMap2..."+batchMap[1]);
                setBatchData(noOfBatches);
            }

        }*\
                }

//logged_inUser = appUser;


final Spinner dropdown = findViewById(R.id.current_location);
        String[] items = new String[]{"None", "NandGram", "Rajnagar Extension", "Vasundhara", "Shastri nagar"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinnertext, items);
        dropdown.setAdapter(adapter);
        int k = Arrays.asList(items).indexOf(appUser.getCurrent_location());
        dropdown.setSelection(k);


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
@Override
public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String cl = adapterView.getSelectedItem().toString();
        System.out.println(cl);

        if (adapterView.getSelectedItem().toString().equals("None")) {


        dropdown.setBackgroundResource(R.drawable.spinner_red);
        String ss = adapterView.getSelectedItem().toString();
        System.out.println(ss);


        } else {
        dropdown.setBackgroundResource(R.drawable.back_spinner);
        System.out.println("EEA BAA");
        }

        if (!cl.equals(appUser.getCurrent_location())) {

        firestore.document("faculty/" + appUser.getUserame()).update("current_location", cl);
        Map<String, Object> j = new HashMap<>();
        j.put(getNowTime(), cl);
        String message;
        if (cl.equals("None"))
        message = appUser.getFirst_name() + " " + appUser.getLast_name() + " left " + appUser.getCurrent_location() + " coaching centre at " + getNowTime();
        else
        message = appUser.getFirst_name() + " " + appUser.getLast_name() + " arrived at " + cl + " coaching centre at " + getNowTime();
        notifyAllAdmins(message);
        firestore.document("faculty/" + appUser.getUserame() + "/attendance/" + getTodayDate()).set(j, SetOptions.merge());
        appUser.setCurrent_location(cl);
        j.clear();
        j.put(getNowTime(), message);
        firestore.document("faculty/" + appUser.getUserame() + "/log/" + getTodayDate()).set(j, SetOptions.merge());
        }
        }

@Override
public void onNothingSelected(AdapterView<?> adapterView) {

        }
        });


        }


@RequiresApi(api = Build.VERSION_CODES.N)
@Override
public void onResume() {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here

        refreshData();


        }



@RequiresApi(api = Build.VERSION_CODES.N)
public void refreshData(){
        tcb=0;
        updateThisDayBatches();
        setBatchData(0);

        }


@RequiresApi(api = Build.VERSION_CODES.N)
public void updateThisDayBatches(){
        userThisDayBatches.clear();
        System.out.println("Update this day batches caled.....");

        for(int i=0; i<userAllbatches.size(); i++){
        Batches ob = userAllbatches.get(i);
        System.out.println(ob.getProgress()+ " :: " + ob.getBatch_id() + " ::: "+ ob.today_time);

        if((!(ob.today_time.equals("99:99") || ob.getProgress().equals("done")) || ob.getProgress().equalsIgnoreCase("active"))) {
        System.out.println("Batch Added");
        userThisDayBatches.add(ob);
        }


        }



        userThisDayBatches.sort(new Sortbyroll() {
@Override
public int compare(Batches a, Batches b) {
        return super.compare(a, b);
        }
        });
        }









public static String getTodayDate() {
        Calendar c = Calendar.getInstance();
        int mm = (int) (c.get(Calendar.MONTH)) + 1;
        int dd = c.get(Calendar.DATE);
        int yyyy = c.get(Calendar.YEAR);
        return (dd < 10 ? "0" + dd : dd + "") + "." + (mm < 10 ? "0" + mm : mm + "") + "." + yyyy;
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



public void setBatchData(int k) {
        System.out.println("Set Batch called....................");

        if (userThisDayBatches.size() == 0) {
        (findViewById(R.id.next_batch)).setVisibility(View.GONE);
        (findViewById(R.id.batches_today_card)).setVisibility(View.GONE);
        (findViewById(R.id.no_more_batches)).setVisibility(View.VISIBLE);
        return;
        }

        else if(userThisDayBatches.size()==1){
        (findViewById(R.id.next_batch)).setVisibility(View.VISIBLE);
        (findViewById(R.id.batches_today_card)).setVisibility(View.GONE);
        (findViewById(R.id.no_more_batches)).setVisibility(View.VISIBLE);

        updateUINextBatch();


        }

        else{
        (findViewById(R.id.next_batch)).setVisibility(View.VISIBLE);
        (findViewById(R.id.batches_today_card)).setVisibility(View.VISIBLE);
        (findViewById(R.id.no_more_batches)).setVisibility(View.GONE);

        updateUINextBatch();

        addCard(k+1);


        }


        }




public void updateUINextBatch(){

        if (userThisDayBatches.get(0).getProgress().trim().equalsIgnoreCase("active")) {
        String batch_id = userThisDayBatches.get(0).getBatch_id();
        TextView ty = findViewById(R.id.set_batch_status);
        ty.setText("Current Status: Batch Started");
        ty = findViewById(R.id.textView17);
        ty.setText("OnGoing Batch: " + batch_id);
        ((Button)findViewById(R.id.button5)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.button8)).setVisibility(View.VISIBLE);

        }
        else{
        TextView ty = findViewById(R.id.set_batch_status);
        ty.setText("Current Status:");
        ty = findViewById(R.id.textView17);
        ty.setText("UpComing Batch: ");
        ((Button)findViewById(R.id.button8)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.button5)).setVisibility(View.VISIBLE);


        }

        TextView tu = findViewById(R.id.set_class);
        String ss = userThisDayBatches.get(0).getBoard()+" - "+userThisDayBatches.get(tcb).getClass_name();
        tu.setText(ss);
        tu = findViewById(R.id.set_sub);
        tu.setText(userThisDayBatches.get(0).getSubject());
        tu = findViewById(R.id.set_time);
        tu.setText(userThisDayBatches.get(0).getTime(day));
        tu = findViewById(R.id.set_time2);
        tu.setText(userThisDayBatches.get(0).getCentre());



        ((Button)findViewById(R.id.button5)).setClickable(true);
        ((Button)findViewById(R.id.button5)).setEnabled(true);

        }







private void init() {
        for (int i = 0; i < SideShowImgs.length; i++)
        SlideShowArray.add(SideShowImgs[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(getApplicationContext(), SlideShowArray));

final Handler handler = new Handler();


final Runnable Update = new Runnable() {
public void run() {
        if (currentPage == SideShowImgs.length) {
        currentPage = 0;
        }
        mPager.setCurrentItem(currentPage++, true);
        }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
@Override
public void run() {
        handler.post(Update);
        }
        }, 5000, 5000);
        }

@RequiresApi(api = Build.VERSION_CODES.N)
public void endBtch(View view) {
        System.out.println("End Batch clicked..........");

        //faculty attendance end
        Map<String, Object> m = new HashMap<>();
        m.put("end", getNowTime());
        firestore.document("faculty/" + appUser.getUserame() + "/attendance/" + getTodayDate() + "/batch/" + userThisDayBatches.get(tcb).getBatch_id()).set(m, SetOptions.merge());
        //batch attendance end
        m = new HashMap<>();
        m.put("end", getNowTime());
        firestore.document("/batches/" + userThisDayBatches.get(tcb).getBatch_id() + "/attendance/" + getTodayDate()).set(m, SetOptions.merge());
        //batch progress end
        userThisDayBatches.get(tcb).setProgress("done");//yahan kyu nahi aaya fir?
        m = new HashMap<>();
        m.put("progress", "done");
        firestore.document("/batches/" + userThisDayBatches.get(tcb).getBatch_id()).set(m, SetOptions.merge());


        //update current today batch
        m = new HashMap<>();
        m.put("current_today_batch", appUser.current_today_batch);
        firestore.document("faculty/" + appUser.getUserame()).set(m, SetOptions.merge());

        //(Y)(Y)

        //yahaan error aara.. index out of bounds exception
        //index 0 pe..
        //refresh data pehle call hua hai..ye aaya hia..
        //refreshdata se user this day batches me se ended batch hat gya hai..

        String message=appUser.getFirst_name()+" "+appUser.getLast_name()+" finished taking Batch "+userThisDayBatches.get(tcb).getBatch_name()+" at "+getNowTime();
        notifyAllAdmins(message);

        m.clear();
        m.put(getNowTime(),message);
        firestore.document("faculty/" + appUser.getUserame() + "/log/" + getTodayDate()).set(m, SetOptions.merge());

        refreshData();

    /*Batches k = todayBatches[0];
    for (int j = 0; j < todayBatches.length - 1; j++) {
        todayBatches[j] = todayBatches[j + 1];
    }
    todayBatches[todayBatches.length - 1] = k;/
    Map<String,Object> m=new HashMap<>();
    logged_inUser.current_today_batch+=1;
    m.put("current_today_batch",logged_inUser.current_today_batch);
    firestore.document("faculty/"+logged_inUser.getUserame()).set(m,SetOptions.merge());
    setBatchDatum();
    CardView cv = findViewById(cardElementIds[logged_inUser.current_today_batch+1][0]);
    cv.setVisibility(View.VISIBLE);
*
        }

static int day = Calendar.getInstance(TimeZone.getDefault()).getTime().getDay();


@TargetApi(Build.VERSION_CODES.N)
@RequiresApi(api = Build.VERSION_CODES.N)
public void startBatch(View view) {
        System.out.println("Start Batch clicked...........");
        userThisDayBatches.get(tcb).setProgress("active");


        String batch_id = userThisDayBatches.get(tcb).getBatch_id();
        TextView ty = findViewById(R.id.set_batch_status);
        ty.setText("Current Status: Batch Started");
        ty = findViewById(R.id.textView17);
        ty.setText("OnGoing Batch : " + batch_id);
        String pathf = "faculty/" + appUser.getUserame() + "/attendance/" + getTodayDate() + "/batch/" + batch_id;
        pathf = pathf.trim();
        Map<String, Object> map = new HashMap<>();
        map.put("start", getNowTime());
        map.put("batch_time", userThisDayBatches.get(tcb).getTime(day));
        firestore.document(pathf).set(map, SetOptions.merge());

        String pathb = "/batches/" + userThisDayBatches.get(tcb).getBatch_id() + "/attendance/" + getTodayDate();
        pathb = pathb.trim();
        Map<String, Object> mapb = new HashMap<>();
        mapb.put("start", getNowTime());
        mapb.put("batch_time", userThisDayBatches.get(tcb).getTime(day));
        firestore.document(pathb).set(mapb, SetOptions.merge());
        Map<String, Object> mm = new HashMap<>();
        mm.put("progress", "active");
        firestore.document("/batches/" + userThisDayBatches.get(tcb).getBatch_id()).set(mm, SetOptions.merge());


        Button bb = ((Button) findViewById(R.id.button5));
        bb.setClickable(false);
        bb.setEnabled(false);
        bb.setVisibility(View.INVISIBLE);
        bb = ((Button) findViewById(R.id.button8));
        bb.setClickable(true);
        bb.setEnabled(true);
        bb.setVisibility(View.VISIBLE);

        refreshData();

//Hello Sir
// App crash ho rha hai location change karne pe..
        //Kal launch karenge ye app..
//Chala AS?
        // haan bataiye
        //ab nahi aaya error






    /*    NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID )

                .setSmallIcon(R.drawable.view_faculty)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);





        String message=appUser.getFirst_name()+" "+appUser.getLast_name()+" started taking Batch "+userThisDayBatches.get(tcb).getBatch_name()+" at "+getNowTime();
        notifyAllAdmins(message);

        mm.clear();
        mm.put(getNowTime(),message);
        firestore.document("faculty/" + appUser.getUserame() + "/log/" + getTodayDate()).set(mm, SetOptions.merge());


        }
static ArrayList<String> admins;

        void notifyAllAdmins(final String message){//faculty_name,String batch_name,String Time) {
//final String message=faculty_name+" finished taking Batch "+batch_name+" at "+Time;

final Map <String,Object> mess= new HashMap<>();
        mess.put(getNowTimeMillis(),message);

        if(admins==null)
        firestore.document("lists/admins/").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
@Override
public void onComplete(@NonNull Task<DocumentSnapshot> task) {
        Map m = task.getResult().getData();
        admins = new ArrayList<>(m.keySet());
        int n = admins.size() - 1;
        while (n >= 0) {
        firestore.document("faculty/" + admins.get(n) + "/notifications/" + getTodayDate()).set(mess, SetOptions.merge());
        n -= 1;
        }
        }
        });
        else
        {
        int n = admins.size() - 1;
        while (n >= 0) {
        firestore.document("faculty/" + admins.get(n) + "/notifications/" + getTodayDate()).set(mess, SetOptions.merge());
        n -= 1;
        }
        }
        }


private Bitmap loadImageFromStorage(String path) {

        try {
        File f = new File(path, appUser.getFirst_name() + "_" + appUser.getLast_name() + ".png");
        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
        ImageView img = (ImageView) findViewById(R.id.imageView13);
        img.setImageBitmap(b);
        img = findViewById(R.id.dipi);
        img = new ImageView(this);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        ImageView imageView = hView.findViewById(R.id.dipi);
        imageView.setImageBitmap(b);
        img.setImageBitmap(b);
        System.out.println("Local se hua READ " );

        return b;

        } catch (Exception e) {
        System.out.println("Local not read bcoz " + e.getMessage());

        return read(null);
        }

        }


public Bitmap read(String path) {



        if (path == null) {
final long OO = 1024 * 1024;
        storageRef.child(FirebaseAuth.getInstance().getCurrentUser().getEmail()).getBytes(OO).addOnSuccessListener(new OnSuccessListener<byte[]>() {
@Override
public void onSuccess(byte[] bytes) {
        bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ImageView dp = findViewById(R.id.imageView13);
        dp.setImageBitmap(bit);
        dp = findViewById(R.id.dipi);
        dp.setImageBitmap(bit);
        System.out.println("Online se hua READ " );

        firestore.document("faculty/" + FirebaseAuth.getInstance().getCurrentUser().getEmail()).update("img_path", saveToInternalStorage(bit));

        }
        });
        return bit;


        }
        return loadImageFromStorage(path);
        }


private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        directory.mkdir();
        // Create imageDir
        File mypath = new File(directory, appUser.getFirst_name() + "_" + appUser.getLast_name() + ".png");

        FileOutputStream fos = null;
        try {
        fos = new FileOutputStream(mypath);
        // Use the compress method on the BitMap object to write image to the OutputStream
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.write(bitmapImage.getByteCount());
        } catch (Exception e) {
        System.out.println("\nG\nA\nD\nB\nA\nD\n : "+e.getMessage());
        } finally {
        try {
        fos.close();
        } catch (Exception e) {
        e.printStackTrace();
        }
        }
        return directory.getAbsolutePath();
        }


public void nikal(View v) {


        firebaseAuth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        }

        { /*

    public void nikall(View v){
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Deleting User Data");
        pd.show();
        firestore.document("faculty/"+uid).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    toast(FacultyDashboard.this, "User Data Deleted");
                    pd.setMessage("Deleting User");
                }
            }});
        storageRef.child(uid).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
           if(task.isSuccessful()){
               toast(FacultyDashboard.this,"Photo Deleted");
               //pd.setIcon();
               }

            }
        });
        firebaseAuth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){toast(FacultyDashboard.this,"User Deleted");pd.dismiss();
                }else toast(FacultyDashboard.this,task.getException().getMessage());
            }
        });
        firebaseAuth.signOut();


        //startActivity(new Intent(this, LoginActivity.class));
    }




*}

public static void toast(Context c, String s) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
        }


@Override
public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START);
        } else {
        super.onBackPressed();
        }
        }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        }

@Override
public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        return true;
        }

        return super.onOptionsItemSelected(item);
        }

@SuppressWarnings("StatementWithEmptyBody")
@Override
public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        if(appUser.getType().equalsIgnoreCase("Admin"))
        startActivity(new Intent(this,FacultyList.class));
        else toast(this,"You are not an Admin!");
        //facultyView(null);

        } else if (id == R.id.nav_slideshow) {

        if(appUser.getType().equalsIgnoreCase("Admin"))
        admin_allBatch(null);
        else toast(this,"You are not an Admin!");

        } else if (id == R.id.nav_tools) {
        new AlertDialog.Builder(this)
        .setTitle("Confirm Exit")
        .setMessage("Are you sure you want to exit?")
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

public void onClick(DialogInterface dialog, int whichButton) {
        finish();
        }})
        .setNegativeButton(android.R.string.no, null).show();



        } else if (id == R.id.Share) {

        startActivity(new Intent(this,AboutView.class));


        } else if (id == R.id.nav_send) {
        new AlertDialog.Builder(this)
        .setTitle("Confirm Sign Out")
        .setMessage("Are you sure you want to SignOut?")
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

public void onClick(DialogInterface dialog, int whichButton) {
        nikal(null);
        }})
        .setNegativeButton(android.R.string.no, null).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }


public void openDrawer(View view) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);


    /*   public void set()
       {
           firestore = FirebaseFirestore.getInstance();
           //firebaseStorage = FirebaseStorage.getInstance();
           firestore.document("faculty/" + FirebaseAuth.getInstance().getCurrentUser().getEmail()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
               @Override
               public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                   Map<String, Object> faculty_map_user;
                   faculty_map_user = documentSnapshot.getData();


                   faculty = new Faculty(faculty_map_user.get("first_name").toString(),
                           faculty_map_user.get("last_name").toString(),
                           faculty_map_user.get("username").toString(),
                           faculty_map_user.get("password").toString(),
                           faculty_map_user.get("dob").toString(),
                           (faculty_map_user.get("gender").toString()).equals("M") ? "Male" : "Female",
                           faculty_map_user.get("type").toString(),
                           faculty_map_user.get("phone").toString(),
                           faculty_map_user.get("img_path").toString(),
                           read(faculty_map_user.get("img_path").toString()),
                           faculty_map_user.get("batches").toString().split("$"));
                   faculty.setFirst_name(faculty_map_user.get("first_name").toString());



               }
           });
       }*/
        /*

        int noOfBatches=faculty_batches.length;

        final Map<String,Object> batchMap[]=new Map[noOfBatches];


        for(int i=0;i<noOfBatches;i++)
        {
            final int finalI = i;
            final String finS=faculty_batches[i].trim();
            firestore.document("batches/"+finS).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                    batchMap[finalI]=documentSnapshot.getData();

                }
            });
        }

        String time= Calendar.getInstance(TimeZone.getDefault()).getTime().getHours()+":"+Calendar.getInstance(TimeZone.getDefault()).getTime().getMinutes();
        for(int i=0;i<noOfBatches;i++)
            for(int j=0;j<noOfBatches-i-1;j++) {
                if (batchMap[j].get("batch_time").toString().compareTo(batchMap[j + 1].get("batch_time").toString()) > 0) {
                    Map k = batchMap[j];
                    batchMap[j] = batchMap[j + 1];
                    batchMap[j + 1] = k;
                }
            }

        TextView tu=findViewById(R.id.set_class);
        tu.setText(batchMap[0].get("class").toString());
        tu=findViewById(R.id.set_sub);
        tu.setText(batchMap[0].get("subject").toString());
        tu=findViewById(R.id.set_time);
        tu.setText(batchMap[0].get("batch_time").toString());

        tu=findViewById(R.id.set_class_2);
        tu.setText(batchMap[1].get("class").toString());
        tu=findViewById(R.id.set_sub_2);
        tu.setText(batchMap[1].get("subject").toString());
        tu=findViewById(R.id.set_time_2);
        tu.setText(batchMap[1].get("batch_time").toString());

         *
        }


        {
 /*   public void todaysBatches() {

                cardElementIds = new int[][]{
                        {R.id.todaycards, R.id.batch_name, R.id.batch_id, R.id.batch_class, R.id.batch_time, R.id.batch_centre},
                        {R.id.todaycards2, R.id.batch_name2, R.id.batch_id2, R.id.batch_class2, R.id.batch_time2, R.id.batch_centre2},
                        {R.id.todaycards3, R.id.batch_name3, R.id.batch_id3, R.id.batch_class3, R.id.batch_time3, R.id.batch_centre3},
                        {R.id.todaycards4, R.id.batch_name4, R.id.batch_id4, R.id.batch_class4, R.id.batch_time4, R.id.batch_centre4},
                        {R.id.todaycards5, R.id.batch_name5, R.id.batch_id5, R.id.batch_class5, R.id.batch_time5, R.id.batch_centre5},
                        {R.id.todaycards6, R.id.batch_name6, R.id.batch_id6, R.id.batch_class6, R.id.batch_time6, R.id.batch_centre6}
                };





        if(todayBatches[logged_inUser.current_today_batch+1]==null){
            CardView cv=findViewById(R.id.batches_today_card);
            cv.setVisibility(View.GONE);
            cv=findViewById(R.id.no_more_batches);
            cv.setVisibility(View.VISIBLE);

            return;

        }

        for (int i = logged_inUser.current_today_batch+1; i < todayBatches.length&&todayBatches[i]!=null; i++) {
            CardView cv = findViewById(cardElementIds[i - 1][0]);
            cv.setVisibility(View.VISIBLE);

            tv = findViewById(cardElementIds[i - 1][1]);
            tv.setText(todayBatches[i].getBatch_name());

            tv = findViewById(cardElementIds[i - 1][2]);
            tv.setText(todayBatches[i].getBatch_id());

            tv = findViewById(cardElementIds[i - 1][3]);
            tv.setText(todayBatches[i].getClass_name() + "  :: " + todayBatches[i].getSubject());

            tv = findViewById(cardElementIds[i - 1][4]);
            tv.setText(todayBatches[i].getTime(week_day - 1));

            tv = findViewById(cardElementIds[i - 1][5]);
            tv.setText(todayBatches[i].getCentre());


        }




    }


*
        }

public void all_batches() {



        if (userAllbatches.size() <= 0) {
        return;
        }

        findViewById(R.id.no_batches_alloted).setVisibility(View.GONE);


        CardView cardView[] = new CardView[userAllbatches.size()];
        LinearLayout t = findViewById(R.id.all_batches_layout);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < userAllbatches.size(); i++) {
        cardView[i] = (CardView) inflater.inflate(R.layout.allcard, t, false);
        ConstraintLayout cl = (ConstraintLayout) cardView[i].getChildAt(0);
        TextView tv = (TextView) cl.getChildAt(0);
        tv.setText(userAllbatches.get(i).batch_name);


        ConstraintLayout c2 = (ConstraintLayout) cl.getChildAt(3);

        tv = (TextView) c2.getChildAt(0);
        tv.setText(userAllbatches.get(i).getClass_name() + " - " + userAllbatches.get(i).getBoard());
        tv = (TextView) c2.getChildAt(1);
        tv.setText(userAllbatches.get(i).getCentre());
        tv = (TextView) c2.getChildAt(2);
        tv.setText(userAllbatches.get(i).getSubject());
        tv = (TextView) c2.getChildAt(3);
        tv.setText(userAllbatches.get(i).getBatch_id());


        ConstraintLayout c3 = (ConstraintLayout) cl.getChildAt(4);


        tv = (TextView) c3.getChildAt(6);

        if (!userAllbatches.get(i).getTime(1).equals("99:99"))
        tv.setText("MON  :  " + userAllbatches.get(i).getTime(1));
        else
        tv.setText("MON :  -- : --");


        tv = (TextView) c3.getChildAt(0);

        if (!userAllbatches.get(i).getTime(2).equals("99:99"))
        tv.setText("TUE  :  " + userAllbatches.get(i).getTime(2));
        else
        tv.setText("TUE :  -- : --");


        tv = (TextView) c3.getChildAt(5);
        if (!userAllbatches.get(i).getTime(3).equals("99:99"))
        tv.setText("WED  :  " + userAllbatches.get(i).getTime(3));
        else
        tv.setText("WED : -- : --");


        tv = (TextView) c3.getChildAt(1);
        if (!userAllbatches.get(i).getTime(4).equals("99:99"))
        tv.setText("THU  : " + userAllbatches.get(i).getTime(4));
        else
        tv.setText("THU : -- : --");


        tv = (TextView) c3.getChildAt(2);
        if (!userAllbatches.get(i).getTime(5).equals("99:99"))
        tv.setText("FRI  :  " + userAllbatches.get(i).getTime(5));
        else
        tv.setText("FRI  :  -- : --");


        tv = (TextView) c3.getChildAt(3);
        if (!userAllbatches.get(i).getTime(6).equals("99:99"))
        tv.setText("SAT  :  " + userAllbatches.get(i).getTime(6));
        else
        tv.setText("SAT  :  -- : --");


        tv = (TextView) c3.getChildAt(4);
        if (!userAllbatches.get(i).getTime(0).equals("99:99"))
        tv.setText("SUN  :  " + userAllbatches.get(i).getTime(0));
        else
        tv.setText("SUN  :  -- : --");


        t.addView(cardView[i]);



        }

        }




        {
/*
       int[][] cardElements = {
                {R.id.aBatchCard, R.id.aBatchName, R.id.aBatcgId, R.id.aBatchClass, R.id.aBatchSubject, R.id.aBatchCentre, R.id.aBatchMon, R.id.aBatchTue, R.id.aBatchWed, R.id.aBatchThu, R.id.aBatchFri, R.id.aBatchSat, R.id.aBatchSun},
                {R.id.aBatchCard2, R.id.aBatchName2, R.id.aBatcgId2, R.id.aBatchClass2, R.id.aBatchSubject2, R.id.aBatchCentre2, R.id.aBatchMon2, R.id.aBatchTue2, R.id.aBatchWed2, R.id.aBatchThu2, R.id.aBatchFri2, R.id.aBatchSat2, R.id.aBatchSun2},
                {R.id.aBatchCard3, R.id.aBatchName3, R.id.aBatcgId3, R.id.aBatchClass3, R.id.aBatchSubject3, R.id.aBatchCentre3, R.id.aBatchMon3, R.id.aBatchTue3, R.id.aBatchWed3, R.id.aBatchThu3, R.id.aBatchFri3, R.id.aBatchSat3, R.id.aBatchSun3},
                {R.id.aBatchCard4, R.id.aBatchName4, R.id.aBatcgId4, R.id.aBatchClass4, R.id.aBatchSubject4, R.id.aBatchCentre4, R.id.aBatchMon4, R.id.aBatchTue4, R.id.aBatchWed4, R.id.aBatchThu4, R.id.aBatchFri4, R.id.aBatchSat4, R.id.aBatchSun4},
                {R.id.aBatchCard5, R.id.aBatchName5, R.id.aBatcgId5, R.id.aBatchClass5, R.id.aBatchSubject5, R.id.aBatchCentre5, R.id.aBatchMon5, R.id.aBatchTue5, R.id.aBatchWed5, R.id.aBatchThu5, R.id.aBatchFri5, R.id.aBatchSat5, R.id.aBatchSun5},
                {R.id.aBatchCard6, R.id.aBatchName6, R.id.aBatcgId6, R.id.aBatchClass6, R.id.aBatchSubject6, R.id.aBatchCentre6, R.id.aBatchMon6, R.id.aBatchTue6, R.id.aBatchWed6, R.id.aBatchThu6, R.id.aBatchFri6, R.id.aBatchSat6, R.id.aBatchSun6},

                {R.id.aBatchCard7, R.id.aBatchName7, R.id.aBatcgId7, R.id.aBatchClass7, R.id.aBatchSubject7, R.id.aBatchCentre7, R.id.aBatchMon7, R.id.aBatchTue7, R.id.aBatchWed7, R.id.aBatchThu7, R.id.aBatchFri7, R.id.aBatchSat7, R.id.aBatchSun7},

                {R.id.aBatchCard8, R.id.aBatchName8, R.id.aBatcgId8, R.id.aBatchClass8, R.id.aBatchSubject8, R.id.aBatchCentre8, R.id.aBatchMon8, R.id.aBatchTue8, R.id.aBatchWed8, R.id.aBatchThu8, R.id.aBatchFri8, R.id.aBatchSat8, R.id.aBatchSun8},

                {R.id.aBatchCard9, R.id.aBatchName9, R.id.aBatcgId9, R.id.aBatchClass9, R.id.aBatchSubject9, R.id.aBatchCentre9, R.id.aBatchMon9, R.id.aBatchTue9, R.id.aBatchWed9, R.id.aBatchThu9, R.id.aBatchFri9, R.id.aBatchSat9, R.id.aBatchSun9},

                {R.id.aBatchCard10, R.id.aBatchName10, R.id.aBatcgId10, R.id.aBatchClass10, R.id.aBatchSubject10, R.id.aBatchCentre10, R.id.aBatchMon10, R.id.aBatchTue10, R.id.aBatchWed10, R.id.aBatchThu10, R.id.aBatchFri10, R.id.aBatchSat10, R.id.aBatchSun10},

                {R.id.aBatchCard11, R.id.aBatchName11, R.id.aBatcgId11, R.id.aBatchClass11, R.id.aBatchSubject11, R.id.aBatchCentre11, R.id.aBatchMon11, R.id.aBatchTue11, R.id.aBatchWed11, R.id.aBatchThu11, R.id.aBatchFri11, R.id.aBatchSat11, R.id.aBatchSun11},

                {R.id.aBatchCard12, R.id.aBatchName12, R.id.aBatcgId12, R.id.aBatchClass12, R.id.aBatchSubject12, R.id.aBatchCentre12, R.id.aBatchMon12, R.id.aBatchTue12, R.id.aBatchWed12, R.id.aBatchThu12, R.id.aBatchFri12, R.id.aBatchSat12, R.id.aBatchSun12},

                {R.id.aBatchCard13, R.id.aBatchName13, R.id.aBatcgId13, R.id.aBatchClass13, R.id.aBatchSubject13, R.id.aBatchCentre13, R.id.aBatchMon13, R.id.aBatchTue13, R.id.aBatchWed13, R.id.aBatchThu13, R.id.aBatchFri13, R.id.aBatchSat13, R.id.aBatchSun13},

                {R.id.aBatchCard14, R.id.aBatchName14, R.id.aBatcgId14, R.id.aBatchClass14, R.id.aBatchSubject14, R.id.aBatchCentre14, R.id.aBatchMon14, R.id.aBatchTue14, R.id.aBatchWed14, R.id.aBatchThu14, R.id.aBatchFri14, R.id.aBatchSat14, R.id.aBatchSun14},

                {R.id.aBatchCard15, R.id.aBatchName15, R.id.aBatcgId15, R.id.aBatchClass15, R.id.aBatchSubject15, R.id.aBatchCentre15, R.id.aBatchMon15, R.id.aBatchTue15, R.id.aBatchWed15, R.id.aBatchThu15, R.id.aBatchFri15, R.id.aBatchSat15, R.id.aBatchSun15},

                {R.id.aBatchCard16, R.id.aBatchName16, R.id.aBatcgId16, R.id.aBatchClass16, R.id.aBatchSubject16, R.id.aBatchCentre16, R.id.aBatchMon16, R.id.aBatchTue16, R.id.aBatchWed16, R.id.aBatchThu16, R.id.aBatchFri16, R.id.aBatchSat16, R.id.aBatchSun16},

                {R.id.aBatchCard17, R.id.aBatchName17, R.id.aBatcgId17, R.id.aBatchClass17, R.id.aBatchSubject17, R.id.aBatchCentre17, R.id.aBatchMon17, R.id.aBatchTue17, R.id.aBatchWed17, R.id.aBatchThu17, R.id.aBatchFri17, R.id.aBatchSat17, R.id.aBatchSun17},
                {R.id.aBatchCard18, R.id.aBatchName18, R.id.aBatcgId18, R.id.aBatchClass18, R.id.aBatchSubject18, R.id.aBatchCentre18, R.id.aBatchMon18, R.id.aBatchTue18, R.id.aBatchWed18, R.id.aBatchThu18, R.id.aBatchFri18, R.id.aBatchSat18, R.id.aBatchSun18},

                {R.id.aBatchCard19, R.id.aBatchName19, R.id.aBatcgId19, R.id.aBatchClass19, R.id.aBatchSubject19, R.id.aBatchCentre19, R.id.aBatchMon19, R.id.aBatchTue19, R.id.aBatchWed19, R.id.aBatchThu19, R.id.aBatchFri19, R.id.aBatchSat19, R.id.aBatchSun19},

                {R.id.aBatchCard20, R.id.aBatchName20, R.id.aBatcgId20, R.id.aBatchClass20, R.id.aBatchSubject20, R.id.aBatchCentre20, R.id.aBatchMon20, R.id.aBatchTue20, R.id.aBatchWed20, R.id.aBatchThu20, R.id.aBatchFri20, R.id.aBatchSat20, R.id.aBatchSun20},

        };

    public void add_batch()
    {
        String claas=((EditText)findViewById(R.id.classname)).getText().toString().trim();
        String board=((EditText)findViewById(R.id.board)).getText().toString().trim();
        String faculty=((EditText)findViewById(R.id.facultyID)).getText().toString().trim();
        String centre=((EditText)findViewById(R.id.centre)).getText().toString().trim().toUpperCase();
        String subject=((EditText)findViewById(R.id.subject)).getText().toString().trim().toUpperCase();
        String batchname=((EditText)findViewById(R.id.batchname)).getText().toString().trim().toUpperCase();
        String days[]=new String[]{"sun","mon","tue","wed","thu","fri","sat","sun"};



        Map<String,Object> map=new HashMap<>();

*


        }



static CardView cardViews[];
public void addCard(int k){

        if(k==userThisDayBatches.size()){
        ((CardView)findViewById(R.id.batches_today_card)).setVisibility(View.GONE);
        ((CardView)findViewById(R.id.no_more_batches)).setVisibility(View.VISIBLE);
        return;
        }

        Calendar calendar = Calendar.getInstance();
        int week_day = calendar.get(Calendar.DAY_OF_WEEK);
        String[] days = {"S  U  N  D  A  Y", "M  O N  D  A  Y", "T  U  E  S  D  A  Y", "W  E  D  N  E  S  D  A  Y", "T  H  U  R  S  D  A  Y", "F  R   I  D  A  Y", "S  A  T  U  R  D  A  Y"};


        cardViews =new CardView[userThisDayBatches.size()];
        LinearLayout t = findViewById(R.id.today_scroll);

        t.removeAllViews();



        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ConstraintLayout tvcl = (ConstraintLayout) inflater.inflate(R.layout.day,t,false);
        TextView dayCard = (TextView) tvcl.getChildAt(0);
        dayCard.setText(days[week_day-1]);

        t.addView(tvcl);

        TextView tv;

        for(int i=0;k<cardViews.length;i++,k++)
        {
        cardViews[i] = (CardView) inflater.inflate(R.layout.card, t, false);
        ConstraintLayout cl = (ConstraintLayout) cardViews[i].getChildAt(0);
        tv = (TextView) cl.getChildAt(0);
        tv.setText(userThisDayBatches.get(k).batch_name);
        tv = (TextView) cl.getChildAt(2);
        tv.setText(userThisDayBatches.get(k).batch_id);
        tv = (TextView) cl.getChildAt(3);
        tv.setText(userThisDayBatches.get(k).subject);
        tv = (TextView) cl.getChildAt(4);
        tv.setText(userThisDayBatches.get(k).getTime(day));
        tv = (TextView) cl.getChildAt(5);
        tv.setText(userThisDayBatches.get(k).getCentre());
        cardViews[i].setVisibility(View.VISIBLE);

        t.addView(cardViews[i]);

        }
        }


public void batchView(View v){
        CardView cv = (CardView) v;
        System.out.println(cv.toString());
        String batchid;
        ConstraintLayout cl = (ConstraintLayout) cv.getChildAt(0);
        ConstraintLayout cl2 = (ConstraintLayout) cl.getChildAt(3);

        TextView tv = (TextView) cl2.getChildAt(3);
        batchid = tv.getText().toString();


        Intent in = new Intent(this, BatchView.class);
        in.putExtra("BatchID", batchid);
        startActivity(in);

        }

public void todaybatchView(View view){
        CardView cv = (CardView) view;
        ConstraintLayout cl = (ConstraintLayout) cv.getChildAt(0);
        TextView id = (TextView) cl.getChildAt(2);
        String batchid = id.getText().toString();

        System.out.println("BatchID:" +batchid);

        Intent in = new Intent(this, BatchView.class);
        in.putExtra("BatchID", batchid);
        startActivity(in);


        }




public void admin_createBatch(View view){
        startActivity(new Intent(this, AddBatch.class));
        }

//Heyy...
//
public void admin_editBatch(View view){
        startActivity(new Intent(this, EditBatch.class));
        }

public void admin_allBatch(View view){
        startActivity(new Intent(this, BatchList.class));
        }
public void notifications(View view){
        startActivity(new Intent(this, Notifications.class));
        }
public void facultyView(View view){




        startActivity(new Intent(this, FacultyList.class));



        }




public void initialisenotifications(){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ghanta);
        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Hi, This is Android Notification Detail!");

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
        int notificationID=1000;
        mNotificationManager.notify(notificationID, mBuilder.build());

        }

static Bitmap bit;
private Intent GalIntent;
private Uri uri;
private Intent CropIntent;

public void imagepicker(View view){

        GalIntent = new Intent(Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);

        }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {

        ImageCropFunction();

        } else if (requestCode == 2) {

        if (data != null) {

        uri = data.getData();
final InputStream imageStream;
        try {
        imageStream = getContentResolver().openInputStream(uri);

final Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

        bit=bitmap;
        ImageView imageView=findViewById(R.id.imageView13);

        imageView.setImageBitmap(bitmap);
        writeImage(bit);
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        }

        }
        else System.out.println("Yahan bhi gadbad hai");
        } else if (requestCode == 1) {

        if (data != null) {

        Bundle bundle = data.getExtras();

        Bitmap bitmap = null;
        try {
        bitmap = (Bitmap) bundle.getParcelable(String.valueOf(MediaStore.Images.Media.getBitmap(getContentResolver(), uri)));
        } catch (IOException e) {
        e.printStackTrace();
        }

        ImageView imageView=findViewById(R.id.imageView13);

        imageView.setImageBitmap(bitmap);
        writeImage(bit);

        }

        else System.out.println("Yahan hai dikkat badi wali");
        }
        }

public void writeImage(Bitmap bit){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();


        storageRef.child(appUser.getUserame()).putBytes(datas).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
@Override
public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        toast(MainActivity.this,"Photo Updated!");
        }
        });
        read(null);
        }
public void ImageCropFunction() {

        // Image Crop Code
        try {
        System.out.println("Crop Intent me aaya ");
        CropIntent = new Intent("com.android.camera.action.CROP");
        System.out.println("Crop Intent banaaya ");
        System.out.println(uri.toString());
        CropIntent.setDataAndType(uri, "image/*");
        System.out.println("Crop Intent me data type lagaaya ");
        CropIntent.putExtra("crop", "true");
        CropIntent.putExtra("outputX", 180);
        CropIntent.putExtra("outputY", 180);
        CropIntent.putExtra("aspectX", 3);
        CropIntent.putExtra("aspectY", 4);
        CropIntent.putExtra("scaleUpIfNeeded", true);
        CropIntent.putExtra("return-data", true);

        System.out.println("Crop Intent me aayop ");
        startActivityForResult(CropIntent, 1);
        System.out.println("ACTIvity shuru");

        } catch (Exception e) {
        System.out.println("Crop exception "+e.getMessage());
        }
        }


public void Tester(View view){
        // startActivity(new Intent(this, Crop.class));
        }

        }


        */