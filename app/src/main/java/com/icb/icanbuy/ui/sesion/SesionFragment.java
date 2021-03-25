package com.icb.icanbuy.ui.sesion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.icb.icanbuy.MainActivity;
import com.icb.icanbuy.R;


public class SesionFragment extends Fragment {


    private Button btExit;
    private Button btn_Salir;
    GoogleApiClient mGoogleApiClient;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sesion, container, false);

        btExit = root.findViewById(R.id.btExit);
        btn_Salir = root.findViewById(R.id.btn_Salir);

        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cerramos la coneccion con firebase
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(),"Desconectado",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        btn_Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                Toast.makeText(getContext(), "Desconectado", Toast.LENGTH_SHORT).show();
                            }
                        });
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Desconectado", Toast.LENGTH_SHORT).show();
                Intent formularioNuevo = new Intent(getActivity(), MainActivity.class);
                startActivity(formularioNuevo);
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
        return root;
    }

}
/*
    Button btExit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sesion, container, false);
        btExit = root.findViewById(R.id.btExit);
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return root;
    }

 */






