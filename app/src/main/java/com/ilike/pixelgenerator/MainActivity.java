package com.ilike.pixelgenerator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.ilike.pixelgenerator.Fragment_Options.pixelSizeL;
import static com.ilike.pixelgenerator.Fragment_Options.pixelSizeW;
import static com.ilike.pixelgenerator.Fragment_Options.tsbPixelLength;
import static com.ilike.pixelgenerator.Fragment_Options.tsbPixelWidth;

public class MainActivity extends AppCompatActivity {
    private static View rootView;
    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    FloatingActionButton backFab;

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

        backFab = findViewById(R.id.fab2);
        backFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                visibleSlide = i;
                invalidateOptionsMenu();

                if (i == 0) {
                    backFab.hide();
                } else {
                    backFab.show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

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

        if (id == R.id.action_save) {
            SaveDialog sd = new SaveDialog();
            sd.showDialog(this, this);

            return true;
        } else if (id == R.id.action_bg) {
            WallpaperDialog wd = new WallpaperDialog();
            wd.showDialog(this, this);
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
            try {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("About")
                        .setMessage("Pixel Generator " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName)

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
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    static int visibleSlide = -1;

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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem bg = menu.findItem(R.id.action_bg);
        MenuItem save = menu.findItem(R.id.action_save);

        if (visibleSlide == 3) {
            bg.setVisible(true);
            save.setVisible(true);
        } else {
            bg.setVisible(false);
            save.setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

}

