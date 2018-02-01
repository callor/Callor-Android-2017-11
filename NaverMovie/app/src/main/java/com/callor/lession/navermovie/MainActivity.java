package com.callor.lession.navermovie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.callor.lession.navermovie.naver.Naver_Items;
import com.callor.lession.navermovie.naver.Naver_Secret;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txt_search;
    RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt_search = findViewById(R.id.txt_search);
        recyclerView = findViewById(R.id.movie_list);

        // 키보드에 있는 찾기(돋보기버튼)을 터치 했을때 event 지정
        txt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH) {

                    String str_search = txt_search.getText().toString();
                    if(str_search.isEmpty()) {
                        Toast.makeText(MainActivity.this,
                                "검색어를 입력한 후 조회를 하세요",Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    // 실제 검색 부분
                }

                NaverSearch naverSearch = new NaverSearch();
                naverSearch.execute();

                return true;
            }
        });


    }// onCreate 가 끝나는 부분

    public class NaverSearch extends AsyncTask<Integer,Integer,Void> {

        private List<MovieVO> movies ;


        // 백그라운드로 실행할 메서드
        // 네이버 검색 method 실행
        @Override
        protected Void doInBackground(Integer... integers) {
            naver_seach();
            return null;
        }

        // doInBackground가 실행이 모두 끝나면 실행될 method
        // 데이터를 naver로 부터 다 가져 온 뒤에 실행될 method
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Movie_Adapter adapter = new Movie_Adapter(movies);
            recyclerView.setAdapter(adapter);

            StaggeredGridLayoutManager layoutManager
                    = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);


        }

        public void naver_seach() {

            String client_Id = Naver_Secret.CLIENT_ID;
            String client_secret = Naver_Secret.CLIENT_SECRET;

            String apiURL = "https://openapi.naver.com/v1/search/movie.json" ;
            String str_search = txt_search.getText().toString();

            try {

                // URLEncode.encode는 exception 처리
                apiURL += "?query=" + URLEncoder.encode(str_search,"UTF-8");
                apiURL += "&display=50";

                // 네이버에 전송할 코드로 변환
                URL url = new URL(apiURL);

                // url.openConnection 리턴값은 HttpURLConnection 로 형변환 시켜야 한다.
                //      Boxing
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                // Http Header 생성
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("X-Naver-Client-Id",client_Id);
                httpURLConnection.setRequestProperty("X-Naver-Client-Secret",client_secret);

                // 데이터 검색이 가능한지 묻기
                int resCode = httpURLConnection.getResponseCode();

                BufferedReader bufferedReader ;

                if(resCode == 200) {

                    bufferedReader = new BufferedReader(
                            new InputStreamReader(
                                    httpURLConnection.getInputStream()
                            )
                    );

                } else {
                    bufferedReader = null;
                    return ;
                }

                String str_line;
                StringBuilder stringBuilder = new StringBuilder();

                /*
                while((str_line = bufferedReader.readLine()) {
                    stringBuilder.append(str_line);
                }
                */

                while(true) {
                    str_line = bufferedReader.readLine();
                    if(str_line == null) break;

                    stringBuilder.append(str_line);
                }
                bufferedReader.close();

                // JSON 파싱 : 데이터 추출

                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray(Naver_Items.ITEM);

                // 데이터를 담을 vo array 생성
                movies = new ArrayList<MovieVO>();

                for(int i = 0 ; i < jsonArray.length();i++) {

                    MovieVO vo = new MovieVO();
                    JSONObject item = jsonArray.getJSONObject(i);

                    vo.setTitle(item.getString(Naver_Items.ITEMS.TITLE));
                    vo.setSubtitle(item.getString(Naver_Items.ITEMS.SUBTITLE));
                    vo.setDirector(item.getString(Naver_Items.ITEMS.DIRECTOR));
                    vo.setActor(item.getString(Naver_Items.ITEMS.ACTOR));
                    vo.setImage(item.getString(Naver_Items.ITEMS.IMAGE));
                    vo.setLink(item.getString(Naver_Items.ITEMS.LINK));
                    vo.setPubDate(item.getString(Naver_Items.ITEMS.PUBDATE));
                    vo.setUserRating(item.getString(Naver_Items.ITEMS.USERRATING));

                    movies.add(vo);

                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}


















