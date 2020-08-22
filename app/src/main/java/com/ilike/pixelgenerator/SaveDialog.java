package com.ilike.pixelgenerator;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.warkiz.tickseekbar.TickSeekBar;

public class SaveDialog {
    Bitmap.CompressFormat cf;

    public void showDialog(final AppCompatActivity activity, final Context context) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_save_dialog);

        String[] types = {".png", ".jpeg", ".webp"};
        final Spinner filetype = dialog.findViewById(R.id.sp_filetype);
        ArrayAdapter aa = new ArrayAdapter(context, android.R.layout.simple_spinner_item, types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filetype.setAdapter(aa);

        final TickSeekBar quality = dialog.findViewById(R.id.tsb_quality);

        cf = Bitmap.CompressFormat.PNG;

        filetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (filetype.getSelectedItem() == ".png") {
                    cf = Bitmap.CompressFormat.PNG;
                    quality.setEnabled(false);
                } else if (filetype.getSelectedItem() == ".jpeg") {
                    cf = Bitmap.CompressFormat.JPEG;
                    quality.setEnabled(true);
                } else if (filetype.getSelectedItem() == ".webp") {
                    cf = Bitmap.CompressFormat.WEBP;
                    quality.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button save = dialog.findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveBitmapAsImage.SaveBitmapAsImage(context, activity, cf, quality.getProgress());
                dialog.dismiss();
            }
        });

        Button cancel = dialog.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}