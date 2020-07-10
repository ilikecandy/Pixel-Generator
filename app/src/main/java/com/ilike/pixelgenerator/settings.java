package com.ilike.pixelgenerator;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class settings extends AppCompatActivity {

    static EditText location;
    Button save;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");


        pref = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        editor = pref.edit();


        Boolean value = pref.getBoolean("default", false);

        location = findViewById(R.id.location);
        save = findViewById(R.id.save);


        if (!value) {
            editor.putBoolean("default", true);
            editor.commit();
            editor.putString("location", "/storage/emulated/0/Pictures/PixelBackgrounds");
            editor.commit();
            String area = pref.getString("location", "No location set. Error 1");
            location.setText(area);
        } else {
            String area = pref.getString("location", "No location set. Error 2");
            location.setText(area);
        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edits();
                Toast.makeText(settings.this, "Saved!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void edits() {
        editor.putString("location", location.getText().toString());
        editor.commit();
    }


}
