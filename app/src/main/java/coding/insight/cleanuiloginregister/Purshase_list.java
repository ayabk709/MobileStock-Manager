package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

public class Purshase_list extends AppCompatActivity {
    private RecyclerView recy;
    DatabaseReference ref;
    purshaseAdapter adapter;
    Button btn;
    Context context;
    FirebaseAuth mAuth;
    FirebaseUser user;
    Button uplaod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purshase_list);

        ref = FirebaseDatabase.getInstance().getReference().child("purshasyyy");
        recy = (RecyclerView) findViewById(R.id.recyclerViewpursh);
        recy.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<purshasy> options =
                new FirebaseRecyclerOptions.Builder<purshasy>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("purshasyyy"), purshasy.class)
                        .build();
        adapter= new purshaseAdapter(options,context);
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
        startActivity(new Intent(Purshase_list.this,MainActivity.class));
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
        FirebaseRecyclerOptions<purshasy>options =
                new FirebaseRecyclerOptions.Builder<purshasy>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("purshasyyy").orderByChild("name").startAt(str).endAt(str+"~"), purshasy.class)
                        .build();
        adapter = new purshaseAdapter(options,context);
        adapter.startListening();
        recy.setAdapter(adapter);
    }


    }


