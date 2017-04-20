package com.example.ivan.cloudmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ivan.cloudmobile.ctrl.Utils;

public class ListWords extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_words);

        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < Utils.getWordArray().size(); i++) {
            TextView wordTV = new TextView(this);
            wordTV.setTextSize(24);
            wordTV.setPadding(8, 8, 0, 0);
            wordTV.setText(Utils.getWordArray().get(i));
            linearLayout.addView(wordTV);

            TextView meaningTV = new TextView(this);
            meaningTV.setTextSize(14);
            meaningTV.setPadding(8, 2, 0, 0);
            meaningTV.setText(Utils.getMeaningArray().get(i));
            linearLayout.addView(meaningTV);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
