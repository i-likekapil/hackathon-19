package com.hackathon.pragati;

import androidx.annotation.NonNull;
import  androidx.appcompat.app.AppCompatActivity;
import static com.hackathon.pragati.SplashScreen.firestore;
import static com.hackathon.pragati.SplashScreen.storageRef;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewProject extends AppCompatActivity {

    EditText prname,prid,area,strt,end,web,typ,budgetu,budgeta,shortd,longd,head;
    List<String> selected_cities;
    Spinner stateSpinner,citySpinner,statusSpinner;
    private Intent GalIntent;
    private Intent CropIntent;
    List<Bitmap> imagesToUpload;
    Map<String,String> pics;
    LinearLayout photosL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_project);
        prname=findViewById(R.id.projectName);
        prid=findViewById(R.id.projectID);
        head=findViewById(R.id.projectHead);
        imagesToUpload=new ArrayList<>();
        pics=new HashMap<>();
        area=findViewById(R.id.Area);
        strt=findViewById(R.id.start);
        end=findViewById(R.id.end);
        web=findViewById(R.id.website);
        typ=findViewById(R.id.type);
        stateSpinner=findViewById(R.id.spinner);
        statusSpinner=findViewById(R.id.spinner3);
        citySpinner=findViewById(R.id.spinner2);
        photosL=findViewById(R.id.photosL);
        budgeta=findViewById(R.id.projectBudgetUsed);
        budgetu=findViewById(R.id.projectBudget);
        shortd=findViewById(R.id.projectDesShort);
        longd=findViewById(R.id.projectDesLong);
        

        String[] states = {"Select State","Andhra_Pradesh", "Arunachal_Pradesh", "Assam", "Bihar","Delhi_NCR", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal_Pradesh", "Jammu_and_Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya_Pradesh",
                "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Rajasthan", "Punjab", "Sikkim", "Tamil_Nadu", "Telangana", "Tripura", "Uttar_Pradesh", "Uttarakhand", "West_Bengal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner, states);
        stateSpinner.setAdapter(adapter);

        selected_cities=new ArrayList<>();


        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                showCities(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] state={"Select Status","ongoing","upcoming","completed"};
        ArrayAdapter<String> adapte = new ArrayAdapter<>(this, R.layout.spinner, state);
        adapte.setDropDownViewResource(R.layout.spinner);
        statusSpinner.setGravity(Gravity.CENTER);
        statusSpinner.setAdapter(adapte);

        selected_cities=new ArrayList<>();

    }

    public void put(View v){
        String pname=prname.getText().toString();
        String pid=prid.getText().toString();
        String stat=statusSpinner.getSelectedItem().toString();
        final String hd=head.getText().toString();
        String are=area.getText().toString();
        String en=end.getText().toString();
        String we=web.getText().toString();
        String ty=typ.getText().toString();
        String bugu=budgetu.getText().toString();
        String buga=budgeta.getText().toString();
        String shd=shortd.getText().toString();
        String lgd=longd.getText().toString();
        String str=strt.getText().toString();

if(pname.equals("")||pname==null)
        {
            Toast.makeText(this, "Enter Project Name", Toast.LENGTH_SHORT).show();
            return;
        }

if(pid.equals("")||pid==null)
        {
            Toast.makeText(this, "Enter Project Id", Toast.LENGTH_SHORT).show();
            return;
        }
        if(hd.equals("")||hd==null)
        {
            Toast.makeText(this, "Enter Project Head", Toast.LENGTH_SHORT).show();
            return;
        }

if(are.equals("")||are==null)
        {
            Toast.makeText(this, "Enter Project Area", Toast.LENGTH_SHORT).show();
            return;
        }
if(en.equals("")||en==null)
        {
            Toast.makeText(this, "Enter Project End date", Toast.LENGTH_SHORT).show();
            return;
        }
if(we.equals("")||we==null)
        {
            Toast.makeText(this, "Enter Project Website", Toast.LENGTH_SHORT).show();
            return;
        }

if(bugu.equals("")||bugu==null)
        {
            Toast.makeText(this, "Enter Project Budget Used", Toast.LENGTH_SHORT).show();
            return;
        }
if(buga.equals("")||buga==null)
        {
            Toast.makeText(this, "Enter Project Budget Allocated", Toast.LENGTH_SHORT).show();
            return;
        }

if(shd.equals("")||shd==null)
        {
            Toast.makeText(this, "Enter Short description", Toast.LENGTH_SHORT).show();
            return;
        }
        if(str.equals("")||str==null)
        {
            Toast.makeText(this, "Enter Project Start date", Toast.LENGTH_SHORT).show();
            return;
        }

        if(statusSpinner.getSelectedItem().toString().equals("Select Status"))
        {
            Toast.makeText(this, "Enter Project Status", Toast.LENGTH_SHORT).show();
            return;
        }
        if(stateSpinner.getSelectedItem().toString().equals("Select State"))
        {
            Toast.makeText(this, "Select State", Toast.LENGTH_SHORT).show();
            return;
        }
        if(citySpinner==null||citySpinner.getSelectedItem().toString().equals("Select City"))
        {
            Toast.makeText(this, "Select City", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Uploading Data");
        pd.show();
        final Map<String,Object> project=new HashMap<>();
        selected_cities.add(0,"All");
        selected_cities.add(citySpinner.getSelectedItem().toString());
        project.put("project_name",pname);
        project.put("project_id",pid);
        project.put("cities",selected_cities);
        project.put("status",stat);
        project.put("project_head",hd);
        project.put("area",are);
        project.put("end_date",en);
        project.put("start_date",str);
        project.put("website",we);
        project.put("type",ty);
        project.put("budget_used",bugu);
        project.put("budget_allocated",buga);
        project.put("short_description",shd);
        project.put("long_description",lgd);



/*for(Bitmap img:imagesToUpload) {
        }*/



        firestore.document("Projects/"+pid).set(project, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful())
        {
            Toast.makeText(AddNewProject.this, "Upload Succesfull!", Toast.LENGTH_SHORT).show();

            //pd.setIcon();



            prname.setText("");
            prid.setText("");
            prname.setText("");
            area.setText("");
            end.setText("");
            web.setText("");
            typ.setText("");
            budgeta.setText("");
            budgetu.setText("");
            shortd.setText("");
            longd.setText("");
            strt.setText("");
            head.setText("");
            statusSpinner.setSelection(0);





            pd.dismiss();
        }
        else
            Toast.makeText(AddNewProject.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
    }
});
        if(!imagesToUpload.isEmpty())
        writeImage(imagesToUpload.get(0),pid+"001");

    }

    public void showCities(View v){
        final String state=stateSpinner.getSelectedItem().toString();
        if(state.equals("Select State"))
        {
            //Toast.makeText(this, "Select State", Toast.LENGTH_SHORT).show();
            return;
        }
        firestore.document("India/"+state).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Map m=task.getResult().getData();
                    List<String> cities= (List<String>) m.get("cities");
                    cities.add(0,"Select City");
                    //String[] items = new String[]{"None", "NandGram", "Rajnagar Extension", "Vasundhara", "Shastri nagar"};
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AddNewProject.this, R.layout.spinner, cities);
                    citySpinner.setAdapter(adapter);

                }
                else
                {
                    Toast.makeText(AddNewProject.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void kill(View v){
        finish();
    }

    public void add_cities(View v){

        String city=citySpinner.getSelectedItem().toString();
        if(city.equals("Select City"))
        {
            Toast.makeText(this, "Select City!!", Toast.LENGTH_SHORT).show();
            return;
        }
        selected_cities.add(city);
        TextView tv=findViewById(R.id.cities);
        tv.setText(tv.getText().toString()+city+" , ");
    }

    public void imagepicker(View view){

        GalIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);

    }
    Uri uri;Bitmap bit;

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
                    ImageView imageView=new ImageView(this);
                    imageView.setImageBitmap(bitmap);
                    imageView.setMaxWidth(100);
                    photosL.addView(imageView);
                    imagesToUpload.add(bitmap);
                    //writeImage(bit);
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

                ImageView imageView=new ImageView(this);
                imageView.setImageBitmap(bitmap);
                photosL.addView(imageView);

                //writeImage(bit);

            }

            else System.out.println("Yahan hai dikkat badi wali");
        }
    }
int i=0;
    public void writeImage(Bitmap bit,String name){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bit.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] datas = baos.toByteArray();
            System.out.println("WrIte here//////////////////////////////////////////////" + i++);


            storageRef.child(name).putBytes(datas).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    LinearLayout imgtt=findViewById(R.id.photosL);
                    ImageView k= (ImageView) imgtt.getChildAt(0);
                    imgtt.removeAllViews();
                    imgtt.addView(k);
                    Toast.makeText(AddNewProject.this, "Photo Added", Toast.LENGTH_SHORT).show();//(MainActivity.this,"Photo Updated!");
                }
            });
        }
        catch (Exception e){
        Toast.makeText(this, "Exception     "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //read(null,null,null);
    }
    public void ImageCropFunction() {
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
            //startActivityForResult(CropIntent, 1);
            System.out.println("ACTIvity shuru");

        } catch (Exception e) {
            System.out.println("Crop exception "+e.getMessage());
        }


    }
    }
