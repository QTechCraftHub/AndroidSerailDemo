package com.example.lab.serialapp;

/**
 * Created by Joye on 2018/1/31.
 */

public class SerialAPI_Interface {
    // Used to load the 'SerialAPI' library on application startup.
    static {
        System.loadLibrary("SerialAPI");
    }
    public char[] getRawData(int[] buf, int len)
    {
        char[] result = new char[len];
        int i;
        for(i = 0; i < len; i++)
        {
            result[i] = (char)buf[i];
        }
        return result;
    }
    //Open Serial Port
    public native int Open(int Port, int Rate);
    //Close Serial Port
    public native int Close(int fd);
    //Read From Serial Port
    public native int[] Read(int fd);
    //Write To Serial Port
    public native int[] Write(int fd, int[] buf, int buflen);
}
