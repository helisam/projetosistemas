package br.com.embarcado.service;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Element;
import org.w3c.dom.Node;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.embarcado.embarco.Carro;
import br.com.embarcado.utils.HttpHelper;
import br.com.embarcado.utils.XMLUtils;

public class CarroService {
    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.xml";
    private static boolean LOG_ON = false;
    private static String TAG = "CarroService";

    public static List<Carro> getCarros(Context context, String tipo) throws IOException{
        String url = URL.replace("{tipo}", tipo);
        String xml = HttpHelper.doGet(url, "UTF-8");
        return parserXML(context, xml);
    }

    private static List<Carro> parserXML(Context context, String xml) {
        List<Carro> carros = new ArrayList<Carro>();
        Element root = XMLUtils.getRoot(xml, "UTF-8");

        List<Node> nodeCarros;
        nodeCarros = XMLUtils.getChildren(root, "carro");

        for (Node node : nodeCarros){
            Carro c = new Carro();

            c.nome = XMLUtils.getText(node, "nome");
            c.desc = XMLUtils.getText(node, "desc");
            c.urlFoto = XMLUtils.getText(node, "url_foto");
            c.urlInfo = XMLUtils.getText(node, "url_info");
            if(LOG_ON){
                Log.d(TAG, "Carro" + c.nome + " > " + c.urlFoto);
            }
            carros.add(c);
        }
           if(LOG_ON){
               Log.d(TAG, carros.size() + " encontrados.");
           }
        return carros;
     }
}
