package coding.insight.cleanuiloginregister;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class orderadapter extends ArrayAdapter {
    private Activity mContext;
    List<sales> S;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      LayoutInflater inflater = mContext.getLayoutInflater();
      View listItemView = inflater.inflate(R.layout.itemoorder,null,true);
        TextView tvname = listItemView.findViewById(R.id.nameid);
        TextView tvcust = listItemView.findViewById(R.id.custid);
        TextView tvquant = listItemView.findViewById(R.id.quantityid);
        TextView tvprice = listItemView.findViewById(R.id.priceid);
        TextView tvdate = listItemView.findViewById(R.id.dateid);
        TextView tvtype = listItemView.findViewById(R.id.typeid);
        sales S1 = S.get(position);
        tvname.setText(S1.getNameprd());
        tvcust.setText(S1.getNamecust());
        tvquant.setText(S1.getQuantity());
        tvprice.setText(S1.getPrice());
        tvdate.setText(S1.getDate());
        tvtype.setText(S1.getType());
        return listItemView;
    }

    public orderadapter(Activity mContext, List<sales> s) {
        super(mContext,R.layout.itemoorder,s);

        this.mContext = mContext;
        this.S = s;
    }
}


