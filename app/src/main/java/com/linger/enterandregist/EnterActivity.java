package com.linger.enterandregist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editPerson,editCade;//用户名，密码
    private TextView textViewR,textTerms;//注册
    private Button btn;//登录按钮
    private String currentUserName,currentPassword;//用于加载完成后的用户名和密码
    private ImageView returnImage,qq,weixin,weipo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_enter);
        init();//初始化方法
    }

    private void init() {
        btn=findViewById(R.id.btn_common_login);
        btn.setOnClickListener(this);
        editPerson=findViewById(R.id.et_usename);
        editCade=findViewById(R.id.et_password);
        textViewR=findViewById(R.id.tv_register);
        textTerms=findViewById(R.id.tv_terms);
        returnImage=findViewById(R.id.iv_return);
        qq=findViewById(R.id.qq_login);
        weixin=findViewById(R.id.weixin_login);
        weipo=findViewById(R.id.weipo_login);
        returnImage.setOnClickListener(this);
        qq.setOnClickListener(this);
        weixin.setOnClickListener(this);
        weipo.setOnClickListener(this);
        textViewR.setOnClickListener(this);
        textTerms.setOnClickListener(this);
    }
/*点击事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_common_login:
                login();
                break;
            case R.id.tv_register:
                Intent intent=new Intent(EnterActivity.this,RegsiterActivity.class);
                startActivity(intent);
                finish();//销毁当前界面
                break;
            case R.id.qq_login:
                Toast.makeText(this, "QQ登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.weixin_login:
                Toast.makeText(this, "微信登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.weipo_login:
                Toast.makeText(this, "微博登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_terms:
                Intent intent1=new Intent(this,MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_return:
                finish();

        }
    }

    private void login() {
        currentUserName=editPerson.getText().toString().trim();//去除空格，得到手机号
        currentPassword=editCade.getText().toString().trim();//去除空格，得到密码
        if(TextUtils.isEmpty(currentUserName)){//用户名是不是为空
           Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
           return;
        }else if(TextUtils.isEmpty(currentPassword)){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("正在登录。。。");
        pd.show();
        /*模拟后台*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pd.dismiss();
                Intent intent=new Intent(EnterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();//销毁当前界面
            }
        }).start();
    }
}
