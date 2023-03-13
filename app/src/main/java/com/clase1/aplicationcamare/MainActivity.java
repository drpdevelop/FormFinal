package com.clase1.aplicationcamare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Debug;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nombre, correo, numero;
    Button newUser;
    Spinner ubication;
    ImageView image_click;
    Bitmap imgBitMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        ubication = (Spinner) findViewById(R.id.ubicacion);
        numero = findViewById(R.id.numero);
        newUser = findViewById(R.id.registrar);
        image_click = findViewById(R.id.click_image);


        image_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });

        List<String> list = new ArrayList<String>();
        list.add("SIM 1");
        list.add("SIM 2");
        list.add("Correo");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ubication.setAdapter(adapter2);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarse(view);
            }
        });

    }

    public void registrarse(View view){
        View parentLayout = findViewById(android.R.id.content);
        if( validateNull()){
            Intent pasarDato = new Intent(this, result.class);
            pasarDato.putExtra("nombre",nombre.getText().toString());
            pasarDato.putExtra("correo",correo.getText().toString());
            pasarDato.putExtra("numero",numero.getText().toString());
            pasarDato.putExtra("ubicacion",ubication.getSelectedItem().toString());
            pasarDato.putExtra("foto", imgBitMap);
            startActivity(pasarDato);

        }else{
            Snackbar.make(parentLayout, "Todos los campos son requeridos.", Snackbar.LENGTH_LONG)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.purple_200))
                    .show();
        }

    }

    public boolean validateNull(){

        boolean result = true;
        if(nombre.getText().toString().matches("") || correo.getText().toString().matches("") ||
        numero.getText().toString().matches("") || ubication.getSelectedItem().toString().matches("")){
            result =  false;
        }else{
            result = true;
        }

        return result;
    }

    public void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imgBitMap = (Bitmap) extras.get("data");
            image_click.setImageBitmap(imgBitMap);
        }
    }

    public static void Log(Object obj){
        System.out.println(obj);
    }
}