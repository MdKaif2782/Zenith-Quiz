package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Sign_in extends AppCompatActivity {
    Context context ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        context = this;
    }
    public void onSignInClick(View view) {
        Intent intent = new Intent(context, home_page.class);
        startActivity(intent);
    }
}