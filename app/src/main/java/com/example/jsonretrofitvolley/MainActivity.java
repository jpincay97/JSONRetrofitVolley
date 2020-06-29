package com.example.jsonretrofitvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jsonretrofitvolley.Interface.KushkiPagos;
import com.example.jsonretrofitvolley.ModeloDatos.ListaBancos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txtBancos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBancos = findViewById(R.id.txtListaBancos);
        getListaBancos();
    }

    private void getListaBancos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-uat.kushkipagos.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KushkiPagos kushkiPagosApi = retrofit.create(KushkiPagos.class);
        Call<List<ListaBancos>> call = kushkiPagosApi.getPosts("5b4cf6e705b942fb9f28f8b58e4310b1");
        call.enqueue(new Callback<List<ListaBancos>>() {
            @Override
            public void onResponse(Call<List<ListaBancos>> call, Response<List<ListaBancos>> response) {

                if(!response.isSuccessful()){
                    txtBancos.setText("Codigo: "+response.code());
                    return;
                }
                List<ListaBancos> listaBancos = response.body();

                for(ListaBancos post: listaBancos){
                    String content = "";
                    content += post.getName() + "\n";
                    txtBancos.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<ListaBancos>> call, Throwable t) {
                txtBancos.setText(t.getMessage());

            }
        });
    }


    public void btnEnviar(View view) {

        Intent intent = new Intent(MainActivity.this, ActValidaLogin.class);
        EditText txtUsuario = (EditText) findViewById(R.id.txtUsr);
        EditText txtPass = (EditText) findViewById(R.id.txtPass);

        Bundle b = new Bundle();
        b.putString("usr", txtUsuario.getText().toString());
        b.putString("pass", txtPass.getText().toString());
        intent.putExtras(b);
        startActivity(intent);

    }
}
