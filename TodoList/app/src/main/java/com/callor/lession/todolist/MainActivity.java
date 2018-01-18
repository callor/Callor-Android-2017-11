package com.callor.lession.todolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txt_memo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt_memo = findViewById(R.id.txt_memo);
        RecyclerView memo_list = findViewById(R.id.memo_list) ;

        // event 핸들러에서 접근할 변수앞에 final 키워드를 주어라
        final List<MemoVO> memos = new ArrayList<MemoVO>();

        // 1 Adapter를 생성하면서 데이터를 담을 VO list 를 넘겨주고
        final RecyclerView.Adapter memoAdapter = new MemoAdapter(memos);

        // 2. 리사클러의 레이아웃 매니저를 생성
        RecyclerView.LayoutManager memoLayoutManager = new LinearLayoutManager(getApplicationContext());

        // 3. 리사이클러와 아답터를 연결
        memo_list.setAdapter(memoAdapter);

        // 4. 리사이클러와 레이아웃 매너저를 연결
        memo_list.setLayoutManager(memoLayoutManager);


        // 아이템별로 구분선을 넣기 위해 설정
        DividerItemDecoration dividerItemDecoration
                = new DividerItemDecoration(getApplicationContext(),
                        new LinearLayoutManager(this).getOrientation());

        memo_list.addItemDecoration(dividerItemDecoration);
        memo_list.addItemDecoration(new VerticalSpace(40));


        ImageButton btn_save = findViewById(R.id.bt_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMemo = txt_memo.getText().toString();
                if(strMemo.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"메모를 입력하세요",Toast.LENGTH_SHORT)
                            .show();
                    return ;
                }

                long now = System.currentTimeMillis();
                Date date = new Date(now);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

                String getDate = simpleDateFormat.format(date);
                String getTime = simpleTimeFormat.format(date);

                memos.add(new MemoVO(getDate,getTime,strMemo));
                memoAdapter.notifyDataSetChanged();
                txt_memo.setText("");

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
