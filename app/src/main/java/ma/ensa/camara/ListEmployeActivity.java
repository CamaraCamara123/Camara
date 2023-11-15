package ma.ensa.camara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.ensa.camara.adapters.EmployeAdapter;
import ma.ensa.camara.entities.Employe;

public class ListEmployeActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    JsonArrayRequest arrayRequest;
    String url = "http://10.0.2.2:8087/employes";
    RecyclerView recyclerView;
    List<Employe> employes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employe);

        employes = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle_view);
        jsonfonction();
    }

    private void jsonfonction() {


        arrayRequest = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = null;
                for (int i =0; i<response.length();i++){
                    Log.d("Message :",response.toString());
                    try {
                        object = response.getJSONObject(i);
                        Employe employe = new Employe();
                        employe.setId(object.getLong("id"));
                        employe.setNom(object.getString("nom"));
                        employe.setPrenom(object.getString("prenom"));
                        employe.setDateNaissance(object.getString("dateNaissance"));
                        employes.add(employe);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                setupEmploye(employes);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(ListEmployeActivity.this);
        requestQueue.add(arrayRequest);
    }

    private void setupEmploye(List<Employe> employes) {
        EmployeAdapter myAdapter = new EmployeAdapter(this,employes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}