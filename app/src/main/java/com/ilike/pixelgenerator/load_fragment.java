package com.ilike.pixelgenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import static android.graphics.Bitmap.createBitmap;
import static com.ilike.pixelgenerator.pixel_fragment.pixelSizeLength;
import static com.ilike.pixelgenerator.pixel_fragment.pixelSizeWidth;
import static com.ilike.pixelgenerator.pixel_fragment.resX;
import static com.ilike.pixelgenerator.pixel_fragment.resY;
import static com.ilike.pixelgenerator.pixel_fragment.smooth;

public class load_fragment extends Fragment {


    public static View rootView;
    public static Bitmap myBitmap;
    static int pixelSizeL;
    static int pixelSizeW;

    public static void PaintCanvas(Context context) {
        Random random = new Random();

        int[] minRGB = new int[3];
        int[] maxRGB = new int[3];

        ImageView drawing;
        drawing = rootView.findViewById(R.id.bitmap);

        myBitmap = createBitmap(resX / pixelSizeW, resY / pixelSizeL, Bitmap.Config.RGB_565);

        minRGB[0] = (int) pixel_fragment.minimumR.getSelectedMinValue();
        minRGB[1] = (int) pixel_fragment.minimumG.getSelectedMinValue();
        minRGB[2] = (int) pixel_fragment.minimumB.getSelectedMinValue();

        maxRGB[0] = (int) pixel_fragment.minimumR.getSelectedMaxValue();
        maxRGB[1] = (int) pixel_fragment.minimumG.getSelectedMaxValue();
        maxRGB[2] = (int) pixel_fragment.minimumB.getSelectedMaxValue();

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
            Toast.makeText(context, "An error has occurred", Toast.LENGTH_SHORT).show();
        }

        if (smooth.isChecked()) {
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

        pixelSizeL = pixelSizeLength.getProgress();
        pixelSizeW = pixelSizeWidth.getProgress();

        return rootView;
    }


}




