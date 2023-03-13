package com.clase1.aplicationcamare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class result extends AppCompatActivity {

    TextView nombre, correo, numero, ubicacion;
    String cel = "3023421647";
    ImageView img_result;

    FloatingActionButton call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();

        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Contacto agregado con éxito", Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(getResources().getColor(R.color.purple_200))
                .show();

        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        numero = findViewById(R.id.numero);
        ubicacion = findViewById(R.id.ubicacion);
        img_result = findViewById(R.id.img_result);
        call = findViewById(R.id.call);

        String name = getIntent().getStringExtra("nombre");
        String email = getIntent().getStringExtra("correo");
        String num = getIntent().getStringExtra("numero");
        String ubication = getIntent().getStringExtra("ubicacion");
        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("foto");

        if (bitmap != null)
        {
            img_result.setImageBitmap(bitmap);
        }else{
            img_result.setImageResource(R.drawable.logo_contact);
        }

        nombre.setText(name);
        correo.setText(email);
        numero.setText(num);
        ubicacion.setText(ubication);



        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_call(view);
            }
        });
    }


    public  void get_call(View view){
        Intent llamada = new Intent(Intent.ACTION_DIAL);
        llamada.setData(Uri.parse("tel: "+cel));


        if(llamada.resolveActivity(getPackageManager()) != null){
            startActivity(llamada);
        }
    }
}