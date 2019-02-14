package com.linger.enterandregist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegsiterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etPhoneNum,etSmsCode,etPassWord;//手机号，验证码，密码
    private Button btnReg,btnSms;//注册码，获得验证码
    private ImageView returnImage;
    private TextView textTerms,enterText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);
        init();
    }
/*初始化控件方法*/
    private void init() {
        etPhoneNum=findViewById(R.id.et_phone_num);
        etSmsCode=findViewById(R.id.et_sms_code);
        etPassWord=findViewById(R.id.et_password);
        btnReg=findViewById(R.id.btn_now_reg);
        btnSms=findViewById(R.id.btn_sms_code);
        returnImage=findViewById(R.id.iv_return);
        textTerms=findViewById(R.id.tv_terms);
        enterText=findViewById(R.id.tv_enter);
        btnReg.setOnClickListener(this);
        btnSms.setOnClickListener(this);
        returnImage.setOnClickListener(this);
        textTerms.setOnClickListener(this);
        enterText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_now_reg://注册按钮
                register();
                break;
            case R.id.tv_enter://没删按钮
                returnEnten();
                break;
            case R.id.iv_return://返回
                returnEnten();
                break;
            case R.id.btn_sms_code://获取验证码
                String phone=etPhoneNum.getText().toString().trim();//获取电话号码
                if(phone.length()!=11){
                    Toast.makeText(this, "电话号码不合理", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "验证码获取成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_terms:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void register() {
        String username=etPhoneNum.getText().toString().trim();//获取电话号码
        String password=etPassWord.getText().toString().trim();//获取密码
        String smscode=etSmsCode.getText().toString().trim();//获取验证码
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            etPhoneNum.requestFocus();//输入框失去焦点
            return;
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            etPassWord.requestFocus();
        }else if(TextUtils.isEmpty(smscode)){
            Toast.makeText(this, "设置密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("正在注册。。。");
        pd.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pd.dismiss();
                returnEnten();//跳转到登录界面
            }
        }).start();//开启线程

    }
/*跳转到登录界面*/
    private void returnEnten() {
        Intent intent=new Intent(this,EnterActivity.class);
        startActivity(intent);
    }
}
