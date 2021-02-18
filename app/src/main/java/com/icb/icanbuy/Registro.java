package com.icb.icanbuy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.Calendar;
import java.util.regex.Pattern;

import static android.view.View.VISIBLE;

public class Registro extends AppCompatActivity {


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //al menos 1 dígito
                    "(?=.*[a-z])" +         //al menos 1 letra minúscula
                    "(?=.*[A-Z])" +         //al menos 1 letra mayúscula
                    "(?=.*[@#$%^&+=.])" +   //al menos 1 carácter especial
                    "(?=\\S+$)." +          //sin espacios en blanco
                    "{6,}" +                //al menos 6 caracteres
                    "$");

    EditText Nombre;
    EditText Apellidos;
    EditText FechaNacimiento;
    EditText Correo;
    EditText Password;
    EditText ConfirmarPass;
    DatePickerDialog.OnDateSetListener setListener;

    CheckBox checkBox;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Nombre=(EditText)findViewById(R.id.etNombre);
        Apellidos=(EditText)findViewById(R.id.etApellidos);
        FechaNacimiento=(EditText)findViewById(R.id.etFechaNacimiento);
        Correo=(EditText)findViewById(R.id.etCorreo1);
        Password=(EditText)findViewById(R.id.etPassword1);
        ConfirmarPass=(EditText)findViewById(R.id.etConfirmar);
        checkBox = findViewById(R.id.checkBox);
        btn = findViewById(R.id.btnRegistro);

                Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        FechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Registro.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        FechaNacimiento.setText(date);
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btn.setVisibility(View.VISIBLE);
                }else{
                    btn.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
    public void Registro(View v){
        if(validar()){
            Toast.makeText(this, "Ingreso datos", Toast.LENGTH_SHORT).show();
        }
    }


        //Validación de campos login
        public boolean validar(){
            boolean retorno=true;
            String c1=Nombre.getText().toString();
            String c2=Apellidos.getText().toString();
            String c3=FechaNacimiento.getText().toString();
            String c4=Correo.getText().toString();
            String c5=Password.getText().toString();
            String c6=ConfirmarPass.getText().toString();

            //Se validan si los campos están vacios
            if(c1.isEmpty())
            {
                Nombre.setError("Este campo es obligatorio");
                retorno=false;
            }
            if(c2.isEmpty())
            {
                Apellidos.setError("Este campo es obligatorio");
                retorno=false;
            }
            if(c3.isEmpty())
            {
                FechaNacimiento.setError("Este campo es obligatorio");
                retorno=false;
            }
            if(c4.isEmpty())
            {
                Correo.setError("Este campo es obligatorio");
                retorno=false;
                //Validar si el correo es válido
            } else if (!Patterns.EMAIL_ADDRESS.matcher(c4).matches()){
                Correo.setError("Por favor ingrese una dirección de correo válido");
                retorno=false;
            }
            if(c5.isEmpty())
            {
                Password.setError("Este campo es obligatorio");
                retorno=false;
                //Validar si la contraseña es válida
            } else if (!PASSWORD_PATTERN.matcher(c5).matches()){
                Password.setError("Contraseña demasiado débil");
                retorno=false;
            }
            if(c6.isEmpty())
            {
                ConfirmarPass.setError("Este campo es obligatorio");
                retorno=false;
            }else if (!c5.equals(c6)){
                ConfirmarPass.setError("Las contraseñas no coinciden");
                retorno=false;
            }/*else
                ConfirmarPass.setError("Las contraseñas coinciden correctamente");
                retorno=false;*/
            return retorno;

        }
    }




