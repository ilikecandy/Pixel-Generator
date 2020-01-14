package com.ilike.pixelgenerator;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;

import java.util.Random;

import static android.app.ProgressDialog.STYLE_HORIZONTAL;
import static android.graphics.Bitmap.createBitmap;
import static com.ilike.pixelgenerator.pixel_fragment.length;
import static com.ilike.pixelgenerator.pixel_fragment.pixelSizeLength;
import static com.ilike.pixelgenerator.pixel_fragment.pixelSizeWidth;
import static com.ilike.pixelgenerator.pixel_fragment.smooth;
import static com.ilike.pixelgenerator.pixel_fragment.width;

public class load_fragment extends Fragment {

    static ImageView drawing;
    static  Button generate;

    static int minR;
    static int minG;
    static int minB;
    static int maxR;
    static int maxG;
    static int maxB;

    static int pixelSizeL;
    static int pixelSizeW;



    static  Random random = new Random();
     static  Paint paint = new Paint();

     static  Bitmap myBitmap;
     static  Canvas canvas;
     static  Bitmap tempBitmap;

     static  Bitmap newBitmap;

     static ConstraintLayout layout;
     static ConstraintSet set;

     public static ProgressBar bar;
     static ProgressDialog dialog;


    public static int percent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_load, container, false);

        paint.setStyle(Paint.Style.STROKE);

        drawing = rootView.findViewById(R.id.bitmap);
        generate = rootView.findViewById(R.id.btn_generate);

        bar = rootView.findViewById(R.id.progressBar);

        pixelSizeL = pixelSizeLength.getProgress();
        pixelSizeW = pixelSizeWidth.getProgress();

       set = new ConstraintSet();

       /*dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIndeterminate(false);

        dialog = ProgressDialog.show(getActivity(), "Generating", dialog.getProgress()
                + " out of " + dialog.getMax() / pixelSize + " pixels generated");
        dialog.setCancelable(true);
        dialog.setProgress(0);
               */


        layout = (ConstraintLayout) rootView.findViewById(R.id.constraintLayout);


        myBitmap = createBitmap(Integer.parseInt(pixel_fragment.width.getText().toString()) / pixelSizeW,
                Integer.parseInt(pixel_fragment.length.getText().toString()) / pixelSizeL, Bitmap.Config.RGB_565);
        canvas = new Canvas(myBitmap);

        tempBitmap  = createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);



        //startDialog();
        paint();

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                paint();
                //startDialog();

            }
        });

        return rootView;
    }
    static String temp = "";
     Context context = getActivity();


    public static Bitmap paint() {
        int r, g, b;

        int pixels = 0;

        drawing.getLayoutParams().height = pixel_fragment.y;
        drawing.getLayoutParams().width = pixel_fragment.x;

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(pixel_fragment.x, pixel_fragment.y);

        minR = (int) pixel_fragment.minimumR.getSelectedMinValue();
        minG = (int) pixel_fragment.minimumG.getSelectedMinValue();
        minB = (int) pixel_fragment.minimumB.getSelectedMinValue();

        maxR = (int) pixel_fragment.minimumR.getSelectedMaxValue();
        maxG = (int) pixel_fragment.minimumG.getSelectedMaxValue();
        maxB = (int) pixel_fragment.minimumB.getSelectedMaxValue();


        //int currentPixel = 0;
        //bar.setProgress(0);


        drawing.setLayoutParams(layoutParams);

        for (int i = 0; i < myBitmap.getHeight(); i++) {
            //dialog.setProgress(currentPixel);
           // drawing.setImageBitmap(newBitmap);


            for (int j = 0; j < myBitmap.getWidth(); j++) {
                r = random.nextInt(maxR - minR + 1) + minR;
                g = random.nextInt(maxG - minG + 1) + minG;
                b = random.nextInt(maxB - minB + 1) + minB;
                myBitmap.setPixel(j, i, Color.rgb(r,g,b));

                //currentPixel = (i+1) * (j+1);
                //dialog.setProgress(currentPixel);
                 //       + " out of " + dialog.getMax() / pixelSize + " pixels generated");
                //if (i * j == (myBitmap.getWidth() * myBitmap.getHeight())) {
                    //dialog.dismiss();
                   // dialog.cancel();
                //}
                //pixels++;
                //if ((myBitmap.getWidth() * myBitmap.getHeight()) / 100 == pixels) {
                    //pixels = 0;
                    //percent++;
                }

            }

        if (smooth.isChecked()) {
            newBitmap = Bitmap.createScaledBitmap(
                    myBitmap,drawing.getLayoutParams().width, drawing.getLayoutParams().height, true);
        } else {
            newBitmap = Bitmap.createScaledBitmap(
                    myBitmap,drawing.getLayoutParams().width, drawing.getLayoutParams().height, false);
        }


        //pixels = 0;
        drawing.setImageBitmap(newBitmap);


       // set.clone(layout);
        // Comment out line above and uncomment line below to make the connection.
       // set.connect(R.id.bitmap, ConstraintSet.TOP, R.id.layout, ConstraintSet.TOP, 0);
       // set.connect(R.id.bitmap, ConstraintSet.BOTTOM, R.id.btn_generate, ConstraintSet.BOTTOM, 0);
       // set.connect(R.id.bitmap, ConstraintSet.LEFT, R.id.layout, ConstraintSet.LEFT, 0);
        //set.connect(R.id.bitmap, ConstraintSet.RIGHT, R.id.btn_generate, ConstraintSet.RIGHT, 0);


        //set.applyTo(layout);

        //bar.setProgress(0);
        return newBitmap;

    }

/*
    public static void refresh(){
        final Handler progressBarHandler = new Handler();

        progressBarHandler.post(new Runnable() {
            public void run() {

                bar.setProgress(percent);
                bar.setProgress(0);
            }
        }); */

/*

    public static void startDialog() {

        //start a new thread to process job
        new Thread(new Runnable() {
            @Override
            public void run() {

                dialog.setMax((Integer.parseInt(width.getText().toString()) * Integer.parseInt(length.getText().toString())) / pixelSize);
                paint();

                dialog.dismiss();
                dialog.cancel();

                //heavy job here
                //send message to main thread
            }
        }).start();
    }


*/


    }




