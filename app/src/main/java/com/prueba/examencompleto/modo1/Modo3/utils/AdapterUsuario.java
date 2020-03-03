package com.prueba.examencompleto.modo1.Modo3.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prueba.examencompleto.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.UsuarioHolder> {

    List<Usuario> usuarios;

        public AdapterUsuario(ArrayList<Usuario> listaUsuario){

            this.usuarios=listaUsuario;

        }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_item,parent,false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new UsuarioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {

            holder.txtUsuario.setText(""+usuarios.get(position).getUsuario());
            holder.txtNombre.setText(""+usuarios.get(position).getNombre());
            holder.txtEdad.setText(""+usuarios.get(position).getEdad());


    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder {

        TextView txtEdad,txtUsuario,txtNombre;


        public UsuarioHolder(@NonNull View itemView) {
            super(itemView);

            txtUsuario=itemView.findViewById(R.id.txtUsuario);
            txtNombre=itemView.findViewById(R.id.txtNombre);
            txtEdad=itemView.findViewById(R.id.txtEdad);


        }
    }
}
