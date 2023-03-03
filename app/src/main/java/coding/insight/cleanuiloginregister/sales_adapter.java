package coding.insight.cleanuiloginregister;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class sales_adapter extends FirebaseRecyclerAdapter<sales,sales_adapter.myviewholder> {
    Context context;

    public sales_adapter(@NonNull FirebaseRecyclerOptions<sales> options, Context context) {
        super(options);
        this.context = context;
    }




    public sales_adapter(@NonNull FirebaseRecyclerOptions<sales> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull sales model) {
        holder.cust.setText(model.getNamecust());
        holder.prd.setText(model.getNameprd());
        holder.price.setText(model.getPrice());
        holder.date.setText(model.getDate());
        holder.type.setText(model.getType());
        holder.quantity.setText(model.getQuantity());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item5, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        TextView cust, prd, date, type, quantity, price;
        Button btnedit2;
        Button btnrmv2;
        DatabaseReference ref;
        DatabaseReference ref2;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            cust = itemView.findViewById(R.id.salescust);
            prd = itemView.findViewById(R.id.salesprod);
            date = itemView.findViewById(R.id.salesdate);
            type = itemView.findViewById(R.id.salestype);
            quantity = itemView.findViewById(R.id.salesquanity);
            price = itemView.findViewById(R.id.salesprice);
            btnedit2 = itemView.findViewById(R.id.editsales);
            btnrmv2 = itemView.findViewById(R.id.removesales);
        }
    }
}
 /*   public sales_adapter(@NonNull FirebaseRecyclerOptions<sales> options) {
        super(options);
    }
    public sales_adapter(FirebaseRecyclerOptions<sales> options, android.content.Context context) {
        super(options);
        this.context=context;
    }
    @Override
    protected void onBindViewHolder(@NonNull mviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull sales model) {
        holder.cust.setText(model.getNamecust());
        holder.prd.setText(model.getNameprd());
        holder.price.setText(model.getPrice());
        holder.date.setText(model.getDate());
        holder.type.setText(model.getType());
        holder.quantity.setText(model.getQuantity());
        holder.btnedit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.cust.getContext()).setContentHolder(new ViewHolder(R.layout.update_sales)).setExpanded(true, 1200).create();
                View view = dialogPlus.getHolderView();
                Spinner name2 = view.findViewById(R.id.podnm);
               /* EditText custumor = view.findViewById(R.id.cusnm);
                EditText Date = view.findViewById(R.id.Date2);
                EditText quantity = view.findViewById(R.id.quantity2);
                EditText price = view.findViewById(R.id.price2);
                EditText type = view.findViewById(R.id.payment);*/
                //Button btnupdate = view.findViewById(R.id.btnupdate);
              //  name2.setOnClickListener(model.getNameprd());
               /* custumor.setText(model.getNamecust());
                Date.setText(model.getDate());
                quantity.setText(model.getQuantity());
                price.setText(model.getPrice());
                type.setText(model.getType());
                dialogPlus.show();
                btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("nameprd", name2.getSelectedItem().toString());
                        /*map.put("namecust", custumor.getText().toString());
                        map.put("date", Date.getText().toString());
                        map.put("quantity", quantity.getText().toString());
                        map.put("price",  price.getText().toString());
                        map.put("type", type.getText().toString());
                        FirebaseDatabase.getInstance().getReference()
                                .child("sales").child(getRef(position).getKey())
                                .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.cust.getContext(), "Data uploaded successfully", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.cust.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });
            }
        });

       holder.btnrmv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.cust.getContext());
                builder.setTitle("are you sure");
                builder.setMessage("Deleted data can't be undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(holder.t1.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                        FirebaseDatabase.getInstance().getReference().child("sales").child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.cust.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

            }

    @NonNull
    @Override
    public mviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item5, parent, false);
        return new mviewholder(view);
    }

    class mviewholder extends RecyclerView.ViewHolder {

        TextView cust, prd, date, type, quantity, price;
        Button btnedit2;
        Button btnrmv2;
        DatabaseReference ref;
        DatabaseReference ref2;
        DatabaseReference ref3;

        public mviewholder(@NonNull View itemView) {
            super(itemView);
            cust = itemView.findViewById(R.id.salescust);
            prd = itemView.findViewById(R.id.salesprod);
            date = itemView.findViewById(R.id.salesdate);
            type = itemView.findViewById(R.id.salestype);
            quantity = itemView.findViewById(R.id.salesquanity);
            price = itemView.findViewById(R.id.salesprice);
            btnedit2 = itemView.findViewById(R.id.editsales);
            btnrmv2 = itemView.findViewById(R.id.removesales);
            ref3 = FirebaseDatabase.getInstance().getReference();
            ref = FirebaseDatabase.getInstance().getReference();
            ref2 = FirebaseDatabase.getInstance().getReference().child("sales");
        }

    }
}
*/
/*
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class sales_adapter extends ArrayAdapter {


    private Activity mContext;
    List<sales> S;

    public sales_adapter(Activity mContext, List<sales> S) {
        super(mContext, R.layout.item5, S);
        this.mContext = mContext;
        this.S = S;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.item5, null, true);
        TextView tvname = listItemView.findViewById(R.id.prdsales);
        TextView tvcust = listItemView.findViewById(R.id.custsales);
        TextView tvquant = listItemView.findViewById(R.id.quantitysaless);
        TextView tvprice = listItemView.findViewById(R.id.pricesales);
        TextView tvdate = listItemView.findViewById(R.id.datesales);
        TextView tvtype = listItemView.findViewById(R.id.typesales);

        sales S1 = S.get(position);
        tvname.setText(S1.getNameprd());
        tvcust.setText(S1.getNamecust());
        tvquant.setText(S1.getQuantity());
        tvprice.setText(S1.getPrice());
        tvdate.setText(S1.getDate());
        tvtype.setText(S1.getType());
return listItemView;
    }}
/*
    Context context;
ArrayList<sales>list;

    public sales_adapter(Context context, ArrayList<sales> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item5, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
sales s=list.get(position);
holder.prd.setText(s.getNameprd());
holder.cust.setText(s.getNamecust());
holder.price.setText(s.getPrice());
holder.quantity.setText(s.getQuantity());
holder.type.setText(s.getType());
holder.date.setText(s.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder{
TextView prd,cust,price,date,quantity ,type;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            prd=itemView.findViewById(R.id.salesprod);
            cust=itemView.findViewById(R.id.salescustumor);
            price=itemView.findViewById(R.id.salesprice);
            date=itemView.findViewById(R.id.salesdate);
            quantity=itemView.findViewById(R.id.salesquanity);
            type=itemView.findViewById(R.id.salestype);
        }*/


