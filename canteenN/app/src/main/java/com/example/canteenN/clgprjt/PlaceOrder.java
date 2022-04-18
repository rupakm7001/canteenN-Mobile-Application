package com.example.canteenN.clgprjt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlaceOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String mPost_key=null;
    private String mDesc= null;
    private String mPrice=null;
    private FirebaseFirestore mDatabase;
    private TextView mOrderplacedesc,mOrderplaceprice,CardNumber,ExpiryDate,CvvNumber;
    private EditText mOrderplacequantity ;
    private FirebaseAuth firebaseAuth;
    Button PlaceOrder;
    Spinner spinner;
    String classnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseFirestore.getInstance();
        mOrderplacedesc=(TextView)findViewById(R.id.placeorderdesc);
        //CvvNumber=(TextView)findViewById(R.id.cvvnumber);
        mOrderplaceprice=(TextView)findViewById(R.id.placeorderprice);
        mOrderplacequantity=(EditText)findViewById(R.id.placeorderquantity);
        PlaceOrder=(Button)findViewById(R.id.placeorderbtn);
        //CardNumber=(TextView)findViewById(R.id.cardnumber);
        //ExpiryDate=(TextView)findViewById(R.id.expirydate);

        spinner =(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this ,R.array.number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);





        mPost_key=getIntent().getExtras().getString("blog_id");
        final String currentUserMailId = firebaseAuth.getCurrentUser().getEmail();
        mDesc=getIntent().getExtras().getString("desc");
        mPrice=getIntent().getExtras().getString("price");



        /*Toast.makeText(PlaceOrder.this,mPost_key,Toast.LENGTH_SHORT).show();*/
       /* Toast.makeText(PlaceOrder.this,currentUserId,Toast.LENGTH_SHORT).show();*/
        mOrderplacedesc.setText(mDesc);
        mOrderplaceprice.setText(mPrice);

        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mquantity = mOrderplacequantity.getText().toString();
                //final String cardnumber = CardNumber.getText().toString();
                //final String expirydate = ExpiryDate.getText().toString();
                //final String cvvnumber = CvvNumber.getText().toString();
                final String id = UUID.randomUUID().toString();

                if (!TextUtils.isEmpty(mquantity)) {
                    Map<String, Object> postMap = new HashMap<>();
                    postMap.put("desc", mDesc);
                    postMap.put("price", mPrice);
                    postMap.put("quantity", mquantity);
                    postMap.put("email", currentUserMailId);
                    postMap.put("id",id);
                    postMap.put("classNumber",classnumber);

                    mDatabase.collection("Order").document(id).set(postMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(PlaceOrder.this, "Order Placed Successful", Toast.LENGTH_LONG).show();
                                /*Toast.makeText(PlaceOrder.this, "Your Order Will Be Ready In 30 Minutes", Toast.LENGTH_LONG).show();*/
                                Intent mainIntent = new Intent(PlaceOrder.this, MainActivity.class);
                                startActivity(mainIntent);
                            }
                            else
                            {

                            }
                        }
                    });

                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        classnumber=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
