package com.example.jsonretrofitvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ActValidaLogin extends AppCompatActivity {

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_valida_login);

        queue = Volley.newRequestQueue(this);
        verificarLogin();
    }
    public void verificarLogin(){
        Bundle bundle = this.getIntent().getExtras();
        StringRequest request = new StringRequest(Request.Method.GET,
                "http://uealecpeterson.net/ws/login.php?usr="
                + bundle.getString("usr") + "&pass=" + bundle.getString("pass"),
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView txtSaludo = (TextView) findViewById(R.id.lblMensaje);
                txtSaludo.setText("Respuesta WS:  "  +  response  );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}
