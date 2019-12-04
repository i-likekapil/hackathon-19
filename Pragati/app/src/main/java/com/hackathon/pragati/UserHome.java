package com.hackathon.pragati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static com.hackathon.pragati.SplashScreen.firebaseAuth;
//ok
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

public class UserHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_home);
        startActivity(new Intent(UserHome.this,ProjectHome.class));

    }



    public void exit(View view)
    {
        firebaseAuth.signOut();
        startActivity(new Intent(UserHome.this,SplashScreen.class));
        finish();
    }

/*

        Map car;
        APIService apiService;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_car_page);

            perform(getIntent().getStringExtra("vehicleID"));
            w=findViewById(R.id.washtatus);
            apiService= Client.getClient("https://fcm.googleapis.com/").create(APIService.class);

        }

        public void perform(String s){
            final LinearLayout t = findViewById(R.id.tt);
            firestore.document("Vehicles/"+s).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){

                        car=task.getResult().getData();

                        if(car.containsKey("tStatus"))
                            if(car.get("tStatus").equals("Washing"))
                                w.setText("StopWashing");

                        Set<String> keys= car.keySet();
                        ArrayList k2 = new ArrayList(keys);

                        ArrayList values= new ArrayList(car.values());
                        for(int i=0;i<keys.size();i++) {
                            LinearLayout l=new LinearLayout(CarPage.this);
                            l.setOrientation(LinearLayout.HORIZONTAL);
                            TextView carK = new TextView(CarPage.this);//(TextView) inflater.inflate(R.layout.cartext, t, false);
                            carK.setText(k2.get(i).toString()+" : ");
                            carK.setTypeface(Typeface.DEFAULT_BOLD);
                            carK.setTextSize(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM,20.0f);
                            TextView carV = new TextView(CarPage.this);//(TextView) inflater.inflate(R.layout.cartext, t, false);
                            carV.setText(values.get(i).toString());
                            carV.setTextSize(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM,18.0f);
                            l.addView(carK);
                            l.addView(carV);
                            t.addView(l);
                        }
                    }
                    else{
                        Toast.makeText(CarPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        TextView w;
        public void updateStatus(View v){
            if(w.getText().equals("StartWashing")){startWashing();}
            else {stopWashing();}
        }

        private void startWashing() {
            Map<String,Object> m=new HashMap<>();
            m.put("tStatus","Washing");
            firestore.document("RUsers/"+car.get("Phone")).set(m,SetOptions.merge())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(CarPage.this, "Started!", Toast.LENGTH_SHORT).show();
                                w.setText("StopWashing");
                            }
                            else
                                Toast.makeText(CarPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            String x="Your Car "+car.get("Manufacturer")+" "+car.get("Model")+" is being washed now!";

            Map<String,Object> k=new HashMap<>();
            k.put("start",getNowTimeMillis());
            firestore.document("RUsers/"+car.get("Phone")+"/History/"+getTodayDate()).set(k,SetOptions.merge());
            Map<String,Object> k2=new HashMap<>();
            k2.put(getNowTimeMillis(),x);
            firestore.document("RUsers/"+car.get("Phone")+"/Notifications/"+getTodayDate()).set(k2,SetOptions.merge());
            firestore.document("Vehicles/"+car.get("VehicleID")).set(m,SetOptions.merge());
            sendNotification(car.get("Phone").toString(),"CarLiteTeam",x);
        }


        CheckBox ws,roof,doors,mirrors,bonnet,bumpers,wc,tyres,fsts,bsts,dbrd,cnsl,ftmt,btspc,ipol,expol;

        private void stopWashing(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Washed Components");

            final LinearLayout pop=new LinearLayout(this);
*/
/*        pop.setOrientation(LinearLayout.HORIZONTAL);
        final TextView ty=new TextView(this);
        ty.setText("WindShield");
        pop.addView(ty);
        final Switch input1 = new Switch(this);
        pop.addView(input1);

        final LinearLayout pop2=new LinearLayout(this);
        pop2.setOrientation(LinearLayout.HORIZONTAL);
        final TextView ty2=new TextView(this);
        ty2.setText("Tyres");
        pop2.addView(ty2);
        final Switch input2 = new Switch(this);
        pop2.addView(input2);



        LinearLayout lol=new LinearLayout(this);
        lol.setOrientation(LinearLayout.VERTICAL);
        lol.addView(pop);
        lol.addView(pop2);
*//*

            LayoutInflater inflater= (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            CardView carD= (CardView) inflater.inflate(R.layout.detail_card,pop,false);


            LinearLayout l= (LinearLayout) ((ScrollView)((ConstraintLayout) carD.getChildAt(0)).getChildAt(0)).getChildAt(0);
            ws= (CheckBox) l.getChildAt(0);
            roof= (CheckBox) l.getChildAt(1);
            doors= (CheckBox) l.getChildAt(2);
            mirrors= (CheckBox) l.getChildAt(3);
            bonnet= (CheckBox) l.getChildAt(4);
            bumpers= (CheckBox) l.getChildAt(5);
            wc= (CheckBox) l.getChildAt(6);
            tyres= (CheckBox) l.getChildAt(7);
            fsts= (CheckBox) l.getChildAt(8);
            bsts= (CheckBox) l.getChildAt(9);
            dbrd= (CheckBox) l.getChildAt(10);
            cnsl= (CheckBox) l.getChildAt(11);
            ftmt= (CheckBox) l.getChildAt(12);
            btspc= (CheckBox) l.getChildAt(13);
            ipol= (CheckBox) l.getChildAt(14);
            expol= (CheckBox) l.getChildAt(15);



            builder.setView(carD);
            builder.setPositiveButton("Notify", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Map<String,Object> m=new HashMap<>();
                    m.put("tStatus","Completed");
                    firestore.document("RUsers/"+car.get("Phone")).set(m,SetOptions.merge())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(CarPage.this, "Notified ;)", Toast.LENGTH_SHORT).show();
                                        w.setText("StartWashing");



                                    }
                                    else
                                        Toast.makeText(CarPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    Map<String,Object> k=new HashMap<>();
                    k.put("stop",getNowTimeMillis());
                    k.put("WindScreen",ws.isChecked());
                    k.put("Roof",roof.isChecked());
                    k.put("Doors",doors.isChecked());
                    k.put("Mirrors",mirrors.isChecked());
                    k.put("Bonnet",bonnet.isChecked());
                    k.put("Bumpers",bumpers.isChecked());
                    k.put("WheelCovers",wc.isChecked());
                    k.put("Tyres",tyres.isChecked());
                    k.put("FrontSeats",fsts.isChecked());
                    k.put("BackSeats",bsts.isChecked());
                    k.put("DashBoard",dbrd.isChecked());
                    k.put("Console",cnsl.isChecked());
                    k.put("FootMats",ftmt.isChecked());
                    k.put("BootSpace",btspc.isChecked());
                    k.put("InteriorPolishing",ipol.isChecked());
                    k.put("ExteriorPolishing",expol.isChecked());

                    String x="Your Car "+car.get("Manufacturer")+" "+car.get("Model")+"  has been washed!!";
                    firestore.document("RUsers/"+car.get("Phone")+"/History/"+getTodayDate()).set(k,SetOptions.merge());
                    firestore.document("Vehicles/"+car.get("VehicleID")).set(m,SetOptions.merge());
                    Map<String,Object> k2=new HashMap<>();
                    k2.put(getNowTimeMillis(),x);
                    firestore.document("RUsers/"+car.get("Phone")+"/Notifications/"+getTodayDate()).set(k2,SetOptions.merge());
                    sendNotification(car.get("Phone").toString(),"CarLiteTeam",x);

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
        public void showHistory(View v){

        }
        String x;
        public void notifyUser(View v){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notification Text");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT );
            builder.setView(input);
            builder.setPositiveButton("Notify", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    x = input.getText().toString();
                    Map<String,Object> text=new HashMap<>();
                    text.put(getNowTimeMillis(),x);
                    firestore.document("RUsers/"+car.get("Phone")+"/Notifications/"+getTodayDate()).set(text, SetOptions.merge())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        // The topic name can be optionally prefixed with "/topics/".
                                        // String topic = "highScores";

// See documentation on defining a message payload.

                                        //String token= firestore.document("RUsers/"+car.get("Phone")).get().getResult().get("token").toString();
                                        RemoteMessage message =new RemoteMessage.Builder(car.get("Phone").toString())
                                                .addData("msg", x)
                                                .build();

// Send a message to the devices subscribed to the provided topic.
                                        FirebaseMessaging.getInstance().send(message);
// Response is a message ID string.
                                        System.out.println("Successfully sent message: " );

                                        Toast.makeText(CarPage.this, "Notified ;)", Toast.LENGTH_SHORT).show();}
                                    else
                                        Toast.makeText(CarPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
//firestore.collection("Notifications").addSnapshotListener(new EventListener<QuerySnapshot>() {
    //@Override
   // public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
     //  List<DocumentChange> k= queryDocumentSnapshots.getDocumentChanges();
   //    ArrayList<String> l=new ArrayList(k.get(0).getDocument().getData().values());
    ///    sendNotification(car.get("Phone").toString(),"CarLiteTeam",l.get(0));
  //  }
//});

        }

        public  void openMap(View v){
            String x=car.get("Address").toString();
            Uri gmmIntentUri = Uri.parse("geo:0,0?q="+x);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
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
            return (hh < 10 ? ("0" + hh) : hh) + ":" + (mm < 10 ? ("0" + mm) : mm)+":"+ (ss < 10 ? ("0" + ss) : ss);
            //+ (mi < 10 ? ("0" + mi) : mi);
        }

        public void feedBack(View v){
            Intent i=new Intent(CarPage.this,FeedBack.class);
            i.putExtra("vehicleID",car.get("VehicleID").toString());
            startActivity(i);
        }

        private void sendNotification(String receiver, final String username, final String message) {

            firestore.document("RUsers/"+receiver).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        Token token=new Token(task.getResult().get("token").toString());
                        Data data=new Data(firebaseAuth.getCurrentUser().getPhoneNumber(),R.drawable.app_icon,message,username,"sented");
                        Sender sender=new Sender(data,token.getToken());
                        apiService.sendNotification(sender)
                                .enqueue(new Callback<MyResponse>() {
                                    @Override
                                    public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                        if(response.code()==200){
                                            if(response.body().success!=1){
                                                Toast.makeText(CarPage.this, "Failed!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<MyResponse> call, Throwable t) {
                                        System.out.println("mattha kharaab ui "+t.getMessage());
                                    }
                                });

                    }
                    else
                        System.out.println("mattha kharaab "+task.getException().getMessage());
                }
            });
        }
*/

}


