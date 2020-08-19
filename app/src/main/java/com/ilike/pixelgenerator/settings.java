package com.ilike.pixelgenerator;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class settings extends AppCompatActivity {

    static EditText location;
    Button save;
    ImageButton ib_folder;

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

        location = findViewById(R.id.location);
        location.setText(pref.getString("location", "/storage/emulated/0/Pictures/PixelBackgrounds/"));

        ib_folder = findViewById(R.id.ib_chooseFolder);
        ib_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionManager.checkAndRequestPermissions(settings.this)) {
                    Intent intent = new Intent(settings.this, FolderPicker.class);
                    //To show a custom title
                    intent.putExtra("title", "Select folder to save");
                    startActivityForResult(intent, 2);
                }
            }
        });

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("location", location.getText().toString());
                editor.apply();
                Toast.makeText(settings.this, "Saved!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(resultCode, resultCode, intent);
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {

            String folderLocation = intent.getExtras().getString("data");
            Log.i("folderLocation", folderLocation);

            location.setText(folderLocation);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }

}
