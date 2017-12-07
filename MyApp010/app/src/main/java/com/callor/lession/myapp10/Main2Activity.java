package com.callor.lession.myapp10;

import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextInputEditText txt_email = null;
    TextInputEditText txt_password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);

        Button btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // text박스의 값을 변수에 할당
                String strEmail = txt_email.getText().toString();
                String strPassword = txt_password.getText().toString();

                // popup 을 띄우기 위한 설정
                AlertDialog.Builder ab = new AlertDialog.Builder(Main2Activity.this);
                AlertDialog dialog = null;

                // 취소버튼 생성
                ab.setCancelable(true);

                // 확인버튼 생성
                ab.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 확인을 클릭햇을때 할 일
                    }
                });


                if(strEmail.isEmpty()) {
                    // email 입력상자에 포커스 이동
                    txt_email.requestFocus();

                    ab.setTitle("로그인 오류");
                    ab.setMessage("Email은 반드시 입력해야 합니다");
                    dialog = ab.create();
                    dialog.show();

                    return;
                }
                if(strPassword.isEmpty()) {
                    txt_password.requestFocus();

                    ab.setTitle("로그인 오류");
                    ab.setMessage("비밀번호를 입력해야 합니다.");
                    dialog = ab.create();
                    dialog.show();

                    // 비빌번호 입력상자에 포커스 이동
                    return;
                }


            }
        });

    }
}
