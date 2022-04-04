package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.PrivilegedAction;

public class MainActivity extends AppCompatActivity {
     TextView mView;
     EditText mUsername;
     EditText mPassword;
     EditText mEmail;
     Button mRegister;
     private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mView = findViewById(R.id.textView1);
        mUsername = findViewById(R.id.username);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mRegister = findViewById(R.id.register);




        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user1 = mUsername.getText().toString();
                String email1 = mEmail.getText().toString();
                String password1 = mPassword.getText().toString();

              if (TextUtils.isEmpty(user1) || TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1)){
                  Toast.makeText(MainActivity.this, "sorry all credetials are supposed to be filled", Toast.LENGTH_SHORT).show();
              }else if(password1.length()<6){
                  Toast.makeText(MainActivity.this, "password is too short", Toast.LENGTH_SHORT).show();
              }else {

                  mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()){

                              Toast.makeText(MainActivity.this, "the authentication is successful", Toast.LENGTH_SHORT ).show();
                          }else {
                              Toast.makeText(MainActivity.this, "the authentication failed", Toast.LENGTH_SHORT ).show();

                          }
                      }
                  });
              }

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Toast.makeText(MainActivity.this, "user entered successfully "+ currentUser, Toast.LENGTH_SHORT).show();
    }

}