package com.icb.icanbuy.ui.perfil;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.icb.icanbuy.R;
import com.icb.icanbuy.models.Usuario.Usuario;

import java.util.regex.Pattern;


public class EditarPerfil extends Fragment {
ImageView iv_fotoperfil;
EditText edt_Nombre, edt_Apellido, edt_Fecha, edt_Correo,
    edt_Telefono, edt_Cedula, edt_TipoID, resetPassword;
public static final String TAG="TAG";
    String nombre, apellido, correo, fechaNac, telefono, tipoID, cedula;
    private Button btn_GuardarCambios, btn_CambiarContrasena, btn_Cancelar;
    private String newPassword;
    private FirebaseUser user;
    private FirebaseAuth Autenticador;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Uri RutaImg; //una Direccion
    private final Integer CODIGO_SOLICITUD = 1234;//Identificador para nuestra app (proceso)
    private StorageReference Almacenamiento;
    private String userID;
    private  Usuario usuario;
    //patrón para validar contraseña
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        iv_fotoperfil=root.findViewById(R.id.iv_fotoperfil);
        edt_Nombre=root.findViewById(R.id.edt_EditarNombre);
        edt_Apellido=root.findViewById(R.id.edt_EditarApellido);
        edt_Fecha=root.findViewById(R.id.edt_Editarfecha);
        edt_Correo=root.findViewById(R.id.edt_EditarCorreo);
        edt_Telefono=root.findViewById(R.id.edt_telefono);
        edt_TipoID=root.findViewById(R.id.edt_tipoID);
        edt_Cedula=root.findViewById(R.id.edt_cedula);

        btn_GuardarCambios=root.findViewById(R.id.btn_GuardarCambios);
        btn_Cancelar=root.findViewById(R.id.btn_Cancelar);
       // btn_CambiarContrasena=root.findViewById(R.id.btn_CambiarContrasena);


        Autenticador=FirebaseAuth.getInstance();
        userID=Autenticador.getCurrentUser().getUid();
        user=Autenticador.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            nombre = bundle.getString("nombre",null);
            apellido = bundle.getString("apellido",null);
            correo = bundle.getString("correo",null);
            fechaNac = bundle.getString("fechaNac",null);
            telefono = bundle.getString("telefono",null);
            tipoID = bundle.getString("tipoID",null);
            cedula = bundle.getString("cedula",null);
        }

        Log.d(TAG, "onCreateView: "+nombre+" "+apellido+" "+correo+" "+fechaNac);

        edt_Nombre.setText(nombre);
        edt_Apellido.setText(apellido);
        edt_Correo.setText(correo);
        edt_Fecha.setText(fechaNac);
        edt_Telefono.setText(telefono);
        edt_TipoID.setText(tipoID);
        edt_Cedula.setText(cedula);


/*
        iv_fotoperfil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent, "Seleccione una Imagen"), CODIGO_SOLICITUD);

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(nombre+" "+apellido)
                        .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                        .build();
            }
        });*/


        /*
        btn_CambiarContrasena.setOnClickListener((v -> {
           resetPassword= new EditText(v.getContext());

            final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Cambiar contraseña");
            passwordResetDialog.setMessage("Ingrese una nueva contraseña. Debe tener por lo menos una mayúscula," +
                    " una minúscula, un número y un caracter especial.");
                passwordResetDialog.setView(resetPassword);

                try{
                    passwordResetDialog.setPositiveButton("Cambiar", ((dialog, which) -> {
                        //extraer correo y mandarle el link
                        newPassword= resetPassword.getText().toString();
                        if(ValidarContrasena()){
                            user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(getContext(), "Error al cambiar contraseña", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }//if

                    }));//password reset dialog positive button

                    passwordResetDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          dialog.dismiss();
                        }
                    });

                }catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

        }));//btn cambiar contraseña*/

        //boton guardar cambios
        btn_GuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(edt_Nombre.getText().toString().isEmpty() || edt_Apellido.getText().toString().isEmpty() ||
                            edt_Fecha.getText().toString().isEmpty() || edt_Correo.getText().toString().isEmpty()
                            || edt_Telefono.getText().toString().isEmpty() || edt_TipoID.getText().toString().isEmpty() ||
                            edt_Cedula.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }//if

                    //update correo
                    correo = edt_Correo.getText().toString();
                    user.updateEmail(correo).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mDatabase.child(user.getUid()).child("correo").setValue(correo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                   Toast.makeText(getContext(), "Cambio exitoso", Toast.LENGTH_SHORT).show();


                                   PerfilFragment perfilFragment = new PerfilFragment();
                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                    transaction.replace(R.id.editar_perfil_fragment, perfilFragment);

                                    transaction.commit();
                                }
                            });


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error al cambiar el correo", Toast.LENGTH_SHORT).show();
                        }
                    });//correo
                    //nombre
                    nombre=edt_Nombre.getText().toString();
                    mDatabase.child(user.getUid()).child("nombre").setValue(nombre).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                           // Toast.makeText(getContext(), "El nombre fue cambido exitosamente", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error al cambiar el nombre", Toast.LENGTH_SHORT).show();
                        }
                    });//nombre
                    //apellido
                    /*
                    mDatabase.child(user.getUid()).child("apellido").setValue(apellido).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                           // Toast.makeText(getContext(), "El apellido fue cambido exitosamente", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error al cambiar el apellido", Toast.LENGTH_SHORT).show();
                        }
                    });*/
                    //apellido
                    apellido=edt_Apellido.getText().toString();
                    mDatabase.child(user.getUid()).child("apellido").setValue(apellido);
                    //fecha
                    fechaNac=edt_Fecha.getText().toString();
                    mDatabase.child(user.getUid()).child("fechaNac").setValue(fechaNac);

                    //telefono
                    telefono=edt_Telefono.getText().toString();
                    mDatabase.child(user.getUid()).child("telefono").setValue(telefono);

                    //tipoID
                    tipoID=edt_TipoID.getText().toString();
                    mDatabase.child(user.getUid()).child("tipoID").setValue(tipoID);
                    //cedula
                    cedula=edt_Cedula.getText().toString();
                    mDatabase.child(user.getUid()).child("cedula").setValue(cedula);

                }catch (Exception ex){
                    Toast.makeText(getContext(), "Error al editar datos", Toast.LENGTH_SHORT).show();
                }//catch
            }//on click
        });//btn guardar cambios

        //boton cancelar
        btn_Cancelar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                PerfilFragment perfilFragment = new PerfilFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.editar_perfil_fragment, perfilFragment);

                transaction.commit();
            }
        });


        return root;
    }

    //Metodo para validar la contraseña
    private boolean ValidarContrasena(){
        String Contrasena = newPassword.trim();

        if(Contrasena.isEmpty()){
            resetPassword.setError("Debe ingresar una contraseña");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(Contrasena).matches()) {
            resetPassword.setError("La contraseña es insegura, debe contener mayúsculas,minúsculas, números y al menos un caracter especial");
            return false;
        } else {
            resetPassword.setError(null);
            return true;
        }
    }


}