package com.epo.gestiondesutilisateurs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText nom_input , prenom_input, age_input , sexe_input, profesion_input, telephone_input;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nom_input = findViewById(R.id.nom);
        prenom_input = findViewById(R.id.prenom);
        age_input = findViewById(R.id.age);
        sexe_input = findViewById(R.id.sexe);
        profesion_input = findViewById(R.id.profession);
        telephone_input = findViewById(R.id.telephone);
        add_button = findViewById(R.id.add_user);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaBaseHelper myDB = new MaBaseHelper(AddActivity.this);
                myDB.addUser(nom_input.getText().toString().trim(),prenom_input.getText().toString().trim(),
                        Integer.valueOf(age_input.getText().toString().trim()),sexe_input.getText().toString().trim(),
                        profesion_input.getText().toString().trim(),telephone_input.getText().toString().trim());
            }
        });

    }
}