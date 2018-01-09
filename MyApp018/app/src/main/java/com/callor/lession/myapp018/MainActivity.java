package com.callor.lession.myapp018;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

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


    RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<PhoneVO> phoneVOs = new ArrayList<PhoneVO>();

        phoneVOs.add(new PhoneVO("홍길동","010-111-1234","절친"));
        phoneVOs.add(new PhoneVO("홍길동","010-111-1234","절친"));
        phoneVOs.add(new PhoneVO("홍길동","010-111-1234","절친"));
        phoneVOs.add(new PhoneVO("홍길동","010-111-1234","절친"));
        phoneVOs.add(new PhoneVO("홍길동","010-111-1234","절친"));
        phoneVOs.add(new PhoneVO("홍길동","010-111-1234","절친"));
        phoneVOs.add(new PhoneVO("홍길동","010-111-1234","절친"));


        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(MainActivity.this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new PhoneAdapter(getApplicationContext(),phoneVOs);
        recyclerView.setAdapter(adapter);



//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
