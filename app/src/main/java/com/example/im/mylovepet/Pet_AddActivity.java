package com.example.im.mylovepet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.example.im.mylovepet.dao.MyDbBeanDao;
import com.example.im.mylovepet.utils.GlideCircleTransform;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import app.MyApplication;
import bean.MyDbBean;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.im.mylovepet.R.id.pz;

public class Pet_AddActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.Pet_Title_image)
    PercentRelativeLayout PetTitleImage;
    @Bind(R.id.Pet_name)
    PercentRelativeLayout PetName;
    @Bind(R.id.Pet_Type)
    PercentRelativeLayout PetType;
    @Bind(R.id.Pet_Sterilize)
    PercentRelativeLayout PetSterilize;
    @Bind(R.id.Pet_Date)
    PercentRelativeLayout PetDate;
    @Bind(R.id.Pet_Kg)
    PercentRelativeLayout PetKg;
    @Bind(R.id.Pet_Immune)
    PercentRelativeLayout PetImmune;
    @Bind(R.id.Pet_JJ)
    EditText PetJJ;
    @Bind(R.id.Pet_Add)
    ImageView exit;
    @Bind(R.id.titlt_image)
    ImageView image_title;

    private PopupWindow pw;
    //    private Uri contentUri;
//    private Bitmap photo1;
//    private File file;
    private MyDbBeanDao beanDao;
    private MyDbBean myDbBean;
    private static final int CODE_PHOTO_REQUEST = 1;
    private static final int CODE_CAMERA_REQUEST = 2;
    private static final int CODE_PHOTO_CLIP = 3;
    private static final File USER_ICON = new File(Environment.getExternalStorageDirectory(), "user_icon.jpg");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet__add);
        beanDao = MyApplication.getApplication().getDaoSession().getMyDbBeanDao();
        initDbBean();
        ButterKnife.bind(this);

    }


    private void getPicFromLocal() {
        Intent intent = new Intent();
        // 获取本地相册方法一
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, CODE_PHOTO_REQUEST);
    }


    private void getPicFromCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(USER_ICON));
        startActivityForResult(intent, CODE_CAMERA_REQUEST);
    }


    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
    /*outputX outputY 是裁剪图片宽高
     *这里仅仅是头像展示，不建议将值设置过高
     * 否则超过binder机制的缓存大小的1M限制
     * 报TransactionTooLargeException
     */
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CODE_PHOTO_CLIP);
    }

    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            byte[] bytes = outputStream.toByteArray();
            Glide.with(Pet_AddActivity.this)
                    .load(bytes)
                    .transform(new GlideCircleTransform(Pet_AddActivity.this))
                    .into(image_title);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(Pet_AddActivity.this, "图片太大，请选择小图片", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            case CODE_CAMERA_REQUEST:
                if (USER_ICON.exists()) {
                    photoClip(Uri.fromFile(USER_ICON));
                }
                break;
            case CODE_PHOTO_REQUEST:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case CODE_PHOTO_CLIP:
                if (data != null) {
                    setImageToHeadView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initDbBean() {
        myDbBean = new MyDbBean();
    }

    @OnClick({R.id.Pet_Add, R.id.Pet_Title_image, R.id.Pet_name, R.id.Pet_Type, R.id.Pet_Sterilize, R.id.Pet_Date, R.id.Pet_Kg, R.id.Pet_Immune})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Pet_Title_image:
                initTakePopup();
                pw.showAtLocation(findViewById(R.id.popup), Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                break;
            case R.id.Pet_name:
                Intent intent = new Intent(Pet_AddActivity.this, Pet_SettingName.class);
                startActivity(intent);
                break;
            case R.id.Pet_Type:
                Intent intent1 = new Intent(Pet_AddActivity.this, Pet_TypeActivity.class);
                startActivity(intent1);

                break;
            case R.id.Pet_Sterilize:
                initSterilizePopup();
                pw.showAtLocation(findViewById(R.id.popup), Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                break;
            case R.id.Pet_Date:
                initDate();

                break;
            case R.id.Pet_Kg:
                Intent intent3 = new Intent(Pet_AddActivity.this, Pet_SettingKg.class);
                startActivity(intent3);

                break;
            case R.id.Pet_Immune:
                Intent intent4 = new Intent(Pet_AddActivity.this, ImmuneActivity.class);
                startActivity(intent4);
                break;
            case R.id.Pet_Add:
                finish();
                break;
            case pz:
                getPicFromCamera();
                pw.dismiss();
                break;
        }
    }

    private void initDate() {
        TimePickerView pvTime = new TimePickerView.Builder(Pet_AddActivity.this, new TimePickerView.OnTimeSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                String time = getTime(date2);
                myDbBean.setShengri(time);
                beanDao.insert(myDbBean);

            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private void initSterilizePopup() {


        View view = getLayoutInflater().inflate(R.layout.popup_sterilize_item, null);
        pw = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        Button yes = view.findViewById(R.id.yes);
        Button no = view.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDbBean.setSterilization("是");
                beanDao.insert(myDbBean);
                pw.dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDbBean.setSterilization("否");
                beanDao.insert(myDbBean);
                pw.dismiss();
            }
        });
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new ColorDrawable());
        pw.setOutsideTouchable(true);
    }

    private void initTakePopup() {
        View view = getLayoutInflater().inflate(R.layout.popup_take_item, null);
        pw = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        Button pz = view.findViewById(R.id.pz);
        Button xc = view.findViewById(R.id.xc);
        Button qx = view.findViewById(R.id.qx);

        xc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPicFromLocal();
                pw.dismiss();
            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pw.dismiss();
            }
        });
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new ColorDrawable());
        pw.setOutsideTouchable(true);
        pz.setOnClickListener(this);
    }

    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
