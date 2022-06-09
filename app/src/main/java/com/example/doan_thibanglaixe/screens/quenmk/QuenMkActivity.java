package com.example.doan_thibanglaixe.screens.quenmk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan_thibanglaixe.R;
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
import android.os.Handler;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class QuenMkActivity extends AppCompatActivity {

    private Button btn_xacnhan;
    private EditText edt_sdt;
    private String user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mk);
        setControl();
        setEvent();

    }

    private void setControl() {
        btn_xacnhan = findViewById(R.id.btn_forgotsdt);
        edt_sdt = findViewById(R.id.forgot_Sdt);
        user = getIntent().getStringExtra("user").toString();

    }

    private boolean checkData() {
        String regexPhone = "0+\\d{9}";
        if (edt_sdt.getText().equals("")) {
            Toast.makeText(this, "Không được để trống số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!edt_sdt.getText().toString().matches(regexPhone)) {
            return false;
        }
        return true;
    }

    private void setEvent() {
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                if (checkData()) {
                    String getPhone = edt_sdt.getText().toString().trim();
                    String phone = "+84" + edt_sdt.getText().toString().substring(1, getPhone.length());

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onClickVerify(phone);
                        }
                    }, 3000);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Số điện thoại không khớp với bất kỳ tài khoản nào", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });
    }

    private void onClickVerify(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(getApplicationContext(), "Lỗi xác thực", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verifycationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifycationId, forceResendingToken);
                                Toast.makeText(getApplicationContext(), "Đã gửi OTP", Toast.LENGTH_SHORT).show();
                                goToVerifyActivity(phone, verifycationId);
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
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
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(getApplicationContext(), "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goToVerifyActivity(String phone, String verifycationId) {
        Intent intent = new Intent(this, VerifyActivity.class);
        intent.putExtra("phone_number", phone);
        intent.putExtra("verifycationId", verifycationId);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}