package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login_Activity extends AppCompatActivity {
    Context context ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
    }
    public void onSignInOptionClick(View view) {
        Intent intent = new Intent(context, Sign_in.class);
        startActivity(intent);

    }
    public void onSignUpOptionClick(View view) {
        Intent intent = new Intent(context, Sign_up.class);
        startActivity(intent);

    }
}