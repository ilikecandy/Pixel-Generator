package com.ilike.pixelgenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.Random;

import static android.graphics.Bitmap.createBitmap;
import static com.ilike.pixelgenerator.Fragment_Options.editLength;
import static com.ilike.pixelgenerator.Fragment_Options.editWidth;
import static com.ilike.pixelgenerator.Fragment_Options.maxRGB;
import static com.ilike.pixelgenerator.Fragment_Options.minRGB;
import static com.ilike.pixelgenerator.Fragment_Options.pixelSizeL;
import static com.ilike.pixelgenerator.Fragment_Options.pixelSizeW;
import static com.ilike.pixelgenerator.Fragment_Options.refreshPixelsXY;
import static com.ilike.pixelgenerator.Fragment_Options.refreshRGB;
import static com.ilike.pixelgenerator.Fragment_Options.refreshXY;
import static com.ilike.pixelgenerator.Fragment_Options.resX;
import static com.ilike.pixelgenerator.Fragment_Options.resY;
import static com.ilike.pixelgenerator.Fragment_Options.switchSmooth;

public class Fragment_Canvas extends Fragment {
    public static View rootView;
    public static Bitmap myBitmap;


    public static void PaintCanvas(Context context) {

        if (String.valueOf(editLength.getText()).equals("")) {
            editLength.setText("1");
            Toast.makeText(context, "Error in Canvas Length, automatically set to 1", Toast.LENGTH_SHORT).show();
        } else if (String.valueOf(editLength.getText()).equals("0")) {
            editLength.setText("1");
            Toast.makeText(context, "Error in Canvas Length, automatically set to 1", Toast.LENGTH_SHORT).show();
        }

        if (String.valueOf(editWidth.getText()).equals("")) {
            editWidth.setText("1");
            Toast.makeText(context, "Error in Canvas Width, automatically set to 1", Toast.LENGTH_SHORT).show();
        } else if (String.valueOf(editWidth.getText()).equals("0")) {
            editWidth.setText("1");
            Toast.makeText(context, "Error in Canvas Width, automatically set to 1", Toast.LENGTH_SHORT).show();
        }

        refreshXY();
        refreshPixelsXY();
        refreshRGB();

        Random random = new Random();

        ImageView drawing;
        drawing = rootView.findViewById(R.id.bitmap);

        myBitmap = createBitmap(resX / pixelSizeW, resY / pixelSizeL, Bitmap.Config.ARGB_8888);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(resX, resY);
        drawing.setLayoutParams(layoutParams);

        int r, g, b;
        try {
            for (int i = 0; i < myBitmap.getHeight(); i++) {
                for (int j = 0; j < myBitmap.getWidth(); j++) {
                    r = random.nextInt(maxRGB[0] - minRGB[0] + 1) + minRGB[0];
                    g = random.nextInt(maxRGB[1] - minRGB[1] + 1) + minRGB[1];
                    b = random.nextInt(maxRGB[2] - minRGB[2] + 1) + minRGB[2];
                    myBitmap.setPixel(j, i, Color.rgb(r, g, b));
                }

            }
        } catch (Exception e) {
            Toast.makeText(context, "An error has occurred while generating", Toast.LENGTH_SHORT).show();
        }

        if (switchSmooth.isChecked()) {
            myBitmap = Bitmap.createScaledBitmap(
                    myBitmap, resX, resY, true);
        } else {
            myBitmap = Bitmap.createScaledBitmap(
                    myBitmap, resX, resY, false);
        }

        drawing.setImageBitmap(myBitmap);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_load, container, false);

        PaintCanvas(getContext());

        return rootView;
    }
}




