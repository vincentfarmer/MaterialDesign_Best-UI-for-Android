package com.example.zacharywu.materialdesign;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //声明带左侧NavigationView的DrawerLayout成员变量
    private DrawerLayout mDrawerLayout;

    //初始化停车场对象数组
    private ParkingLot[] parkingLots = {
            new ParkingLot("停车场1", R.drawable.parkinglot_1),
            new ParkingLot("停车场2", R.drawable.parkinglot_2),
            new ParkingLot("停车场3", R.drawable.parkinglot_3),
            new ParkingLot("停车场4", R.drawable.parkinglot_4),
            new ParkingLot("停车场5", R.drawable.parkinglot_5),
            new ParkingLot("停车场6", R.drawable.parkinglot_6),
            new ParkingLot("停车场7", R.drawable.parkinglot_7),
            new ParkingLot("停车场9", R.drawable.parkinglot_8),
    };

    private List<ParkingLot> parkingLotList = new ArrayList<>();

    private ParkingLotAdapter adapter;

    //声明下拉刷新布局类
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //将顶部toolbar布局文件实例化为对象
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //将对象传入Activity之中
        setSupportActionBar(toolbar);
        //将DrawerLayout布局文件实例化为对象
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        //将NavigationView布局实例化为对象
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        //获取顶端actionBar队形
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //显示导航按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //实例化布局文件为导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //默认选中phone选项
        navigationView.setCheckedItem(R.id.nav_phone);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //初始化fab按钮的布局文件为对象，并未按钮添加点击事件
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this,"data restored",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
        //初始化initParkingLots，将我们卸载前面的数据加载到ParkingLotList中去
        initParkingLots();
        //实例化recyclerView布局为对象
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ParkingLotAdapter(parkingLotList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化顶部toolbar的menu菜单栏
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     *处理各个按钮的点击事件
     * @param menuItem
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            //HomeAsUp导航按钮的id永远都是android.R.id.home
            case android.R.id.home:
                //展示左侧滑动菜单，保证和XML文件一致，使用GravityCompat.START
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this,"You click Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"You click delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"You click settings", Toast.LENGTH_SHORT).show();
                break;
                default:
        }
        return true;
    }
    public void initParkingLots(){
        parkingLotList.clear();
        for(int i = 0; i < 50; i++){
            Random random = new Random();
            int index = random.nextInt(parkingLots.length);
            parkingLotList.add(parkingLots[index]);
        }
    }

    /**
     * 初始化SwipeRefreshLayout，其中recyclerView是它的子控件，SwipeRefreshLayout的功能是为它的子控件添加下拉刷新的功能
     */
    public void initSwipeRefreshLayout(){
        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
    }
}
