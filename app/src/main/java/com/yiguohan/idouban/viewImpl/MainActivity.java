package com.yiguohan.idouban.viewImpl;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.adapter.ThemeColorAdapter;
import com.yiguohan.idouban.base.ActivityCollector;
import com.yiguohan.idouban.base.BaseActivity;
import com.yiguohan.idouban.base.BaseFragment;
import com.yiguohan.idouban.base.EasyRecyclerViewAdapter;
import com.yiguohan.idouban.bean.home.ThemeColor;
import com.yiguohan.idouban.bean.top250.Root;
import com.yiguohan.idouban.utils.ThemeUtils;
import com.yiguohan.idouban.utils.StatusBarUtil;
import com.yiguohan.idouban.viewImpl.book.BookFragment;
import com.yiguohan.idouban.viewImpl.film.FilmFragment;
import com.yiguohan.idouban.viewImpl.music.MusicFragment;
import com.yiguohan.idouban.viewImpl.other.AboutActivity;
import com.yiguohan.idouban.viewImpl.other.AuthorInfoActivity;
import com.yiguohan.idouban.viewImpl.other.RecommendActivity;
import com.yiguohan.idouban.viewinterface.film.IGetTop250View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements IGetTop250View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawerlayout_home)
    DrawerLayout drawerlayoutHome;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.navigaitonview)
    NavigationView navigationView;
    @BindView(R.id.radiogroup)
    RadioGroup radioGroup;


    private FilmFragment filmFragment;
    private BookFragment bookFragment;
    private MusicFragment musicFragment;
    private List<android.support.v4.app.Fragment> listFragment;
    private int currentFragment;

    private ThemeColorAdapter themeColorAdapter = new ThemeColorAdapter();
    private List<ThemeColor> themeColorList = new ArrayList<ThemeColor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        drawerlayoutHome = (DrawerLayout) findViewById(R.id.drawerlayout_home);
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, drawerlayoutHome, ThemeUtils.getThemeColor());
        initView();
        initViewPagerAndFragment();
        initListener();
        initChangeTheme();
    }

    private void initView() {
        toolbar.setBackgroundColor(ThemeUtils.getThemeColor());
        //DrawerLayout的开关，用来打开关闭DrawerLayout
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayoutHome, toolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        drawerlayoutHome.setDrawerListener(mActionBarDrawerToggle);
        navigationView.inflateHeaderView(R.layout.header_nav);
        View headerView = navigationView.getHeaderView(0);
        CircleImageView sdvHeader = (CircleImageView) headerView.findViewById(R.id.sdv_avatar);
        sdvHeader.setImageResource(R.drawable.ic_avatar);
        navigationView.inflateMenu(R.menu.menu_nav);
        navigationView.setItemIconTintList(ThemeUtils.getNaviItemIconTintList());
        onNavigationViewMenuItemSelected(navigationView);
    }

    private void initViewPagerAndFragment() {
        filmFragment = FilmFragment.newInstance();
        bookFragment = BookFragment.newInstance();
        musicFragment = MusicFragment.newInstance();
        listFragment = new ArrayList<>();
        listFragment.add(filmFragment);
        listFragment.add(bookFragment);
        listFragment.add(musicFragment);
        viewPager.setOffscreenPageLimit(3);//预加载fragment数量
        viewPager.setOnPageChangeListener(onPageChangeListener);

    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    radioGroup.check(R.id.rb_home);
                    break;
                case 1:
                    radioGroup.check(R.id.rb_dynamic);
                    break;
                case 2:
                    radioGroup.check(R.id.rb_message);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        currentFragment = 0;
                        break;
                    case R.id.rb_dynamic:
                        currentFragment = 1;
                        break;
                    case R.id.rb_message:
                        currentFragment = 2;
                        break;
                }
                viewPager.setCurrentItem(currentFragment, false);
            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return listFragment.get(position);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }
        });
    }

    private void initChangeTheme() {
        themeColorAdapter = new ThemeColorAdapter();
        themeColorList.add(new ThemeColor(R.color.theme_red_base));
        themeColorList.add(new ThemeColor(R.color.theme_blue));
        themeColorList.add(new ThemeColor(R.color.theme_blue_light));
        themeColorList.add(new ThemeColor(R.color.theme_black));
        themeColorList.add(new ThemeColor(R.color.theme_teal));
        themeColorList.add(new ThemeColor(R.color.theme_green));
        themeColorList.add(new ThemeColor(R.color.theme_brown));
        themeColorList.add(new ThemeColor(R.color.theme_red));
        themeColorAdapter.setDatas(themeColorList);
        themeColorAdapter.setOnItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int positon, Object data) {
                for (ThemeColor themeColor :
                        themeColorList) {
                    themeColor.setChosen(false);
                }

                themeColorList.get(positon).setChosen(true);
                themeColorAdapter.notifyDataSetChanged();

            }
        });
    }

    private void onNavigationViewMenuItemSelected(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_home:
                        startActivity(AuthorInfoActivity.class);
                        break;
                    case R.id.nav_menu_categories:
                        startActivity(AboutActivity.class);
                        break;
                    case R.id.nav_menu_recommend:
                        startActivity(RecommendActivity.class);
                        break;
                    case R.id.nav_menu_theme:
                        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_theme_color, null, false);
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.theme_recycler_view);
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                        recyclerView.setAdapter(themeColorAdapter);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("主题选择")
                                .setView(view)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ThemeUtils.setThemeColor(getResources().getColor(themeColorList.get(themeColorAdapter.getPosition()).getColor()));
                                        ThemeUtils.setThemePosition(themeColorAdapter.getPosition());

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                ActivityCollector.getInstance().refreshAllActivity();
                                            }
                                        }, 100);
                                    }
                                }).show();
                        break;
                }
                item.setChecked(true);
                return true;
            }
        });
    }

    @Override
    public void getTop250Success(Root root, boolean isLoadMore) {

    }

    @Override
    public void getDataFail() {

    }

    @Override
    public String setActName() {
        return null;
    }
}
