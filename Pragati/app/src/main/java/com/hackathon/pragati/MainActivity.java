package com.hackathon.pragati;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hackathon.pragati.ProjectHome.getNowTimeMillis;
import static com.hackathon.pragati.ProjectHome.getTodayDate;
import static com.hackathon.pragati.SplashScreen.appUser;
import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;
import static com.hackathon.pragati.SplashScreen.storageRef;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Spinner stateSpinner, citySpinner;
    private AppBarConfiguration mAppBarConfiguration;
    private Intent GalIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        stateSpinner = findViewById(R.id.spinnerState);
        citySpinner = findViewById(R.id.spinnerCity);

/*        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(
                this);
/*                new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.navy_home) {
                    DrawerLayout drawer = findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    // Handle the camera action
                } else if (id == R.id.navy_gallery) {

            /*if(appUser.getType().equalsIgnoreCase("Admin"))
                startActivity(new Intent(this,FacultyList.class));
            else toast(this,"You are not an Admin!");*
                    //facultyView(null);

                } else if (id == R.id.navy_slideshow) {

            /*if(appUser.getType().equalsIgnoreCase("Admin"))
                admin_allBatch(null);
            else toast(this,"You are not an Admin!");*

                } else if (id == R.id.navy_tools) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Confirm Exit")
                            .setMessage("Are you sure you want to exit?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    finish();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();



                } else if (id == R.id.nav_share) {

                    //startActivity(new Intent(this,AboutView.class));


                } else if (id == R.id.navy_send) {
 *
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Confirm Sign Out")
                            .setMessage("Are you sure you want to SignOut?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    firebaseAuth.signOut();
                                    startActivity(new Intent(MainActivity.this, LoginPage.class));
                                    finish();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
*/
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
  //              R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
    //            R.id.nav_tools, R.id.nav_share, R.id.nav_send)
      //          .setDrawerLayout(drawer)
        //        .build();
       // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);






