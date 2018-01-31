package com.callor.lession.naverfrend;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.callor.lession.naverfrend.data.BookVO;
import com.callor.lession.naverfrend.naver.Naver_Secret;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txt_search ;
    RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt_search = findViewById(R.id.txt_search);
        recyclerView = findViewById(R.id.search_list);
        ImageButton btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 검색어를 추출
                String str_search = txt_search.getText().toString();
                if(str_search.isEmpty()) {

                    Toast
                            .makeText(getApplicationContext(),"검색어를 입력하세요",Toast.LENGTH_SHORT)
                            .show();

                    return ;
                }
                NaverSearch naverSearch = new NaverSearch(str_search);
                naverSearch.execute();

            }
        });

        txt_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (txt_search.getRight() - txt_search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Toast.makeText(getApplicationContext(),"마이크 터치",Toast.LENGTH_SHORT).show();

                        return true;
                    }
                }
                return false;
            }
        });
    }

    // 비동기적으로 네이버와 통신하기 위해서 AsyncTask를 상속받는다.
    public class NaverSearch extends AsyncTask<Integer, Integer, Void> {

        // 네이버로 부터 받을 데이터 리스트
        private List<BookVO.BookItemVO> books;
        private String txt_search ;


        public NaverSearch() {
        }

        // 실제 검색에서 사용할 생성자
        public NaverSearch(String txt_search) {
            this.txt_search = txt_search;
        }

        // 실제적으로 백그라운드에서 일을 수행할 method
        // 네이버와 통신을 하도록 한다.
        @Override
        protected Void doInBackground(Integer... integers) {
            naver_search();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            BookAdapter adapter = new BookAdapter(books);
            recyclerView.setAdapter(adapter);

            StaggeredGridLayoutManager layoutManager
                    = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }

        // 네이버 검색을 할 method
        public void naver_search() {

            String client_ID = Naver_Secret.CLIENT_ID ;
            String client_Secret = Naver_Secret.CLIENT_SECRET;

            try {

                String apiURL = "https://openapi.naver.com/v1/search/book.json";
                apiURL += "?query=" + URLEncoder.encode(txt_search,"UTF-8");
                apiURL += "&display=20";

                URL url = new URL(apiURL);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                // Http Header 를 직접 생성해줘야 한다.
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("X-Naver-Client-Id",client_ID);
                httpURLConnection.setRequestProperty("X-Naver-Client-Secret",client_Secret);

                int resCode = httpURLConnection.getResponseCode(); // 네이버에 GET query를 하고 res code 받기

                BufferedReader bufferedReader;

                // 네이버로 부터 데이터를 수신해서 bufferedReader에 받는다
                if(resCode == 200) {
                    // 이후의 데이터를 받아서 처리하기
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                } else {
                    // 어떤 문제로 데이터를 받을 수 없다.
                    bufferedReader = null; // 데이터가 없다.
                }

                if(bufferedReader == null) {
                    return ;
                }

                // bufferedReader 로 부터 한줄씩 추출해서 resString에 담는다.
                String inputLine ;
                StringBuilder resString = new StringBuilder();
                while((inputLine = bufferedReader.readLine()) != null) {
                    resString.append(inputLine);
                }
                bufferedReader.close();

                // 데이터를 JSON 구조로 변경한다
                JSONObject resJson = new JSONObject(resString.toString());

                // 아이템 리스트를 JSON 배열로 변환시킨다
                JSONArray resItems = resJson.getJSONArray("items");

                books = new ArrayList<BookVO.BookItemVO>();
                Log.d("Books",String.valueOf(books.size()));

                // for(JSONObject j : resItems) {} : 사용할 수 없다.
                for(int i = 0 ; i < resItems.length(); i++) {

                    JSONObject itemObject = resItems.getJSONObject(i) ;
                    BookVO.BookItemVO vo = new BookVO.BookItemVO();

                    vo.setTitle(itemObject.getString("title"));
                    vo.setImage(itemObject.getString("image"));
                    vo.setDesciption(itemObject.getString("description"));

                    vo.setLink(itemObject.getString("link"));

                    vo.setAuthor(itemObject.getString("author"));
                    vo.setPrice(itemObject.getString("price"));
                    vo.setIsbn(itemObject.getString("isbn"));
                    vo.setDiscount(itemObject.getString("discount"));

                    books.add(vo);

                }


//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

}
