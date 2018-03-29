package com.example.im.mylovepet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.im.mylovepet.dao.MyDbBeanDao;

import java.util.List;

import app.MyApplication;
import bean.MyDbBean;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Pet_SettingName extends AppCompatActivity {

    @Bind(R.id.Pet_Add)
    ImageView PetAdd;
    @Bind(R.id.Pet_SettingName_save)
    TextView PetSettingNameSave;
    @Bind(R.id.Pet_SettingName)
    EditText PetSettingName;
    private MyDbBeanDao beanDao;
    private MyDbBean myDbBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet__setting_name);
        ButterKnife.bind(this);
        beanDao = MyApplication.getApplication().getDaoSession().getMyDbBeanDao();


    }


    @OnClick({R.id.Pet_Add, R.id.Pet_SettingName_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Pet_Add:
                finish();
                break;
            case R.id.Pet_SettingName_save:
                String name = PetSettingName.getText().toString().trim();
                myDbBean = new MyDbBean();
                myDbBean.setName(name);
                beanDao.insert(myDbBean);
                List<MyDbBean> myDbBeen = beanDao.loadAll();
                Toast.makeText(this, "myDbBeen:" + myDbBeen, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
