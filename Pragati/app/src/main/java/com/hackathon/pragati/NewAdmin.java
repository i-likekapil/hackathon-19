package com.hackathon.pragati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;

public class NewAdmin extends AppCompatActivity {
   TextInputEditText name;
    TextInputEditText email,password,organisation,position;
    RadioButton a,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_admin);
        email=findViewById(R.id.input_email);
        password=findViewById(R.id.input_password);
        name=findViewById(R.id.input_name);
        organisation=findViewById(R.id.input_org);
        position=findViewById(R.id.input_pos);
        a=findViewById(R.id.radioButton);
        c=findViewById(R.id.radioButton2);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.setChecked(true);
                c.setChecked(false);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.setChecked(true);
                a.setChecked(false);
            }
        });
    }
    String lol="Admins";

    public void createAdmin(View v){
        final String mail=email.getText().toString(),
        pass=password.getText().toString(),
        nam=name.getText().toString(),
        org=organisation.getText().toString(),
        pos=position.getText().toString(),
        type=a.isChecked()?"admin":"worker";
        if(!(mail.contains("@")&&mail.contains(".")))
        {
            Toast.makeText(this, "Invalid Email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.length()<8)
        {
            Toast.makeText(this, "Invalid Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(nam.equals(""))
        {
            Toast.makeText(this, "Enter name first!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(org.equals(""))
        {
            Toast.makeText(this, "Enter Organisation name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pos.equals(""))
        {
            Toast.makeText(this, "Enter Position!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!a.isChecked()&&!c.isChecked())
        {
            Toast.makeText(this, "Select account type!", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Creating Account...");
        pd.show();

        firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Map<String,Object> admin=new HashMap<>();
                    admin.put("name",nam);
                    admin.put("email",mail);
                    admin.put("password",pass);
                    admin.put("org",org);
                    admin.put("pos",pos);
                    admin.put("type",type);
                    admin.put("verified","no");

                    firestore.document("Admins/"+mail).set(admin, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nam).build();
                                firebaseAuth.getCurrentUser().updateProfile(profileUpdates);

                                Toast.makeText(NewAdmin.this, "Registration Succesful!\nVerification request sent to admin.", Toast.LENGTH_LONG).show();

                                if(!type.equals("admin"))
                                    lol="Constructors";

                                firestore.document("Admins/list"+lol).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            Map m=task.getResult().getData();
                                            List<String> admins= (List<String>) m.get("unVerified");
                                            if(!admins.contains(mail))
                                            {
                                                admins.add(mail);
                                                m.put("unVerified",admins);
                                                firestore.document("Admins/list"+lol).set(m,SetOptions.merge());
                                                Intent i;
                                                pd.dismiss();

                                                    i = new Intent(NewAdmin.this, SplashScreen.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        }
                                    }
                                });

                            }
                            else
                            {
                                Toast.makeText(NewAdmin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else
                    Toast.makeText(NewAdmin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void adminLogin(View v){
        Intent i = new Intent(NewAdmin.this, AdminLogin.class);
        startActivity(i);
        finish();

    }
}
