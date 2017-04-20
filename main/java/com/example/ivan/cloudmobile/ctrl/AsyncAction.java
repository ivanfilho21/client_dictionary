package com.example.ivan.cloudmobile.ctrl;

import android.os.AsyncTask;
import com.example.ivan.cloudmobile.MainActivity;
import com.example.ivan.cloudmobile.R;

public class AsyncAction extends AsyncTask<String, Void, String> {

    private String serverAddress, word;

    public AsyncAction(String serverAddress, String word) {
        this.serverAddress = serverAddress;
        this.word = word;
    }

    protected String doInBackground(String... args) {

        /*
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) { e.printStackTrace(); }
        */
        return SendData.sendData(serverAddress, word);
    }

    /*
     * Depois que executar "doInBackground" este método é automaticamente executado,
     * sendo "result" o valor retornado do método anterior.
     */
    protected void onPostExecute(String result) {
        if (MainActivity.progressDialog != null) {
            MainActivity.progressDialog.dismiss();
        }
        if (result.equalsIgnoreCase("cannot reach server")) {
            MainActivity.showAlertDialog("Error", result);
        }
        else if (result.equalsIgnoreCase("word not found"))
            MainActivity.meaningTV.setText(R.string.word_not_found);
        else
            MainActivity.meaningTV.setText(result);
    }

    //Exemplo de Dialogo que espera resposta
    /*
    new AlertDialog.Builder(context)
                    .setTitle("Delete entry")
                    .setMessage("Are you sure you want to delete this entry?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
     */
}