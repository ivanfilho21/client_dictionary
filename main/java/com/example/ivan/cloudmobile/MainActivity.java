package com.example.ivan.cloudmobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.example.ivan.cloudmobile.ctrl.AsyncAction;
import com.example.ivan.cloudmobile.ctrl.SendData;
import com.example.ivan.cloudmobile.ctrl.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button searchButton;
    private EditText word, socketName;
    public static TextView meaningTV;
    public static ProgressDialog progressDialog = null;
    public static AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Utils(this);

        searchButton = (Button) findViewById(R.id.search_button);
        word = (EditText) findViewById(R.id.wordET);
        socketName = (EditText) findViewById(R.id.socketName);
        meaningTV = (TextView) findViewById(R.id.title_meaningBox);
        searchButton.setOnClickListener(this);

        alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public static void showAlertDialog(String title, String message) {
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void showProgressDialog() {
        //Forma resumida:
        //contexto, mensagem, titulo, indeterminado true, cancelavel falso?
        //progressDialog = ProgressDialog.show(this, "Searching word.", "Please Wait...", true, false);

        progressDialog.setMessage("Searching Word.");
        progressDialog.setTitle("Please Wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onClick(View v) {

        String server = socketName.getText().toString();
        String word = this.word.getText().toString();
        if (!word.isEmpty() && !server.isEmpty()) {

            //Busca local
            int index = Utils.searchWordLocally(word);
            if (index != -1) {
                meaningTV.setText(Utils.getMeaning(index));
            }
            //Busca no Servidor
            else {
                try {
                    showProgressDialog();
                    new AsyncAction(server, word).execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent addWordActivity = new Intent(this, AddWord.class);
        Intent listWordsActivity = new Intent(this, ListWords.class);

        switch (item.getItemId()) {
            case R.id.add_word_item_menu:
                startActivity(addWordActivity);
                return true;
            case R.id.list_words_item_menu:
                startActivity(listWordsActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
