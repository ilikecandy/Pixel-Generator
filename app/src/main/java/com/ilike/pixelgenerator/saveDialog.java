package com.ilike.pixelgenerator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class saveDialog {
    public void showDialog(final Activity activity, final Context context){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_save_dialog);

        String[] types = {".png", ".jpeg", ".webp"};
        final Spinner filetype = dialog.findViewById(R.id.sp_filetype);
        ArrayAdapter aa = new ArrayAdapter(context, android.R.layout.simple_spinner_item, types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filetype.setAdapter(aa);

        Button save = dialog.findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap.CompressFormat cf = Bitmap.CompressFormat.PNG;
                if (filetype.getSelectedItem() == ".png") {
                    cf = Bitmap.CompressFormat.PNG;
                } else if (filetype.getSelectedItem() == ".jpeg") {
                    cf = Bitmap.CompressFormat.JPEG;
                } else if (filetype.getSelectedItem() == ".webp") {
                    cf = Bitmap.CompressFormat.WEBP;
                }

                SaveBitmapAsImage.SaveBitmapAsImage(context, activity, cf , 100);
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