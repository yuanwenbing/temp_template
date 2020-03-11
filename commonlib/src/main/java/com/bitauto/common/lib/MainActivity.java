package com.bitauto.common.lib;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.bitauto.android.commonlib.R;
import com.bitauto.common.lib.widget.TabViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        TabViewPager viewPager = findViewById(R.id.home_viewpager);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bv_home_navigation);
        viewPager.setupBottomNavigationView(bottomNavigationView);

        List<TabFragment> fragments = new ArrayList<>();
        Menu menu = bottomNavigationView.getMenu();
        for(int i = 0; i< menu.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("title", menu.getItem(i).getTitle().toString());
            TabFragment tabFragment = TabFragment.create(bundle);
            fragments.add(tabFragment);
        }

        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments));


    }

    class TabAdapter extends FragmentPagerAdapter {

        private List<TabFragment> mFragments;

        public TabAdapter(FragmentManager fm, @NonNull List<TabFragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


}
