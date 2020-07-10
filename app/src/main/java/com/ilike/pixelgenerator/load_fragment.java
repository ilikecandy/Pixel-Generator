package com.ilike.pixelgenerator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.graphics.Bitmap.createBitmap;
import static com.ilike.pixelgenerator.pixel_fragment.pixelSizeLength;
import static com.ilike.pixelgenerator.pixel_fragment.pixelSizeWidth;

public class load_fragment extends Fragment {


    public static View rootView;
    static int pixelSizeL;
    static int pixelSizeW;
    static Paint paint = new Paint();
    static Canvas canvas;
    static Bitmap tempBitmap;
    static ConstraintLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_load, container, false);

        paint.setStyle(Paint.Style.STROKE);


        pixelSizeL = pixelSizeLength.getProgress();
        pixelSizeW = pixelSizeWidth.getProgress();


        layout = rootView.findViewById(R.id.constraintLayout);


        final PaintCanvas pc = new PaintCanvas();
        pc.myBitmap = createBitmap(Integer.parseInt(pixel_fragment.width.getText().toString()) / pixelSizeW,
                Integer.parseInt(pixel_fragment.length.getText().toString()) / pixelSizeL, Bitmap.Config.RGB_565);
        canvas = new Canvas(pc.myBitmap);

        tempBitmap = createBitmap(pc.myBitmap.getWidth(), pc.myBitmap.getHeight(), Bitmap.Config.RGB_565);


        //startDialog();
        pc.PaintCanvas(rootView, getContext());

        pc.generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pc.PaintCanvas(rootView, getContext());
                //startDialog();
            }
        });

        return rootView;
    }


}




