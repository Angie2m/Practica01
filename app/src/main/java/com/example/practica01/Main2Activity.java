package com.example.practica01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practica01.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main2Activity extends AppCompatActivity {

    private static final String LOGTAG = "DEPURACION";

    private TextView tvNombre,tvHoroscopo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.meter,R.anim.mantener);

        setContentView(R.layout.activity_main2);

        Bundle bundle = new Bundle();

        bundle = getIntent().getExtras();

        String nombre = bundle.getString("NOMBRE", "No hay dato");
        String apellidos = bundle.getString("APELLIDOS");
        int dia, mes,anio;
        dia=bundle.getInt("DIA");
        mes=bundle.getInt("MES");
        anio=bundle.getInt("ANIO");

        tvNombre= findViewById(R.id.tvNombre);
        tvHoroscopo= findViewById(R.id.tvHoroscopo);

        String diaS = (Integer.toString(dia).length()<1)?dia+"":"0"+dia;
        String meS = (Integer.toString(mes).length()<1)?mes+"":"0"+mes;

        String fech = (diaS+"/"+meS+"/"+anio);
        Date fecha =null;
        java.util.Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual=dateFormat.format(date);
        try {
            fecha = dateFormat.parse(fech);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d(LOGTAG,"El nombre que llegó al Activity 2 es: "+ nombre +" "+apellidos);
        String signo = "";
        int modulo = anio % 12;
        switch (modulo) {
            case 0:
                signo = "mono";
                break;
            case 1:
                signo = "gallo";
                break;
            case 2:
                signo = "perro";
                break;
            case 3:
                signo = "jábali";
                break;
            case 4:
                signo = "rata";
                break;
            case 5:
                signo = "buey";
                break;
            case 6:
                signo = "tigre";
                break;
            case 7:
                signo = "liebre";
                break;
            case 8:
                signo = "dragon";
                break;
            case 9:
                signo = "serpiente";
                break;
            case 10:
                signo = "caballo";
                break;
            case 11:
                signo = "Cabra";
                break;
        }

        tvNombre.setText("Hola "+ nombre +" "+apellidos +". Tu signo es "+signo+" y tienes "+restar(fech,fechaActual)+" años.");






    }

    private int restar(String fech, String actual) {
        try {
            Calendar inicio = new GregorianCalendar();
            Calendar fin = new GregorianCalendar();
            inicio.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(fech));
            fin.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(actual));
            int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
            return difA;
        } catch(ParseException ex) {
            return 0;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.mantener, R.anim.sacar);
    }
}
