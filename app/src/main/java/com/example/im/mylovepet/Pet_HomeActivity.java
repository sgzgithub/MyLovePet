package com.example.im.mylovepet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class Pet_HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView home_exit;
    private ListView home_lv;
    private Button add_pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        initView();
    }

    private void initView() {
        home_exit = (ImageView) findViewById(R.id.home_exit);
        home_lv = (ListView) findViewById(R.id.home_lv);
        add_pet = (Button) findViewById(R.id.add_pet);
        home_exit.setOnClickListener(this);
        add_pet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_pet:
                Intent intent = new Intent(Pet_HomeActivity.this, Pet_AddActivity.class);
                startActivity(intent);
                break;
            case R.id.home_exit:
                finish();
                break;
        }
    }
}
