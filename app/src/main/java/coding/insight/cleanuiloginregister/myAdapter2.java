package coding.insight.cleanuiloginregister;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

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

public class myAdapter2 extends FirebaseRecyclerAdapter<vendor,myAdapter2.myviewholder>{



    public myAdapter2(@NonNull FirebaseRecyclerOptions<vendor> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull vendor ho) {
        holder.name.setText(ho.getName());
        holder. company.setText(ho.getCompany());
        holder.adress.setText(ho.getAdress());
        holder. mobile.setText(ho.getMobile());
        holder.email.setText(ho.getEmail());

        holder.btnedit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.name.getContext()).setContentHolder(new ViewHolder(R.layout.update_vendor)).setExpanded(true,1200).create();
                View view=dialogPlus.getHolderView();
                EditText name2=view.findViewById(R.id.namevend);
                EditText company=view.findViewById(R.id.companyvend);
                EditText adress=view.findViewById(R.id.adressvend);
                EditText mobil=view.findViewById(R.id.mobilvend);
                EditText email=view.findViewById(R.id.emailvend);
                Button btnupdate=view.findViewById(R.id.editbutton);
                name2.setText(ho.getName());
                company.setText(ho.getCompany());
                adress.setText(ho.getAdress());
                mobil.setText(ho.getMobile());
                email.setText(ho.getEmail());
                dialogPlus.show();

                btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name", name2.getText().toString());
                        map.put("color", company.getText().toString());
                        map.put("category", adress.getText().toString());
                        map.put("price", mobil.getText().toString());
                        map.put("quantity", email.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("vendor").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                holder.btnrmv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                        builder.setTitle("are you sure");
                        builder.setMessage("Deleted data can't be undo");
                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("vendor").child(getRef(position).getKey()).removeValue();
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView name,company,adress,mobile,email;
       CircleImageView image;
        Button btnedit2;
        Button btnrmv2;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            btnedit2=itemView.findViewById(R.id.edit);
            btnrmv2=itemView.findViewById(R.id.remove);
            name=itemView.findViewById(R.id.txtname);
            company=itemView.findViewById(R.id.txtcompany);
            adress=itemView.findViewById(R.id.txtadress);
            mobile=itemView.findViewById(R.id.txtmobile);
            email=itemView.findViewById(R.id.txtemail);


        }
}}
