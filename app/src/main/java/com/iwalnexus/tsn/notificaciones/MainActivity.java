package com.iwalnexus.tsn.notificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText titulo;
    private EditText msg;
    private Button btn;

    private EditText id;
    private EditText grupo;

    private NotficationHandler handler;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulo = findViewById(R.id.titulo);
        msg = findViewById(R.id.mensaje);
        btn = findViewById(R.id.enviar);

        id = findViewById(R.id.IdGrupo);
        grupo = findViewById(R.id.Grupo);

        handler = new NotficationHandler(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String t = titulo.getText().toString();
               String m = msg.getText().toString();
               String g = grupo.getText().toString();
               int i = Integer.parseInt(id.getText().toString());

                if(!t.isEmpty() && !m.isEmpty()){
                    Notification.Builder builder = handler.createNotification(t,m,g);
                    handler.getManager().notify(counter++,builder.build());

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        handler.agruparNotificaciones(g, i);
                    }
                }
            }
        });
    }
}
