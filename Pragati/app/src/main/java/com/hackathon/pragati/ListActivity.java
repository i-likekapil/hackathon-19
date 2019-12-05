package com.hackathon.pragati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;
import static com.hackathon.pragati.SplashScreen.storageRef;



public class ListActivity extends AppCompatActivity {

    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        pd=new ProgressDialog(this);
        pd.setTitle("Please wait.");
        pd.setMessage("Fetching lol details...");
        pd.show();

        type=getIntent().getStringExtra("type");
        t=findViewById(R.id.allFacScroll);
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        firestore.collection("Admins").whereEqualTo("type",type).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        t.removeAllViews();

                        if(task.isSuccessful()){
                            List<DocumentSnapshot> lis=task.getResult().getDocuments();

                            for(DocumentSnapshot l:lis)
                            {
                                addCard(l);
                            }


                        }
                    }
                });
    }



    static CardView cardViews;String type;
    LinearLayout t;
    LayoutInflater inflater;

    public void addCard(DocumentSnapshot doc){





            cardViews = (CardView) inflater.inflate(R.layout.allfaculties, t, false);

            TextView tv;// = (TextView) cardViews[i].getChildAt(0);
            //tv.setText(allFacID.get(i).toString());

            //cardViews.setContentPadding(2,2,2,2);



            ConstraintLayout c1=(ConstraintLayout)cardViews.getChildAt(0);
            tv=(TextView)c1.getChildAt(1);
            tv.setText(doc.get("name").toString());

            tv = (TextView) c1.getChildAt(2);
            tv.setText(doc.get("email").toString());

/*            CardView cardView=(CardView)c1.getChildAt(0);
            ImageView imageView =(ImageView)cardView.getChildAt(0);
            String k[]=allFacName.get(i).toString().split(" ");


            Bitmap bb=read(imageView,getPath(),k[0]+"_"+k[1]+".png",allFacID.get(i).toString());
            if(bb!=null)
                imageView.setImageBitmap(bb);
*/



            //cardViews[i].setVisibility(View.VISIBLE);

            t.addView(cardViews);


            pd.dismiss();


        /*
        CardView c = (CardView) inflater.inflate(R.layout.allfaculties, t, false);
        c.setVisibility(View.INVISIBLE);
        t.addView(c);
*/

    }

  /*  private Bitmap loadImageFromStorage(ImageView imageView,String path,String fileName,String username) {

        try {
            File f = new File(path, fileName);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));


            System.out.println("Local se hua READ " );

            return b;

        } catch (Exception e) {
            System.out.println("Local not read bcoz " + e.getMessage());

            return read(imageView,null,fileName,username);
        }

    }

    Bitmap bit;
    public Bitmap read(final ImageView imageView, String path, final String fileName, String username) {



        if (path == null) {
            final long OO = 1024 * 1024;
            storageRef.child(username).getBytes(OO).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    imageView.setImageBitmap(bit);

                    System.out.println("Online se hua READ " );

                    firestore.document("faculty/" + FirebaseAuth.getInstance().getCurrentUser().getEmail()).update("img_path", saveToInternalStorage(bit,fileName));

                }

            });

            return bit;


        }
        return loadImageFromStorage(imageView,path,fileName,username);
    }

    private String saveToInternalStorage(Bitmap bitmapImage,String fileName) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        directory.mkdir();
        // Create imageDir
        File mypath = new File(directory, fileName);

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
    private String getPath() {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        directory.mkdir();
        // Create imageDir
        return directory.getAbsolutePath();
    }



    public void viewFaculty(View view){
        CardView cv = (CardView) view;
        ConstraintLayout c2=(ConstraintLayout)cv.getChildAt(0);
        TextView id = (TextView) c2.getChildAt(2);
        String uid = id.getText().toString().trim();

        id=(TextView)c2.getChildAt(1);
        String name=id.getText().toString().trim();


        System.out.println("UserID:" +uid);

        Intent in = new Intent(this, FacultyView.class);
        in.putExtra("UserID", uid);
        in.putExtra("Name",name);
        startActivity(in);


    }

    public void simonGoBack(View v){
        finish();
    }*/
}