String[] states = {"All", "Andhra_Pradesh", "Arunachal_Pradesh", "Assam", "Bihar", "Delhi_NCR", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal_Pradesh", "Jammu_and_Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya_Pradesh",
                "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Rajasthan", "Punjab", "Sikkim", "Tamil_Nadu", "Telangana", "Tripura", "Uttar_Pradesh", "Uttarakhand", "West_Bengal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner, states);
        stateSpinner.setAdapter(adapter);


        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                showCities(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });












        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                perform();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }













    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }






















    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
        || super.onSupportNavigateUp();
    }












    public void perform(){

        String cityT=citySpinner.getSelectedItem().toString();
        final LinearLayout t = findViewById(R.id.projectsList);
        t.removeAllViews();
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        firestore.collection("Projects").whereArrayContains("cities",cityT).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

                        like.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(!like.isSelected()) {
                                    like.setImageResource(R.drawable.ic_thumb_up_select_24dp);
                                    like.setSelected(true);
                                    //liker(firebaseAuth.getCurrentUser().getDisplayName(),document.get("project_id").toString());

                                    if (dislike.isSelected()) {
                                        dislike.setImageResource(R.drawable.ic_thumb_down_not_select);
                                        dislike.setSelected(false);
                                        likerundisliker(firebaseAuth.getCurrentUser().getDisplayName(),document.get("project_id").toString());
                                    }
                                    else
                                        liker(firebaseAuth.getCurrentUser().getDisplayName(),document.get("project_id").toString());
                                }
                                else
                                {
                                    like.setImageResource(R.drawable.ic_thumb_up_black_24dp);
                                    like.setSelected(false);
                                    unliker(firebaseAuth.getCurrentUser().getDisplayName(),document.get("project_id").toString());
                                }
                            }
                        });

                        dislike.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(!dislike.isSelected()) {
                                    dislike.setImageResource(R.drawable.ic_thumb_down_select_24dp);
                                    dislike.setSelected(true);

                                    if (like.isSelected()) {
                                        like.setImageResource(R.drawable.ic_thumb_up_black_24dp);
                                        like.setSelected(false);
                                        dislikerunliker(firebaseAuth.getCurrentUser().getDisplayName(),document.get("project_id").toString());
                                    }
                                    else
                                        disliker(firebaseAuth.getCurrentUser().getDisplayName(),document.get("project_id").toString());
                                }
                                else
                                {
                                    dislike.setImageResource(R.drawable.ic_thumb_down_not_select);
                                    dislike.setSelected(false);
                                    undisliker(firebaseAuth.getCurrentUser().getDisplayName(),document.get("project_id").toString());
                                }
                        }});

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
                                    Toast.makeText(MainActivity.this, "Enter Comment First", Toast.LENGTH_SHORT).show();
                                }

                                addComment(document.get("project_id"),cmnt);
                                cmntTxt.setText("");

                            }
                        });

                        pic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(MainActivity.this, ProjectHome.class);
                                i.putExtra("project_id", document.get("project_id").toString());
                                startActivity(i);
                            }
                        });

                        /*if(document.contains("pics")) {
                            Map<String, String> pics = (Map<String, String>) document.get("pics");
                            List<String> picKeys = new ArrayList<>(pics.keySet());

                            String path = picKeys.get(0);
                            String filename = pics.get(path);
                            read(pic, filename);

                        }
*/
                        read(pic,document.get("project_id")+"001");

                        t.addView(cd);
                    }
                    Button button=new Button(MainActivity.this);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            firebaseAuth.signOut();
                            startActivity(new Intent(MainActivity.this, LoginPage.class));
                            finish();
                        }
                    });
                    t.addView(button);
                }

                else{
                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

























    private void addComment(Object projectID, String cmnt) {
        Map<String,Object> text=new HashMap<>();
        text.put(firebaseAuth.getCurrentUser().getDisplayName(),cmnt);
        firestore.document("Projects/"+projectID+"/Comments/"+getTodayDate()).set(text, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(MainActivity.this, "Commented ;)", Toast.LENGTH_SHORT).show();}
                        else
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }














    public void showCities(View v){
        final String state=stateSpinner.getSelectedItem().toString();
        if(state.equals("All"))
        {
            List<String> cities=new ArrayList<>();
            cities.add(0,"All");
            //String[] items = new String[]{"None", "NandGram", "Rajnagar Extension", "Vasundhara", "Shastri nagar"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.spinner, cities);
            citySpinner.setAdapter(adapter);

            //Toast.makeText(this, "Select State", Toast.LENGTH_SHORT).show();
            return;
        }
        firestore.document("India/"+state).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Map m=task.getResult().getData();
                    List<String> cities= (List<String>) m.get("cities");
                    cities.add(0,"All");
                    //String[] items = new String[]{"None", "NandGram", "Rajnagar Extension", "Vasundhara", "Shastri nagar"};
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.spinner, cities);
                    citySpinner.setAdapter(adapter);
                    perform();
                }
                else
                {
                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
/*
    private Bitmap loadImageFromStorage(ImageView img,String path,String filename) {

        try {
            File f = new File(path );
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            img.setImageBitmap(b);
*/
/*
            img = findViewById(R.id.dipi);
            img = new ImageView(this);
            NavigationView navigationView = findViewById(R.id.nav_view);
            View hView =  navigationView.getHeaderView(0);
            ImageView imageView = hView.findViewById(R.id.dipi);
            imageView.setImageBitmap(b);

            img.setImageBitmap(b);
*//*

System.out.println("Local se hua READ " );

            return b;

        } catch (Exception e) {
            System.out.println("Local not read bcoz " + e.getMessage());

            return read(img,null,filename);
        }

    }
*/




















    Bitmap bit;
    public  void read(final ImageView dp,final String filename) {
        //if (path == null) {
            final long OO = 1024 * 1024;
            storageRef.child(filename).getBytes(OO).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
                public void onSuccess(byte[] bytes) {
                    bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    dp.setImageBitmap(bit);
            System.out.println("Online se hua READ " );

                    //firestore.document("faculty/" + FirebaseAuth.getInstance().getCurrentUser().getEmail()).update("img_path", saveToInternalStorage(bit,filename));

                }
            });


        //}
        //return loadImageFromStorage(dp,path,filename);
    }


