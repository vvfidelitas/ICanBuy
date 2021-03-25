package com.icb.icanbuy.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.icb.icanbuy.R;
import com.icb.icanbuy.ui.ScannerQR2.ScannerQRActivity;

public class HomeFragment extends Fragment {
    private Button btn_Escanear;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btn_Escanear=root.findViewById(R.id.btn_Escanear);

        btn_Escanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ScannerQRActivity.class);
                startActivity(i);
            }
        });

        return root;
    }
}