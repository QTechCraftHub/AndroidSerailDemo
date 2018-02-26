package com.example.lab.serialapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int serial_port;
    int serial_rate;
    SerialAPI_Interface  serialapi;
    EditText serialPort;
    EditText serialRate;
    int fd;
    int[] buf = {1, 3, 4, 5, 6, 7, 8 ,9 ,2 ,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serialapi = new SerialAPI_Interface();

        fd = serialapi.Open(3, 115200);
        if(fd > 0)
        {
            Toast.makeText(MainActivity.this, "Successfully!", Toast.LENGTH_LONG).show();
            serialapi.Write(fd, buf, 10);
        }
        Button rawDataView = (Button)findViewById(R.id.DeviceButton);
        rawDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serialapi.Close(fd);
                Toast.makeText(MainActivity.this, "Press!", Toast.LENGTH_LONG).show();
                //Intent intent =new Intent(MainActivity.this,RawData.class);
                //startActivity(intent);
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,RawData.class);
                startActivity(intent);
            }
        });
        //Get Serial port & rate
        //serialPort = (EditText)findViewById(R.id.SerialPort);
        //serialRate = (EditText)findViewById(R.id.SerialRate);

        //serial_port = Integer.valueOf(serialPort.getText().toString()).intValue();
        //serial_rate = Integer.valueOf(serialRate.getText().toString()).intValue();
    }

}
