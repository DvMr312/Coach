package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coach.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creerMenu();
    }


    private void creerMenu(){

        ecouteMenu((ImageButton)findViewById(R.id.btnMonIMG), CalculActivity.class);
        ecouteMenu((ImageButton)findViewById(R.id.btnMonHistorique), HistoActivity.class);

    }

    private void ecouteMenu(ImageButton btn, Class classe) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classe);
                startActivity(intent);
            }
        });
    }





}