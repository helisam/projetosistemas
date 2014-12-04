package br.com.embarcado.embarco2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by helisam.bentes on 24/11/2014.
 */
public class Inicial extends Activity {

    private EditText edNome;
    private Button btExibir, btModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial);

        edNome = (EditText) findViewById(R.id.edNome);
        btExibir = (Button) findViewById(R.id.btExibir);
        btModal = (Button) findViewById(R.id.btModal);

        btExibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), edNome.getText(), Toast.LENGTH_LONG).show();
            }
        });

        btModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Inicial.this);
                builder.setMessage("Operação realizada com sucesso");
                builder.setNeutralButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.setTitle("Resultado da execução");
                dialog.show();
            }
        });

        Log.i("CICLO DE VIDA", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CICLO DE VIDA", "onStart()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CICLO DE VIDA", "onResume()");
    }
}
