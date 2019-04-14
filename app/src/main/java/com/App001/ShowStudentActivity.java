package com.App001;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.App001.Model.Alumno;

public class ShowStudentActivity extends AppCompatActivity {
    TextView document, surname, name, bornDate, studiesYear, section, average, photo;
    Alumno alumno;
    Button exit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);
        Intent intent = this.getIntent();
        alumno = (Alumno) intent.getSerializableExtra("Student");

        document = (TextView) findViewById(R.id.viewDocument);
        surname = (TextView) findViewById(R.id.viewSurname);
        name = (TextView) findViewById(R.id.viewName);
        bornDate = (TextView) findViewById(R.id.viewBornDate);
        studiesYear = (TextView) findViewById(R.id.viewStudiesYear);
        section = (TextView) findViewById(R.id.viewSection);
        average = (TextView) findViewById(R.id.viewAverage);
        photo = (TextView) findViewById(R.id.viewPhoto);
        exit = (Button) findViewById(R.id.btnExit);

        document.setText("Documento: " + alumno.getDocIdentidad());
        surname.setText("Apellidos: " + alumno.getApellidos());
        name.setText("Nombres: " + alumno.getNombres());
        bornDate.setText("Fecha de Nacimiento: " + alumno.getFechaNac());
        studiesYear.setText("Años de estudio: " + String.valueOf(alumno.getAnioEstudios()));
        section.setText("Seccion: " + alumno.getSeccion());
        average.setText("Promedio: " + String.valueOf(alumno.getPromedio()));
        photo.setText("Foto: " + alumno.getFoto());

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowStudentActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
