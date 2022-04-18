package com.example.canteenN.clgprjt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceAdmin extends AppCompatActivity {

    Button additem,vieworder,viewqr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_admin);
        additem=(Button)findViewById(R.id.add_item);
        vieworder=(Button)findViewById(R.id.view_order);
        viewqr =(Button)findViewById(R.id.view_order2);

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent newPostIntent = new Intent(ChoiceAdmin.this, NewPostActivity.class);
                    startActivity(newPostIntent);

            }
        });


        vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newPostIntent1 = new Intent(ChoiceAdmin.this, ShowOrder.class);
                startActivity(newPostIntent1);

            }
        });

        viewqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewqr = new Intent(ChoiceAdmin.this ,ViewQr.class);
                startActivity(viewqr);
            }
        });

    }


}
