package com.App001;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.App001.Adapter.AlumnoAdapter;
import com.App001.Model.Alumno;
import com.App001.Model.MySingleton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Alumno> alumnoList = new ArrayList<>();
    AlumnoAdapter alumnoAdapter;
    private final String url = "http://qa.geo-point.com:8880/EXAMEN/api/Alumno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.RV_alumnos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        getItems();
    }

    private void getItems() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("msg", response.toString());
                try {
                    alumnoList.clear();
                    for (int i = 0; i < response.length(); i++) {
                        Alumno alumno = new Alumno();
                        JSONObject row = response.getJSONObject(i);
                        alumno.setId(row.getInt("Id"));
                        alumno.setDocIdentidad(row.getString("DocIdentidad"));
                        alumno.setApellidos(row.getString("Apellidos"));
                        alumno.setNombres(row.getString("Nombres"));
                        alumno.setFechaNac(row.getString("FechaNac"));
                        alumno.setAnioEstudios(row.getInt("AnioEstudios"));
                        alumno.setSeccion(row.getString("Seccion"));
                        alumno.setPromedio(row.getDouble("Promedio"));
                        alumno.setFoto(row.getString("Foto"));
                        alumnoList.add(alumno);
                    }
                    setupRecycler();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

    private void setupRecycler() {
        alumnoAdapter = new AlumnoAdapter(getApplicationContext(), alumnoList);
        recyclerView.setAdapter(alumnoAdapter);
        alumnoAdapter.notifyDataSetChanged();
    }

}
