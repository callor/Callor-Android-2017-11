package com.callor.myapp.myapp02;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 3개의 입력박스값을 가져오거나 할당 하기 위해 (빈)객체.
    EditText txtName = null ;
    TextInputEditText txtPhone = null  ;
    TextInputEditText txtEmail = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Empty Theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 객체 변수 초기화
        txtName = (EditText)findViewById(R.id.txt_name);
        txtPhone = (TextInputEditText)findViewById(R.id.txt_phone);
        txtEmail = (TextInputEditText)findViewById(R.id.txt_email);

        // 객체의 text 변수에 값을 setting
        txtName.setText("홍길동");
        txtPhone.setText("010-111-2222");
        txtEmail.setText("test@daum.net");

        // Basic theme
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // 1번째와 2번째 방법은 event가 개별적으로 실행할때
        // 1번째 방법
        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtName.setText("이몽룡");
            }
        };

        // 버튼을 클릭한 신호가 OS로 부터 오면
        // 할일을 지정
        // fab.setOnClickListener(onClick);

        // 2번째 일반적인 방법
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // txtPhone.setText("000-999-8888");
                // 1번 변수 만들어서 사용하기
                // 만들기
                // Toast를 호출하는 곳(View)
                Toast toast = Toast.makeText(MainActivity.this,
                        "보여줄 메시지",Toast.LENGTH_SHORT);
                toast.show(); // 나타내기


                // 2번 변수 만들지 않고 쓰고 버리기
                // 작은 popup창에 메시지 띄우기
                // 잠시 나타 났다 사라지는 메시지 창
                Toast.makeText(MainActivity.this,
                        "안녕하세요",Toast.LENGTH_SHORT).show();

                Snackbar.make(v,"스낵바",Snackbar.LENGTH_LONG).show();
            }
        });
    }




    /*
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
    */

}
