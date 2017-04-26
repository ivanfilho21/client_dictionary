package com.example.ivan.cloudmobile.ctrl;

import android.content.Context;
import android.os.AsyncTask;
import com.example.ivan.cloudmobile.MainActivity;
import com.example.ivan.cloudmobile.R;

public class AsyncAction extends AsyncTask<String, Void, String> {

    private String serverAddress, word;
    private Context context;

    public AsyncAction(Context context, String serverAddress, String word) {
        this.serverAddress = serverAddress;
        this.word = word;
        this.context = context;
    }

    protected String doInBackground(String... args) {

        /*
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) { e.printStackTrace(); }
        */
        return SendData.sendData(context, serverAddress, word);
    }

    /*
     * Depois que executar "doInBackground" este método é automaticamente executado,
     * sendo "result" o valor retornado do método anterior.
     */
    protected void onPostExecute(String result) {
        if (MainActivity.progressDialog != null) {
            MainActivity.progressDialog.dismiss();
        }
        if (result.equalsIgnoreCase(context.getString(R.string.server_error))) {
            MainActivity.showAlertDialog(context.getString(R.string.error), result);
        }
        else if (result.equalsIgnoreCase("word not found\n"))
            MainActivity.meaningTV.setText(context.getString(R.string.word_not_found));
        else
            MainActivity.meaningTV.setText(result);
    }
}