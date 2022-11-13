package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Sign_in extends AppCompatActivity {
    Context context ;
    EditText email,password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        context = this;
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void onSignInClick(View view) {
       firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(task -> {
           if (task.isSuccessful()){
               startActivity(new Intent(context,home_page.class));
               finish();
           }
       });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
    public void uploadImage(){
        AsyncTask.execute(() -> {
            
        });
    }
}