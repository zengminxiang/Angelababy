package com.zmx.angelababy;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zmx.angelababy.adapter.MainFragmentPagerAdapter;
import com.zmx.angelababy.fragment.MainFragment;
import com.zmx.angelababy.mvp.bean.GirlMessageBean;
import com.zmx.angelababy.mvp.presenter.MainPresenter;
import com.zmx.angelababy.mvp.view.MainView;
import com.zmx.angelababy.ui.LoginActivity;
import com.zmx.angelababy.ui.MyAppointmentActivity;
import com.zmx.angelababy.ui.MyWalletActivity;
import com.zmx.angelababy.ui.SetupActivity;
import com.zmx.angelababy.utils.view.CustPagerTransformer;
import com.zmx.angelababy.utils.view.ImageViewUtil;
import com.zmx.angelababy.utils.view.PopupWindowHelper;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * 开发者：胖胖祥
 * 时间：2016/10/28 19:48
 * 功能模块：主界面
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainView,View.OnClickListener,PopupWindowHelper.MyOnClickListener {

    private ViewPager viewPager;
    private List<MainFragment> fragments = new ArrayList<>(); // 供ViewPager使用
    private List<GirlMessageBean> gmbs = new ArrayList<>();
    private MainFragmentPagerAdapter adapter;

    private NavigationView navigationView;//左侧布局
    private View headerLayout;//左侧头部
    private ImageViewUtil head;//用户头像
    private TextView text_name;//用户名称

    private MenuItem  gMenuItem=null;//动态修改菜单栏的文字

    private MainPresenter mp;

    private TextView my_hobby,age_text,occupation_text,evaluate_text;//弹出框：我的爱好，年龄，职业，评价
    private ProgressBar login_load;

    private RelativeLayout error_layout;//加载错误布局5656623123999
    private Button load_button;

    private final int LOGIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Resources resource=(Resources)getBaseContext().getResources();
        ColorStateList csl=(ColorStateList)resource.getColorStateList(R.color.navigation_menu_item_color);
        navigationView.setItemTextColor(csl);
        /**设置MenuItem默认选中项**/
        navigationView.getMenu().getItem(0).setChecked(true);

        headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        head = (ImageViewUtil) headerLayout.findViewById(R.id.head);
        text_name = (TextView) headerLayout.findViewById(R.id.name);
        getUserMessage();//获取用户资料

        my_hobby = (TextView) findViewById(R.id.my_hobby);
        my_hobby.setOnClickListener(this);
        error_layout = (RelativeLayout) findViewById(R.id.error_layout);
        load_button = (Button) findViewById(R.id.load_button);
        load_button.setOnClickListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        mp = new MainPresenter(this,this);
        mp.getGrilMessageList("0","10");

        login_load = (ProgressBar) findViewById(R.id.login_load);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_pubish) {

            //判断是否登录
            if(SharePreferenceUtil.getInstance(this).getString(SharePreferenceUtil.session_id,"").equals("")){

                startActivity(LoginActivity.class,null,LOGIN);

            }else{



            }



        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            //判断是否登录
            if(SharePreferenceUtil.getInstance(this).getString(SharePreferenceUtil.session_id,"").equals("")){

                startActivity(LoginActivity.class,null,LOGIN);

            }else{

                Intent intent = new Intent(this, MyAppointmentActivity.class);
                startActivity(intent);

            }



        } else if (id == R.id.nav_gallery) {

            //判断是否登录
            if(SharePreferenceUtil.getInstance(this).getString(SharePreferenceUtil.session_id,"").equals("")){

                startActivity(LoginActivity.class,null,LOGIN);

            }else{

                Intent intent = new Intent(this, MyWalletActivity.class);
                startActivity(intent);

            }

        } else if (id == R.id.nav_slideshow) {


            //判断是否登录
            if(SharePreferenceUtil.getInstance(this).getString(SharePreferenceUtil.session_id,"").equals("")){

                startActivity(LoginActivity.class,null,LOGIN);

            }else{

                Intent intent = new Intent(this, SetupActivity.class);
                startActivity(intent);

            }


        }else if (id == R.id.nav_service) {

            Toast.makeText(this,"等待接口开放",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_send) {

            SharePreferenceUtil.getInstance(this).clear();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 1:

                    login_load.setVisibility(View.GONE);

                    if(adapter == null){

                        // 1. viewPager添加parallax效果，使用PageTransformer就足够了
                        viewPager.setPageTransformer(false, new CustPagerTransformer(MainActivity.this));
                        viewPager.setOffscreenPageLimit(fragments.size());//卡片数量
                        viewPager.setPageMargin(-20);//两个卡片之间的距离，单位dp
                        if (viewPager!=null){
                            viewPager.removeAllViews();
                        }

                        adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments,gmbs);
                        viewPager.setAdapter(adapter);

                    }else{

                        // 2. viewPager添加adapter
                        adapter.notifyDataSetChanged();

                    }


                    break;


            }

        }
    };


    @Override
    public void VGetGirlMessage(List<GirlMessageBean> lists, boolean no_have) {

        for (GirlMessageBean gb:lists){

            gmbs.add(gb);
            fragments.add(new MainFragment());

            Log.e("数据","名称："+gb.getUser_name());

        }

        handler.sendEmptyMessage(1);

    }

    @Override
    public void VError() {

        error_layout.setVisibility(View.VISIBLE);
        login_load.setVisibility(View.GONE);

    }

    private Dialog dialog;//操作按钮弹出框
    private View dialog_operation;//操作的view

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.my_hobby:

                myHobby();

                break;

            //点击选择年龄
            case R.id.age_text:


                popView = LayoutInflater.from(this).inflate(R.layout.popupview, null);
                layout_dialog = (LinearLayout) popView.findViewById(R.id.layout_dialog);

                layout_dialog.addView(addAgeTextView("18岁-21岁",0));
                layout_dialog.addView(addAgeTextView("22岁-25岁",0));
                layout_dialog.addView(addAgeTextView("26岁-30岁",0));
                layout_dialog.addView(addAgeTextView("31岁-40岁",50));

                popupWindowHelper = new PopupWindowHelper(popView);
                popupWindowHelper.setMyOnClickListener(this);
                popupWindowHelper.showAsPopUp(age_text);
                setTextImage(R.mipmap.icon_up,age_text);

                break;

            case R.id.occupation_text:

                popView = LayoutInflater.from(this).inflate(R.layout.popupview, null);
                layout_dialog = (LinearLayout) popView.findViewById(R.id.layout_dialog);

                layout_dialog.addView(addOccupationTextView("学生",0));
                layout_dialog.addView(addOccupationTextView("老师",0));
                layout_dialog.addView(addOccupationTextView("白领",0));
                layout_dialog.addView(addOccupationTextView("少妇",50));

                popupWindowHelper = new PopupWindowHelper(popView);
                popupWindowHelper.setMyOnClickListener(this);

                popupWindowHelper.showAsPopUp(occupation_text);
                setTextImage(R.mipmap.icon_up,occupation_text);

                break;

            case R.id.evaluate_text:

                popView = LayoutInflater.from(this).inflate(R.layout.popupview, null);
                layout_dialog = (LinearLayout) popView.findViewById(R.id.layout_dialog);

                layout_dialog.addView(addEvaluateTextView("90分以上",0));
                layout_dialog.addView(addEvaluateTextView("80分以上",0));
                layout_dialog.addView(addEvaluateTextView("70分以上",0));
                layout_dialog.addView(addEvaluateTextView("60分以上",50));

                popupWindowHelper = new PopupWindowHelper(popView);
                popupWindowHelper.setMyOnClickListener(this);

                popupWindowHelper.showAsPopUp(evaluate_text);
                setTextImage(R.mipmap.icon_up,evaluate_text);

                break;

            //重新加载
            case R.id.load_button:

                error_layout.setVisibility(View.GONE);
                login_load.setVisibility(View.VISIBLE);
                mp.getGrilMessageList("0","10");

                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle bundle;

        switch (resultCode){

            case LOGIN:

                Log.e("进来了","进来了");
                getUserMessage();

                break;

        }


    }

    /**
     * 获取用户资料
     */
    public void getUserMessage(){

        if(SharePreferenceUtil.getInstance(this).getString(SharePreferenceUtil.session_id,"").equals("")){

            text_name.setText("请登录......");

        }else{

            text_name.setText(SharePreferenceUtil.getInstance(this).getString(SharePreferenceUtil.user_name,""));

        }

    }

    /**
     * 通过Class跳转界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    protected void startActivity(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 弹出爱好选择
     */
    public void myHobby() {

        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        dialog_operation = LayoutInflater.from(this).inflate(R.layout.dialog_my_hobby, null);

        age_text = (TextView) dialog_operation.findViewById(R.id.age_text);
        age_text.setOnClickListener(this);

        occupation_text = (TextView) dialog_operation.findViewById(R.id.occupation_text);
        occupation_text.setOnClickListener(this);

        evaluate_text = (TextView) dialog_operation.findViewById(R.id.evaluate_text);
        evaluate_text.setOnClickListener(this);

        //将布局设置给Dialog
        dialog.setContentView(dialog_operation);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.y = 20;//设置Dialog距离底部的距离

//// 以下这两句是为了保证按钮可以水平满屏
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
// 设置显示位置
        dialog.onWindowAttributesChanged(lp);
//       将属性设置给窗体
        dialog.show();//显示对话框
    }

    private PopupWindowHelper popupWindowHelper;
    private View popView;
    private LinearLayout layout_dialog;

    /**
     * 动态添加textview（）
     * @param str
     * @return
     */
    public View addAgeTextView(final String str,int bottom){

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 50, 0, bottom);

        TextView  text=(TextView) LayoutInflater.from(this).inflate( R.layout.dialog_textview, null);
        text.setLayoutParams(lp);
        text.setText(str);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowHelper.dismiss();
                setTextImage(R.mipmap.icon_down,age_text);
                age_text.setText(str);
            }
        });

        return text;
    }


    /**
     * 动态添加textview（）
     * @param str
     * @return
     */
    public View addOccupationTextView(final String str,int bottom){

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 50, 0, bottom);

        TextView  text=(TextView) LayoutInflater.from(this).inflate( R.layout.dialog_textview, null);
        text.setLayoutParams(lp);
        text.setText(str);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowHelper.dismiss();
                setTextImage(R.mipmap.icon_down,occupation_text);
                occupation_text.setText(str);
            }
        });

        return text;
    }
    /**
     * 动态添加textview（）
     * @param str
     * @return
     */
    public View addEvaluateTextView(final String str,int bottom){

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 50, 0, bottom);

        TextView  text=(TextView) LayoutInflater.from(this).inflate( R.layout.dialog_textview, null);
        text.setLayoutParams(lp);
        text.setText(str);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowHelper.dismiss();
                setTextImage(R.mipmap.icon_down,evaluate_text);
                evaluate_text.setText(str);
            }
        });

        return text;
    }

    /**
     * 给TextView右边设置图片
     * @param resId
     */
    private void setTextImage(int resId,TextView v) {

        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());// 必须设置图片大小，否则不显示
        v.setCompoundDrawables(null, null, drawable, null);

    }


    @Override
    public void setMyOnClickListener() {

        setTextImage(R.mipmap.icon_down,age_text);
        setTextImage(R.mipmap.icon_down,occupation_text);

    }

}
