package com.callor.myapp.myapp005;

import android.content.Intent;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText txt_name ;
    private TextInputEditText txt_phone ;
    private TextInputEditText txt_email ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 입력박스의 값을 java 코드에서 추출해서 사용하기 위한 준비
        txt_name = findViewById(R.id.txt_name);
        txt_phone = findViewById(R.id.txt_phone);
        txt_email = findViewById(R.id.txt_email);

        // Alt + Enter : import
        Button btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = "";

//                Toast.makeText(MainActivity.this,
//                        "이름이 입력되지 않았습니다",Toast.LENGTH_SHORT).show();

                // 이름항목에 값이 입력되지 않았으면
                if(txt_name.getText().toString().isEmpty()) {
//                    Toast.makeText(MainActivity.this,
//                            "이름이 입력되지 않았습니다",Toast.LENGTH_SHORT).show();
                    msg += "이름 ";
//                    return; // onClick method를 멈추어랴

                }
                if(txt_phone.getText().toString().isEmpty()) {
//                    Toast.makeText(MainActivity.this,
//                            "전화번호를 입력해야 합니다",Toast.LENGTH_SHORT).show();
                    msg += "전화번호 ";
//                    return;

                }
                if(txt_email.getText().toString().isEmpty()) {

                    // Snackbar는 키보드가 보일경우 감춰져서 안보일 수 있다.
                    // Snackbar.make(v,"이메일을 입력해야 합니다",Snackbar.LENGTH_SHORT).show();

                    msg += "이메일 ";
//                    Toast.makeText(MainActivity.this,
//                            "이메일을 입력해야 합니다",Toast.LENGTH_SHORT).show();

//                    return;
                }

                if(msg.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "등록되었습니다",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this,
                            msg + " 항목이 입력되지 않았습니다",Toast.LENGTH_SHORT).show();
                }

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 두번재 화면 열기
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
