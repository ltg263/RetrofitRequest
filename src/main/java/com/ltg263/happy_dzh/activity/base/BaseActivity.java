package com.ltg263.happy_dzh.activity.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by ltg_17710666165 on 2017/2/25.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setOnCreate());
        initUI(savedInstanceState);
        initData();

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
    /**
     * 重写onCreate
     */
    protected abstract int setOnCreate();
    /**
     * 初始化控件
     */
    protected abstract void initUI(Bundle savedInstanceState);

    /**
     * 初始化控件
     */
    protected abstract void initData();
}
