package com.icb.icanbuy.ui.perfil;

import android.os.Bundle;
<<<<<<< HEAD
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.icb.icanbuy.R;
import com.icb.icanbuy.models.Usuario.Usuario;

import java.util.Map;


public class PerfilFragment extends Fragment {
private TextView tv_name, tv_fecha,tv_Correo, tv_telefono, tv_tipoID,
        tv_Cedula, tv_Compras, tv_Cupones;
private ImageView iv_fotoperfil;
private FirebaseDatabase database;
private DatabaseReference reference;
private static final String USERS="users";
   private String email;
   private final String TAG=this.getClass().getName().toUpperCase();
    private Map<String, String> userMap;
    private FirebaseUser user;
    private String userID;

=======

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icb.icanbuy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
<<<<<<< HEAD
        View root= inflater.inflate(R.layout.fragment_perfil, container, false);

       user= FirebaseAuth.getInstance().getCurrentUser();
       reference=FirebaseDatabase.getInstance().getReference("Users");
       userID=user.getUid();

       // Log.v("USERID", userRef.getKey());

        tv_name=root.findViewById(R.id.tv_name);
        tv_fecha=root.findViewById(R.id.tv_fecha);
        tv_Correo=root.findViewById(R.id.tv_Correo);
        tv_telefono=root.findViewById(R.id.tv_telefono);
        tv_tipoID=root.findViewById(R.id.tv_tipoID);
        tv_Cedula=root.findViewById(R.id.tv_cedula);
        iv_fotoperfil=root.findViewById(R.id.iv_fotoperfil);
        tv_Compras=root.findViewById(R.id.tv_compras);
        tv_Cupones=root.findViewById(R.id.tv_cupones);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        Usuario userProfile = snapshot.getValue(Usuario.class);

                        if (userProfile != null) {
                            String fullname = (userProfile.getNombre()).concat(" ".concat(userProfile.getApellido()));
                            String correo = userProfile.getCorreo();
                            String fechaNac = userProfile.getFechaNac();

                            tv_name.setText(fullname);
                            tv_fecha.setText(fechaNac);
                            tv_Correo.setText(correo);
                    }



                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "¡Error!", Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }

=======
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
}