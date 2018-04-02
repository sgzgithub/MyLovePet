package com.example.im.mylovepet;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.im.mylovepet.fragemnt.pet_type_fragment.Pet_Type_CatFragment;
import com.example.im.mylovepet.fragemnt.pet_type_fragment.Pet_Type_DogFragment;
import com.example.im.mylovepet.fragemnt.pet_type_fragment.Pet_Type_RestsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Pet_TypeActivity extends AppCompatActivity {

    @Bind(R.id.Pet_Type_exit)
    ImageView PetTypeExit;
    @Bind(R.id.Pet_Type_Rb_Dog)
    RadioButton PetTypeRbDog;
    @Bind(R.id.Pet_Type_Rb_Cat)
    RadioButton PetTypeRbCat;
    @Bind(R.id.Pet_Type_Rb_Rests)
    RadioButton PetTypeRbRests;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet__type);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.Pet_Tyoe_Frame, new Pet_Type_DogFragment()).commit();
    }

    @OnClick({R.id.Pet_Type_exit, R.id.Pet_Type_Rb_Dog, R.id.Pet_Type_Rb_Cat, R.id.Pet_Type_Rb_Rests})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Pet_Type_exit:
                finish();
                break;
            case R.id.Pet_Type_Rb_Dog:
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.Pet_Tyoe_Frame, new Pet_Type_DogFragment()).commit();
                break;
            case R.id.Pet_Type_Rb_Cat:
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.replace(R.id.Pet_Tyoe_Frame, new Pet_Type_CatFragment()).commit();
                break;
            case R.id.Pet_Type_Rb_Rests:
                FragmentTransaction transaction2 = manager.beginTransaction();
                transaction2.replace(R.id.Pet_Tyoe_Frame, new Pet_Type_RestsFragment()).commit();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
