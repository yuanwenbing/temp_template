package com.bitauto.common.lib;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bitauto.android.commonlib.R;

/**
 * 项目名称：Template
 *
 * @author menggod
 */
public class SplashActivity extends AppCompatActivity {
    private TextView mTvSkip;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        mTvSkip = findViewById(R.id.tv_splash_skip);
        mTvSkip.setOnClickListener(view -> processJump());
        startClock();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void startClock() {
        countDownTimer = new CountDownTimer(3200, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long l) {
                mTvSkip.setText("跳过广告" + l / 1000 + "s");
            }

            @Override
            public void onFinish() {
                processJump();
            }
        };
        countDownTimer.start();
    }

    /**
     * 处理跳主页还是引导页
     */
    private void processJump() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        finish();
    }


}
