package inc.typhon.zenithquiz.Task;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.net.URI;

import inc.typhon.zenithquiz.API.ImgBBHost;

public class ImgbbAsyncAPI extends AsyncTaskRunner {
    private Context context;
    private String url;
    private String key;
    private URI file;
    private String delete_url;
    private ImgBBHost imgBBHost;

    public ImgbbAsyncAPI(Context context, String key, URI file) {
        super(context);
        this.context = context;
        this.key = key;
        this.file = file;
    }

    @Override
    protected String doInBackground(String... params) {
        imgBBHost.execute();
        return super.doInBackground(params);
    }

    @Override
    protected void onPostExecute(String result) {
        this.url=imgBBHost.getLink();
        this.delete_url=imgBBHost.getDelete_url();
        Log.d("uwu",url);
        Log.d("uwu",delete_url);
        super.onPostExecute(result);
    }

    @Override
    protected void onPreExecute() {
        //uri to file
        File file = new File(this.file);
        imgBBHost = new ImgBBHost(key,file);
        super.onPreExecute();
    }
    public void uploadToFirbase(){

    }
}

