package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

public class home_page extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
   Button quiz, web, blog, message;
   LinearLayout linearLayout;
   ImageView user_image;
   int SCREEN_HEIGHT, SCREEN_WIDTH;
   TextView user_name;
   SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        lottieAnimationView.setAnimation(R.raw.guy_on_laptop);
        lottieAnimationView.playAnimation();
        quiz = findViewById(R.id.quiz_button);
        web = findViewById(R.id.websites_button);
        blog = findViewById(R.id.blog_button);
        message = findViewById(R.id.message_button);
        linearLayout= findViewById(R.id.linearLayout2);
        user_image = findViewById(R.id.user_image);
        user_name = findViewById(R.id.user_name_in_home_page);


        SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;
        SCREEN_WIDTH = getResources().getDisplayMetrics().widthPixels;
        quiz.setHeight((int) (SCREEN_HEIGHT/6.2));
        web.setHeight((int) (SCREEN_HEIGHT/6.2));
        blog.setHeight((int) (SCREEN_HEIGHT/6.2));
        message.setHeight((int) (SCREEN_HEIGHT/6.2));
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        //use glide to set image from url circularly
        Glide.with(this)
                .load(R.drawable.user_image)
                .circleCrop()
                .into(user_image);

        user_name.setText(sharedPreferences.getString("name",""));



    }
}