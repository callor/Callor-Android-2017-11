package com.callor.lession.todolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Environment;
import android.os.Message;
import android.speech.SpeechRecognizer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.callor.lession.todolist.naver.AudioWriterPCM;
import com.callor.lession.todolist.naver.Naver_Secret;
import com.naver.speech.clientapi.SpeechRecognitionResult;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class NaverClova extends AppCompatActivity {


    // naver clova 설정
    private final String CLIENT_ID = Naver_Secret.CLIENT_ID;

    // Naver와 hand Shake 할 클래스 선언
    private RecogHandle handler ; // 네이버로 부터 메시지를 수신하는 클래스
    private NaverRecognizer naverRecognizer; // 네이버와 교신하는 클래스
    private String strResult; // 음성인식된 text를 임시로 보관할 변수
    private AudioWriterPCM writer; // 네이버에 음성을 보내는 클래스

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();

    // 기존의 mContentView를 btn_mic 이름변경
    private ImageButton btn_mic;
    private TextView txt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_naver_clova);

        // 핸들러 초기화
        handler = new RecogHandle(this);
        naverRecognizer = new NaverRecognizer(this,handler,CLIENT_ID);


        btn_mic = findViewById(R.id.btn_mic);
        txt_message = findViewById(R.id.txt_message);
        txt_message.setText("마이크를 터치하세요");

        btn_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(!naverRecognizer.getSpeechRecognizer().isRunning()) {
                txt_message.setText("클로버 연결중..");
                strResult = "";
                naverRecognizer.recognize();
            } else {
                naverRecognizer.getSpeechRecognizer().stop();
            }
            }
        });

        txt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naverRecognizer.getSpeechRecognizer().stop();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        // 음성인식을 열어주세요
        naverRecognizer.getSpeechRecognizer().initialize();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 음성인식을 닫아 주세요
        naverRecognizer.getSpeechRecognizer().release();
    }

    // TODO : 네이버 리턴 메시지 처리
    private void handleMessage(Message msg){

        // naver가 보낸 메시지를 분석
        switch(msg.what) { // command
            case R.id.clientReady :// 음성인식을 할 준비가 되었다라는 command
                // mContentView.setText("말을 시작하세요");
                txt_message.setText("말을 시작하세요");
                writer = new AudioWriterPCM(
                        Environment.getExternalStorageDirectory().getAbsolutePath()+"/naverSpech"
                );
                writer.open("naver_speech");
                break;

            case R.id.audioRecording :
                writer.write((short[]) msg.obj);
                break;

            case R.id.partialResult: // 음성인식중
                strResult = (String)msg.obj;
                txt_message.setText(strResult);
                break;

            case R.id.finalResult: // 음성인식 완료
                SpeechRecognitionResult speechRecognitionResult = (SpeechRecognitionResult) msg.obj;
                List<String> results = speechRecognitionResult.getResults();

                StringBuilder stringBuilder = new StringBuilder();

                // String strResult = "";
                for(String s : results) {
                    stringBuilder.append(s);
                    stringBuilder.append(("\n"));
                }
                txt_message.setText(stringBuilder.toString());

                // naverClova를 호출한 메인에게 음성 인식 결과를 돌려준다.
                Intent intent = new Intent(); // 나를 호출한 Activit의 정보를 가져온다.

                intent.putExtra("naver_result",strResult);
                setResult(RESULT_OK, intent); // 정상적으로 내 할일을 종료 했으니 알아서 하세요
                // setResult(RESULT_CANCELED,intent);

                this.finish();
                break;

            case R.id.recognitionError: // 인식과정에서 오류가 발생
                break;

            case R.id.clientInactive: // 네이버 음성인식기에서 접속 거부
                break;
        }
    }

    // Naver로 부터 데이터(Text)를 받을 클래스
    class RecogHandle extends Handler {

        // 현재 Activity 내에서 백그라운드로 실행 하도록 하는 절차
        private final WeakReference<NaverClova> thisActivity;
        RecogHandle(NaverClova activity) {
            thisActivity = new WeakReference<NaverClova>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            // inner class에서 outter class의 method를 사용하는 방법
             NaverClova activity = thisActivity.get();
            if(activity != null) {
                activity.handleMessage(msg);
            }

        }
    }

}
