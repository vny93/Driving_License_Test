package com.example.doan_thibanglaixe.screens.quenmk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.screens.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {

    EditText digit1;
    Button button;
    String mPhoneNumber, mVerifycationId,user;
    PhoneAuthProvider.ForceResendingToken mForceResendingToken;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        setControl();
        setEvent();
    }
    private void setEvent() {
        digit1.requestFocus();
        mAuth = FirebaseAuth.getInstance();
        getDataIntent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verifyCode = digit1.getText().toString().trim();
                onClickSendOtpCode(verifyCode);
            }
        });
    }

    private void setControl() {
        digit1 = findViewById(R.id.edt_code);
        button = findViewById(R.id.btnotp);

    }
    private void gotoChangePasswordActivity(String phoneNumber) {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        intent.putExtra("phone_number", phoneNumber);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    private void getDataIntent() {
        mPhoneNumber = getIntent().getStringExtra("phone_number");
        mVerifycationId = getIntent().getStringExtra("verifycationId");
        user = getIntent().getStringExtra("user");
    }

    private void onClickSendOtpCode(String verifyCode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerifycationId, verifyCode);
        signInWithPhoneAuthCredential(credential);
    }

    public void onLoginClick(View view) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mPhoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)
                        .setForceResendingToken(mForceResendingToken)
                        // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerifyActivity.this, "Verify Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verifycationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifycationId, forceResendingToken);
                                mVerifycationId = verifycationId;
                                mForceResendingToken = forceResendingToken;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    public void backLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("TAG", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            gotoChangePasswordActivity(mPhoneNumber);

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(VerifyActivity.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                });
    }
}