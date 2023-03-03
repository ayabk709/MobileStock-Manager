package coding.insight.cleanuiloginregister;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class adapto extends RecyclerView.ViewHolder implements View.OnClickListener {
TextView prod,quantity,date,price,vend,type;

    Button btnedit2;
    Button btnrmv2;
private final Context context;
public NewsItemClickListener listener;

    public adapto(@NonNull View itemView) {
        super(itemView);
        context =itemView.getContext();
      prod=itemView.findViewById(R.id.purchprod);
        date = itemView.findViewById(R.id.purchdate);
       type = itemView.findViewById(R.id.purchtype);
       quantity = itemView.findViewById(R.id.purchquanity);
        price = itemView.findViewById(R.id.purchprice);
       vend = itemView.findViewById(R.id.purshvendor);
        btnedit2 = itemView.findViewById(R.id.editpursh);
        btnrmv2 = itemView.findViewById(R.id.removepursh);

    }
public void setItemClickListener(NewsItemClickListener listener){
        this.listener=listener;
}
    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
