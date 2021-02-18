package com.icb.icanbuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import java.util.Arrays;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;


import com.icb.icanbuy.ui.scanner.Scanner;

public class MainActivity extends AppCompatActivity {

    //GOOGLE
    ImageButton signin;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;

    //FACEBOOK
    private CallbackManager callbackManager;
    private LoginButton loginButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Facebook

        //incula boton de facebook
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









        //Google
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

        Button bt = (Button) findViewById(R.id.buttonIngreso);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MenuActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
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

    //FACEBOOK





}