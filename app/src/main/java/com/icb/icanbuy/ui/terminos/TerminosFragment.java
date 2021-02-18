package com.icb.icanbuy.ui.terminos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.icb.icanbuy.R;
import com.icb.icanbuy.Registro;


public class TerminosFragment extends Fragment {

    Button bt_aceptar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_terminos, container, false);
        bt_aceptar =root.findViewById(R.id.bt_accept);

        bt_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Registro.class);
                startActivity(intent);
            }
        });

        return root;
    }


}