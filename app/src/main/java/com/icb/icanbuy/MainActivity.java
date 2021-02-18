package com.icb.icanbuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.icb.icanbuy.models.Usuario.Usuario;
import com.icb.icanbuy.ui.scanner.Scanner;

public class MainActivity extends AppCompatActivity {
    //Ingreso
    EditText Correo;
    EditText Password;

    Usuario usuario;

    ImageButton signin;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Correo=(EditText)findViewById(R.id.etCorreo);
        Password=(EditText)findViewById(R.id.etPassword);

        signin = findViewById(R.id.sign_in_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;

                }

            }
        });
        /*Configure el inicio de sesión para solicitar el ID del usuario,
        la dirección de correo electrónico.
        El ID y el perfil básico están incluidos en DEFAULT_SIGN_IN.*/
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Cree un GoogleSignInClient con las opciones especificadas por gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        Button btn = (Button) findViewById(R.id.buttonRegistro);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Registro.class);
                startActivityForResult(intent, 0);
            }
        });
        /* Ingreso a la aplicación
        Button bt = (Button) findViewById(R.id.buttonIngreso);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MenuActivity.class);
                startActivityForResult(intent, 0);
            }
        });*/
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Resultado devuelto al iniciar Intent desde GoogleSignInClient.getSignInIntent;
        if (requestCode == RC_SIGN_IN) {
            // La tarea devuelta de esta llamada siempre se completa, no es necesario adjuntar
            //       un oyente
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            account.getId();
            /*https://api.airtable.com/v0/appPvM705sztvANQP/Usuario?filterByFormula={GoogleId}="elIdquemedagoogle"
            resultado es igual al get del URL
            si resultado es un usuario entonces la propiedad usuario es igual al resultado
            si no
            entonces hacer post
            los datos se obtiene de acount (tabla de usuario agregar google id)*/

            // Accedió correctamente, muestra la interfaz de usuario autenticada.
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // El código de estado de ApiException indica el motivo detallado del error.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }
    public void Ingreso(View v){
        if(validar()){
            Toast.makeText(this, "Ingreso datos", Toast.LENGTH_SHORT).show();
        }
    }

    //Validación de campos login
    public boolean validar(){
        boolean retorno=true;
        String c1=Correo.getText().toString();
        String c2=Password.getText().toString();
        //Se validan si los campos están vacios
        if(c1.isEmpty())
        {
            Correo.setError("Este campo no puede quedar vacío");
            retorno=false;
        }
        if(c2.isEmpty())
        {
            Password.setError("Este campo no puede quedar vacío");
            retorno=false;
        }


        return retorno;
    }
}