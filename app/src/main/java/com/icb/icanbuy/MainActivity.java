package com.icb.icanbuy;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
//GOOGLE
    SignInButton signin;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;
    //FACEBOOK
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    ImageView iv_fotoperfil;
    public static final String TAG="TAG";

    //private PropertiesConfig propertiesConfig;
    //final String urlLogin = "https://api.airtable.com/v0/appPvM705sztvANQP";

    private EditText edt_Correo, edt_Contrasena;
    TextView olvidastecontrasena;
    //Instanciamos el autenticador de Firebase
    private FirebaseAuth Autenticador;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazamos los componentes de la interfaz
        edt_Contrasena = findViewById(R.id.edt_Pass);
        edt_Correo = findViewById(R.id.edt_Correo);
        olvidastecontrasena = findViewById(R.id.olvidastecontra);
        iv_fotoperfil=findViewById(R.id.iv_fotoperfil);

        //Obtenemos una instancia de el Autenticador
        Autenticador = FirebaseAuth.getInstance();

        /*
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);*/

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

        olvidastecontrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(intent);
                finish();
            }
        });

        /*Configure el inicio de sesión para solicitar el ID del usuario,
        la dirección de correo electrónico.
        El ID y el perfil básico están incluidos en DEFAULT_SIGN_IN.*/

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
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
/*
        Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
*/
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
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
            //handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.d("1", "firebaseAuthWithGoogle:" + account.getId());
            // Accedió correctamente, muestra la interfaz de usuario autenticada.
            firebaseAuthWithGoogle(account.getIdToken());

            // Accedió correctamente, muestra la interfaz de usuario autenticada.
           // Intent intent = new Intent(MainActivity.this, MenuActivity.class);
           // startActivity(intent);
        } catch (ApiException e) {
            // El código de estado de ApiException indica el motivo detallado del error.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        Autenticador.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("1", "signInWithCredential:success");
                            FirebaseUser user = Autenticador.getCurrentUser();
                            Acutalizar_Interfaz(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("1", "signInWithCredential:failure", task.getException());
                            Acutalizar_Interfaz(null);
                        }
                    }
                });
    }


/*
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
            Log.d("1", "firebaseAuthWithGoogle:" + account.getId());

            // Accedió correctamente, muestra la interfaz de usuario autenticada.
            firebaseAuthWithGoogle(account.getIdToken());

            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // El código de estado de ApiException indica el motivo detallado del error.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        Autenticador.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("1", "signInWithCredential:success");
                            FirebaseUser user = Autenticador.getCurrentUser();
                            Acutalizar_Interfaz(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("1", "signInWithCredential:failure", task.getException());
                            Acutalizar_Interfaz(null);
                        }
                    }
                });
    }
*/


}

