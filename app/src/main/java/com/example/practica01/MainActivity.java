package com.example.practica01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button btnActivity2;
    private EditText etDia, etMes, etAnio,etNombre,etApellidos;
    Calendar cal= Calendar.getInstance();
    int year= cal.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnActivity2 = findViewById(R.id.btnCalcular);
        etDia = findViewById(R.id.etDia);
        etMes = findViewById(R.id.etMes);
        etAnio = findViewById(R.id.etAnio);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);

        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaEditText()){
                    int dia = Integer.valueOf(etDia.getText().toString());
                    int mes = Integer.valueOf(etMes.getText().toString());
                    int anio = Integer.valueOf(etAnio.getText().toString());

                    if(esDiaValido(dia) && esMesValido(mes) ){
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("NOMBRE", etNombre.getText().toString());
                        bundle.putString("APELLIDOS", etApellidos.getText().toString());
                        bundle.putInt("DIA", Integer.valueOf(etDia.getText().toString()));
                        bundle.putInt("MES", mes);
                        bundle.putInt("ANIO", anio);

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }


                    else{
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.respuesta)  + getResources().getString(R.string.no) + getResources().getString(R.string.primo), Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
    private boolean validaEditText(){
        if(etDia.getText().length()==0 || etMes.getText().length()==0 || etAnio.getText().length()==0){
            Toast.makeText(MainActivity.this, getResources().getText(R.string.error),Toast.LENGTH_LONG).show();
            etDia.setError(getResources().getText(R.string.requerido));
            etMes.setError(getResources().getText(R.string.requerido));
            etAnio.setError(getResources().getText(R.string.requerido));
            return false;
        }
        return true;
    }
    private boolean esDiaValido(int numero){
        if(numero>31 || numero <= 0){
            Toast.makeText(MainActivity.this, getResources().getText(R.string.error),Toast.LENGTH_LONG).show();
            etDia.setError(getResources().getText(R.string.requerido));
            return false;
        }
        return true;
    }

    private boolean esMesValido(int numero){
        if(numero>12 || numero < 0){
            Toast.makeText(MainActivity.this, getResources().getText(R.string.error),Toast.LENGTH_LONG).show();
            etDia.setError(getResources().getText(R.string.requerido));
            return false;
        }
        return true;
    }
    private boolean esAnioValido(int numero){
        if(numero>1910 || numero < year){
            Toast.makeText(MainActivity.this, getResources().getText(R.string.error),Toast.LENGTH_LONG).show();
            etDia.setError(getResources().getText(R.string.requerido));
            return false;
        }
        return true;
    }

}
