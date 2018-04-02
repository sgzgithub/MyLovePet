package com.example.im.mylovepet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.im.mylovepet.dao.MyDbBeanDao;

import app.MyApplication;
import bean.MyDbBean;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Pet_SettingKg extends AppCompatActivity {

    @Bind(R.id.Pet_Add_Kg_exit)
    ImageView PetAddKgExit;
    @Bind(R.id.Pet_SettingKg_save)
    TextView PetSettingKgSave;
    @Bind(R.id.Pet_SettingKg)
    EditText PetSettingKg;
    private MyDbBeanDao beanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet__setting_kg);
        ButterKnife.bind(this);
        beanDao = MyApplication.getApplication().getDaoSession().getMyDbBeanDao();
    }

    @OnClick({R.id.Pet_Add_Kg_exit, R.id.Pet_SettingKg_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Pet_Add_Kg_exit:
                finish();
                break;
            case R.id.Pet_SettingKg_save:
                MyDbBean dbBean = new MyDbBean();
                dbBean.setKg(PetSettingKg.getText().toString().trim());
                beanDao.insert(dbBean);
                finish();
                break;
        }
    }
}
