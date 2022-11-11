package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import inc.typhon.zenithquiz.Particapnt_info.AccountHolder;

public class Sign_up extends AppCompatActivity {
    private EditText email,password,confirmPassword,name;
    private RadioButton male,female;
    private Context context;
    private AccountHolder accountHolder;
    private FirebaseAuth firebaseAuth;
    private SharedPreferences sharedPreferences;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        accountHolder = new AccountHolder();
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        context = this;
        email = findViewById(R.id.sign_up_email_input);
        password = findViewById(R.id.sign_up_password_input);
        confirmPassword = findViewById(R.id.sign_up_confirm_password_input);
        name = findViewById(R.id.name_input_sign_up);
        male = findViewById(R.id.male_radio_button);
        female = findViewById(R.id.female_radio_button);
    }


    public void onSignUpFinal(View view){
        if ((male.isChecked() || female.isChecked()) &&
            name.getText().toString().length() > 5 &&
            email.getText().toString().length() > 5 &&
            password.getText().toString().length() > 5 &&
            confirmPassword.getText().toString().length() > 5 &&
            password.getText().toString().equals(confirmPassword.getText().toString()) &&
            password.getText().toString().length() > 5 &&
            email.getText().toString().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
        ) {
            accountHolder.setName(name.getText().toString());
            accountHolder.setEmail(email.getText().toString());
            accountHolder.setPassword(password.getText().toString());
            accountHolder.setMale(male.isChecked());
            firebaseAuth.createUserWithEmailAndPassword(accountHolder.getEmail(),accountHolder.getPassword())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("name",accountHolder.getName());
                            editor.putString("email",accountHolder.getEmail());
                            editor.putString("password",accountHolder.getPassword());
                            editor.putBoolean("isMale",accountHolder.getMale());
                            editor.apply();
                            firebaseFirestore
                                    .collection("users")
                                            .document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid())
                                            .set(accountHolder);

                            Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, home_page.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(context, "Account already exists with this email", Toast.LENGTH_SHORT).show();
                        }
                    });


        } else {
            if (name.getText().toString().length() == 0){
                Toast.makeText(context, "Name is required", Toast.LENGTH_SHORT).show();
            }
            if (email.getText().toString().length() == 0){
                Toast.makeText(context, "Email is required", Toast.LENGTH_SHORT).show();
            }
            if (password.getText().toString().length() == 0){
                Toast.makeText(context, "Password is required", Toast.LENGTH_SHORT).show();
            }
            if (confirmPassword.getText().toString().length() == 0){
                Toast.makeText(context, "Confirm Password is required", Toast.LENGTH_SHORT).show();
            }
            if (!password.getText().toString().equals(confirmPassword.getText().toString())){
                Toast.makeText(context, "Password and Confirm Password must be same", Toast.LENGTH_SHORT).show();
            }
            if (!email.getText().toString().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
                Toast.makeText(context, "Email is not valid", Toast.LENGTH_SHORT).show();
            }
            if (password.getText().toString().length() < 6){
                Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            }
            if (name.getText().toString().length() < 6){
                Toast.makeText(context, "Name must be at least 6 characters", Toast.LENGTH_SHORT).show();
            }
            if (email.getText().toString().length() < 6){
                Toast.makeText(context, "Email is too short", Toast.LENGTH_SHORT).show();
            }
            if (!male.isChecked() || !female.isChecked()){
                Toast.makeText(context,"Choose a gender",Toast.LENGTH_SHORT).show();
            }
        }
    }
}