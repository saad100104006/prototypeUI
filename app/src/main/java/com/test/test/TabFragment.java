package com.test.test;

import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Ratan on 7/27/2015.
 */
public class TabFragment extends Fragment {

    public  TabLayout tabLayout;
    public  ViewPager viewPager;
    public  int int_items = 4 ;
    public EditText edtText;
    public ImageButton attachBtn,micBtn,upBtn;
    View textLayout;
    LinearLayout viewPagerLayout;


    private int[] tabIcons = {
            R.drawable.ic_gallery_normal,
            R.drawable.ic_docs_normal,
            R.drawable.ic_contact_normal,
            R.drawable.ic_camera_normal
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
            View x =  inflater.inflate(R.layout.tab_layout,null);
            tabLayout = (TabLayout) x.findViewById(R.id.tabs);
            viewPager = (ViewPager) x.findViewById(R.id.viewpager);
            edtText= (EditText) x.findViewById(R.id.edt_text);
        micBtn= (ImageButton) x.findViewById(R.id.mic_btn);
        upBtn= (ImageButton) x.findViewById(R.id.up_btn);
        attachBtn= (ImageButton) x.findViewById(R.id.attach_button);

        textLayout= (View) x.findViewById(R.id.text_layout);
        viewPagerLayout= (LinearLayout) x.findViewById(R.id.viewpagerLayout);



        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

                    tabLayout.setupWithViewPager(viewPager);



        setupTabIcons();

        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(getActivity(), R.color.highlight_blue);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(getActivity(), R.color.battleship);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.highlight_blue), PorterDuff.Mode.SRC_IN);

        attachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textLayout.setVisibility(View.GONE);
                viewPagerLayout.setVisibility(View.VISIBLE);



            }
        });


        edtText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                {
                    micBtn.setVisibility(View.GONE);
                    upBtn.setVisibility(View.VISIBLE);
                }
                else{
                    micBtn.setVisibility(View.VISIBLE);
                    upBtn.setVisibility(View.GONE);
                }

            }
        });





        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
          switch (position){
              case 0 : return new PrimaryFragment();
              case 1 : return new SecondFragment();
              case 2 : return new ThirdFragment();
              case 3 : return new FourthFragment();
          }
        return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

                return null;
        }



    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }
}
