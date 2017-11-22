package com.callor.myapp.myapp04;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // 이벤트 1번째 방법 : 익명 클래스를 생성해서 사용하는 방법
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean ret = false ;
                // getItemId : 위젯의 @+id로 만든 이름 : 정수값
                if(item.getItemId() == R.id.navigation_home) {}
                if(item.getItemId() == R.id.navigation_dashboard) {}
                if(item.getItemId() == R.id.navigation_notifications) {}
                if(item.getItemId() == R.id.navigation_camera) {}
                if(item.getItemId() == R.id.navigation_map) {}


                switch (item.getItemId()) {
                    case R.id.navigation_home :
                        // 직접 text를 작성
//                        mTextMessage.setText("처음");

                        // string.xml 에 있는 값을 불러와서 사용
                        // 어플이 확장되었을때 유리한 방법
                        mTextMessage.setText(R.string.title_home);
                        ret = true;
                        break;
                    case R.id.navigation_dashboard :
//                        mTextMessage.setText("그래프");
                        mTextMessage.setText(R.string.title_dashboard);

                        // 다른 화면을 열기
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);


//                        return true;
                        ret = true;
                        break ;
                    case R.id.navigation_notifications :
//                        mTextMessage.setText("알림");
                        mTextMessage.setText(R.string.title_notifications);
//                        return true;
                        ret = true ;
                        break ;
                    case R.id.navigation_camera :
//                        mTextMessage.setText("카메라");
                        mTextMessage.setText(R.string.title_camera);
//                        return true;
                        ret = true ;
                        break;
                    case R.id.navigation_map :
//                        mTextMessage.setText("지도");
                        mTextMessage.setText(R.string.title_map);
//                        return true;
                        ret = true ;
                        break;
                }
                return ret ;
            }
        });
    }


    // 이벤트 2번째 방법
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return false;
        }
    };


    /*
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    */



}
