package com.hackathon.pragati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

import static com.hackathon.pragati.SplashScreen.firebaseAuth;
import static com.hackathon.pragati.SplashScreen.firestore;

public class LoginPage extends AppCompatActivity {

    String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();


    }

    public void verifyNumber(View view){



        String name=((EditText)(findViewById(R.id.inp_name))).getText().toString().trim();
        String phone=((EditText)(findViewById(R.id.inp_phone))).getText().toString().trim();
        if(name.equals("")){
            Toast.makeText(this, "Enter Name first!", Toast.LENGTH_SHORT).show();return;}
        if(phone.length()!=10){
            Toast.makeText(this, "Invalid Phone number", Toast.LENGTH_SHORT).show();return;}
        phone="+91"+phone;
        ((TextView)(findViewById(R.id.OTP_sent_to ))).setText("OTP sent to "+phone);

        CardView cv = findViewById(R.id.carDet);
        CardView cv2 = findViewById(R.id.cardOTP);
        Button bt=findViewById(R.id.button);
        Button bt2=findViewById(R.id.button2);
        cv.setVisibility(View.GONE);
        cv2.setVisibility(View.VISIBLE);
        bt.setVisibility(View.GONE);
        bt2.setVisibility(View.GONE);

        sendOTP(name,phone);




    }

    public void sendOTP(String name,final String phoneNumber){
        final ProgressDialog pb=new ProgressDialog(this);
        pb.setMessage("Sending OTP...");
        Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();

        //pb.show();



        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();

                String smsCode=credential.getSmsCode();
                firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber,smsCode);
                ((EditText)findViewById(R.id.editText3)).setText(smsCode);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Toast.makeText(LoginPage.this, e.toString(), Toast.LENGTH_SHORT).show();
                System.out.println(e.toString());
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
                //pb.dismiss();
                System.out.println("Verification ID:" + verificationId);

                mVerificationId=verificationId;
                pb.setMessage("Detecting OTP");
            }
        };


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }


    public void confirmRegistration(View view) {

        Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG).show();
/*

        String code=((EditText)findViewById(R.id.editText3)).getText().toString();
        if(code.equals("")){
            Toast.makeText(this, "OTP daalo pehle...", Toast.LENGTH_SHORT).show();
            return;
        }
        PhoneAuthCredential credential;
        try {
            credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        }
        catch (Exception e){
            System.out.println(e.getMessage()+"            kjlhkjhb ------------------------------");
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            credential=null;
        }
        if(credential==null)
        {
            Toast.makeText(this, "Unable to verify...\nPlease try later!", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            String name=((EditText)(findViewById(R.id.inp_name ))).getText().toString().trim();
                            String phone="+91"+((EditText)(findViewById(R.id.inp_phone ))).getText().toString().trim();


                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();
                            firebaseAuth.getCurrentUser().updateProfile(profileUpdates);
                            Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
*/
/*                            Map<String, Object> map = new HashMap<>();
                            map.put("name", name);
                            map.put("phone", phone);
                            map.put("type","user");

                            firestore.document("Users/"+phone).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(LoginPage.this, "User Successfully Created", Toast.LENGTH_SHORT).show();
                                }
                            });
*//*




                            System.out.println("Login Successful: " + firebaseAuth.getCurrentUser().getPhoneNumber());

                            Intent i = new Intent(LoginPage.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }

                    }
                });

*/
    }

    public void resend(View v){
        CardView cv = findViewById(R.id.carDet);
        CardView cv2 = findViewById(R.id.cardOTP);
        cv.setVisibility(View.GONE);
        cv2.setVisibility(View.VISIBLE);

    }

    public void adminLogin(View v){
        Intent i = new Intent(LoginPage.this, AdminLogin.class);
        startActivity(i);
        finish();

    }

    public void anonymousLogin(View v){
        firebaseAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(LoginPage.this, UserHome.class);
                    startActivity(i);
                    finish();

                }
                else Toast.makeText(LoginPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
