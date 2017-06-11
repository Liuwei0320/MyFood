package com.example.liuwei112.Ordering.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.liuwei112.Ordering.fragment.FragmentAdd;
import com.example.liuwei112.Ordering.fragment.FragmentSearch;
import com.example.liuwei112.Ordering.fragment.FragmentUser;
import com.example.liuwei112.Ordering.fragment.FragmentHome;
import com.example.liuwei112.myfood.R;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private LinearLayout mTabHome;
    private LinearLayout mTabAdd;
    private LinearLayout mTabSearch;
    private LinearLayout mTabUser;

    private ImageButton mImgHome;
    private ImageButton mImgAdd;
    private ImageButton mImgSearch;
    private ImageButton mImgUser;

    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private Fragment mTab04;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();//初始化控件
        initEvent();//初始化事件
        setSelect(0);//1.管理fragment  2.改变图片的背景色

    }

    private void initEvent()
    {
        mTabHome.setOnClickListener(this);
        mTabAdd.setOnClickListener(this);
        mTabSearch.setOnClickListener(this);
        mTabUser.setOnClickListener(this);
    }

    private void initView()
    {
        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabAdd = (LinearLayout) findViewById(R.id.id_tab_add);
        mTabSearch = (LinearLayout) findViewById(R.id.id_tab_search);
        mTabUser = (LinearLayout) findViewById(R.id.id_tab_user);

        mImgHome = (ImageButton) findViewById(R.id.id_tab_home_img);
        mImgAdd = (ImageButton) findViewById(R.id.id_tab_add_img);
        mImgSearch = (ImageButton) findViewById(R.id.id_tab_search_img);
        mImgUser = (ImageButton) findViewById(R.id.id_tab_user_img);
    }

    private void setSelect(int i)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);

        switch (i)
        {
            case 0:
                if (mTab01 == null)
                {
                    mTab01 = new FragmentHome();
                    transaction.add(R.id.id_content, mTab01);
                } else
                {
                    transaction.show(mTab01);
                }
                mImgHome.setImageResource(R.drawable.ic_home_pressed);
                break;
            case 1:
                if (mTab02 == null)
                {
                    mTab02 = new FragmentAdd();transaction.add(R.id.id_content, mTab02);
                } else
                {
                    transaction.show(mTab02);

                }
                mImgAdd.setImageResource(R.drawable.ic_add_pressed);
                break;
            case 2:
                if (mTab03 == null)
                {
                    mTab03 = new FragmentSearch();
                    transaction.add(R.id.id_content, mTab03);
                } else
                {
                    transaction.show(mTab03);
                }
                mImgSearch.setImageResource(R.drawable.ic_search_pressed);
                break;
            case 3:
                if (mTab04 == null)
                {
                    mTab04 = new FragmentUser();
                    transaction.add(R.id.id_content, mTab04);
                } else
                {
                    transaction.show(mTab04);
                }
                mImgUser.setImageResource(R.drawable.ic_user_pressed);
                break;

            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction)
    {
        if (mTab01 != null)
        {
            transaction.hide(mTab01);
        }
        if (mTab02 != null)
        {
            transaction.hide(mTab02);
        }
        if (mTab03 != null)
        {
            transaction.hide(mTab03);
        }
        if (mTab04 != null)
        {
            transaction.hide(mTab04);
        }
    }

    @Override
    public void onClick(View v)
    {
        resetImgs();
        switch (v.getId())
        {
            case R.id.id_tab_home:
                setSelect(0);
                break;
            case R.id.id_tab_add:
                setSelect(1);
                break;
            case R.id.id_tab_search:
                setSelect(2);
                break;
            case R.id.id_tab_user:
                setSelect(3);
                break;

            default:
                break;
        }
    }


    private void resetImgs()
    {
        mImgHome.setImageResource(R.drawable.ic_home);
        mImgAdd.setImageResource(R.drawable.ic_add);
        mImgSearch.setImageResource(R.drawable.ic_search);
        mImgUser.setImageResource(R.drawable.ic_user);
    }

}

