package com.icb.icanbuy.ui.pago;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
=======

import androidx.fragment.app.Fragment;

>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

<<<<<<< HEAD
import androidx.fragment.app.Fragment;

import com.icb.icanbuy.R;
=======
import com.icb.icanbuy.R;
import com.icb.icanbuy.Registro;
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a

public class PagoFragment extends Fragment {


    public PagoFragment() {

    }

<<<<<<< HEAD

=======
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pago, container, false);

        Button button_agregar_tarjeta = (Button) view.findViewById(R.id.button_agregar_tarjeta);
        button_agregar_tarjeta.setOnClickListener(new View.OnClickListener() {
<<<<<<< HEAD
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), TarjetaPago.class);
                in.putExtra("some", "some data");
=======
            public void onClick(View v){
                Intent in = new Intent(getActivity(),TarjetaPago.class);
                in.putExtra("some","some data");
>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
                startActivity(in);
            }
        });

        return view;
    }
<<<<<<< HEAD
=======

>>>>>>> 3db4cc3027fc86728daeaea22102e24630b4fa1a
}
