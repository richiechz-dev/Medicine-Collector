package com.example.recomed.adpter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recomed.R;
import com.example.recomed.model.user;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class userAdapter extends FirestoreRecyclerAdapter<user,userAdapter.ViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public userAdapter(@NonNull FirestoreRecyclerOptions<user> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull user user) {
        viewHolder.nombre.setText(user.getNombre());
        viewHolder.ApellidoP.setText(user.getApellidoP());
        viewHolder.ApellidoM.setText(user.getApellidoM());
        viewHolder.Email.setText(user.getEmail());
        viewHolder.contraseña.setText(user.getContraseña());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_single, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,ApellidoP,ApellidoM,Email,contraseña;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nom);
            ApellidoP = itemView.findViewById(R.id.apellidoP);
            ApellidoM = itemView.findViewById(R.id.apellidoM);
            Email = itemView.findViewById(R.id.email);
            contraseña = itemView.findViewById(R.id.contra);
        }
    }
}
