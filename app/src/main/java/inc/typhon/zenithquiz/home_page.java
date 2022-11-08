package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

public class home_page extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
   Button quiz, web, blog, message;

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
        quiz.setWidth(quiz.getHeight());
        web.setWidth(web.getHeight());
        blog.setWidth(blog.getHeight());
        message.setWidth(message.getHeight());




    }
}