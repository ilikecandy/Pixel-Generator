package com.ilike.pixelgenerator;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {
    public static boolean checkAndRequestPermissions(AppCompatActivity activity) {
        int permissionWriteExternal = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionWriteExternal != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            Toast.makeText(activity.getApplicationContext(), "Please grant storage permissions", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
