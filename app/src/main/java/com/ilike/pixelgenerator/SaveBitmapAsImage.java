package com.ilike.pixelgenerator;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;
import static com.ilike.pixelgenerator.Fragment_Canvas.myBitmap;

public class SaveBitmapAsImage {

    static String date;
    static String path;
    static SharedPreferences pref;

    public static void SaveBitmapAsImage(Context context, Activity activity, Bitmap.CompressFormat format,
                                         int quality) {
        if (myBitmap != null) {
            if (PermissionManager.checkAndRequestPermissions(activity)){
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                date = simpleDateFormat.format(new Date());

                pref = context.getSharedPreferences("prefs", MODE_PRIVATE);
                path = pref.getString("location", "/storage/emulated/0/Pictures/PixelBackgrounds/");

                File appDirectory = new File(path);
                appDirectory.mkdirs();
                File file = new File(path, date + "." + format.toString().toLowerCase());

                if (!path.endsWith("/")) {
                    path += "/";
                }

                Log.d("path", file.toString());
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    myBitmap.compress(format, quality, fos);
                    fos.flush();
                    fos.close();
                    Toast.makeText(context, "Saved to " + path + date + "." + format.toString().toLowerCase(), Toast.LENGTH_SHORT).show();
                } catch (java.io.IOException e) {
                    Toast.makeText(context, "Error saving background", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(context, "Please generate a background first", Toast.LENGTH_LONG).show();
        }

    }
}
