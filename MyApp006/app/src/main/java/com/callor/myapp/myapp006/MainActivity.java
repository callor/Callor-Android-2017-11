package com.callor.myapp.myapp006;

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

// 비슷한 일을 하는 버튼이 4개있어서
// 비슷한 코드가 많이 중복되므로
// 해결하기 위해서
// 1. View.OnClickListener 인터페이스를 상속(implents) 해주고
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    // class 내 전체에서 접근 할 수 있도록 선언
    private TextInputEditText txt_num1 = null;
    private TextInputEditText txt_num2 = null;
    private TextInputEditText txt_result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt_num1 = findViewById(R.id.txt_num1);
        txt_num2 = findViewById(R.id.txt_num2);
        txt_result = findViewById(R.id.txt_result);


        Button btn_plus = findViewById(R.id.btn_plus);
        // 2. 각 버튼들의 OnClickListener(this)로 세팅
        btn_plus.setOnClickListener(this);

        Button btn_minus = findViewById(R.id.btn_minus);
        btn_minus.setOnClickListener(this);

        Button btn_times = findViewById(R.id.btn_times);
        btn_times.setOnClickListener(this);

        Button btn_div = findViewById(R.id.btn_div);
        btn_div.setOnClickListener(this);

        // event 선언
//        btn_plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//                // 입력된 숫자는 문자열이기 때문에 정수로 형변환 시켜야 한다.
//                int int_num1 = Integer.valueOf(txt_num1.getText().toString());
//                int_num1 = Integer.parseInt(txt_num1.getText().toString());
//
//                int int_num2 = Integer.valueOf(txt_num2.getText().toString());
//
//                int sum = int_num1 + int_num2;
//
//                // 숫자를 문자열로 변환시켜서 결과 input box에 표시
//                txt_result.setText(String.valueOf(sum));


//            }
//        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    // onClick 메서의 매개변수인 v는
    // 어떤 버튼이 눌러졌는지 알고있다.
    @Override
    public void onClick(View v) {

        // 입력된 숫자는 문자열이기 때문에 정수로 형변환 시켜야 한다.
        int int_num1 = Integer.valueOf(txt_num1.getText().toString());
        // int_num1 = Integer.parseInt(txt_num1.getText().toString());
        int int_num2 = Integer.valueOf(txt_num2.getText().toString());

        int result = 0;
        String msg = "";
        switch(v.getId()) {
            case R.id.btn_plus :
                result = int_num1 + int_num2;
                msg = "덧셈 결과 : ";
                break;
            case R.id.btn_minus :
                result = int_num1 + int_num2;
                msg = "뺄셈 결과 : ";
                break;
            case R.id.btn_times :
                result = int_num1 * int_num2;
                msg = "곱셈 결과 : ";
                break;
            case R.id.btn_div :
                result = int_num1 / int_num2;
                msg = "나눗셈 결과 : ";
                break;
        }

        // 숫자를 문자열로 변환시켜서 결과 input box에 표시
        txt_result.setText(msg + String.valueOf(result));


        //        if(v.getId() == R.id.btn_plus) {
//            result = int_num1 + int_num2;
//        }
//        if(v.getId() == R.id.btn_minus) {
//            result = int_num1 - int_num2;
//        }



        //        int sum = int_num1 + int_num2;


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
