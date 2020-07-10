package com.ilike.pixelgenerator;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveBitmapAsImage {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    String date;
    String path;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static void SaveBitmapAsImage(Activity activity, Context context) {
        PaintCanvas pc = new PaintCanvas();
        SaveBitmapAsImage sbai = new SaveBitmapAsImage();

        sbai.path = "/storage/emulated/0/Pictures/PixelBackgrounds/";

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        sbai.date = simpleDateFormat.format(new Date());

        sbai.path = sbai.pref.getString("location", "/storage/emulated/0/Pictures/PixelBackgrounds/");

        verifyStoragePermissions(activity);
        ContextWrapper cw = new ContextWrapper(context);
        //File directory = cw.getDir("imageDir", MODE_PRIVATE);
        File appDirectory = new File(sbai.path);
        appDirectory.mkdirs();
        File file = new File(sbai.path, sbai.date + ".png");
        Toast.makeText(context, "Saved to " + sbai.path + sbai.date + ".png", Toast.LENGTH_SHORT).show();

        Log.d("path", file.toString());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            PaintCanvas.newBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE

            );
        }
    }
}
