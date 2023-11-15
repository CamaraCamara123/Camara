package ma.ensa.camara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ma.ensa.camara.entities.Employe;

public class MainActivity extends AppCompatActivity {

    EditText nom, prenom, naissance;

    String url ="http://10.0.2.2:8087/employes";
    RequestQueue requestQueue;
    JsonArrayRequest arrayRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        naissance = findViewById(R.id.naissance);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("nom", nom.getText().toString() );
                    jsonBody.put("prenom", prenom.getText().toString());
                    jsonBody.put("dateNaissance", naissance.getText().toString() );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("resultat", response+"");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Erreur", error.toString());
                    }
                });
                requestQueue.add(request);

                Intent intent = new Intent(MainActivity.this,ListEmployeActivity.class);
                startActivity(intent);

            }
        });

    }


}