package com.ilike.pixelgenerator;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import static com.ilike.pixelgenerator.Fragment_Canvas.myBitmap;

public class WallpaperDialog {
    static ArrayList<String> options;

    @TargetApi(24)
    public void showDialog(final AppCompatActivity activity, final Context context) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_wallpaper_dialog);

        options = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            options.add("Set Home Screen");
            options.add("Set Lock Screen");
            options.add("Set Both");
        } else {
            options.add("Set Home Screen");
        }

        ListView lv = dialog.findViewById(R.id.lv_options);

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, options);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (myBitmap != null) {
                    String selectedItem = options.get(i);
                    WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                    try {
                        if (selectedItem.equals(options.get(0))) {
                            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
                                wallpaperManager.setBitmap(myBitmap);
                            } else {
                                wallpaperManager.setBitmap(myBitmap, null, true, WallpaperManager.FLAG_SYSTEM);
                            }
                        } else if (selectedItem.equals(options.get(1))) {
                            wallpaperManager.setBitmap(myBitmap, null, true, WallpaperManager.FLAG_LOCK);
                        } else {
                            wallpaperManager.setBitmap(myBitmap, null, true, WallpaperManager.FLAG_LOCK);
                            wallpaperManager.setBitmap(myBitmap, null, true, WallpaperManager.FLAG_SYSTEM);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Error setting background", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(context, "Set background", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Please generate a background first", Toast.LENGTH_LONG).show();
                }
                //wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                // ^ NEEDS API 24 FOR SPECIFIC SETTING
            }
        });

        dialog.show();

    }
}