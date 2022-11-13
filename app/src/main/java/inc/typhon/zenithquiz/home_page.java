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
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;


import java.io.IOException;
import java.util.Objects;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

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

        firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("users").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
        documentReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot.exists()) {
                    user_name.setText(documentSnapshot.getString("name"));
                    String image_url = documentSnapshot.getString("avatar");
                    if (image_url != null) {
                        Glide.with(this)
                                .load(image_url)
                                .circleCrop()
                                .into(user_image);
                    }else {
                        Glide.with(this)
                                .load(R.drawable.user_image)
                                .circleCrop()
                                .into(user_image);
                    }
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


            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", encodedImage)
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.imgbb.com/1/upload?key=2d9c2ec2fbfaec2b814f52fad5268f31")
                    .post(requestBody)
                    .build();
            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    Toast.makeText(home_page.this, "Image Upload Failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String myResponse = response.body().string();
                        System.out.println("\n\n\n\n\n"+myResponse+"\n\n\n\n\n");
                        try {
                            JSONObject json = new JSONObject(myResponse);
                            JSONObject data = json.getJSONObject("data");
                            String url = data.getString("url");
                            String delete_url = data.getString("delete_url");
                            System.out.println("\n\n\n\n\n"+url+"\n\n\n\n\n");
                            System.out.println("\n\n\n\n\n"+delete_url+"\n\n\n\n\n");
                            DocumentReference documentReference = firebaseFirestore.collection("users").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
                            documentReference.update("avatar",url);
                            documentReference.update("delete_url",delete_url);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }
    }
}