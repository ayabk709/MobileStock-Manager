package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Vendordetails extends AppCompatActivity {
    private RecyclerView recy;
    myAdapter2 adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendordetails);
        recy=(RecyclerView)findViewById(R.id.recyclerViewcust);
        recy.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<vendor> options =
                new FirebaseRecyclerOptions.Builder<vendor>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("vendor"), vendor.class)
                        .build();
       adapter1 = new myAdapter2(options);
        recy.setAdapter(adapter1);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
    }

    public void move(View view) {
        startActivity(new Intent(Vendordetails.this,addVendor.class));
    }

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
        FirebaseRecyclerOptions<vendor>options =
                new FirebaseRecyclerOptions.Builder<vendor>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("vendor").orderByChild("name").startAt(str).endAt(str+"~"), vendor.class)
                        .build();

        adapter1 = new myAdapter2(options);

        adapter1.startListening();
        recy.setAdapter(adapter1);
    }



}