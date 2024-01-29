package com.example.prova;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void tornaAllaMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
