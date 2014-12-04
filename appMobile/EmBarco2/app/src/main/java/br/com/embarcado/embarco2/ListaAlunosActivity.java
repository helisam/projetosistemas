package br.com.embarcado.embarco2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helisam.bentes on 24/11/2014.
 */
public class ListaAlunosActivity extends Activity {

    private EditText edNome;
    private Button botao;
    private ListView lvListagem;

    private List<String> listaAlunos;

    private ArrayAdapter<String> adapter;

    private int adapterLayout = android.R.layout.simple_list_item_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaalunoslayout);

        edNome = (EditText) findViewById(R.id.edNomeListagem);
        botao = (Button) findViewById(R.id.btAddListagem);
        lvListagem = (ListView) findViewById(R.id.lvListagem);

        listaAlunos = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, adapterLayout, listaAlunos);

        lvListagem.setAdapter(adapter);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaAlunos.add(edNome.getText().toString());
                edNome.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        lvListagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Toast.makeText(ListaAlunosActivity.this, "Aluno: " + listaAlunos.get(posicao), Toast.LENGTH_SHORT).show();
            }
        });

        lvListagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Toast.makeText(ListaAlunosActivity.this, "Aluno Longo: " + listaAlunos.get(posicao), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}
