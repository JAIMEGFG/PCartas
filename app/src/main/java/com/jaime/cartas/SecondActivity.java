package com.jaime.cartas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity implements ImageButton.OnClickListener {

    ImageButton down,up;
    int[] imagenes;
    ImageView carta;
    int NCarta,con=0;
    boolean practicando;
    final static String TAG_RES= "resultado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        instancias();
        acciones();
        recuperDatos();
    }


    private void instancias() {
        down = findViewById(R.id.down);
        up = findViewById(R.id.up);
        carta = findViewById(R.id.carta);
        imagenes = new int[]{R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
                R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,R.drawable.c11,
                R.drawable.c12,R.drawable.c13};
        carta = findViewById(R.id.carta);
        NCarta=(int) (Math.random() * 13);
        carta.setImageDrawable(getDrawable(imagenes[NCarta]));
    }
    private void acciones() {
        down.setOnClickListener(this);
        up.setOnClickListener(this);

    }
    private void recuperDatos() {
        if (getIntent().getExtras() != null) {
                practicando=(boolean)getIntent().getExtras().get(MainActivity.TAG_1);
            }
        }


    @Override
    public void onClick(View v) {
        int NCartaNueva;
        switch (v.getId()) {
            case R.id.up:
                NCartaNueva=(int) (Math.random() * 13);
                if(NCartaNueva>=NCarta){
                    NCarta=NCartaNueva;
                    carta.setImageDrawable(getDrawable(imagenes[NCarta]));
                    con++;
                }else if (NCartaNueva<NCarta && !practicando){
                    Intent in = new Intent();
                    in.putExtra(TAG_RES,con);
                    setResult(MainActivity.RES_CODE_JUGANDO,in);
                    finish();
                }else{
                    setResult(MainActivity.RES_CODE_PRACTICANDO);
                    finish();
                }
                break;
            case R.id.down:
                NCartaNueva=(int) (Math.random() * 13);
                if(NCartaNueva<=NCarta){
                    NCarta=NCartaNueva;
                    carta.setImageDrawable(getDrawable(imagenes[NCarta]));
                    con++;
                }else if (NCartaNueva>NCarta && !practicando){
                    Intent in = new Intent();
                    in.putExtra(TAG_RES,con);
                    setResult(MainActivity.RES_CODE_JUGANDO,in);
                    finish();
                }else{
                    setResult(MainActivity.RES_CODE_PRACTICANDO);
                    finish();
                }
                break;
        }
    }
}