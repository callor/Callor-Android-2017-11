package com.callor.lession.myapp017;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    RecyclerView recyclerView = null;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<AddrVO> addrVO = new ArrayList<AddrVO>();

        addrVO.add(new AddrVO(R.id.my_icon,"홍길동","나의 절친"));
        addrVO.add(new AddrVO(R.id.my_icon,"이몽룡","남원에 온 암행어사"));
        addrVO.add(new AddrVO(R.id.my_icon,"성춘향","남원에 사는 춘향이"));
        addrVO.add(new AddrVO(R.id.my_icon,"임꺽정","의적"));
        addrVO.add(new AddrVO(R.id.my_icon,"장보고","해상왕"));
        addrVO.add(new AddrVO(R.id.my_icon,"장영실","조선시대 발병왕"));

        // RecyclerView 모양을 어떻게 할것인가 결정
        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(MainActivity.this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adpter = new AddrAdapter(addrVO,getApplicationContext());
        recyclerView.setAdapter(adpter);



//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
