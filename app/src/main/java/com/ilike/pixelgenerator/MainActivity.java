package com.ilike.pixelgenerator;

import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import static com.ilike.pixelgenerator.load_fragment.pixelSizeL;
import static com.ilike.pixelgenerator.load_fragment.pixelSizeW;
import static com.ilike.pixelgenerator.pixel_fragment.length;
import static com.ilike.pixelgenerator.pixel_fragment.pixelSizeLength;
import static com.ilike.pixelgenerator.pixel_fragment.pixelSizeWidth;
import static com.ilike.pixelgenerator.pixel_fragment.width;

public class MainActivity extends AppCompatActivity {

    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */

    private static View rootView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rootView = getLayoutInflater().inflate(R.layout.fragment_load, null);

        final PaintCanvas pc = new PaintCanvas();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            boolean clicked = false;

            @Override
            public void onClick(View view1) {

                //setting buttons start now
                if (!length.getText().toString().equals("") && !width.getText().toString().equals("")) {
                    if (Integer.parseInt(length.getText().toString()) != 0 &&
                            Integer.parseInt(width.getText().toString()) != 0) {

                        if (clicked) {
                            Toast t = Toast.makeText(MainActivity.this,
                                    "Done", Toast.LENGTH_SHORT);
                            t.show();
                            mViewPager.setCurrentItem(4);
                            PaintCanvas pc = new PaintCanvas();
                            pc.PaintCanvas(rootView, MainActivity.this);

                        } else {
                            Toast t = Toast.makeText(MainActivity.this,
                                    "Done", Toast.LENGTH_SHORT);
                            t.show();
                            mViewPager.removeAllViews();
                            mViewPager.setCurrentItem(4);
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

        FloatingActionButton fab2 = findViewById(R.id.fab2);
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
        PaintCanvas pc = new PaintCanvas();
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {

            SaveBitmapAsImage.SaveBitmapAsImage(getParent(), getApplicationContext());

            return true;
        } else if (id == R.id.action_bg) {
            WallpaperManager wallpaperManager =
                    WallpaperManager.getInstance(getApplicationContext());

            try {
                wallpaperManager.setBitmap(PaintCanvas.newBitmap);
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
                case 0:
                    pixel_fragment pixel = new pixel_fragment();
                    return pixel;

                case 1:
                    anim_fragment anim = new anim_fragment();
                    return anim;

                case 2:
                    other_fragment other = new other_fragment();
                    return other;

                case 3:
                    load_fragment load = new load_fragment();
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
    }
}

