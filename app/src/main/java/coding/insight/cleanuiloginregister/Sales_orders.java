package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sales_orders extends AppCompatActivity {
  /*sales_adapter adapter;
    private ListView recy;
    DatabaseReference database;
    ArrayList<sales>list;
    */
  private RecyclerView recy;
    DatabaseReference ref;
    sales_adapter adapter;
    Button btn;
    Context context;
    FirebaseAuth mAuth;
    FirebaseUser user;
    Button uplaod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_orders);
        recy =  findViewById(R.id.recyclerViewsales);
        ref = FirebaseDatabase.getInstance().getReference().child("sales");
        recy.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<sales> options =
                new FirebaseRecyclerOptions.Builder<sales>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("sales"), sales.class)
                        .build();
        adapter= new sales_adapter(options,context);
        recy.setAdapter(adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void moby(View view) {
        startActivity(new Intent(Sales_orders.this,Sales_entry.class));}
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.search,menu);
            MenuItem item=menu.findItem(R.id.search);
            SearchView searchView =(SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    txtSearch(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query ) {
                    txtSearch(query);
                    return false;
                }
            });
            return super.onCreateOptionsMenu(menu);
        }
        public  void txtSearch(String str){
            FirebaseRecyclerOptions<sales>options =
                    new FirebaseRecyclerOptions.Builder<sales>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("sales").orderByChild("nameprd").startAt(str).endAt(str+"~"), sales.class)
                            .build();
            adapter = new sales_adapter(options,context);
            adapter.startListening();
            recy.setAdapter(adapter);
        }

    }






       /* list=new ArrayList<>();
database=FirebaseDatabase.getInstance().getReference("sales");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
for (DataSnapshot dataSnapshot : snapshot.getChildren()){
    sales S = dataSnapshot.getValue(sales.class);
    list.add(S);
}
adapter=new sales_adapter(Sales_orders.this,list);
recy.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
/*private void show(String id,String name){
    AlertDialog.Builder mDialog=new AlertDialog.Builder(this);
    LayoutInflater inflater=getLayoutInflater();
    View mDialogView=inflater.inflate(R.layout.update,null);
    mDialog.setView(mDialogView);
    Spinner spn=mDialogView.findViewById(R.id.purch1);
    Spinner spn2=mDialogView.findViewById(R.id.purch2);
    EditText edit1=mDialogView.findViewById(R.id.purch3);
    EditText edit2=mDialogView.findViewById(R.id.purch4);
    EditText edit3=mDialogView.findViewById(R.id.purch5);
   Spinner spn3=mDialogView.findViewById(R.id.purch6);
    Button btn= mDialogView.findViewById(R.id.editbutton12);
   mDialog.setTitle("hi");
   mDialog.show();
*/




   /* public void add(View view) {
        startActivity(new Intent(Sales_orders.this,Sales_entry.class));
    }
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView =(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query ) {
                txtSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    public  void txtSearch(String str){
        FirebaseRecyclerOptions<sales> options =
                new FirebaseRecyclerOptions.Builder<sales>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("sales"), sales.class)
                        .build();
        adapter = new sales_adapter(options);
        adapter.startListening();
        recy.setAdapter(adapter);
    }*/

