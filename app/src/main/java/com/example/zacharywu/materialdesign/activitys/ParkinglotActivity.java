package com.example.zacharywu.materialdesign.activitys;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.zacharywu.materialdesign.R;

public class ParkinglotActivity extends AppCompatActivity {

    public static final String PARRINGLOT_NAME = "parkinglot_name";
    public static final String PARRINGLOT_IMAGE_ID = "parkinglot_image_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkinglot);
        //通过Intent获取到停车场的名称和图片
        Intent intent = getIntent();
        String parkinglogtName = intent.getStringExtra(PARRINGLOT_NAME);
        int parkinglotImageId = intent.getIntExtra(PARRINGLOT_IMAGE_ID,0);
        //初始化各个控件的实例
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView parkinglotImageView = (ImageView)findViewById(R.id.parkinglot_image_view);
        TextView parkinglotContentText = (TextView)findViewById(R.id.parkinglot_content_text);
        //将toobar作为actionbar显示，别切启用homeASup按钮，默认是一个返回箭头图标
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //将collapsingToolbarLayout的白哦提设置为父页面传过来parkinglogtName
        collapsingToolbar.setTitle(parkinglogtName);
        //使用Glide加载图片
        Glide.with(this).load(parkinglotImageId).into(parkinglotImageView);
        //重复名字500次，并且设置到详情页面上
        String parkinglotContent = generateParkinglotContent(parkinglogtName);
        parkinglotContentText.setText(parkinglotContent);
    }

    /**
     * 创造一个重复五百遍停车场名字的字符串
     * @param parkinglogtName
     * @return
     */
    public String generateParkinglotContent(String parkinglogtName){

        StringBuilder parkinglotContent = new StringBuilder();
        for(int i = 0; i<500; i++){
            parkinglotContent.append(parkinglogtName);
        }
        return parkinglotContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //处理HomeAsUp按钮即返回按钮的点击事件
            case android.R.id.home:
                //关闭当前活动
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
