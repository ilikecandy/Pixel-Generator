package com.ilike.pixelgenerator;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;

import com.warkiz.tickseekbar.OnSeekChangeListener;
import com.warkiz.tickseekbar.SeekParams;
import com.warkiz.tickseekbar.TickSeekBar;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;

public class Fragment_Options extends Fragment {

    static EditText editLength;
    static EditText editWidth;

    static Switch switchSmooth;
    static Switch switchSquare;

    static TickSeekBar tsbPixelLength;
    static TickSeekBar tsbPixelWidth;

    static RangeSeekBar rsbMinRed;
    static RangeSeekBar rsbMinGreen;
    static RangeSeekBar rsbMinBlue;

    static int resX;
    static int resY;
    static int pixelSizeL;
    static int pixelSizeW;

    static int[] minRGB = new int[3];
    static int[] maxRGB = new int[3];

    Button btnCurrentRes;

    public static void refreshXY() {
        if (!String.valueOf(editLength.getText()).equals("") && !String.valueOf(editLength.getText()).equals("0")) {
            resY = Integer.parseInt(editLength.getText().toString());
        }

        if (!String.valueOf(editWidth.getText()).equals("") && !String.valueOf(editWidth.getText()).equals("0")) {
            resX = Integer.parseInt(editWidth.getText().toString());
        }

        tsbPixelWidth.setTickCount(findFactors(resX).length);
        tsbPixelWidth.customTickTexts(findFactors(resX));
        tsbPixelWidth.

        tsbPixelWidth.setTickCount(findFactors(resX).length);
        tsbPixelWidth.customTickTexts(findFactors(resX));
    }

    public static void refreshPixelsXY() {
        pixelSizeL = tsbPixelLength.getProgress();
        pixelSizeW = tsbPixelWidth.getProgress();
    }

    public static void refreshRGB() {
        minRGB[0] = (int) Fragment_Options.rsbMinRed.getSelectedMinValue();
        minRGB[1] = (int) Fragment_Options.rsbMinGreen.getSelectedMinValue();
        minRGB[2] = (int) Fragment_Options.rsbMinBlue.getSelectedMinValue();

        maxRGB[0] = (int) Fragment_Options.rsbMinRed.getSelectedMaxValue();
        maxRGB[1] = (int) Fragment_Options.rsbMinGreen.getSelectedMaxValue();
        maxRGB[2] = (int) Fragment_Options.rsbMinBlue.getSelectedMaxValue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pixel, container, false);

        editLength = rootView.findViewById(R.id.edit_length);
        editWidth = rootView.findViewById(R.id.edit_width);

        switchSmooth = rootView.findViewById(R.id.smooth);
        switchSquare = rootView.findViewById(R.id.switch_square);

        btnCurrentRes = rootView.findViewById(R.id.btn_currentres);

        rsbMinRed = rootView.findViewById(R.id.rsb_minR);
        rsbMinGreen = rootView.findViewById(R.id.rsb_minG);
        rsbMinBlue = rootView.findViewById(R.id.rsb_minB);

        tsbPixelLength = rootView.findViewById(R.id.tsb_pixelL);
        tsbPixelWidth = rootView.findViewById(R.id.tsb_pixelW);


        if (pixelSizeL != 0) {
            tsbPixelLength.setMax(pixelSizeL);
        }

        if (pixelSizeW != 0) {
            tsbPixelWidth.setMax(pixelSizeW);
        }

        switchSmooth.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(85, 139, 47)));
        switchSmooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchSmooth.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(85, 139, 47)));
                } else {
                    switchSmooth.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255, 138, 101)));
                }
            }
        });

        switchSquare.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchSquare.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(85, 139, 47)));
                } else {
                    switchSquare.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255, 138, 101)));
                }
            }
        });


        refreshRGB();

        rsbMinRed.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                refreshRGB();
            }
        });

        rsbMinGreen.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                refreshRGB();
            }
        });

        rsbMinBlue.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                refreshRGB();
            }
        });

        editLength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                refreshXY();
                tsbPixelLength.setMax(Integer.parseInt(String.valueOf(resY)));

                if (String.valueOf(editLength.getText()).equals("")) {
                    tsbPixelLength.setMax(1);
                } else if (String.valueOf(editLength.getText()).equals("0")) {
                    tsbPixelLength.setMax(1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editWidth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                refreshXY();
                tsbPixelWidth.setMax(Integer.parseInt(String.valueOf(resX)));

                if (String.valueOf(editWidth.getText()).equals("")) {
                    tsbPixelWidth.setMax(1);
                } else if (String.valueOf(editWidth.getText()).equals("0")) {
                    tsbPixelWidth.setMax(1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        tsbPixelLength.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                if (switchSquare.isChecked()) {
                    if (tsbPixelLength.getProgress() <= tsbPixelWidth.getMax()) {
                        tsbPixelWidth.setProgress(tsbPixelLength.getProgress());
                        pixelSizeW = tsbPixelLength.getProgress();
                        pixelSizeL = tsbPixelLength.getProgress();
                    }
                }
                refreshPixelsXY();
            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {

            }
        });


        tsbPixelWidth.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                if (switchSquare.isChecked()) {
                    if (tsbPixelWidth.getProgress() <= tsbPixelLength.getMax()) {
                        tsbPixelLength.setProgress(tsbPixelWidth.getProgress());
                        pixelSizeL = tsbPixelWidth.getProgress();
                        pixelSizeW = tsbPixelWidth.getProgress();
                    }
                }
                refreshPixelsXY();
            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {

            }
        });

        switchSquare.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchSquare.isChecked()) {
                    if (resY > resX) {
                        tsbPixelLength.setMax(resX);
                    } else if (resX > resY) {
                        tsbPixelWidth.setMax(resY);
                    }

                    if (pixelSizeL > pixelSizeW) {
                        pixelSizeL = pixelSizeW;
                        tsbPixelLength.setProgress(tsbPixelWidth.getProgress());
                    } else {
                        tsbPixelWidth.setMax(resX);
                        tsbPixelLength.setMax(resY);

                        pixelSizeW = pixelSizeL;
                        tsbPixelWidth.setProgress(tsbPixelLength.getProgress());
                    }
                }
            }
        });

        btnCurrentRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] screenData = getScreenDimension();
                editLength.setText(screenData[1]);
                editWidth.setText(screenData[0]);
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

    private static String[] findFactors(int number) {
        ArrayList<String> factors = new ArrayList<>();
        for (int i = 1; i <= number; ++i) {

            if (number % i == 0) {
                factors.add(String.valueOf(i));
            }
        }

        String[] factorsArray = factors.toArray(new String[factors.size()]);

        return factorsArray;
    }
}
