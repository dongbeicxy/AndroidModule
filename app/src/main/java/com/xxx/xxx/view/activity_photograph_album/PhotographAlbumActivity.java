package com.xxx.xxx.view.activity_photograph_album;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.xxapplication.android7library.FileProvider7;
import com.xxx.xxx.R;
import com.xxx.xxx.utils.FileUtils;
import com.xxx.xxx.utils.PhotoFromPhotoAlbum;

import java.io.File;

/**
 * @作者 xzb
 * @描述: 拍照 相册 页面
 * @创建时间 :2020/4/3 16:26
 */
public class PhotographAlbumActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photograph_album);

        TitleBar mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {
                //ToastUtils.show("中间View被点击");
            }

            @Override
            public void onRightClick(View v) {
                //ToastUtils.show("右项View被点击");
            }
        });
    }

    private File cameraPicSavePath = null;
    private Uri uri;//照片uri
    // 是否是Android 10以上手机
    private boolean isAndroidQ = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q;

    /**
     * 拍照
     * <p>
     * 在Android 10，你可以通过uri获取图片Bitmap，获取到Bitmap后，上传图片就跟图片的上传文件的操作是一样的。
     */
    public void photograph(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (isAndroidQ) {
            // 适配android 10
            uri = createImageUri();
        } else {
            //拍照照片路径
            cameraPicSavePath = new File(FileUtils.getFolderOrFilePath(this, "images", true, System.currentTimeMillis() + ".jpg"));
            if (null != cameraPicSavePath) {
                //注意：下面打开相机方式拍照后文件会保存在imgUri中，onActivityResult回调不会返回数据，当回调成功后直接拿imgUri就是你拍的照片内容。
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //第二个参数为 包名.fileprovider
                    uri = FileProvider7.getUriForFile(this, cameraPicSavePath);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                } else {
                    uri = Uri.fromFile(cameraPicSavePath);
                }
            }
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 2);
    }

    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     */
    private Uri createImageUri() {
        String status = Environment.getExternalStorageState();
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        } else {
            return getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new ContentValues());
        }
    }

    /**
     * 相册
     * <p>
     * Intent.ACTION_GET_CONTENT获取的是所有本地图片，（饿了么相册选择方式） Intent.ACTION_PICK获取的是相册中的图片。
     */
    public void album(View view) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent, 1);

        // 参考 Ucrop
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT)
                .setType("image/*")
                .addCategory(Intent.CATEGORY_OPENABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String[] mimeTypes = {"image/jpeg", "image/png"};
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        }
        startActivityForResult(Intent.createChooser(intent, "选择图片"), 1);
    }

    /**
     * 点击文件
     */
    public void file(View view) {
        //https://blog.csdn.net/a1681681238/article/details/79997746
        //https://github.com/Yis92/FileSelector
    }

    //https://blog.csdn.net/keep_my_mine/article/details/82497541   android 跳转到系统相册选择图片
    //https://www.jianshu.com/p/41b093d213fb                        Android调用相册、相机（兼容6.0、7.0、8.0）
    //Android图片剪裁之调用系统剪裁                                   https://www.jianshu.com/p/663321bff7d3?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
    //https://www.jianshu.com/p/4a19c0665467
    //https://blog.csdn.net/chengfu116/article/details/74923161     Android获取本地图片之ACTION_GET_CONTENT与ACTION_PICK区别

    private String photoPath = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //相册
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Toast.makeText(this, PhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData()), Toast.LENGTH_SHORT).show();
        }

        //拍照
        if (requestCode == 2 && resultCode == RESULT_OK) {
            //安卓 7 到 安卓 9
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                photoPath = String.valueOf(cameraPicSavePath);
                Toast.makeText(this, photoPath, Toast.LENGTH_SHORT).show();
            }
            // 安卓 10 及 以上
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ImageView imageView = findViewById(R.id.image_view);
                imageView.setImageURI(uri);
            } else {
                photoPath = uri.getEncodedPath();
                Toast.makeText(this, photoPath, Toast.LENGTH_SHORT).show();
            }
            Log.d("拍照返回图片路径:", photoPath);
        }
        //文件
        if (requestCode == 3 && resultCode == RESULT_OK) {
            Toast.makeText(this, data.getData().getPath(), Toast.LENGTH_SHORT).show();
        }
    }
}
