package com.callor.lession.navermovie;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt_search = findViewById(R.id.txt_search);
        txt_search.setOnEditorActionListener(new TextInputEditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String str_search = txt_search.getText().toString();

                // 키보드에서 찾기 버튼을 눌렀을때만 실행하라
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (str_search.isEmpty()) {
                        // Toast.makeText(getApplicationContext(),"검색어를 입력하세요",Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "검색어를 입력하세요", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    Toast.makeText(MainActivity.this, str_search, Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;

            }
        });

    }
}
