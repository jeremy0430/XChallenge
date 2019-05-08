package com.example.xchallenge;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;

import java.util.ArrayList;
import java.util.List;

import adapter.PersonAdapter;
import bean.Person;
import fragment.StatisticsFragment;
import fragment.StatusFragment;
import utils.TypefaceUtil;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private List<Person> personList = new ArrayList<>();
    private PersonAdapter adapter;
    private MaterialViewPager mViewPager;
    static final int TAPS = 2;
    private View contentViewGroup;

    public KeyguardManager keyguardManager = null;
    public KeyguardManager.KeyguardLock keyguardLock = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TypefaceUtil.replaceFont(this, "fonts/Roboto-Bold.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        //StatusBar
        setStatusBarFullTransparent();
//        setFitSystemWindow(true);

        initKeyguardManager();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });//        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
//            @Override
//            public HeaderDesign getHeaderDesign(int page) {
//                switch (page) {
//                    case 0:
//                        return HeaderDesign.fromColorResAndDrawable(
//                                R.color.transparent, getResources().getDrawable(R.drawable.head_background));
//                    case 1:
//                        return HeaderDesign.fromColorResAndDrawable(
//                                R.color.transparent,
//                                getResources().getDrawable(R.drawable.head_background));
//                }
//                //execute others actions if needed (ex : modify your header logo)
//                return null;
//            }
//        });
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        //设置Adapter
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % TAPS) {
                    case 0:
                        return StatusFragment.newInstance();
                    default:
                        return StatisticsFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return TAPS;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % TAPS) {
                    case 0:
                        return "STATUS";
                    case 1:
                        return "STATISTICS";
                    default:
                        return "TAPN";
                }
            }
        });
        //设置setViewPager
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

    }
//        initPeople();
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
//        recyclerView.setLayoutManager(layoutManager);
//        FragmentManager fm = getSupportFragmentManager();
//        adapter = new PersonAdapter(fm,personList);
//        recyclerView.setAdapter(adapter);
//    }

//        public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                Toast.makeText(this, "You clicked android.R.id.home", Toast.LENGTH_SHORT).
                        show();
                break;
//            case R.id.backup:
//            MyAlertDialogFragment fragment = MyAlertDialogFragment.newInstance("你的样子");
//            fragment.show(getSupportFragmentManager(), "myAlert");
//                break;
//            case R.id.delete:
//                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).
//                        show();
//                break;
//            case R.id.settings:
//                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).
//                        show();
//                break;
            default:
        }
        return true;
    }

    private void initPeople() {
        personList.clear();
        Person tony = new Person("Tony", R.drawable.person_tony, "在线");
        personList.add(tony);
        Person tom = new Person("Tom", R.drawable.person_tom, "在线");
        personList.add(tom);
        Person ella = new Person("Ella", R.drawable.person_ella, "在线");
        personList.add(ella);
        Person tony1 = new Person("Tony", R.drawable.person_tony, "在线");
        personList.add(tony1);
        Person tom1 = new Person("Tom", R.drawable.person_tom, "在线");
        personList.add(tom1);
        Person ella1 = new Person("Ella", R.drawable.person_ella, "在线");
        personList.add(ella1);
    }

    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    protected void setFitSystemWindow(boolean fitSystemWindow) {
        if (contentViewGroup == null) {
            contentViewGroup = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }
        contentViewGroup.setFitsSystemWindows(fitSystemWindow);
    }
    private void initKeyguardManager() {
        keyguardManager =
                (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        keyguardLock = keyguardManager.newKeyguardLock("");
        keyguardLock.disableKeyguard();//取消系统锁屏
        startService(new Intent(MainActivity.this, LockService.class));
    }

}
