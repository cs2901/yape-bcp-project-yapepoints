package com.example.yapecupones;

//dependencias
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

//Request
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

//Leer JSON
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//STL
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private static final String URL_DATA = "https://api.github.com/search/users?q=language:java+location:chicago";//Se cambia por el de Yape, se puede poner cualquier ciudad para probar

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DevelopersList> developersLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {// inicializa la vista de repeticion y el array de tarjetas (solo su info, no los objetos)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        developersLists = new ArrayList<>();

        loadUrlData();
    }

    private void loadUrlData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {//extrae los JSONs
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("items");

                    for (int i = 0; i < array.length(); i++){

                        JSONObject jo = array.getJSONObject(i);

                        DevelopersList developers = new DevelopersList(jo.getString("login"), jo.getString("html_url"), jo.getString("avatar_url"));
                        developersLists.add(developers);

                    }

                    adapter = new DevelopersAdapter(developersLists, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {//porsiaca
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity2.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}