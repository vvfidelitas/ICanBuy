package com.icb.icanbuy;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    ImageButton signin;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;

    //private PropertiesConfig propertiesConfig;
    //final String urlLogin = "https://api.airtable.com/v0/appPvM705sztvANQP";

    private EditText edt_Correo, edt_Contrasena;
    //Instanciamos el autenticador de Firebase
    private FirebaseAuth Autenticador;
    //Creamos un patrón para nuestra contraseña segura



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazamos los componentes de la interfaz
        edt_Contrasena = findViewById(R.id.edt_Pass);
        edt_Correo = findViewById(R.id.edt_Correo);

        //Obtenemos una instancia de el Autenticador
        Autenticador = FirebaseAuth.getInstance();

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

    }

    //metodo para mantener la sesion activa
    @Override
    protected void onStart() {
        super.onStart();
        //obtener el usuario y lo autenticamos
        FirebaseUser usuario = Autenticador.getCurrentUser();
        //Actualizamos nuestra interfaz
        Acutalizar_Interfaz(usuario);
    }

    private void Acutalizar_Interfaz(FirebaseUser usuario) {
        if (usuario != null) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }


    //Ingreso de usuarios existentes
    public void Ingreso(View view) {
        String Correo = edt_Correo.getText().toString();
        String Contrasena = edt_Contrasena.getText().toString();


        // pasamos parametros para crear nuestro usuario
        Autenticador.signInWithEmailAndPassword(Correo, Contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser Usuario = Autenticador.getCurrentUser();
                            Acutalizar_Interfaz(Usuario);
                        } else {
                            Toast.makeText(getApplicationContext(), "El usuario es incorrecto o no está registrado",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }




/*
    public class LoginUser extends AsyncTask<String,Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String email = strings[0];
            String password = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("user_id", email)
                    .add("user_password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(urlLogin)
                    .post(formBody)
                    .build();

            Response response =null;
            try {
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result = response.body().string();
                    if(result.equalsIgnoreCase("login")){
                        Intent i = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Los datos ingresados no son correctos.", Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }*/





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

            // Accedió correctamente, muestra la interfaz de usuario autenticada.
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // El código de estado de ApiException indica el motivo detallado del error.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }








}

