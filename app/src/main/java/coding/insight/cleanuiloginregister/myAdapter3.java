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

public class myAdapter3 extends FirebaseRecyclerAdapter <custumor,myAdapter3.myviewholder>{

    public myAdapter3(@NonNull FirebaseRecyclerOptions<custumor> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull custumor model) {
        holder.t1.setText(model.getName());
        holder. t2.setText(model.getCompany());
        holder.t3.setText(model.getAdress());
        holder. t4.setText(model.getMobile());
        holder.t5.setText(model.getEmail());
        holder.btnedit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.t1.getContext()).setContentHolder(new ViewHolder(R.layout.update_ustumor)).setExpanded(true,1200).create();
                View view=dialogPlus.getHolderView();
                EditText name2=view.findViewById(R.id.t1);
                EditText company=view.findViewById(R.id.t2);
                EditText adress=view.findViewById(R.id.t3);
                EditText mobil=view.findViewById(R.id.t4);
                EditText email=view.findViewById(R.id.t5);
                Button btnupdate=view.findViewById(R.id.editbuttoncust);
                name2.setText(model.getName());
                company.setText(model.getCompany());
                adress.setText(model.getAdress());
                mobil.setText(model.getMobile());
                email.setText(model.getEmail());
                dialogPlus.show();
                btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object>map=new HashMap<>();
                        map.put("name", name2.getText().toString());
                        map.put("company",  company.getText().toString());
                        map.put("adress", adress.getText().toString());
                        map.put("mobile", mobil.getText().toString());
                        map.put("email",  email.getText().toString());
                        FirebaseDatabase.getInstance().getReference()
                                .child("custumor").child(getRef(position).getKey())
                                .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.t1.getContext(), "Data uploaded successfully", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.t1.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });
                holder.btnrmv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.t1.getContext());
                        builder.setTitle("are you sure");
                        builder.setMessage("Deleted data can't be undo");
                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Toast.makeText(holder.t1.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                                FirebaseDatabase.getInstance().getReference().child("custumor").child(getRef(position).getKey()).removeValue();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(holder.t1.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item3,parent,false);
   return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
TextView t1,t2,t3,t4,t5;
        Button btnedit3;
        Button btnrmv3;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            btnedit3=itemView.findViewById(R.id.editcust);
            btnrmv3=itemView.findViewById(R.id.removecust);
            t1=itemView.findViewById(R.id.custname);
            t2=itemView.findViewById(R.id.custcompany);
            t3=itemView.findViewById(R.id.custadress);
            t4=itemView.findViewById(R.id.custmobile);
            t5=itemView.findViewById(R.id.custemail);
        }
    }
}
