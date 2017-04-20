package com.example.ivan.cloudmobile;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ivan.cloudmobile.ctrl.Utils;

public class AddWord extends AppCompatActivity {

    private Button addWord;
    private EditText wordET, meaningET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        addWord = (Button) findViewById(R.id.add_word_button);
        wordET = (EditText) findViewById(R.id.word_et);
        meaningET = (EditText) findViewById(R.id.meaning_et);

        addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordET.getText().toString();
                String meaning = meaningET.getText().toString();

                if (!word.isEmpty() && !meaning.isEmpty()) {
                    if (word.contains(")") || word.contains("/") || word.contains("\\n") ||
                            meaning.contains(")") || meaning.contains("/") || meaning.contains("\\n")) {
                        Toast.makeText(AddWord.this, "Caracteres inválidos", Toast.LENGTH_SHORT).show();
                    } else {
                        Utils.insertWord(word, meaning);
                        Toast.makeText(AddWord.this, "Palavra Inserida", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
