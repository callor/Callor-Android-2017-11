package com.callor.lession.naverfrend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
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
import java.util.Locale;

import static android.speech.tts.TextToSpeech.QUEUE_FLUSH;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextInputEditText txt_search;
    RecyclerView recyclerView;

    // 음성합성(TTS)
    TextToSpeech myTTS;

    // 음성합성(TTS) 가 완성된 후 할일 등록
    BroadcastReceiver m_br;

    public enum TTS_RECV {
        INIT, SEARCH
    }

    TTS_RECV tts_recev = TTS_RECV.INIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myTTS = new TextToSpeech(this, this);


        txt_search = findViewById(R.id.txt_search);
        recyclerView = findViewById(R.id.search_list);
        // ImageButton btn_search = findViewById(R.id.btn_search);


        txt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String str_search = txt_search.getText().toString();
                if (str_search.isEmpty()) {

                    Toast
                            .makeText(getApplicationContext(), "검색어를 입력하세요", Toast.LENGTH_SHORT)
                            .show();

                    return false;
                }
                NaverSearch naverSearch = new NaverSearch(str_search);
                naverSearch.execute();
                return true;
            }
        });

        /*
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 검색어를 추출
                String str_search = txt_search.getText().toString();
                if (str_search.isEmpty()) {

                    Toast
                            .makeText(getApplicationContext(), "검색어를 입력하세요", Toast.LENGTH_SHORT)
                            .show();

                    return;
                }
                NaverSearch naverSearch = new NaverSearch(str_search);
                naverSearch.execute();

            }
        });
        */

        txt_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (txt_search.getRight() - txt_search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        tts_recev = TTS_RECV.SEARCH;

                        // your action here
                        Toast.makeText(getApplicationContext(), "마이크 터치", Toast.LENGTH_SHORT).show();

                        // IntentFilter 객체를 생성한다.
                        IntentFilter filter = new IntentFilter();

                        // TTS의 음성출력의 완료되는 동작에 대한 알림를 수신하기위해
                        // IntentFilter 에 해당 동작을 추가한다.
                        // filter.addAction(TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
                        filter.addAction(TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);

                        // 그리고 현재 액티비티가 실행될 때 음성 출력의 완료 동작에 관한 알림을 수신받아 처리하는 브로드
                        //  캐스트 리시버를 등록합니다.
                        // IntentFilter 에 추가된 동작이 발생할 경우 이 알림을 수신하는
                        // Broadcast Receiver 를 등록한다.
                        registerReceiver(m_br, filter);
                        myTTS.speak(" 말하기 창이 나오면 검색어를 말하세요", QUEUE_FLUSH, null, null);


                        // 벨소리
//                        Uri currentRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), TYPE_RINGTONE);

//                        Ringtone currentRingtone = RingtoneManager.getRingtone(getApplicationContext(),  currentRintoneUri);
//                        currentRingtone.play();


//                        Ringtone defaultRingtone = RingtoneManager.getRingtone(MainActivity.this,
//                                Settings.System.DEFAULT_RINGTONE_URI);

//                        defaultRingtone.play();


                        return true;
                    }
                }
                return false;
            }
        });
    }

    // 음성합성 API interface
    @Override
    public void onInit(int status) {

        myTTS.setLanguage(Locale.getDefault());
        myTTS.setPitch(1.3f);
        myTTS.setSpeechRate(1f);

        String strText1 = ""; // 안녕하세요 네이버 도서 정보입니다.";
        String strText2 = "" ; // 반갑습니다.";

        myTTS.speak(strText1, QUEUE_FLUSH, null, null);
        myTTS.speak(strText2, TextToSpeech.QUEUE_ADD, null, null);


        /**음성출력 완료를 처리하기 위해 BroadCast를 이용하여 처리한다.
         * 아래와 같이 음성 출력이 완료되었을 때를 알려주는 브로드캐스트 리시버 객체를 생성합니다. 브로드
         * 캐스트  리시버가 알림을 수신하게되면 onReceive() 메소드가 호출되고, TTS 엔진의 음성 출력이
         * 완료되어 알림이 수신된 경우 토스트 메시지를 통해 사용자에게 알려줍니다.
         */
        // 브로드캐스트 리시버 객체를 생성한다.
        m_br = new BroadcastReceiver() {

            // 브로드캐스트 알림이 수신되면 호출되는 onReceive 메소드를 정의한다.
            public void onReceive(Context context, Intent intent) {
                // Intent 로부터 어떤 동작으로 인해 Broadcast Receiver 에게 알림이
                // 수신되었는지에 대한 정보를 가져온다.
                String act = intent.getAction();

                // TTS의 음성출력이 완료되어 알림이 수신된 경우

                Toast.makeText(MainActivity.this, act, Toast.LENGTH_LONG).show();

                if (act.equals(TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED) && (tts_recev == TTS_RECV.SEARCH)) {

                    // 토스트 메시지를 통해 TTS 의 음성출력이 완료되었음을 사용자에게 알린다.
                    Toast.makeText(MainActivity.this, "TTS 음성 출력 완료", Toast.LENGTH_SHORT).show();

                    // 알림음 송출
                    ToneGenerator toneG
                            = new ToneGenerator(AudioManager.STREAM_ALARM, 50);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200)) {
//                        try {
//                            Thread.sleep(100);
//                            Thread.sleep(500);
//                            toneG.stopTone();
//                            Thread.sleep(100);
//                            toneG.stopTone();
//                            toneG.release();
//
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }

                    // 음성인식 시작
                    promptSpeechInput();
                    tts_recev = TTS_RECV.INIT;

                }
            }
        };
    }

    public static final int REQ_CODE_SPEECH_INPUT = 0;
    // 음성인식 시작
    private void promptSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "검색어를 말하세요");

        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT:
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(
                            RecognizerIntent.EXTRA_RESULTS);
                    //txtSpeechInput.setText(result.get(0));

                    Log.d("speech", "speech" + result.get(0));

                    String speechtext = result.get(0); //인식한 것에 대해 저장한 값
                    txt_search.setText(speechtext);

                    NaverSearch naverSearch = new NaverSearch(speechtext);
                    naverSearch.execute();


                }
        }
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

        //  등록했던 브로드캐스트 리시버를 아래와 같이 해제합니다.
        // 등록된 브로드캐스트 리시버를 해제한다.
        unregisterReceiver(m_br);
        myTTS.shutdown();
    }


    // 비동기적으로 네이버와 통신하기 위해서 AsyncTask를 상속받는다.
    public class NaverSearch extends AsyncTask<Integer, Integer, Void> {

        // 네이버로 부터 받을 데이터 리스트
        private List<BookVO.BookItemVO> books;
        private String str_search;


        public NaverSearch() {
        }

        // 실제 검색에서 사용할 생성자
        public NaverSearch(String str_search) {
            this.str_search = str_search;
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
                    = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            String str_Speech = txt_search.getText().toString();
            str_Speech += "검색을 완료 했습니다";
            myTTS.speak(str_Speech,TextToSpeech.QUEUE_FLUSH,null,null);
            txt_search.setText("");
        }

        // 네이버 검색을 할 method
        public void naver_search() {

            String client_ID = Naver_Secret.CLIENT_ID;
            String client_Secret = Naver_Secret.CLIENT_SECRET;

            try {

                String apiURL = "https://openapi.naver.com/v1/search/book.json";
                apiURL += "?query=" + URLEncoder.encode(str_search, "UTF-8");
                apiURL += "&display=20";

                URL url = new URL(apiURL);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                // Http Header 를 직접 생성해줘야 한다.
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("X-Naver-Client-Id", client_ID);
                httpURLConnection.setRequestProperty("X-Naver-Client-Secret", client_Secret);

                int resCode = httpURLConnection.getResponseCode(); // 네이버에 GET query를 하고 res code 받기

                BufferedReader bufferedReader;

                // 네이버로 부터 데이터를 수신해서 bufferedReader에 받는다
                if (resCode == 200) {
                    // 이후의 데이터를 받아서 처리하기
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                } else {
                    // 어떤 문제로 데이터를 받을 수 없다.
                    bufferedReader = null; // 데이터가 없다.
                }

                if (bufferedReader == null) {
                    return;
                }

                // bufferedReader 로 부터 한줄씩 추출해서 resString에 담는다.
                String inputLine;
                StringBuilder resString = new StringBuilder();
                while ((inputLine = bufferedReader.readLine()) != null) {
                    resString.append(inputLine);
                }
                bufferedReader.close();

                // 데이터를 JSON 구조로 변경한다
                JSONObject resJson = new JSONObject(resString.toString());

                // 아이템 리스트를 JSON 배열로 변환시킨다
                JSONArray resItems = resJson.getJSONArray("items");

                books = new ArrayList<BookVO.BookItemVO>();
                Log.d("Books", String.valueOf(books.size()));

                // for(JSONObject j : resItems) {} : 사용할 수 없다.
                for (int i = 0; i < resItems.length(); i++) {

                    JSONObject itemObject = resItems.getJSONObject(i);
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
