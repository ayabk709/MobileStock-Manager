package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {


    private RecyclerView recy;
    myAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        recy=(RecyclerView)findViewById(R.id.recyclerView2);
        recy.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Product>options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product"), Product.class)
                        .build();
        adapter=new myAdapter(options);
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

    public void move2(View view) {
            startActivity(new Intent(ProductDetails.this,add_Product.class));
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
       FirebaseRecyclerOptions<Product>options =
               new FirebaseRecyclerOptions.Builder<Product>()
                       .setQuery(FirebaseDatabase.getInstance().getReference().child("Product").orderByChild("name").startAt(str).endAt(str+"~"), Product.class)
                       .build();
      adapter = new myAdapter(options);
      adapter.startListening();
      recy.setAdapter(adapter);
   }
}