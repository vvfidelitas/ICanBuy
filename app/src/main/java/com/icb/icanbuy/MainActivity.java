package com.icb.icanbuy;

<<<<<<< HEAD
=======
import androidx.appcompat.app.AppCompatActivity;
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
=======
import android.widget.ImageButton;
import android.widget.Toast;

>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
<<<<<<< HEAD
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
//GOOGLE
    SignInButton signin;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;
    //FACEBOOK
    private CallbackManager callbackManager;
    private LoginButton loginButton;

    //private PropertiesConfig propertiesConfig;
    //final String urlLogin = "https://api.airtable.com/v0/appPvM705sztvANQP";

    private EditText edt_Correo, edt_Contrasena;
    //Instanciamos el autenticador de Firebase
    private FirebaseAuth Autenticador;

=======
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
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        //Enlazamos los componentes de la interfaz
        edt_Contrasena = findViewById(R.id.edt_Pass);
        edt_Correo = findViewById(R.id.edt_Correo);

        //Obtenemos una instancia de el Autenticador
        Autenticador = FirebaseAuth.getInstance();

        /*
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);*/

        signin = findViewById(R.id.sign_in_button);

=======
        Correo=(EditText)findViewById(R.id.etCorreo);
        Password=(EditText)findViewById(R.id.etPassword);

        signin = findViewById(R.id.sign_in_button);
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
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
<<<<<<< HEAD

        /*Configure el inicio de sesión para solicitar el ID del usuario,
        la dirección de correo electrónico.
        El ID y el perfil básico están incluidos en DEFAULT_SIGN_IN.*/

=======
        /*Configure el inicio de sesión para solicitar el ID del usuario,
        la dirección de correo electrónico.
        El ID y el perfil básico están incluidos en DEFAULT_SIGN_IN.*/
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
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
<<<<<<< HEAD


        //Facebook

        //vincula boton de facebook
        loginButton = findViewById(R.id.sign_face);
        callbackManager = CallbackManager.Factory.create();

        //Permisos solicitados
        loginButton.setPermissions(Arrays.asList("user_gender, user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Demo", "Login Correcto!");
            }

            @Override
            public void onCancel() {
                Log.d("Demo","Login Cancelado");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Demo","Error Login");

            }
        });

    }

    //metodo para mantener la sesion activa
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().signOut();
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
        if(validar()) {
            // pasamos parametros para crear nuestro usuario
            Autenticador.signInWithEmailAndPassword(Correo, Contrasena)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (Autenticador.getCurrentUser().isEmailVerified()) {
                                    FirebaseUser Usuario = Autenticador.getCurrentUser();
                                    Acutalizar_Interfaz(Usuario);
                                } else {
                                    Toast.makeText(MainActivity.this,
                                            "Por favor verifique su correo",
                                            Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(getApplicationContext(), "El usuario es incorrecto o no está registrado",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(MainActivity.this,
                    "Datos incorrectos",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Validación de campos login
    public boolean validar(){
        boolean retorno=true;
        String c1=edt_Correo.getText().toString();
        String c2=edt_Contrasena.getText().toString();
        //Se validan si los campos están vacios
        if(c1.isEmpty())
        {
            edt_Correo.setError("Este campo no puede quedar vacío");
            retorno=false;
        }
        if(c2.isEmpty())
        {
            edt_Contrasena.setError("Este campo no puede quedar vacío");
            retorno=false;
        }


        return retorno;
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





=======
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
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
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
<<<<<<< HEAD

=======
            account.getId();
            /*https://api.airtable.com/v0/appPvM705sztvANQP/Usuario?filterByFormula={GoogleId}="elIdquemedagoogle"
            resultado es igual al get del URL
            si resultado es un usuario entonces la propiedad usuario es igual al resultado
            si no
            entonces hacer post
            los datos se obtiene de acount (tabla de usuario agregar google id)*/
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a

            // Accedió correctamente, muestra la interfaz de usuario autenticada.
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // El código de estado de ApiException indica el motivo detallado del error.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }
<<<<<<< HEAD








}

=======
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
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
