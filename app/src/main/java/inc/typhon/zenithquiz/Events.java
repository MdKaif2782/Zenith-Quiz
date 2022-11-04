package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class Events extends AppCompatActivity {
    private FirebaseFirestore db;
    private Context context;
    private ArrayList<String> events;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String name;
    private TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        db = FirebaseFirestore.getInstance();
        context = this;
        events = new ArrayList<>();
        listView = findViewById(R.id.list_events);
        nameView = findViewById(R.id.display_name);
        setLoginName();
        adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,events);
        listView.setAdapter(adapter);
        getEvents();
        refresh();
    }

    public void getEvents(){
        assert db != null;
        db.collection("Events").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    events.add(document.getId());
                    Log.d("uwu", document.getId() + " => " );
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void refresh(){
        adapter.notifyDataSetChanged();
    }

    public void setLoginName(){
        String email = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
        db.collection("participants").document(email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                name = task.getResult().get("name").toString();
                nameView.setText("Logged in as: "+name);
            } else {
                Toast.makeText(context, "Error getting name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}