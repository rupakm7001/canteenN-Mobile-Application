package com.example.canteenN.clgprjt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class OrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerAdapter.ViewHolder> {

    public List<Orders> orders_list;
    public Context context;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public OrdersRecyclerAdapter(List<Orders> orders_list){
        this.orders_list=orders_list;
    }

    @Override
    public OrdersRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_orders_layout,parent,false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull OrdersRecyclerAdapter.ViewHolder holder, final int position) {

        holder.setIsRecyclable(false);


        final String orderPostId = orders_list.get(position).getId();
        final String currentUserId = firebaseAuth.getCurrentUser().getUid();


        String desc_data=orders_list.get(position).getDesc();
        holder.setDescText(desc_data);

        String price_data=orders_list.get(position).getPrice();
        holder.setPriceText(price_data);

        String quantity_data=orders_list.get(position).getQuantity();
        holder.setQuantityText(quantity_data);

        String email_data=orders_list.get(position).getEmail();
        holder.setEmailText(email_data);

        String classnum =orders_list.get(position).getClassNumber();
        holder.setClassnumberText(classnum);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,orderPostId ,Toast.LENGTH_LONG).show();
                firebaseFirestore.collection("Order").document(orderPostId).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
    }



    @Override
    public int getItemCount() {
        return orders_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView descView,priceView,quantityView,emailView,classnumberView;

        private View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setQuantityText(String quantityText){
            quantityView=mView.findViewById(R.id.quantity);
            quantityView.setText(quantityText);
        }

        public void setEmailText(String emailText){
            emailView=mView.findViewById(R.id.email);
            emailView.setText(emailText);
        }

        public void setDescText(String descText){

            descView=mView.findViewById(R.id.desc);
            descView.setText(descText);
        }

        public void setPriceText(String priceText){
            priceView=mView.findViewById(R.id.price);
            priceView.setText(priceText);
        }

        public void setClassnumberText(String classnumbeText){
            classnumberView=mView.findViewById(R.id.classnumber1);
            classnumberView.setText(classnumbeText);
        }

    }
}
