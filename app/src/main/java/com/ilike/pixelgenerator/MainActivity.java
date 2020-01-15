package com.ilike.pixelgenerator;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.ilike.pixelgenerator.load_fragment.dialog;
import static com.ilike.pixelgenerator.load_fragment.myBitmap;
import static com.ilike.pixelgenerator.load_fragment.newBitmap;
import static com.ilike.pixelgenerator.load_fragment.paint;
import static com.ilike.pixelgenerator.load_fragment.pixelSizeL;
import static com.ilike.pixelgenerator.load_fragment.pixelSizeW;
import static com.ilike.pixelgenerator.load_fragment.set;
import static com.ilike.pixelgenerator.pixel_fragment.length;
import static com.ilike.pixelgenerator.pixel_fragment.pixelSizeLength;
import static com.ilike.pixelgenerator.pixel_fragment.pixelSizeWidth;
import static com.ilike.pixelgenerator.pixel_fragment.width;
import static com.ilike.pixelgenerator.pixel_fragment.x;
import static com.ilike.pixelgenerator.pixel_fragment.y;


public class MainActivity extends AppCompatActivity {

    static String date;

    static String path;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    static SharedPreferences pref;
    static SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        path = "/storage/emulated/0/Pictures/PixelBackgrounds/";



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);





        FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    boolean clicked = false;
                    @Override
                    public void onClick(View view) {

                        //setting buttons start now
                        if (!length.getText().toString().equals("") && !width.getText().toString().equals("")) {
                            if (Integer.parseInt(length.getText().toString()) != 0 &&
                                    Integer.parseInt(width.getText().toString()) != 0) {

                                if (clicked) {
                                    mViewPager.setCurrentItem(4);
                                    Toast t = Toast.makeText(MainActivity.this,
                                            "Regenerating", Toast.LENGTH_SHORT);
                                    t.show();
                                    //load_fragment.startDialog();
                                    paint();
                                    t.cancel();
                                } else {
                                    mViewPager.setCurrentItem(4);
                                    Toast t = Toast.makeText(MainActivity.this,
                                            "Generating", Toast.LENGTH_SHORT);
                                    t.show();
                                    //load_fragment.startDialog();
                                    paint();
                                    t.cancel();
                                    clicked = true;
                                }
                            } else {
                                Toast.makeText(MainActivity.this,
                                        "Check your settings and try again.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Check your settings and try again.", Toast.LENGTH_SHORT).show();
                        }




            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            boolean clicked = false;

            @Override
            public void onClick(View view) {

                //setting buttons start now
                mViewPager.setCurrentItem(0);


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {

            /*
            String name = "test" + "." + ".png";
            FileOutputStream fileOutputStream;
            try {
                FileOutputStream out = this.openFileOutput(name, Context.MODE_PRIVATE);
                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Failed! Try again?", Toast.LENGTH_SHORT).show();

                e.printStackTrace();
            }

             */

/*
            try (FileOutputStream out = new FileOutputStream("/storage/emulated/0/PixelBackgrounds/image")) {
                load_fragment.myBitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                // PNG is a lossless format, the compression factor (100) is ignored
            } catch (IOException e) {
                e.printStackTrace();
            }


            */


            mSectionsPagerAdapter.save();


            return true;
        } else if (id == R.id.action_bg) {
            WallpaperManager wallpaperManager =
                    WallpaperManager.getInstance(getApplicationContext());

            try {
                wallpaperManager.setBitmap(newBitmap);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
            // ^ NEEDS API 24 FOR SPECIFIC SETTING
        } else if (id == R.id.action_settings) {


            try {
                Intent k = new Intent(getApplicationContext(), settings.class);
                startActivity(k);

            } catch (Exception e) {
                e.printStackTrace();
            }

            pixelSizeWidth.setProgress(pixelSizeW);
            pixelSizeLength.setProgress(pixelSizeL);





        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE

            );
        }
    }

    /*


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_pixel, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    } /*

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           switch (position) {
               case 0: pixel_fragment pixel = new pixel_fragment();
                   return pixel;

               case 1: anim_fragment anim = new anim_fragment();
                   return anim;

               case 2: other_fragment other = new other_fragment();
                   return other;

               case 3: load_fragment load = new load_fragment();
                   return load;

                   default:
                       return null;
           }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        public void save() {

            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            date = simpleDateFormat.format(new Date());

            path = pref.getString("location", "/storage/emulated/0/Pictures/PixelBackgrounds/");

            verifyStoragePermissions(MainActivity.this);
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            //File directory = cw.getDir("imageDir", MODE_PRIVATE);
            File appDirectory = new File(path);
            appDirectory.mkdirs();
            File file = new File(path, date + ".png");
            Toast.makeText(MainActivity.this, "Saved to " + path + date + ".png", Toast.LENGTH_SHORT).show();

            Log.d("path", file.toString());
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    newBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }

        }


    }







    }

