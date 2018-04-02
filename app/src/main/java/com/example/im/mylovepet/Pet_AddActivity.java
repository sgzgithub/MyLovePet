package com.example.im.mylovepet;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bigkoo.pickerview.TimePickerView;
import com.example.im.mylovepet.dao.MyDbBeanDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

    private PopupWindow pw;
    private static final int PHOTO_TK = 0;
    private static final int PHOTO_PZ = 1;
    private static final int PHOTO_CLIP = 2;
    private Uri contentUri;
    private Bitmap photo1;
    private File file;
    private MyDbBeanDao beanDao;
    private MyDbBean myDbBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet__add);
        beanDao = MyApplication.getApplication().getDaoSession().getMyDbBeanDao();
        initDbBean();
        ButterKnife.bind(this);

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
                TimePickerView pvTime = new TimePickerView.Builder(Pet_AddActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTimeSelect(Date date2, View v) {//选中事件回调
//                        String time = getTime(date2);
//                        button3.setText(time);

                    }
                })
                        .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(20)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
//                        .setTitleText("请选择时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                        .setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)//默认是1900-2100年
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                        .setRangDate(startDate,endDate)//起始终止年月日设定
//                        .setLabel("年","月","日","时","分","秒")
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .isDialog(true)//是否显示为对话框样式
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();

                break;
            case R.id.Pet_Kg:
                Intent intent3 = new Intent(Pet_AddActivity.this, Pet_SettingKg.class);
                startActivity(intent3);

                break;
            case R.id.Pet_Immune:

                break;

            case R.id.Pet_Add:
                finish();
                break;
            case pz:
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri mImageCaptureUri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    contentUri = FileProvider.getUriForFile(Pet_AddActivity.this, "com.example.im.mylovepet", new File(Environment.getExternalStorageDirectory(), "temp.jpg"));
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                } else {
                    mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg"));
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                }
                startActivityForResult(intent2, PHOTO_PZ);
                break;
        }
    }

    private void initSterilizePopup() {


        View view = getLayoutInflater().inflate(R.layout.popup_sterilize_item, null);
//        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
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
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PHOTO_TK);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PHOTO_PZ:
                    Uri pictur;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        pictur = contentUri;
                    } else {
                        pictur = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/temp.jpg"));
                    }

                    startPhotoZoom(pictur);
                    break;
                case PHOTO_TK:
                    startPhotoZoom(data.getData());

                    break;

                case PHOTO_CLIP:
                    try {
                        photo1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    String path = Environment.getExternalStorageDirectory()
                            .getPath() + "/Pic";
                    file = new File(path);
                    Log.e("file", file.getPath());
                    file.mkdirs();
                    long i = System.currentTimeMillis();
                    file = new File(file.toString() + "/" + i + ".png");
                    Log.e("fileNew", file.getPath());
                    OutputStream out = null;
                    try {
                        out = new FileOutputStream(file.getPath());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    boolean flag = photo1.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    if (file.getName() != null || !file.getName().equals(""))
                        break;
            }
        }
    }

    private Uri uritempFile;

    public void startPhotoZoom(Uri uri) {
        Log.e("uri====", "" + uri);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("aspectX", 60);
        intent.putExtra("aspectY", 60);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setClipData(ClipData.newRawUri(MediaStore.EXTRA_OUTPUT, uri));
            uritempFile = uri;
        } else {
            uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }

//
//    public String getTime(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
//        return format.format(date);
//    }
}
