package com.callor.lession.my0018;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView memo_list ;
    TextInputEditText txt_memo ;

    // 버튼이벤트에서 접근할수 있도록 위치 변경
    List<MemoVO> memos = new ArrayList<MemoVO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        memos.add(new MemoVO("2018-01-01","새해첫날"));
        memos.add(new MemoVO("2018-01-02","새해 복 많이 받으세요"));
        memos.add(new MemoVO("2018-01-03","작심삼일"));
        memos.add(new MemoVO("2018-01-04","새로운 시작"));
        memos.add(new MemoVO("2018-01-05","올해는 뭘 할까"));
        memos.add(new MemoVO("2018-01-06","지난해 보다 나은 새해"));
        */

        memo_list = findViewById(R.id.list_memo);

        // 리사클러뷰를 목록형태로 배치
        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(MainActivity.this);

        memo_list.setLayoutManager(layoutManager);

        // 이벤트 리스너에서 adapter를 호출하기 위해서는
        // final 키워드를 붙여준다.
        final RecyclerView.Adapter adapter = new MemoAdapter(getApplicationContext(),memos);
        memo_list.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleCallback
                = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(memo_list);



        txt_memo = findViewById(R.id.txt_memo);
        Button btn_save = findViewById(R.id.btn_save);



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 입력박스에 입력된 값을 꺼내서 strMemo에 임시 보관
                String strMemo = txt_memo.getText().toString();

                // 입력이 되지 않았으면
                if(strMemo.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "메모를 입력하세요",Toast.LENGTH_SHORT).show();
                }

                // 현재 입력된 날짜 가져오기
                long now = System.currentTimeMillis(); // 현재 날짜 시리얼 가져오기

                // 날짜를 원하는 문자열로 변환
                Date date = new Date(now); // 시리얼을 날짜 객체로 변환

                // 날짜를 문자열로 변환하기 위한 형식지정 2018-01-16
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String getDateTime = simpleDateFormat.format(date);

                // list를 보여주기 위해 VO에 추가
                memos.add(new MemoVO(getDateTime,strMemo));

                String memo_count = String.valueOf(memos.size());
                Toast.makeText(MainActivity.this,
                       "메모개수:" + memo_count,Toast.LENGTH_SHORT ).show();

                // 데이터 리스트 내용이 변경되었다는 것을 adapter에게 알리기
                adapter.notifyDataSetChanged();

            }
        });
    }
}













