package com.callor.myapp.myapp007;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // 1. xml에서 만든 위젯을 java class에서 사용하기 위한 선언
    private TextInputEditText txt_plus_1 = null;
    private TextInputEditText txt_plus_2 = null;
    private TextInputEditText txt_plus_result = null;

    private Button btn_plus = null;

    private TextInputEditText txt_minus_1 = null;
    private TextInputEditText txt_minus_2 = null;
    private TextInputEditText txt_minus_result = null;

    private Button btn_minus = null;

    private TextInputEditText txt_multi_1 = null;
    private TextInputEditText txt_multi_2 = null;
    private TextInputEditText txt_multi_result = null;

    private Button btn_multi = null;


    private TextInputEditText txt_divid_1 = null;
    private TextInputEditText txt_divid_2 = null;
    private TextInputEditText txt_divid_result = null;

    private Button btn_divid = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. 선언된 widget 변수를 초기화
        txt_plus_1 = findViewById(R.id.txt_plus_1);
        txt_plus_2 = findViewById(R.id.txt_plus_2);
        txt_plus_result = findViewById(R.id.txt_plus_result);

        btn_plus = findViewById(R.id.btn_plus);

        txt_minus_1 = findViewById(R.id.txt_minus_1);
        txt_minus_2 = findViewById(R.id.txt_minus_2);
        txt_minus_result = findViewById(R.id.txt_minus_result);

        btn_minus = findViewById(R.id.btn_minus);


        txt_multi_1 = findViewById(R.id.txt_multi_1);
        txt_multi_2 = findViewById(R.id.txt_multi_2);
        txt_multi_result = findViewById(R.id.txt_multi_result);

        btn_multi = findViewById(R.id.btn_multi);


        txt_divid_1 = findViewById(R.id.txt_divid_1);
        txt_divid_2 = findViewById(R.id.txt_divid_2);
        txt_divid_result = findViewById(R.id.txt_divid_result);

        btn_divid = findViewById(R.id.btn_divid);


        // 버튼에 event 선언
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intNum1 = Integer.valueOf(txt_plus_1.getText().toString());
                int intNum2 = Integer.valueOf(txt_plus_2.getText().toString());

                int intSum = intNum1 + intNum2;
                txt_plus_result.setText(String.valueOf(intSum));
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intNum1 = Integer.valueOf(txt_minus_1.getText().toString());
                int intNum2 = Integer.valueOf(txt_minus_2.getText().toString());

                int intSum = intNum1 - intNum2;
                txt_minus_result.setText(String.valueOf(intSum));
            }
        });

        btn_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intNum1 = Integer.valueOf(txt_multi_1.getText().toString());
                int intNum2 = Integer.valueOf(txt_multi_1.getText().toString());

                int intSum = intNum1 * intNum2;
                txt_multi_result.setText(String.valueOf(intSum));
            }
        });

        // 나눗셈, 소수점 표시
        btn_divid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float fNum1 = Float.valueOf(txt_divid_1.getText().toString());
                float fNum2 = Float.valueOf(txt_divid_2.getText().toString());

                float fSum = fNum1 / fNum2;
                txt_divid_result.setText(String.valueOf(fSum));
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
