package com.ilike.pixelgenerator;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
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

import static com.ilike.pixelgenerator.Fragment_Options.pixelSizeL;
import static com.ilike.pixelgenerator.Fragment_Options.pixelSizeW;
import static com.ilike.pixelgenerator.Fragment_Options.tsbPixelLength;
import static com.ilike.pixelgenerator.Fragment_Options.tsbPixelWidth;

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
            @Override
            public void onClick(View view1) {
                mViewPager.setCurrentItem(4);
                Fragment_Canvas.PaintCanvas(getApplicationContext());
            }
        });

        FloatingActionButton backFab = findViewById(R.id.fab2);
        backFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

            SaveBitmapAsImage.SaveBitmapAsImage(getApplicationContext());

            return true;
        } else if (id == R.id.action_bg) {
            WallpaperManager wallpaperManager =
                    WallpaperManager.getInstance(getApplicationContext());

            try {
                wallpaperManager.setBitmap(Fragment_Canvas.myBitmap);
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

            tsbPixelWidth.setProgress(pixelSizeW);
            tsbPixelLength.setProgress(pixelSizeL);
        } else if (id == R.id.action_about) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("About")
                    .setMessage("Pixel Generator v0.1.0")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Thanks for using my app!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setIcon(android.R.drawable.ic_menu_search)
                    .show();
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
                    Fragment_Options pixel = new Fragment_Options();
                    return pixel;

                case 1:
                    anim_fragment anim = new anim_fragment();
                    return anim;

                case 2:
                    other_fragment other = new other_fragment();
                    return other;

                case 3:
                    Fragment_Canvas load = new Fragment_Canvas();
                    return load;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}

