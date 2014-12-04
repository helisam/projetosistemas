package br.com.embarcado.embarco3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by helisam.bentes on 03/12/2014.
 */
public class LoginActivity extends Activity {

    private EditText edLogin, edPass;
    private Button btLogin;
    private TextView loginErrorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inibe a exibição do titulo da aplicacao
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        edLogin = (EditText) findViewById(R.id.loginUsuario);
        edPass = (EditText) findViewById(R.id.loginPassword);
        btLogin = (Button) findViewById(R.id.btnLogin);
        loginErrorMsg = (TextView) findViewById(R.id.login_error);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edLogin.getText().toString().equals("admin") && edPass.getText().toString().equals("123")) {
                    Log.d("Login: ", edLogin.getText().toString());
                    Log.d("Pass: ", edPass.getText().toString());

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    edLogin.setError("Nome de usuário errado!");
                    edPass.setError("Senha errada!");
                }
            }
        });

    }

}
