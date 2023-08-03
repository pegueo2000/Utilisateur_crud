package com.epo.gestiondesutilisateurs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    Activity activity;
    private ArrayList id_row, nom_row, prenom_row, age_row, sexe_row, profession_row, telephone_row;
    Animation translate_anim;


    CustomAdapter(Activity activity, Context context, ArrayList id_row, ArrayList nom_row, ArrayList prenom_row, ArrayList age_row,ArrayList sexe_row, ArrayList profession_row,ArrayList telephone_row){
        this.activity = activity;
        this.context = context;
        this.id_row = id_row;
        this.nom_row = nom_row;
        this.prenom_row = prenom_row;
        this.age_row =age_row;
        this.sexe_row=sexe_row;
        this.profession_row=profession_row;
        this.telephone_row= telephone_row;

    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new  MyViewHolder(view);
    }
    @RequiresApi(api= Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {

        holder.id_row_txt.setText(String.valueOf(id_row.get(position)));
        holder.nom_row_txt.setText(String.valueOf(nom_row.get(position)));
        holder.prenom_row_txt.setText(String.valueOf(prenom_row.get(position)));
        holder.age_row_txt.setText(String.valueOf(age_row.get(position)));
        holder.sexe_row_txt.setText(String.valueOf(sexe_row.get(position)));
        holder.profession_row_txt.setText(String.valueOf(profession_row.get(position)));
        holder.telephone_row_txt.setText(String.valueOf(telephone_row.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(id_row.get(position)));
                intent.putExtra("nom",String.valueOf(nom_row.get(position)));
                intent.putExtra("prenom",String.valueOf(prenom_row.get(position)));
                intent.putExtra("age",String.valueOf(age_row.get(position)));
                intent.putExtra("sexe",String.valueOf(sexe_row.get(position)));
                intent.putExtra("profession",String.valueOf(profession_row.get(position)));
                intent.putExtra("telephone",String.valueOf(telephone_row.get(position)));

                //context.startActivity(intent);
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_row.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_row_txt, nom_row_txt, prenom_row_txt,age_row_txt,sexe_row_txt,profession_row_txt,telephone_row_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_row_txt = itemView.findViewById(R.id.user_id);
            nom_row_txt = itemView.findViewById(R.id.nom_row);
            prenom_row_txt = itemView.findViewById(R.id.prenom_row);
            age_row_txt = itemView.findViewById(R.id.age_row);
            sexe_row_txt = itemView.findViewById(R.id.sexe_row);
            profession_row_txt = itemView.findViewById(R.id.profession_row);
            telephone_row_txt = itemView.findViewById(R.id.telephone_row);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
