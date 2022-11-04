package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Sign_up_activity extends AppCompatActivity {
    private FirebaseFirestore db;
    private EditText name,email,institute,claas;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = this;
        db = FirebaseFirestore.getInstance();
        name = findViewById(R.id.username_input);
        email = findViewById(R.id.email_input);
        institute = findViewById(R.id.institute_input);
        claas = findViewById(R.id.class_input);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(context,Events.class));
            finish();
        }
    }

    public void onSignIn(View view) {
        if (isValidEmail(email.getText().toString())) {

            DocumentReference docRef = db.collection("participants").document(email.getText().toString());
            docRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        Participant participant = new Participant(name.getText().toString(), email.getText().toString(), institute.getText().toString(), claas.getText().toString());
                        db.collection("participants").document(email.getText().toString()).set(participant);
                        Toast.makeText(context, "User created", Toast.LENGTH_SHORT).show();
                        createAccount(view);
                    }
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show();
        }
    }

    public void createAccount(View view) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), "aaeeiioo").addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(), "aaeeiioo");
                Toast.makeText(context, "Signed in", Toast.LENGTH_SHORT).show();
                switchToEvents(view);
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                Log.d("uwu", Objects.requireNonNull(task.getException()).toString());
            }
        });

    }

    public boolean isValidEmail(String email){
        Boolean isAValidEmail = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        return isAValidEmail;
    }

    public void switchToEvents(View view) {
        Intent intent = new Intent(context, Events.class);
        startActivity(intent);
        finish();
    }

    private class AsyncTaskExample extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... strings) {

            return null;
        }
        @Override
        protected void onPostExecute(String link) {
            super.onPostExecute(link);

        }
    }
}

