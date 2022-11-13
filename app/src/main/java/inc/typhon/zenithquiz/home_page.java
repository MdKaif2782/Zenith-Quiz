package inc.typhon.zenithquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;


import java.io.IOException;
import java.util.Objects;

import inc.typhon.zenithquiz.API.OkhttpImgBB;

public class home_page extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
   Button quiz, web, blog, message;
   LinearLayout linearLayout;
   ImageView user_image;
   int SCREEN_HEIGHT, SCREEN_WIDTH;
   TextView user_name;
   SharedPreferences sharedPreferences;
   FirebaseAuth firebaseAuth;
   FirebaseFirestore firebaseFirestore;

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
        firebaseAuth = FirebaseAuth.getInstance();
        //use glide to set image from url circularly
        Glide.with(this)
                .load(R.drawable.user_image)
                .circleCrop()
                .into(user_image);
        firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("users").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
        documentReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot.exists()) {
                    user_name.setText(documentSnapshot.getString("name"));
                }
            }
        });
    }
    public void onUserImageClick(android.view.View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            android.net.Uri selectedImage = data.getData();
            Glide.with(this)
                    .load(selectedImage)
                    .circleCrop()
                    .into(user_image);
            OkhttpImgBB okhttpImgBB = new OkhttpImgBB();
            //convert to base64

            Uri uri = data.getData();
            Bitmap bitmap= null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            byte[] bytes=stream.toByteArray();
            String encodedImage= Base64.encodeToString(bytes,Base64.DEFAULT);
            System.out.println("\n\n\n\n\n"+encodedImage+"\n\n\n\n\n");
            okhttpImgBB.uploadImage(encodedImage, Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());


        }
    }
}