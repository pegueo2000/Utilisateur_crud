package com.epo.gestiondesutilisateurs;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText nom_edit, prenom_edit, age_edit, sexe_edit, profession_edit, telephone_edit;
    Button update_button,deleteButton;
    String id, nom, prenom, age, sexe, profession, telephone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        nom_edit =  findViewById(R.id.nom_edit);
        prenom_edit = findViewById(R.id.prenom_edit);
        age_edit = findViewById(R.id.age_edit);
        sexe_edit = findViewById(R.id.sexe_edit);
        profession_edit = findViewById(R.id.profession_edit);
        telephone_edit = findViewById(R.id.telephone_edit);
        update_button = findViewById(R.id.edit_user);
        deleteButton = findViewById(R.id.delete_user);
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle(nom + " "+ prenom);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaBaseHelper myDB = new MaBaseHelper(UpdateActivity.this);
                nom = nom_edit.getText().toString().trim();
                prenom = prenom_edit.getText().toString().trim();
                age = age_edit.getText().toString().trim();
                sexe = sexe_edit.getText().toString().trim();
                profession = profession_edit.getText().toString().trim();
                telephone = telephone_edit.getText().toString().trim();
                myDB.updateData(id,nom,prenom,age,sexe,profession,telephone);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialogue();

            }
        });
    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id")&& getIntent().hasExtra("nom")&&getIntent().hasExtra("prenom")
                &&getIntent().hasExtra("age")&&getIntent().hasExtra("sexe")
                &&getIntent().hasExtra("profession")&&getIntent().hasExtra("telephone")){
            id = getIntent().getStringExtra("id");
            nom=getIntent().getStringExtra("nom");
            prenom= getIntent().getStringExtra("prenom");
            age = getIntent().getStringExtra("age");
            sexe = getIntent().getStringExtra("sexe");
            profession = getIntent().getStringExtra("profession");
            telephone = getIntent().getStringExtra("telephone");

            nom_edit.setText(nom);
            prenom_edit.setText(prenom);
            age_edit.setText(age);
            sexe_edit.setText(sexe);
            profession_edit.setText(profession);
            telephone_edit.setText(telephone);
        }
        else {
            Toast.makeText(this, "Pas de donnee", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Suprrimer "+ nom + " " + prenom+ " ?");
        builder.setMessage("Etes vous sûr de vouloir supprimer?");
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MaBaseHelper myDB = new MaBaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}