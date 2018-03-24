package com.ltg263.happy_dzh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.activity.base.BaseActivity;
import com.ltg263.happy_dzh.apiService.RetrofitUtil;
import com.ltg263.happy_dzh.apiService.RxHttpParams;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_lj)
    Button btnLj;
    @BindView(R.id.tv_str)
    TextView tvStr;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        btnLj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxHttpParams params = new RxHttpParams.Build()
                        .url("http://v.juhe.cn/wepiao/query")
                        .addQuery("key", "b9028c6563dcb2cf68efeac77395341a")
                        .build();
                RetrofitUtil.request(params, String.class, new RetrofitUtil.HttpCallBackImpl<String>() {
                    @Override
                    public void onCompleted(String contactsBean) {
                        tvStr.setText(contactsBean);
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {

    }
}
