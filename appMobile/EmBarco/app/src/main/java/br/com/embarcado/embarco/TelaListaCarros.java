package br.com.embarcado.embarco;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.embarcado.service.CarroService;

public class TelaListaCarros extends Activity {

    private static final String TAG = "livroandroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carros);
        try {
            String tipo;
            tipo = getIntent().getStringExtra("tipo");
            List<Carro> carros = CarroService.getCarros(this, tipo);
            for (Carro c : carros) {
                Log.i(TAG, "Carro: " + c.nome);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
