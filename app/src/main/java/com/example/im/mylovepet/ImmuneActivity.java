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
    @Bind(R.id.up_immune_time)
    ImageView upImmuneTime;
    @Bind(R.id.Immune_Yes_No)
    RelativeLayout ImmuneYesNo;
    @Bind(R.id.a)
    TextView a;
    @Bind(R.id.b)
    TextView b;
    @Bind(R.id.Pet_Check_one)
    CheckBox PetCheckOne;
    @Bind(R.id.Pet_Check_two)
    CheckBox PetCheckTwo;
    @Bind(R.id.Pet_Check_three)
    CheckBox PetCheckThree;
    @Bind(R.id.Pet_Check_four)
    CheckBox PetCheckFour;
    @Bind(R.id._up_immune_RelativeLayout)
    RelativeLayout UpImmuneRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immune);
        ButterKnife.bind(this);

    }

    private void initChick_One() {
        if (PetSettingImmune.isChecked()) {
            ImmuneYesNo.setVisibility(View.VISIBLE);
        } else {
            UpImmuneRelativeLayout.setVisibility(View.GONE);
            ImmuneYesNo.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.Pet_immune_exit, R.id.Pet_SettingImmune_save, R.id.Pet_Setting_Immune, R.id.Immune_Yes_No, R.id.Pet_Check_one, R.id.Pet_Check_two, R.id.Pet_Check_three, R.id.Pet_Check_four})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Pet_immune_exit:
                finish();
                break;
            case R.id.Pet_SettingImmune_save:

                break;
            case R.id.Pet_Setting_Immune:
                initChick_One();
                break;

            case R.id.Immune_Yes_No:
                UpImmuneRelativeLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.Pet_Check_one:

                break;
            case R.id.Pet_Check_two:

                break;
            case R.id.Pet_Check_three:

                break;
            case R.id.Pet_Check_four:

                break;
        }
    }


}
