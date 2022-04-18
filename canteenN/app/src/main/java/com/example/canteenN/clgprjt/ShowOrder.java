package com.example.canteenN.clgprjt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowOrder extends AppCompatActivity {

    private RecyclerView order_list_view;
    private List<Orders> orders_list;

    private FirebaseFirestore firebaseFirestore;

    private OrdersRecyclerAdapter ordersRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);


        firebaseFirestore=FirebaseFirestore.getInstance();


        orders_list=new ArrayList<>();
        order_list_view=(RecyclerView)findViewById(R.id.showorderlist);
        ordersRecyclerAdapter=new OrdersRecyclerAdapter(orders_list);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        order_list_view.setLayoutManager(linearLayoutManager);
        order_list_view.setAdapter(ordersRecyclerAdapter);
        order_list_view.setHasFixedSize(true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);



        firebaseFirestore.collection("Order").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                for(DocumentChange doc :documentSnapshots.getDocumentChanges()){

                    if(doc.getType()== DocumentChange.Type.ADDED){

                        Orders orders=doc.getDocument().toObject(Orders.class);
                        orders_list.add(orders);
                        ordersRecyclerAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }
}
