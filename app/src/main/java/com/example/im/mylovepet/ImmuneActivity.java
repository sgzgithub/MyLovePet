package com.example.im.mylovepet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImmuneActivity extends AppCompatActivity {

    @Bind(R.id.Pet_immune_exit)
    ImageView PetImmuneExit;
    @Bind(R.id.pet_SettingImmune_title)
    TextView petSettingImmuneTitle;
    @Bind(R.id.Pet_SettingImmune_save)
    TextView PetSettingImmuneSave;
    @Bind(R.id.Pet_Setting_Immune)
    CheckBox PetSettingImmune;
    @Bind(R.id.Immune_Yes_No)
    RelativeLayout ImmuneYesNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immune);
        ButterKnife.bind(this);
        initChick();
    }

    private void initChick() {
        if (PetSettingImmune.isChecked()) {
            ImmuneYesNo.setVisibility(View.VISIBLE);
        }
            ImmuneYesNo.setVisibility(View.GONE);

    }

    @OnClick({R.id.Pet_immune_exit, R.id.Pet_SettingImmune_save, R.id.Pet_Setting_Immune})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Pet_immune_exit:

                break;
            case R.id.Pet_SettingImmune_save:

                break;
            case R.id.Pet_Setting_Immune:

                break;

        }
    }


}
