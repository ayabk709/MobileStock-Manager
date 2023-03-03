package coding.insight.cleanuiloginregister;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class myAdapter extends FirebaseRecyclerAdapter <Product,myAdapter.myviewholder>{


    public myAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull Product eh) {
        holder.name.setText(eh.getName());
        holder.color.setText(eh.getColor());
        holder.category.setText(eh.getCategory());
        holder.price.setText(eh.getPrice());
        holder.quantity.setText(eh.getQuantity());
        holder.codebarre.setText(eh.getNumber_code());

        Glide.with(holder.image.getContext()).load(eh.getImage()).into(holder.image);

 holder.btnedit.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
final DialogPlus dialogPlus=DialogPlus.newDialog(holder.image.getContext()).setContentHolder(new ViewHolder(R.layout.activity_update_product)).setExpanded(true,1200).create();
//dialogPlus.show();
         View view=dialogPlus.getHolderView();
         EditText name1=view.findViewById(R.id.nameedit);
         EditText URL=view.findViewById(R.id.urlimg);
         EditText color1=view.findViewById(R.id.coloredit);
         EditText category1=view.findViewById(R.id.categoryedit);
         EditText price1=view.findViewById(R.id.priceedit);
         EditText quantity1=view.findViewById(R.id.quantityedit);
         EditText codebarre1=view.findViewById(R.id.btncodeedit);
         Button btnupdate=view.findViewById(R.id.btnedit);
         name1.setText(eh.getName());
         color1.setText(eh.getColor());
         category1.setText(eh.getCategory());
         price1.setText(eh.getPrice());
         quantity1.setText(eh.getQuantity());
         codebarre1.setText(eh.getNumber_code());
         URL.setText(eh.getImage());
         Glide.with(holder.image2.getContext()).load(eh.getImage()).into(holder.image2);
         dialogPlus.show();

         btnupdate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Map<String,Object> map=new HashMap<>();
                 map.put("name",name1.getText().toString());
                 map.put("color",color1.getText().toString());
                 map.put("category",category1.getText().toString());
                 map.put("price",price1.getText().toString());
                 map.put("quantity",quantity1.getText().toString());
                 map.put("codebarre",codebarre1.getText().toString());
                 map.put("image",URL.getText().toString());
                 FirebaseDatabase.getInstance().getReference().child("Product").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void unused) {
                         Toast.makeText(holder.name.getContext(), "Data uploaded successfully", Toast.LENGTH_SHORT).show();
                   dialogPlus.dismiss();
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(holder.name.getContext(), "Error", Toast.LENGTH_SHORT).show();
                         dialogPlus.dismiss();
                     }
                 });


             }
         });

         holder.btnrmv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                 builder.setTitle("are you sure");
                 builder.setMessage("Deleted data can't be undo");
                 builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         FirebaseDatabase.getInstance().getReference().child("Product").child(getRef(position).getKey()).removeValue();
                     }
                 });
                 builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         Toast.makeText(holder.name.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                     }
                 });
                 builder.show();

             }
         });
     }
 });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView name,color,category,price,quantity,codebarre;
        CircleImageView image;
        CircleImageView image2;



        Button btnedit;
        Button btnrmv;
        Button btnscan;
        Uri imageUri1;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.img1);


            name=itemView.findViewById(R.id.prdname);
            color=itemView.findViewById(R.id.txtcolor);

            category=itemView.findViewById(R.id.txtCategory);
            price=itemView.findViewById(R.id.txtPrice);
            quantity=itemView.findViewById(R.id.txtQuantity);
            codebarre=itemView.findViewById(R.id.txtCodebarre);
            btnedit=itemView.findViewById(R.id.btnupdate);
            btnrmv=itemView.findViewById(R.id.btnremove);
            //btnscan=itemView.findViewById(R.id.btncscan);
        }
    }




}
