package com.example.im.mylovepet;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet__add);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.Pet_Add, R.id.Pet_Title_image, R.id.Pet_name, R.id.Pet_Type, R.id.Pet_Sterilize, R.id.Pet_Date, R.id.Pet_Kg, R.id.Pet_Immune})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Pet_Title_image:
                initPopup();
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


                break;
            case R.id.Pet_Date:


                break;
            case R.id.Pet_Kg:


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

    private void initPopup() {
        View view = getLayoutInflater().inflate(R.layout.popup_item, null);
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
}