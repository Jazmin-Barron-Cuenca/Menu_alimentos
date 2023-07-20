package com.example.menu_alimentos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Menu_opciones extends AppCompatActivity {

    private TextView titulo,titulo2;
    private static String roll;
    private static String nombrel;
    private static String  idlog;
    private Button btnregistrar, btnlistaempleados, btnlistaordenesmesero,btnlistaordenescocinero,btncontrasena, btnsalir, btnhistorialventas;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opc);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.barcolor2));
        window.setNavigationBarColor(this.getResources().getColor(R.color.navcolor2));





        btnregistrar = findViewById(R.id.btnregistrar);
        btnhistorialventas = findViewById(R.id.btnhistorialventas);
        btnlistaempleados = findViewById(R.id.btnlistaempleados);
        btnlistaordenesmesero = findViewById(R.id.btnlistaordenesmesero);
        btnlistaordenescocinero = findViewById(R.id.btnlistaordenescocinero);
        btncontrasena = findViewById(R.id.btncontrasena);

        btnsalir = findViewById(R.id.btnsalir);
        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(Menu_opciones.this);
                builder2.setMessage("¿Deseas Cerrar Sesion?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Menu_opciones.this, Login.class);
                                startActivity(intent);
                                finish(); // Cierra la actividad actual
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss(); // Descarta el diálogo y vuelve a la actividad
                            }
                        });

                builder2.show();
            }
        });

        //datos recibidos
        titulo = findViewById(R.id.titulomenu);
        titulo2 = findViewById(R.id.titulonombre);

        if (getIntent().hasExtra("roll")) {
            roll = getIntent().getStringExtra("roll");
            nombrel = getIntent().getStringExtra("nombrel");
            idlog = getIntent().getStringExtra("idlog");

            titulo.setText("Señor: " + roll);
            titulo2.setText("Nombre: " + nombrel);
            Toast.makeText(this, "roll: "+roll+" nombre:"+nombrel+" id:" +idlog, Toast.LENGTH_SHORT).show();
            if (roll.equals("Admin")){
                btnlistaordenesmesero.setVisibility(View.GONE);
                btnlistaordenescocinero.setVisibility(View.GONE);
                btncontrasena.setVisibility(View.GONE);

            }
            if (roll.equals("Cocinero")){
                btnregistrar.setVisibility(View.GONE);
                btnlistaempleados.setVisibility(View.GONE);
                btnlistaordenesmesero.setVisibility(View.GONE);
                btnhistorialventas.setVisibility(View.GONE);


            }
            if (roll.equals("Mesero")){
                btnregistrar.setVisibility(View.GONE);
                btnlistaempleados.setVisibility(View.GONE);
                btnlistaordenescocinero.setVisibility(View.GONE);

            }

        } else {
            Toast.makeText(this, "fallo extra", Toast.LENGTH_SHORT).show();
        }


    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Estás seguro de que deseas cerrar sesion?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Realizar la acción para salir de la aplicación
                Intent intent = new Intent( Menu_opciones.this, Logeo.class);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}