/*
    private String saveToInternalStorage(Bitmap bitmapImage,String name) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        directory.mkdir();
        // Create imageDir
        File mypath = new File(directory, name);

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

*/











    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navy_home) {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            // Handle the camera action
        } else if (id == R.id.navy_gallery) {

            /*if(appUser.getType().equalsIgnoreCase("Admin"))
                startActivity(new Intent(this,FacultyList.class));

            else toast(this,"You are not an Admin!");*/
            //facultyView(null);

        } else if (id == R.id.navy_slideshow) {

            /*if(appUser.getType().equalsIgnoreCase("Admin"))
                admin_allBatch(null);
            else toast(this,"You are not an Admin!");*/

        } else if (id == R.id.navy_tools) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Exit")
                    .setMessage("Are you sure you want to exit?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            finish();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();



        } else if (id == R.id.navy_share) {

            //startActivity(new Intent(this,AboutView.class));


        } else if (id == R.id.navy_send) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Sign Out")
                    .setMessage("Are you sure you want to SignOut?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Confirm Sign Out")
                                    .setMessage("Pakka bahar jana hai????\nSoch lo...")
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            firebaseAuth.signOut();
                                            startActivity(new Intent(MainActivity.this, LoginPage.class));
                                            finish();
                                        }})
                                    .setNegativeButton(android.R.string.no, null).show();
                       }})
                    .setNegativeButton(android.R.string.no, null).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Toast.makeText(this, "YYYYaaaahooooo", Toast.LENGTH_SHORT).show();
        return true;
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
            //////////----------------------image picker----------------------------


            public void imagepicker (View view){

                GalIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);

            }
            Uri uri;


            @Override
            protected void onActivityResult ( int requestCode, int resultCode, Intent data){
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

                            bit = bitmap;
                            ImageView imageView = findViewById(R.id.imageView);

                            imageView.setImageBitmap(bitmap);
                            writeImage(bit);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    } else System.out.println("Yahan bhi gadbad hai");
                } else if (requestCode == 1) {

                    if (data != null) {

                        Bundle bundle = data.getExtras();

                        Bitmap bitmap = null;
                        try {
                            bitmap = (Bitmap) bundle.getParcelable(String.valueOf(MediaStore.Images.Media.getBitmap(getContentResolver(), uri)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ImageView imageView = findViewById(R.id.imageView);

                        imageView.setImageBitmap(bitmap);
                        writeImage(bit);

                    } else System.out.println("Yahan hai dikkat badi wali");
                }
            }


            //------------------------------write image -------------------------------


            public void writeImage (Bitmap bit){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bit.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] datas = baos.toByteArray();
                storageRef.child("fileName").putBytes(datas).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //toast(MainActivity.this,"Photo Updated!");
                    }
                });
                //read(null,null,null);
            }


            public void ImageCropFunction () {

       /* // Image Crop Code
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
        }*/

            }















    public void liker(String user, final String pid){
        
        firestore.document("Projects/"+pid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Map m=task.getResult().getData();
                    int likes= Integer.parseInt(m.get("likes").toString());
                    likes++;
                    m.put("likes",likes);
                    firestore.document("Projects/"+pid).set(m,SetOptions.merge());
                }
            }
        });

    }
    public void unliker(String user,final  String pid){
        firestore.document("Projects/"+pid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Map m=task.getResult().getData();
                    int likes= Integer.parseInt(m.get("likes").toString());

                    if(Integer.parseInt(m.get("likes").toString())>0){
                    likes--;}
                    m.put("likes",likes);
                    firestore.document("Projects/"+pid).set(m,SetOptions.merge());
                }
            }
        });

    }
    public void disliker(String user, final String pid){
        firestore.document("Projects/"+pid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Map m=task.getResult().getData();
                    int likes= Integer.parseInt(m.get("dislikes").toString());
                    likes++;
                    m.put("dislikes",likes);
                    firestore.document("Projects/"+pid).set(m,SetOptions.merge());
                }
            }
        });

    }
    public void undisliker(String user, final String pid){
        firestore.document("Projects/"+pid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Map m=task.getResult().getData();
                    int likes= Integer.parseInt(m.get("dislikes").toString());
                    likes--;
                    m.put("dislikes",likes);
                    firestore.document("Projects/"+pid).set(m,SetOptions.merge());
                }
            }
        });

    }

    public void likerundisliker(String user, final String pid){
        firestore.document("Projects/"+pid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Map m=task.getResult().getData();
                    int likes= Integer.parseInt(m.get("likes").toString());
                    likes++;
                    int dislikes= Integer.parseInt(m.get("dislikes").toString());
                    dislikes--;
                    m.put("likes",likes);
                    m.put("dislikes",dislikes);
                    firestore.document("Projects/"+pid).set(m,SetOptions.merge());
                }
            }
        });

    }

    public void dislikerunliker(String user, final String pid){
        firestore.document("Projects/"+pid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Map m=task.getResult().getData();
                    int likes= Integer.parseInt(m.get("likes").toString());
                    likes--;
                    int dislikes= Integer.parseInt(m.get("dislikes").toString());
                    dislikes++;
                    m.put("likes",likes);
                    m.put("dislikes",dislikes);
                    firestore.document("Projects/"+pid).set(m,SetOptions.merge());
                }
            }
        });

    }
    public void bhr_nikl(View view) {
        firebaseAuth.signOut();
        finish();
    }
}
