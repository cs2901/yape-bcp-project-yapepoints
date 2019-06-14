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

public class MainActivity extends AppCompatActivity {
    private static final String URL_DATA = "http://178.128.216.229:8080/getAllCoupons";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CouponsList> couponsLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        couponsLists = new ArrayList<>();

        loadUrlData();
    }

    private void loadUrlData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("coupons");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jo = array.getJSONObject(i);

                        CouponsList coupons = new CouponsList(
                                jo.getJSONObject("_id"),
                                jo.getString("title"),
                                jo.getString("description"),
                                jo.getString("image_path"),
                                jo.getString("business_name"));
                        couponsLists.add(coupons);

                    }

                    adapter = new CouponsAdapter(couponsLists, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}