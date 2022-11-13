package inc.typhon.zenithquiz.API;

import android.os.Build;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

public class ImgBBHost {
    private String base_url = "https://api.imgbb.com/1/upload?key=";
    private String key;
    private File file;
    private Long expiration;
    private String link;
    private String delete_url;
    private String responseBody;



    public ImgBBHost(String key, File file) {
        this.key = key;
        this.file = file;

    }
    public void execute(){
        String url = base_url + key;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        try {
            builder.addBinaryBody(
                    "image",
                    new FileInputStream(file),
                    ContentType.APPLICATION_OCTET_STREAM,
                    file.getName()
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpEntity responseEntity = response.getEntity();
        String responseString = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                responseString = new BufferedReader(
                        new InputStreamReader(responseEntity.getContent())
                ).lines().reduce("", (accumulator, actual) -> accumulator + actual);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(responseString);
        responseBody = responseString;
    }

    public String getLink() {
        return responseBody.substring(responseBody.indexOf("display_url") + 15, responseBody.indexOf("size") - 3);
    }

    public String getDelete_url() {
        return responseBody.substring(responseBody.indexOf("delete_url") + 13, responseBody.indexOf("display_url") - 3);
    }

    public String getResponseBody() {
        return responseBody;
    }
}
