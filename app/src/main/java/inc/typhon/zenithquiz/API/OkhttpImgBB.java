package inc.typhon.zenithquiz.API;

import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

import io.grpc.okhttp.internal.proxy.Request;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class OkhttpImgBB {
    private static final String API_KEY = "2d9c2ec2fbfaec2b814f52fad5268f31";
    private static final String API_URL = "https://api.imgbb.com/1/upload?key=" + API_KEY;
    private URI file_uri;
    private static String response_body;
    private static String url;
    private static String delete_url;

    //make a multipart request
    //get the response
    //return the response
    public void uploadImage(URI file_uri,String user_id) {
        File file = new File(file_uri);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), RequestBody.create(file, MediaType.parse("image/*")))
                .build();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .build();
        try {
            okhttp3.Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                String responseString = Objects.requireNonNull(response.body()).string();
                System.out.println(responseString);
                response_body = responseString;
                url = responseString.substring(responseString.indexOf("url")+7,responseString.indexOf("display_url")-3);
                delete_url = responseString.substring(responseString.indexOf("delete_url")+13,responseString.indexOf("page_url")-3);
                Log.d("url",url);
                Log.d("delete_url",delete_url);
                uploadToFirebase(user_id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void uploadToFirebase(String user_id){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("users").document(user_id);
        documentReference.update("avatar",url);
        documentReference.update("avatar_delete_url",delete_url);
    }
}
