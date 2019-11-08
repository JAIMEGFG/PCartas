package com.jaime.cartas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener,CompoundButton.OnCheckedChangeListener {

    Button empezar;
    CheckBox practicando;
    TextView record;
    boolean practica = false;
    final static int REQ_CODE = 1, RES_CODE_PRACTICANDO = 0, RES_CODE_JUGANDO = 1;
    final static String TAG_1="Practicando";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        instancias();
        acciones();
    }

    private void instancias() {

        empezar = findViewById(R.id.empezar);
        practicando = findViewById(R.id.practicando);
        record = findViewById(R.id.record);
    }

    private void acciones() {

        empezar.setOnClickListener(this);
        practicando.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.empezar:
                Intent intent1 = new Intent(getApplicationContext(), SecondActivity.class);
                intent1.putExtra(TAG_1,practica);
                startActivityForResult(intent1, REQ_CODE);
                Toast.makeText(getApplicationContext(), "Vamos a jugar a las Cartas",
                        Toast.LENGTH_LONG).show();
                break;



        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.practicando:
                if (isChecked)
                    practica = true;
                else
                    practica = false;
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE && resultCode==RES_CODE_JUGANDO &&
                (int)data.getExtras().get(SecondActivity.TAG_RES)>Integer.valueOf(record.getText()
                        .toString()) ){
            record.setText(String.valueOf(data.getExtras().get(SecondActivity.TAG_RES)));
        }else if(requestCode==REQ_CODE && resultCode==RES_CODE_PRACTICANDO){

        }
    }
}
