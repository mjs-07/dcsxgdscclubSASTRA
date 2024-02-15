package com.example.upasthithi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.upasthithi.databinding.ActivitySignupBinding;

public class Signup extends AppCompatActivity {

    ActivitySignupBinding signupBinding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(signupBinding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        signupBinding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = signupBinding.Name.getText().toString();
                String email = signupBinding.email.getText().toString();
                String password = signupBinding.password.getText().toString();
                String confirm_password = signupBinding.confirmPassword.getText().toString();

                if (name.equals("") ||  email.equals("") || password.equals("") || confirm_password.equals("")) {
                    Toast.makeText(Signup.this, "All Fields are mandatory !!!", Toast.LENGTH_SHORT).show();
                }else {
                    if (password.equals(confirm_password)){
                        Boolean checkUsername = databaseHelper.checkName(name);
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);

                        if (checkUserEmail == false || checkUsername == false ){
                            Boolean insert = databaseHelper.insertData(name,email, password);

                            if (insert == true){
                                Toast.makeText(Signup.this, "SIGN-UP SUCCESSFUL !!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(Signup.this, "Sign-up failed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(Signup.this, "User already exist !!! \n Please Login ", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Signup.this, "Invalid Password !!! ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupBinding.loginredirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}