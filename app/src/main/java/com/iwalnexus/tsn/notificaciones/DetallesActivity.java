package com.iwalnexus.tsn.notificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetallesActivity extends AppCompatActivity {

    private TextView t;
    private TextView m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatlles);

        t = findViewById(R.id.T);
        m = findViewById(R.id.M);

        if(getIntent() != null){
            t.setText(getIntent().getStringExtra("t"));
            m.setText(getIntent().getStringExtra("m"));
        }
    }
}
