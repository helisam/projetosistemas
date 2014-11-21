package br.com.embarcado.embarco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by helisam.bentes on 19/11/2014.
 */
public class Main extends Activity implements View.OnClickListener {
    private Button btnEsportivos;
    private Button btnClassicos;
    private Button btnLuxo;
    private Button btnSobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Layout do DashBoard
        setContentView(R.layout.main);
        btnEsportivos = (Button) findViewById(R.id.btEsportivos);
        btnEsportivos.setOnClickListener(this);
        btnClassicos = (Button) findViewById(R.id.btClassicos);
        btnClassicos.setOnClickListener(this);
        btnLuxo = (Button) findViewById(R.id.btLuxo);
        btnLuxo.setOnClickListener(this);
        btnSobre = (Button) findViewById(R.id.btSobre);
        btnSobre.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, TelaListaCarros.class);
        if (v == btnEsportivos) {
            intent.putExtra(Carro.TIPO, Carro.TIPO_ESPORTIVOS);
            startActivity(intent);
        } else if (v == btnClassicos) {
            intent.putExtra(Carro.TIPO, Carro.TIPO_CLASSICO);
            startActivity(intent);
        } else if (v == btnLuxo) {
            intent.putExtra(Carro.TIPO, Carro.TIPO_LUXO);
            startActivity(intent);
        } else if (v == btnSobre) {
            startActivity(new Intent(this, TelaSobre.class));
        }
    }
}
