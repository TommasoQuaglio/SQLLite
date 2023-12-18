package com.example.sql_lite;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity
{
    private EditText testo;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testo = findViewById(R.id.Name);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "dati salvati", Toast.LENGTH_LONG);
                toast.show();
                db.save(testo.getEditableText().toString(),
                        "prova2",
                        "prova3");
            }
        });
        public void onClick (View v){
            String oggetto = null;
            Toast toast = Toast.makeText(MainActivity.this, "carico i dati", Toast.LENGTH_LONG);
            toast.show();
            Cursor crs = db.query();
            while (crs.moveToNext()) {
                oggetto = crs.getString(crs.getColumnIndex(DatabaseStrings.FIELD_SUBJECT)).toString();
            }
            label.setText(oggetto);
        }
    }

    private void setContentView(int activityMain) {
    }
}