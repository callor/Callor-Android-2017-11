package com.callor.myapp.myapp005;

import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {


    private TextInputEditText txt_num1 ;
    private TextInputEditText txt_num2 ;
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        txt_num1 = findViewById(R.id.txt_num1);
        txt_num2 = findViewById(R.id.txt_num2);

        Button btn_plus = findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txt_num1.getText().toString().isEmpty()) {
                    Toast.makeText(Main2Activity.this,
                            "숫자1을 입력하세요",Toast.LENGTH_SHORT).show();
                    return ;

                }
                if(txt_num2.getText().toString().isEmpty()) {
                    Toast.makeText(Main2Activity.this,
                            "숫자2를 입력하세요",Toast.LENGTH_SHORT).show();
                    return ;
                }

                int sum = Integer.valueOf(txt_num1.getText().toString());
                sum += Integer.valueOf(txt_num2.getText().toString());

                AlertDialog.Builder ab = new AlertDialog.Builder(Main2Activity.this);
                ab.setTitle("계산결과");

                String msg = txt_num1.getText().toString() + " 와 " ;
                msg += txt_num2.getText().toString() + " 의 덧셈 결과는 \n";
                msg += String.valueOf(sum) + " 입니다.";
//                ab.setMessage(String.valueOf(sum));
                ab.setMessage(msg);

                // 아이콘 붙이기
                ab.setIcon(getResources().getDrawable(R.drawable.ic_assignment_turned_in_black_24dp));
                ab.setCancelable(false);

                // 확인버튼 생성하는 부분
                ab.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 확인을 클릭햇을때 할 일
                    }
                });

                ab.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog = ab.create();
                alertDialog.show();

            }
        });


    }
}
