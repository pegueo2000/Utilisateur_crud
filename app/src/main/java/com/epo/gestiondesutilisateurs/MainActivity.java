package com.epo.gestiondesutilisateurs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MaBaseHelper myDB ;
    ArrayList<String> user_id, user_nom,user_prenom, user_age, user_sexe,user_profession, user_telephone;
    CustomAdapter customAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        myDB = new MaBaseHelper(MainActivity.this);
        user_id = new ArrayList<>();
        user_nom = new ArrayList<>();
        user_prenom = new ArrayList<>();
        user_age = new ArrayList<>();
        user_sexe = new ArrayList<>();
        user_profession = new ArrayList<>();
        user_telephone = new ArrayList<>();
        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this,this, user_id, user_nom,user_prenom,user_age,user_sexe,user_profession,user_telephone);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() ==0)
        {
            Toast.makeText(this, "Pas d'utilisateur", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                user_id.add(cursor.getString(0));
                user_nom.add(cursor.getString(1));
                user_prenom.add(cursor.getString(2));
                user_age.add(cursor.getString(3));
                user_sexe.add(cursor.getString(4));
                user_profession.add(cursor.getString(5));
                user_telephone.add(cursor.getString(6));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delet_all){

         confirmDialogue();
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Suprrimer tout ?");
        builder.setMessage("Etes vous sûr de vouloir supprimer?");
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MaBaseHelper myDB = new MaBaseHelper(MainActivity.this);
                Toast.makeText(MainActivity.this, "Supprimer", Toast.LENGTH_SHORT).show();
                myDB.deleteAllData();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
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