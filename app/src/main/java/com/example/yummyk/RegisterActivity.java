package com.example.yummyk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button createAcntBtn;
    private EditText inputName, inputPhone, inputPwd;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAcntBtn = (Button) findViewById(R.id.register_btn);
        inputName = (EditText) findViewById(R.id.reg_username_input);
        inputPhone = (EditText) findViewById(R.id.reg_phone_input);
        inputPwd = (EditText) findViewById(R.id.reg_password_input);
        loadingBar = new ProgressDialog(this);

        createAcntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String name = inputName.getText().toString();
        String phone = inputPhone.getText().toString();
        String password = inputPwd.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please enter name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please enter phone", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait while we are checking your credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber(name, phone, password);
        }
    }

    private void ValidatephoneNumber(final String name, final String phone, final String password) {
       final DatabaseReference RootRef;
       RootRef = FirebaseDatabase.getInstance().getReference();

       RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(!(snapshot.child("Users").child(phone).exists())){
                   HashMap<String,Object> userDataMap = new HashMap<>();
                   userDataMap.put("phone",phone);
                   userDataMap.put("username", name);
                   userDataMap.put("password", password);

                   RootRef.child("Users").child(phone).updateChildren(userDataMap)
                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){
                                       Toast.makeText(RegisterActivity.this, "Congratulations your Yummy Account has been created!!!", Toast.LENGTH_SHORT).show();
                                       loadingBar.dismiss();

                                       Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                       startActivity(intent);
                                   }
                                   else{
                                       loadingBar.dismiss();
                                       Toast.makeText(RegisterActivity.this, "Network error occured: Please retry.", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
               }
               else{
                   Toast.makeText(RegisterActivity.this, "The "+phone+ " already exists", Toast.LENGTH_SHORT).show();
                   loadingBar.dismiss();
                   Toast.makeText(RegisterActivity.this, "Please try using another phone number", Toast.LENGTH_SHORT).show();

                   Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                   startActivity(intent);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }
}