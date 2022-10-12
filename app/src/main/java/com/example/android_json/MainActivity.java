package com.example.android_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView text;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        text = findViewById(R.id.text);


        requestQueue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonparse() ;
            }
        });

    }

    public void jsonparse() {
        String url = "https://api.jsonserve.com/xcDprS";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("colors");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String firstName = employee.getString("color");
                                String age = employee.getString("value");

                                text.append(firstName + ", " + age + ", " + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }

        });

        requestQueue.add(request);
    }
}