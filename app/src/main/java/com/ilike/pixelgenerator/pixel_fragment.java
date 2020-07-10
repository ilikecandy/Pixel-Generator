package com.ilike.pixelgenerator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.warkiz.tickseekbar.OnSeekChangeListener;
import com.warkiz.tickseekbar.SeekParams;
import com.warkiz.tickseekbar.TickSeekBar;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import static com.ilike.pixelgenerator.load_fragment.pixelSizeL;
import static com.ilike.pixelgenerator.load_fragment.pixelSizeW;

public class pixel_fragment extends Fragment {


    static EditText length;
    static EditText width;

    static Switch smooth;
    static Switch square;


    static TickSeekBar pixelSizeLength;
    static TickSeekBar pixelSizeWidth;
    static RangeSeekBar minimumR;
    static RangeSeekBar minimumG;
    static RangeSeekBar minimumB;
    static int x;
    static int y;
    Button current;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pixel, container, false);

        length = rootView.findViewById(R.id.length);
        width = rootView.findViewById(R.id.width);


        smooth = rootView.findViewById(R.id.smooth);
        square = rootView.findViewById(R.id.square);

        current = rootView.findViewById(R.id.current);

        minimumR = rootView.findViewById(R.id.minimumR);
        minimumG = rootView.findViewById(R.id.minimumG);
        minimumB = rootView.findViewById(R.id.minimumB);


        final String[] screenData = getScreenDimension();


        pixelSizeLength = rootView.findViewById(R.id.pixelLength);
        pixelSizeWidth = rootView.findViewById(R.id.pixelWidth);


        length.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!length.getText().toString().equals("")) {
                    pixelSizeLength.setMax(Integer.parseInt(length.getText().toString()));
                    y = Integer.parseInt(length.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        width.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!width.getText().toString().equals("")) {
                    pixelSizeWidth.setMax(Integer.parseInt(width.getText().toString()));
                    x = Integer.parseInt(width.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        pixelSizeLength.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                if (square.isChecked()) {
                    if (pixelSizeLength.getProgress() <= pixelSizeWidth.getMax()) {
                        pixelSizeWidth.setProgress(pixelSizeLength.getProgress());
                        pixelSizeW = pixelSizeLength.getProgress();
                        pixelSizeL = pixelSizeLength.getProgress();
                    }
                }
                pixelSizeL = pixelSizeLength.getProgress();

            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {

            }
        });


        pixelSizeWidth.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                if (square.isChecked()) {
                    if (pixelSizeWidth.getProgress() <= pixelSizeLength.getMax()) {
                        pixelSizeLength.setProgress(pixelSizeWidth.getProgress());
                        pixelSizeL = pixelSizeWidth.getProgress();
                        pixelSizeW = pixelSizeWidth.getProgress();
                    }
                }
                pixelSizeW = pixelSizeWidth.getProgress();
            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {

            }
        });

        square.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (square.isChecked()) {
                    if (y > x) {
                        pixelSizeLength.setMax(x);
                    } else if (x > y) {
                        pixelSizeWidth.setMax(y);
                    }

                    if (pixelSizeL > pixelSizeW) {
                        pixelSizeL = pixelSizeW;
                        pixelSizeLength.setProgress(pixelSizeWidth.getProgress());
                    } else {
                        pixelSizeWidth.setMax(x);
                        pixelSizeLength.setMax(y);

                        pixelSizeW = pixelSizeL;
                        pixelSizeWidth.setProgress(pixelSizeLength.getProgress());
                    }
                }
            }
        });


        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                length.setText(screenData[1]);
                width.setText(screenData[0]);
            }
        });

        refreshXY();


        return rootView;
    }

    private String[] getScreenDimension() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        String[] screenInformation = new String[3];
        screenInformation[0] = String.valueOf(width);
        screenInformation[1] = String.valueOf(height);

        return screenInformation;
    }

    public static void refreshXY() {
        x = Integer.parseInt(width.getText().toString());
        y = Integer.parseInt(length.getText().toString());
    }
}


