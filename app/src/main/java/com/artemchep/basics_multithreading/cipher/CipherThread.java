package com.artemchep.basics_multithreading.cipher;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class CipherThread implements Runnable{
    final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final String textToCypher;
    private CipherI cipherCallback;
    private long startTime;

    public CipherThread(String textToCypher, long startThreadTime) {
        this.textToCypher = textToCypher;
        this.startTime = startThreadTime;
    }

    public void setCICallback(CipherI entity) {
        this.cipherCallback = entity;
    }

    @Override
    public void run() {
        Log.d("TaskStatus", "updateUICallback: " + Thread.currentThread().getName());
        final String encryptedText = CipherUtil.encrypt(textToCypher);
        final long threadTime = System.currentTimeMillis() - startTime;
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                cipherCallback.updateUICallback(encryptedText, threadTime);
            }
        });
        Log.d("TaskStatus", Thread.currentThread().getName() + " is finished");
        Log.d("TaskStatus", encryptedText);
    }
}
