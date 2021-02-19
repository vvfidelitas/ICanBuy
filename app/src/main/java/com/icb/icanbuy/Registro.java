package com.icb.icanbuy;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.icb.icanbuy.ui.terminos.TermActivity;

import java.util.Calendar;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {
    private EditText Nombre;
    private EditText Apellido;
    private EditText FechaNacimiento;
    private Button btnRegistro;
    EditText ConfirmarPass;
    DatePickerDialog.OnDateSetListener setListener;
    TextView txTerminos;

    CheckBox checkBox;
    Button btn;
    private EditText edt_Correo, edt_Contrasena;
    //Instanciamos el autenticador de Firebase
    private FirebaseAuth Autenticador;
    //Creamos un patrón para nuestra contraseña segura
    private static final Pattern PASSWORD_PATTERN =
                    Pattern.compile("^" +
                            "(?=.*[0-9])" +         //al menos 1 dígito
                            "(?=.*[a-z])" +         //al menos 1 letra minúscula
                            "(?=.*[A-Z])" +         //al menos 1 letra mayúscula
                            "(?=.*[@#$%^&+=.])" +   //al menos 1 carácter especial
                            "(?=\\S+$)." +          //sin espacios en blanco
                            "{6,}" +                //al menos 6 caracteres
                            "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Enlazamos los componentes de la interfaz
        edt_Contrasena = findViewById(R.id.edt_Pass);
        edt_Correo = findViewById(R.id.edt_Correo);

        //Obtenemos una instancia de el Autenticador
        Autenticador = FirebaseAuth.getInstance();

        Nombre=(EditText)findViewById(R.id.edt_Nombre);
        Apellido=(EditText)findViewById(R.id.edt_Apellido);
        FechaNacimiento=(EditText)findViewById(R.id.picker_FechaNac);
        edt_Correo=(EditText)findViewById(R.id.edt_Correo);
        edt_Contrasena=(EditText)findViewById(R.id.edt_Pass);
        ConfirmarPass=(EditText)findViewById(R.id.edt_PassConfirmar);
        checkBox = findViewById(R.id.checkBox);
        btn = findViewById(R.id.btn_Registro);
        txTerminos = findViewById(R.id.txTerminos);
        btn.setVisibility(View.INVISIBLE);

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

        txTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, TermActivity.class);
                startActivity(intent);
            }
        });



/*
        final Button send = (Button) this.findViewById(R.id.btn_Registro);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    GMailSender sender = new GMailSender(edttxt, "password");
                    sender.sendMail("This is Subject",
                            "This is Body",
                            "user@gmail.com",
                            edttxt);
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

            }
        });*/
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    //metodo para mantener la sesion activa
    @Override
    protected void onStart() {
        super.onStart();
        //obtener el usuario y lo autenticamos
        FirebaseUser usuario = Autenticador.getCurrentUser();
        //Acuatilamos nuestra interfaz
        Acutalizar_Interfaz(usuario);
    }

    private void Acutalizar_Interfaz(FirebaseUser usuario) {
        if (usuario != null) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }




    //Creacion de nuevos usuarios
    public void Registro(View view) {
        String Correo = edt_Correo.getText().toString();
        String Contrasena = edt_Contrasena.getText().toString();
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Verificación de correo")
                .setMessage("Se le ha enviado un link a su correo. " +
                        "Por favor dele click para verificar su cuenta. ")
                .setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent intent = new Intent(Registro.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });


        //Validamos Correo y Contraseña
        ValidarContrasena();
        ValidarCorreo();
        if(validar()){
// pasamos parametros para crear nuestro usuario
            Autenticador.createUserWithEmailAndPassword(Correo, Contrasena)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Autenticador.getCurrentUser().sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    AlertDialog alert11 = dialog.create();
                                                    dialog.show();
                                                    edt_Correo.setText("");
                                                    edt_Contrasena.setText("");

                                                    FirebaseAuth.getInstance().signOut();

                                                }else{
                                                    Toast.makeText(Registro.this,
                                                            task.getException().getMessage(),
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });



                            } else {
                                Toast.makeText(getApplicationContext(), "Creación de usuario fallida",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(getApplicationContext(), "Creación de usuario fallida",
                    Toast.LENGTH_LONG).show();
        }

    }
    //Metodo para validar el correo
    private boolean ValidarCorreo(){
        String Correo  = edt_Correo.getText().toString().trim();

        if (Correo.isEmpty()){
            edt_Correo.setError("Debe ingresar un correo");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Correo).matches()){
            edt_Correo.setError("Por favor ingrese una correo valido utilice el formato: ejemplo@correo.com");
            return false;
        } else {
            edt_Correo.setError(null);
            return true;
        }
    }

    //Metodo para validar la contraseña
    private boolean ValidarContrasena(){
        String Contrasena = edt_Contrasena.getText().toString().trim();

        if(Contrasena.isEmpty()){
            edt_Contrasena.setError("Debe ingresar una contraseña");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(Contrasena).matches()) {
            edt_Contrasena.setError("La contrasena es insegura, debe contener mayusculas,minusculas, numeros y al menos un caracter especial");
            return false;
        } else {
            edt_Contrasena.setError(null);
            return true;
        }
    }
    //Validación de campos login
    public boolean validar(){
        boolean retorno=true;
        String c1=Nombre.getText().toString();
        String c2=Apellido.getText().toString();
        String c3=FechaNacimiento.getText().toString();
        String c4=edt_Correo.getText().toString();
        String c5=edt_Contrasena.getText().toString();
        String c6=ConfirmarPass.getText().toString();

        //Se validan si los campos están vacios
        if(c1.isEmpty())
        {
            Nombre.setError("Este campo es obligatorio");
            retorno=false;
        }
        if(c2.isEmpty())
        {
            Apellido.setError("Este campo es obligatorio");
            retorno=false;
        }
        if(c3.isEmpty())
        {
            FechaNacimiento.setError("Este campo es obligatorio");
            retorno=false;
        }
        if(c4.isEmpty())
        {
            edt_Correo.setError("Este campo es obligatorio");
            retorno=false;
            //Validar si el correo es válido
        } else if (!Patterns.EMAIL_ADDRESS.matcher(c4).matches()){
            edt_Correo.setError("Por favor ingrese una dirección de correo válido");
            retorno=false;
        }
        if(c5.isEmpty())
        {
            edt_Contrasena.setError("Este campo es obligatorio");
            retorno=false;
            //Validar si la contraseña es válida
        } else if (!PASSWORD_PATTERN.matcher(c5).matches()){
            if(c5.length()<6){
                edt_Contrasena.setError("Debe tener mínimo 6 caracteres");
            }else{
                edt_Contrasena.setError("Debe tener números, mayúsculas y caracteres especiales.");
            }


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

