package com.example.canteenN.clgprjt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminLogin extends AppCompatActivity {

    EditText email,password;
    Button adminlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        email=(EditText)findViewById(R.id.reg_email);
        password=(EditText)findViewById(R.id.reg_confirm_pass);
        adminlogin=(Button)findViewById(R.id.login_btn);

        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             login();
            }
        });
    }

    public void login(){
        String adminemail=email.getText().toString().trim();
        String adminpass=password.getText().toString().trim();
        if(adminemail.equals("abcd") && adminpass.equals("12345")){
            Intent intentchoice=new Intent(AdminLogin.this,ChoiceAdmin.class);
            startActivity(intentchoice);

        }
    }
}
