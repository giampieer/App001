package com.App001;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.App001.Model.MySingleton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AddStudentActivity extends AppCompatActivity {
    private final String url = "http://qa.geo-point.com:8880/EXAMEN/api/Alumno";
    EditText txtAddDocument, txtAddSurname, txtAddName, txtAddBornDate, txtAddStudiesYear, txtAddSection, txtAddAverage, txtAddPhoto;
    Button btnAddStudent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        txtAddDocument = (EditText) findViewById(R.id.txtAddDocument);
        txtAddSurname = (EditText) findViewById(R.id.txtAddSurname);
        txtAddName = (EditText) findViewById(R.id.txtAddName);
        txtAddBornDate = (EditText) findViewById(R.id.txtAddBornDate);
        txtAddStudiesYear = (EditText) findViewById(R.id.txtAddStudiesYear);
        txtAddSection = (EditText) findViewById(R.id.txtAddSection);
        txtAddAverage = (EditText) findViewById(R.id.txtAddAverage);
        txtAddPhoto = (EditText) findViewById(R.id.txtAddPhoto);
        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtAddDocument.length() == 0) {
                    txtAddDocument.setError("El campo dni se encuentra vacio");
                    txtAddDocument.requestFocus();
                } else if (txtAddDocument.length() < 8) {
                    txtAddDocument.setError("Dni debe contener 8 digitos");
                    txtAddDocument.requestFocus();
                } else if (txtAddSurname.length() == 0) {
                    txtAddSurname.setError("El campo apellido se encuentra vacio");
                    txtAddSurname.requestFocus();
                } else if(txtAddName.length() == 0) {
                    txtAddName.setError("El campo nombre se encuentra vacio");
                    txtAddName.requestFocus();
                } else if(txtAddBornDate.length() == 0) {
                    txtAddBornDate.setError("El campo fecha de nacimiento se encuentra vacio");
                    txtAddBornDate.requestFocus();
                } else if(txtAddStudiesYear.length() == 0) {
                    txtAddStudiesYear.setError("El campo año de estudios se encuentra vacio");
                    txtAddStudiesYear.requestFocus();
                } else if(txtAddSection.length() == 0) {
                    txtAddSection.setError("El campo seccion se encuentra vacio");
                    txtAddSection.requestFocus();
                } else if(txtAddAverage.length() == 0) {
                    txtAddAverage.setError("El campo promedio se encuentra vacio");
                    txtAddAverage.requestFocus();
                } else if(txtAddPhoto.length() == 0) {
                    txtAddPhoto.setError("El campo photo se encuentra vacio");
                    txtAddPhoto.requestFocus();
                } else {
                    addStudent();
                }
            }
        });
    }

    private void addStudent() {
        try {
            String document = txtAddDocument.getText().toString();
            String surname = txtAddSurname.getText().toString();
            String name = txtAddName.getText().toString();
            String bornDate = txtAddBornDate.getText().toString();
            String studiesYear = txtAddStudiesYear.getText().toString();
            String section = txtAddSection.getText().toString();
            String average = txtAddAverage.getText().toString();
            String photo = txtAddPhoto.getText().toString();

            JSONObject params = new JSONObject();
            params.put("DocIdentidad", document);
            params.put("Apellidos", surname);
            params.put("Nombres", name);
            params.put("FechaNac", bornDate);
            params.put("AnioEstudios", Integer.parseInt(studiesYear));
            params.put("Seccion", section);
            params.put("Promedio", Double.parseDouble(average));
            params.put("Foto", photo);
            Log.d("JsonParams", params.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddStudentActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VolleyError", error.getMessage());
                }
            });
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        } catch (Exception e) {
            Log.e("AddStudentActivity", e.getMessage());
        }
    }
}
