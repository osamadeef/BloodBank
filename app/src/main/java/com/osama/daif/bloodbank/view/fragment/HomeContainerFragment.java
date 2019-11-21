package com.osama.daif.bloodbank.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.osama.daif.bloodbank.R;
import com.osama.daif.bloodbank.adapter.ViewPagerWithFragmentAdapter;
import com.osama.daif.bloodbank.view.fragment.homeCycle.donation.CreateDonationFragment;
import com.osama.daif.bloodbank.view.fragment.homeCycle.donation.DonationListFragment;
import com.osama.daif.bloodbank.view.fragment.homeCycle.post.PostDetailsFragment;
import com.osama.daif.bloodbank.view.fragment.homeCycle.post.PostsAndFavoritesListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.osama.daif.bloodbank.helper.HelperMethods.replaceFragment;

public class HomeContainerFragment extends BaseFragment {

    @BindView(R.id.home_container_fragment_tl_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.home_container_fragment_f_a_btn_add)
    FloatingActionButton fab;
    @BindView(R.id.home_container_fragment_vp_view_pager)
    ViewPager tabViewPager;
    private long backPressedTime;
    private Toast backToast;

    private Unbinder unbinder = null;

    public HomeContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home_screen, container, false);
        unbinder = ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        initFragment();


        setupViewPager(tabViewPager);
        tabLayout.setupWithViewPager(tabViewPager);

        if (tabLayout.getSelectedTabPosition() == 0) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(MainActivity.this, NoteEditorActivity.class);
//                    startActivity(intent);
                }
            });
        }
        tabViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        fab.show();
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_container_fr_frame, new PostDetailsFragment());
                            }
                        });
                        break;

                    case 1:
                        fab.show();
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_container_fr_frame, new CreateDonationFragment());
                            }
                        });
                        break;

                    default:
                        fab.hide();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerWithFragmentAdapter adapter = new ViewPagerWithFragmentAdapter(getActivity().getSupportFragmentManager());
        adapter.addPager(new PostsAndFavoritesListFragment(), getString(R.string.posts));
        adapter.addPager(new DonationListFragment(), getResources().getString(R.string.donation));

        viewPager.setAdapter(adapter);
    }
    @Override
    public void onBack() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBack();
            return;
        } else {
            backToast = Toast.makeText(getActivity(), getResources().getString(R.string.Press_back_again), Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}