package inc.typhon.zenithquiz.Task;

import android.content.Context;
import android.os.AsyncTask;

public class AsyncTaskRunner extends AsyncTask<String, String, String> {

    private String resp;
    private Context context;

    public AsyncTaskRunner(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        return resp;
    }

    @Override
    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation

    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(String... text) {


}
}
