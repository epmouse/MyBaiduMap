package zz.itcast.mybaidumap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class SecondFragment extends Fragment {
    private Button btnCamera,btnEmail;
    private ImageView imageView;
    private String path;
    private String photopath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.camera_layout, null);
         btnCamera= (Button) view.findViewById(R.id.btn_capture);
         btnEmail=(Button)view.findViewById(R.id.btn_email);
         imageView=(ImageView)view.findViewById(R.id.imageview);

        path = Environment.getExternalStorageDirectory().getPath();
        photopath = path +"/"+"temp.jpg";
      //  startCamera();
       // startEmail();
        return view;
    }

    /**
     * 开启相机
     */
    private void startCamera() {
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri photoUri = Uri.fromFile(new File(photopath));

                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                //startActivity(intent);
                startActivityForResult(intent, 99);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FileInputStream fis=null;
              if(requestCode==99){
//                  Bundle extras = data.getExtras();
//                  Bitmap bitmap= (Bitmap) extras.get("data");
//                  imageView.setImageBitmap(bitmap);
                  try {
                      fis=new FileInputStream(photopath);
                      Bitmap bitmap= BitmapFactory.decodeStream(fis);
                      imageView.setImageBitmap(bitmap);
                  } catch (FileNotFoundException e) {
                      e.printStackTrace();
                  }finally {
                      try {
                          fis.close();
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }

              }
    }

    /**
     * 开启email
     */
    private void startEmail() {
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] email = {"18838265776@163.com"};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, email);//接受方
                //抄送
                intent.putExtra(Intent.EXTRA_CC, new String[]{"18622117113@126.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "这是主题");
                intent.putExtra(Intent.EXTRA_TEXT, "这是正文");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(photopath));
                intent.setType("image/*");

                //Intent.createChooser（）打开一个该intent包含的选择器，而不是一个activity
                startActivity(Intent.createChooser(intent, "请选择邮箱类别"));

            }
        });
    }
}
