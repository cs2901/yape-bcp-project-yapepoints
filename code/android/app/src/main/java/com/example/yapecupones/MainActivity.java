package com.example.yapecupones;

//dependencias
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String URL_DATA = "http://142.93.160.192/getAllCoupons";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CouponsList> couponsLists;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        spinner = findViewById(R.id.districts_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.districts, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        couponsLists = new ArrayList<>();

        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }

            private void refreshContent() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        couponsLists.clear();
                        String filter = spinner.getSelectedItem().toString();
                        loadUrlData(filter);
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1);
            }
        });

        loadUrlData("Todos");
    }

    private void loadUrlData(final String filter) {
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
                                jo.getString("cost"),
                                jo.getString("image_path"),
                                jo.getString("business_name"),
                                jo.getString("business_region"),
                                jo.getString("business_address"));

                        if (coupons.getBusiness_region().equals(filter)) {
                            couponsLists.add(coupons);
                        }else if (!coupons.getBusiness_region().equals(filter) && filter.equals("Todos")) {
                            couponsLists.add(coupons);
                        }

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loadUrlData(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        loadUrlData(null);
    }

    public void goToURL(View view) {
        String url = view.findViewById(R.id.business_address).getContentDescription().toString();
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}