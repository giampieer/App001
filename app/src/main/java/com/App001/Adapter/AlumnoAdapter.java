package com.App001.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.App001.Model.Alumno;
import com.App001.R;
import com.App001.ShowStudentActivity;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.ViewHolder>{
    Context context;
    List<Alumno> alumnoList;

    public AlumnoAdapter(Context context, List<Alumno> alumnoList) {
        this.context = context;
        this.alumnoList = alumnoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.documentUser.setText("DNI: " + alumnoList.get(i).getDocIdentidad());
        viewHolder.nameUser.setText("Nombre: " + alumnoList.get(i).getNombres());
        viewHolder.surnameUser.setText("Apellido: " + alumnoList.get(i).getApellidos());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowStudentActivity.class);
                intent.putExtra("Student", alumnoList.get(i));
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return null != alumnoList ? alumnoList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView documentUser;
        TextView nameUser;
        TextView surnameUser;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.alumnoitems);
            documentUser = itemView.findViewById(R.id.documentUser);
            nameUser = itemView.findViewById(R.id.nameUser);
            surnameUser = itemView.findViewById(R.id.surnameUser);
        }
    }
}
