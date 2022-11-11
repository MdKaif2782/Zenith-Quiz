package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

public class Sign_up extends AppCompatActivity {
    private EditText email,password,confirmPassword,name;
    private RadioButton male,female;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        context = this;
        email = findViewById(R.id.sign_up_email_input);
    }
}