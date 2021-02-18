package com.icb.icanbuy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {
    private EditText txtCorreo;
    private EditText edtNombre;
    private EditText edtApellido;
    private EditText edtFechaNac;
    private Button btnRegistro;
    private EditText edt_Correo, edt_Contrasena;
    //Instanciamos el autenticador de Firebase
    private FirebaseAuth Autenticador;
    //Creamos un patrón para nuestra contraseña segura
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(
                    "(?=.*[0-9])" +       // Permite numeros
                            "(?=.*[a-zA-Z])" +    // Permite letras
                            "(?=.*[!@#$%&*+=.])" +   // Permite caracteres especiales
                            "(?=\\S+$)" +         // sin espacios en blanco
                            ".{6,18}" +           // entre 6 y 18 caracteres
                            "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Enlazamos los componentes de la interfaz
        edt_Contrasena = findViewById(R.id.edt_Pass);
        edt_Correo = findViewById(R.id.editTextTextEmailAddress);

        //Obtenemos una instancia de el Autenticador
        Autenticador = FirebaseAuth.getInstance();




        final Button send = (Button) this.findViewById(R.id.btn_Registro);
        /*send.setOnClickListener(new View.OnClickListener() {

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

        //Validamos Correo y Contraseña
        ValidarContrasena();
        ValidarCorreo();
        if(ValidarCorreo()&& ValidarContrasena()){
// pasamos parametros para crear nuestro usuario
            Autenticador.createUserWithEmailAndPassword(Correo, Contrasena)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser Usuario = Autenticador.getCurrentUser();
                                Acutalizar_Interfaz(Usuario);
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

}