package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Enter extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        context = this;
    }
    public void onEnter(View view) {
        Intent intent = new Intent(context, Login_Activity.class);
        startActivity(intent);
    }
}