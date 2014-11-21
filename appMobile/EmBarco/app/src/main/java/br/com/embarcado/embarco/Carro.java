package br.com.embarcado.embarco;

import java.io.Serializable;

/**
 * Created by helisam.bentes on 19/11/2014.
 */
public class Carro implements Serializable {
    private static final long serialVersionUID = 2806421523585360625L;

    public static final String KEY = "carro";
    public static final String TIPO = "tipo";
    public static final String TIPO_CLASSICO = "classicos";
    public static final String TIPO_ESPORTIVOS = "esportivos";
    public static final String TIPO_LUXO = "luxo";
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;
}
