package com.ilike.pixelgenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import static com.ilike.pixelgenerator.pixel_fragment.smooth;
import static com.ilike.pixelgenerator.pixel_fragment.x;
import static com.ilike.pixelgenerator.pixel_fragment.y;

public class PaintCanvas {
    public static Bitmap newBitmap;
    public Bitmap myBitmap;
    private ImageView drawing;
    private int minR;
    private int minG;
    private int minB;
    private int maxR;
    private int maxG;
    private int maxB;

    public Bitmap PaintCanvas(View view, Context context) {

        drawing = view.findViewById(R.id.bitmap);
        newBitmap = Bitmap.createBitmap(1, 1, Config.ARGB_8888);

        Random random = new Random();

        int r, g, b;

        drawing.getLayoutParams().height = pixel_fragment.y;
        drawing.getLayoutParams().width = pixel_fragment.x;

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(pixel_fragment.x, pixel_fragment.y);

        minR = (int) pixel_fragment.minimumR.getSelectedMinValue();
        minG = (int) pixel_fragment.minimumG.getSelectedMinValue();
        minB = (int) pixel_fragment.minimumB.getSelectedMinValue();

        maxR = (int) pixel_fragment.minimumR.getSelectedMaxValue();
        maxG = (int) pixel_fragment.minimumG.getSelectedMaxValue();
        maxB = (int) pixel_fragment.minimumB.getSelectedMaxValue();


        drawing.setLayoutParams(layoutParams);

        try {
            for (int i = 0; i < myBitmap.getHeight(); i++) {
                for (int j = 0; j < myBitmap.getWidth(); j++) {
                    r = random.nextInt(maxR - minR + 1) + minR;
                    g = random.nextInt(maxG - minG + 1) + minG;
                    b = random.nextInt(maxB - minB + 1) + minB;
                    myBitmap.setPixel(j, i, Color.rgb(r, g, b));

                }

            }
        } catch (Exception e) {
            Toast.makeText(context, "An error has occurred", Toast.LENGTH_SHORT).show();
        }

        if (smooth.isChecked()) {
            newBitmap = Bitmap.createScaledBitmap(
                    myBitmap, x, y, true);
        } else {
            newBitmap = Bitmap.createScaledBitmap(
                    myBitmap, x, y, false);
        }


        //pixels = 0;
        drawing.setImageBitmap(newBitmap);

        return newBitmap;

    }
}
