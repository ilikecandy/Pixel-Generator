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

import static android.content.Context.MODE_PRIVATE;
import static com.ilike.pixelgenerator.load_fragment.myBitmap;

public class SaveBitmapAsImage {

    static String date;
    static String path;
    static SharedPreferences pref;

    public static void SaveBitmapAsImage(Context context) {

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        date = simpleDateFormat.format(new Date());

        pref = context.getSharedPreferences("prefs", MODE_PRIVATE);
        path = pref.getString("location", "/storage/emulated/0/Pictures/PixelBackgrounds/");

        File appDirectory = new File(path);
        appDirectory.mkdirs();
        File file = new File(path, date + ".png");
        Toast.makeText(context, "Saved to " + path + date + ".png", Toast.LENGTH_SHORT).show();

        Log.d("path", file.toString());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            myBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}
