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

public class custumor_details extends AppCompatActivity {
    private RecyclerView recy1;
    myAdapter3 adapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custumor_details);
        recy1=(RecyclerView)findViewById(R.id.recyclerViewcusty);
        recy1.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<custumor> options =
                new FirebaseRecyclerOptions.Builder<custumor>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("custumor"), custumor.class)
                        .build();
        adapter3=new myAdapter3(options);
        recy1.setAdapter(adapter3);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter3.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter3.stopListening();
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
        FirebaseRecyclerOptions<custumor>options =
                new FirebaseRecyclerOptions.Builder<custumor>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("custumor").orderByChild("name").startAt(str).endAt(str+"~"), custumor.class)
                        .build();

        adapter3 = new myAdapter3(options);

        adapter3.startListening();
        recy1.setAdapter(adapter3);
    }

    public void addcustumor(View view) {
        startActivity(new Intent(custumor_details.this,add_Customer.class));
    }
}