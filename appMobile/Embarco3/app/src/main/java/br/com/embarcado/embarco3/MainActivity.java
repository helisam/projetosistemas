package br.com.embarcado.embarco3;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    private ProgressDialog pDialog;

    // URL to get barcos JSON
    private static String url = "http://private-9cd63-embarcado.apiary-mock.com/";

    // JSON Node names
    private static final String TAG_BARCOS = "barcos";
    private static final String TAG_ID = "id";
    private static final String TAG_NOME = "nome";
    private static final String TAG_ANOFABRICACAO = "anoFabricacao";
    private static final String TAG_DESCRICAO = "address";
    private static final String TAG_PROPRIETARIO = "proprietario";
    private static final String TAG_PROPRIETARIO_NOME = "nomeP";
    private static final String TAG_PROPRIETARIO_CPF = "cpfP";
    private static final String TAG_PROPRIETARIO_MAIL = "mailP";
    private static final String TAG_PROPRIETARIO_FONE = "foneP";
    private static final String TAG_ITINERARIO = "itinerario";
    private static final String TAG_DESTINO = "destino";
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

    // contacts JSONArray
    JSONArray barcos = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> barcoList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barcoList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        // Listview on item click listener
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String nomeBarco = ((TextView) view.findViewById(R.id.nomeBarco))
                        .getText().toString();
                String proprietarioBarco = ((TextView) view.findViewById(R.id.propietarioBarco))
                        .getText().toString();
                String destinoBarco = ((TextView) view.findViewById(R.id.destinoBarco))
                        .getText().toString();

                String ano = ((TextView) view.findViewById(R.id.anoBarco))
                        .getText().toString();

                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),
                        ListaBarcoActivity.class);
                in.putExtra(TAG_NOME, nomeBarco);
                in.putExtra(TAG_PROPRIETARIO_NOME, proprietarioBarco);
                in.putExtra(TAG_ITINERARIO_DESTINO, destinoBarco);
                in.putExtra(TAG_ANOFABRICACAO, ano);
                startActivity(in);

            }
        });

        // Calling async task to get json
        new GetBarcos().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetBarcos extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Carregando lista de barcos");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    barcos = jsonObj.getJSONArray(TAG_BARCOS);

                    // looping through All Barcos
                    for (int i = 0; i < barcos.length(); i++) {
                        JSONObject c = barcos.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String nomeBarco = c.getString(TAG_NOME);
                        String anoBarco = c.getString(TAG_ANOFABRICACAO);
                        //String proprietarioBarco = c.getString(TAG_PROPRIETARIO_NOME);
                        //String destinoBarco = c.getString(TAG_ITINERARIO_DESTINO);

                        // Phone node is JSON Object
                        //JSONObject phone = c.getJSONObject(TAG_PHONE);
                        // String mobile = phone.getString(TAG_PHONE_MOBILE);
                        // String home = phone.getString(TAG_PHONE_HOME);
                        // String office = phone.getString(TAG_PHONE_OFFICE);
                        // Proprietario node is JSON Object
                        JSONObject proprietario = c.getJSONObject(TAG_PROPRIETARIO);
                        String proprietarioBarco = proprietario.getString(TAG_PROPRIETARIO_NOME);


                        // Proprietario node is JSON Object
                        JSONObject itinerario = c.getJSONObject(TAG_ITINERARIO);
                        JSONObject destino = itinerario.getJSONObject(TAG_DESTINO);
                        String destinoBarco = destino.getString(TAG_ITINERARIO_DESTINO);


                        // tmp hashmap for single barco
                        HashMap<String, String> barco = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        barco.put(TAG_ID, id);
                        barco.put(TAG_NOME, nomeBarco);
                        barco.put(TAG_PROPRIETARIO_NOME, proprietarioBarco);
                        barco.put(TAG_ITINERARIO_DESTINO, destinoBarco);

                        barco.put(TAG_ANOFABRICACAO, anoBarco);

                        // adding barco to barco list
                        barcoList.add(barco);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Não foi possível pegar nenhum dado da URL");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, barcoList,
                    R.layout.list_item_main, new String[]{TAG_NOME, TAG_PROPRIETARIO_NOME,
                    TAG_ITINERARIO_DESTINO, TAG_ANOFABRICACAO}, new int[]{R.id.nomeBarco,
                    R.id.propietarioBarco, R.id.destinoBarco, R.id.anoBarco});

            setListAdapter(adapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}