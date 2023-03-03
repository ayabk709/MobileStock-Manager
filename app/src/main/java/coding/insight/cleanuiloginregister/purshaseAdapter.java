package coding.insight.cleanuiloginregister;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class purshaseAdapter extends FirebaseRecyclerAdapter<purshasy,purshaseAdapter.mviewholder>{
    private android.content.Context context;

    public purshaseAdapter(@NonNull FirebaseRecyclerOptions<purshasy> options) {
        super(options);


    }

    public purshaseAdapter(FirebaseRecyclerOptions<purshasy> options, android.content.Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull mviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull purshasy model) {
        holder.name.setText(model.getNamevend());
        holder.prd.setText(model.getNameprd());
        holder.price.setText(model.getPrice());
        holder.date.setText(model.getDate());
        holder.type.setText(model.getType());
        holder.quantity.setText(model.getQuantity());
      holder.btnedit2.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   Intent myIntent = new Intent(v.getContext(),updatepurshase.class);

                                                   v.getContext().startActivity(myIntent);

                                               }
                                           });

       /*older.btnedit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext()).setContentHolder(new ViewHolder(R.layout.update))
                        .setExpanded(true, 1200)
                         .create();

                View view = dialogPlus.getHolderView();
                Spinner spn= view.findViewById(R.id.purch1);
                dialogPlus.show();


                /*adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spn.setAdapter(adapter);


                /*

                AutoCompleteTextView produit = (AutoCompleteTextView) view.findViewById(R.id.purch1);
                AutoCompleteTextView vendor = (AutoCompleteTextView) view.findViewById(R.id.purch2);
                TextInputEditText quantity = (TextInputEditText) view.findViewById(R.id.purch3);
                TextInputEditText Date = (TextInputEditText) view.findViewById(R.id.purch4);
                TextInputEditText price = (TextInputEditText) view.findViewById(R.id.purch5);
                AutoCompleteTextView type = (AutoCompleteTextView) view.findViewById(R.id.purch6);
               /* Button btnupdate = view.findViewById(R.id.button12);
                produit.setText(model.getNameprd());
                vendor.setText(model.getNamevend());
                quantity.setText(model.getQuantity());
                Date.setText(model.getDate());
                price.setText(model.getPrice());
                type.setText(model.getType());
                dialogPlus.show();






                /*btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("nameprd", produit.getText().toString());
                        map.put("namevend", vendor.getText().toString());
                        map.put("quantity", quantity.getText().toString());
                        map.put("Date", Date.getText().toString());
                        map.put("price", price.getText().toString());
                        map.put("Type", type.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("purshasyyy").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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


*/


              /*  holder.btnrmv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                        builder.setTitle("are you sure");
                        builder.setMessage("Deleted data can't be undo");
                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("purshasyyy").child(getRef(position).getKey()).removeValue();
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
        });*/

    }

            @NonNull
            @Override
            public mviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item4, parent, false);
                return new mviewholder(view);
            }

            class mviewholder extends RecyclerView.ViewHolder {

                TextView name, prd, date, type, quantity, price;
                Button btnedit2;
                Button btnrmv2;
                DatabaseReference ref;
                DatabaseReference ref2;
                DatabaseReference ref3;

                public mviewholder(@NonNull View itemView) {
                    super(itemView);
                    name = itemView.findViewById(R.id.purshvendor);
                    prd = itemView.findViewById(R.id.purchprod);
                    date = itemView.findViewById(R.id.purchdate);
                    type = itemView.findViewById(R.id.purchtype);
                    quantity = itemView.findViewById(R.id.purchquanity);
                    price = itemView.findViewById(R.id.purchprice);
                    btnedit2 = itemView.findViewById(R.id.editpursh);
                    btnrmv2 = itemView.findViewById(R.id.removepursh);
                    ref3 = FirebaseDatabase.getInstance().getReference();
                    ref = FirebaseDatabase.getInstance().getReference();
                    ref2 = FirebaseDatabase.getInstance().getReference().child("purshasyyy");
                }

            }


        }