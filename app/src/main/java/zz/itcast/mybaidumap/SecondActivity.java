package zz.itcast.mybaidumap;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * 实现了调用系统邮件功能。
 */
public class SecondActivity extends FragmentActivity {
    private Button btn1;
    private Button btn2;
    private RadioGroup radioGroup;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        btn1 = (Button) findViewById(R.id.btn_email);
        btn2 = (Button) findViewById(R.id.btn_capture);

//        fragmentList = new ArrayList();
//
//        fragmentList.add(new FirstFragment());
//        fragmentList.add(new SecondFragment());
//        fragmentList.add(new ThreeFragment());
//        fragmentList.add(new FourFragment());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {

                    case R.id.radiobutton1:

                        transaction.replace(R.id.framelayout, new FirstFragment()).commit();
                        break;
                    case R.id.radiobutton2:
                        transaction.replace(R.id.framelayout, new SecondFragment()).commit();
                        break;
                    case R.id.radiobutton3:
                        transaction.replace(R.id.framelayout, new ThreeFragment()).commit();
                        break;
                    case R.id.radiobutton4:
                        transaction.replace(R.id.framelayout, new FourFragment()).commit();
                        break;


                }
            }
        });
        radioGroup.check(R.id.radiobutton1);


//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse("mailto:18838265776@163.com");
//                String[] email = {"18838265776@163.com"};
//                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//                intent.putExtra(Intent.EXTRA_EMAIL, email);//接受方
//                //抄送
//                intent.putExtra(Intent.EXTRA_CC, new String[]{"18622117113@126.com"});
//                intent.putExtra(Intent.EXTRA_SUBJECT, "这是主题");
//                intent.putExtra(Intent.EXTRA_TEXT, "这是正文");
//                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/shared/image/p14.jpg"));
//
//                //Intent.createChooser（）打开一个该intent包含的选择器，而不是一个activity
//                startActivity(Intent.createChooser(intent, "请选择邮箱类别"));
//
//            }
//        });

//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                //startActivity(intent);
//                startActivityForResult(intent,99);
//
//            }
//        });
        //设置view树的观察者
    /*    btn2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //当绘制完成后会调用此方法。
            @Override
            public void onGlobalLayout() {
            //该方法会多次调用，为了不给我们操作造成麻烦在第一次调用后我们完成自己的操作后，就删掉监听

                System.out.println("");//可以替换成 我们的操作
                //删除
                btn2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });*/
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
