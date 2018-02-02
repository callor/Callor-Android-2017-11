package com.callor.lession.navermovie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
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
import java.util.Locale;

//                                                              TTS 서비를 쉽게 사용하기 위한 인터페이스
public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextInputEditText txt_search;
    RecyclerView recyclerView ;

    TextToSpeech myTTS ; // Text To Speech, 글자를 말로 읽어 주는 서비스

    int REQ_CODE_SPEECH_ID = 1;

    BroadcastReceiver broadcastReceiver ;


    public enum TTS_RECV {
        INIT,
        SEARCH
    }

    // 누가 TTS 서비스를 호출했는가 표시하기 위한 ID
    TTS_RECV tts_recv = TTS_RECV.INIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myTTS = new TextToSpeech(this,this);

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



        // 마이크 아이콘을 터치했을때 할일
        txt_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    // 마이크 버튼의 그래픽 좌표를 계산해서
                    // 그 위를 터치 했냐?
                    if(event.getRawX() >= (txt_search.getRight()
                            - txt_search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        Toast.makeText(MainActivity.this,
                                "마이크 아이콘 터치",Toast.LENGTH_SHORT).show();


                        // 서비스 완료 후 수신기
                        IntentFilter filter = new IntentFilter();
                        filter.addAction(TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
                        registerReceiver(broadcastReceiver,filter);


                        tts_recv = TTS_RECV.SEARCH ;
                        myTTS.speak("말하기 창이 나타나면 검색어를 말하세요",TextToSpeech.QUEUE_FLUSH,null,null);


                        /*
                        Intent myInten = new Intent(A.this, B.class)
                            내 app에 있는 B Activity 화면을 여는 방법

                        */




                    }
                }
                return false;
            }
        });

    }// onCreate 가 끝나는 부분

    private void promptSpeedInput() {
        // 시스템(OS), 다른 app에 있는 기능을 사용하기
        // RecognizerIntent.ACTION_RECOGNIZE_SPEECH : 안드로이드 음성 인식 서비스 호출

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // 서비스 명칭
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); // 음성인식 방법

        // 어떤 언어로
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"검색어를 입력하세요");
        startActivityForResult(intent,REQ_CODE_SPEECH_ID);
    }


    // 음성 인식된 결과를 받을 수신기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE_SPEECH_ID) {
            if(resultCode == RESULT_OK && data != null) {
                Toast.makeText(MainActivity.this,"완료", Toast.LENGTH_SHORT).show();

                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);

                String speechToText = result.get(0);
                txt_search.setText(speechToText);

                NaverSearch naverSearch = new NaverSearch();
                naverSearch.execute();
            }
        }
    }


    // TTS를 이용하기 위해 초기화
    @Override
    public void onInit(int status) {

        myTTS.setLanguage(Locale.getDefault()); // 안드로이드 기본 글자 체계를 가져온다. 한글 모드
        myTTS.setPitch(1.0f);
        myTTS.setSpeechRate(1.0f);

        String strText1 = "안녕하세요 나의 영화 이야기 입니다";
        String strText2 = "반갑습니다";

        myTTS.speak(strText1,TextToSpeech.QUEUE_FLUSH,null,null);
        myTTS.speak(strText2,TextToSpeech.QUEUE_ADD,null,null);


        tts_recv = TTS_RECV.INIT;

        // 음성 합성(TTS) 서비스가 OS에서 종료되면, 호출될 Call Back 함수
        broadcastReceiver = new BroadcastReceiver() {



            @Override
            public void onReceive(Context context, Intent intent) {

                // 누가 결과를 주었느냐
                // filter에 설정된 action인가를 확인
                String action = intent.getAction();

                if(action == TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED) {
                    // intent.getAction();
                    // 검색버튼을 눌렀을때 TTS 기능이 실행됬을때만
                    // 아래 기능을 실행하라
                    if(tts_recv == TTS_RECV.SEARCH) {


                        // 다음 동작을 실행하기 전에 1초간 멈추어라
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(MainActivity.this,
                                "음성 합성(TTS) 서비스 완료",Toast.LENGTH_SHORT).show();

                        promptSpeedInput();
                    }
                }
            }
        };


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
        myTTS.shutdown(); // TTS 서비를 사용하고 나면 반드시 종료 해주어야 한다.


    }

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


















