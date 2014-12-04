package br.com.embarcado.embarco3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by helisam.bentes on 02/12/2014.
 */
public class ListaBarcoActivity extends Activity {

    // JSON node keys
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PHONE_MOBILE = "mobile";


    // JSON Node names
    private static final String TAG_BARCOS = "barcos";
    private static final String TAG_ID = "id";
    private static final String TAG_NOME = "nome";
    private static final String TAG_ANOFABRICACAO = "anoFabricacao";
    private static final String TAG_DESCRICAO = "address";
    private static final String TAG_PROPRIETARIO_NOME = "nomeP";
    private static final String TAG_PROPRIETARIO_CPF = "cpfP";
    private static final String TAG_PROPRIETARIO_MAIL = "mailP";
    private static final String TAG_PROPRIETARIO_FONE = "foneP";
    private static final String TAG_ITINERARIO_DESTINO = "nomeD";
    private static final String TAG_ITINERARIO_DESCRICAO = "descricaoD";
    private static final String TAG_ITINERARIO_ESTADO_SIGLA = "siglaE";
    private static final String TAG_ITINERARIO_ESTADO_NOME = "nomeE";
    private static final String TAG_ITINERARIO_ESTADO_CAPITAL = "capitalE";
    private static final String TAG_ITINERARIO_LOCALPARTIDA = "localPartida";
    private static final String TAG_ITINERARIO_HORASAIDA = "horaSaida";
    private static final String TAG_ITINERARIO_HORACHEGADA = "horaChegada";
    private static final String TAG_ITINERARIO_DISTANCIA = "distancia";
    private static final String TAG_ITINERARIO_OBSERVACAO = "observacaoI";
    private static final String TAG_ITINERARIO_DATA = "data";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        // getting intent data
        Intent in = getIntent();

        // Get JSON values from previous intent
        String nomeBarco = in.getStringExtra(TAG_NOME);
        String anoBarco = in.getStringExtra(TAG_ANOFABRICACAO);
        String descricaoBarco = in.getStringExtra(TAG_DESCRICAO);
        String proprietarioBarco = in.getStringExtra(TAG_PROPRIETARIO_NOME);
        String destinoBarco = in.getStringExtra(TAG_ITINERARIO_DESTINO);
        String partidaBarco = in.getStringExtra(TAG_ITINERARIO_LOCALPARTIDA);
        String hSaidaBarco = in.getStringExtra(TAG_ITINERARIO_HORASAIDA);
        String dataBarco = in.getStringExtra(TAG_ITINERARIO_DATA);

        // Displaying all values on the screen
        TextView lblNomeBarco = (TextView) findViewById(R.id.nomeBarco);
        TextView lblAnoBarco = (TextView) findViewById(R.id.anoBarco);
        TextView lblDescricaoBarco = (TextView) findViewById(R.id.descricaoBarco);
        TextView lblProprietarioBarco = (TextView) findViewById(R.id.propietarioBarco);
        TextView lblDestinoBarco = (TextView) findViewById(R.id.destinoBarco);
        TextView lblPartidaBarco = (TextView) findViewById(R.id.partidaBarco);
        TextView lblSaidaBarco = (TextView) findViewById(R.id.hSaidaBarco);
        TextView lblDataBarco = (TextView) findViewById(R.id.dataBarco);

        lblNomeBarco.setText(nomeBarco);
        lblAnoBarco.setText(anoBarco);
        lblDescricaoBarco.setText(descricaoBarco);
        lblProprietarioBarco.setText(proprietarioBarco);
        lblDestinoBarco.setText(destinoBarco);
        lblPartidaBarco.setText(partidaBarco);
        lblSaidaBarco.setText(hSaidaBarco);
        lblDataBarco.setText(dataBarco);
    }


}
