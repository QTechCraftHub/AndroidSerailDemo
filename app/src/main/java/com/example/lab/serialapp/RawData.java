package com.example.lab.serialapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class RawData extends AppCompatActivity {

    private static final String TAG = "SerialPort Read";

    SerialAPI_Interface serialapi;
    char[] receive;
    protected OutputStream mOutputStream;
    private InputStream mInputStream;
    private ReadThread mReadThread;
    int fd;

    TextView rawData_1;
    TextView rawData_2;
    TextView rawData_3;
    TextView rawData_4;
    TextView rawData_5;

    char[] disp1;
    char[] disp2;
    char[] disp3;
    char[] disp4;
    char[] disp5;
    int line;

    Button sendBtn;
    EditText sendEdit;
    int[] sendbuf;

    private class ReadThread extends Thread {
        public void run() {
            super.run();
            while(!isInterrupted()) {
                int temp = 0;
                int[] buffer = new int[512];
                if (serialapi == null)
                {
                    Log.e(TAG, "serialapi == null!");
                    return;
                }
                if(fd > 0)
                {
                    buffer = serialapi.Read(fd);
                    char temp_record;
                    if (buffer != null) {
                        Log.i(TAG, "Size:" + String.valueOf(buffer.length));
                        Log.i(TAG, "buffer");
                        receive = serialapi.getRawData(buffer, buffer.length);
                        onDataReceived(receive);
                    }
                    else
                    {

                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_data);
        rawData_1 = (TextView)findViewById(R.id.RawData_1);
        rawData_2 = (TextView)findViewById(R.id.RawData_2);
        rawData_3 = (TextView)findViewById(R.id.RawData_3);
        rawData_4 = (TextView)findViewById(R.id.RawData_4);
        rawData_5 = (TextView)findViewById(R.id.RawData_5);
        line = 0;
        serialapi = new SerialAPI_Interface();
        sendBtn = (Button)findViewById(R.id.SendButton);
        sendEdit = (EditText)findViewById(R.id.DataSend);

        fd = serialapi.Open(3, 115200);
        if(fd > 0)
        {
            mReadThread = new ReadThread();
            mReadThread.start();
        }
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char[] send_temp = sendEdit.getText().toString().toCharArray();
                int temp = 0;
                if(send_temp != null)
                {
                    sendbuf = new int[send_temp.length];
                    for(;temp < send_temp.length; temp++)
                    {
                        sendbuf[temp] = (int)send_temp[temp];
                    }
                    serialapi.Write(fd, sendbuf, send_temp.length);
                    Toast.makeText(RawData.this, "Send Successfully!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    protected void onDataReceived(final char[] buffer) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (rawData_1 != null) {
                    if(line < 4)
                        line ++;
                    switch (line){
                        case 0:
                            disp1 = buffer;
                            disp2 = null;
                            disp3 = null;
                            disp4 = null;
                            disp5 = null;
                            break;
                        case 1:
                            disp2 = disp1;
                            disp1 = buffer;
                            break;
                        case 2:
                            disp3 = disp2;
                            disp2 = disp1;
                            disp1 = buffer;
                            break;
                        case 3:
                            disp4 = disp3;
                            disp3 = disp2;
                            disp2 = disp1;
                            disp1 = buffer;
                            break;
                        case 4:
                            disp5 = disp4;
                            disp4 = disp3;
                            disp3 = disp2;
                            disp2 = disp1;
                            disp1 = buffer;
                            break;
                    }
                    if(disp5 != null)
                    {
                        rawData_1.setText(disp5, 0, disp5.length);
                    }
                    if(disp4 != null)
                    {
                        rawData_2.setText(disp4, 0, disp4.length);
                    }
                    if(disp3 != null)
                    {
                        rawData_3.setText(disp3, 0, disp3.length);
                    }
                    if(disp2 != null)
                    {
                        rawData_4.setText(disp2, 0, disp2.length);
                    }
                    if(disp1 != null)
                    {
                        rawData_5.setText(disp1, 0, disp1.length);
                    }
                }
                else
                {
                    Log.i(TAG, "rawData == null");
                }
            }
        });
    }
}
