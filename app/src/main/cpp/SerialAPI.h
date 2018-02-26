//
// Created by Joye on 2018/1/31.
// Author : Jesse He
// Class  : SerialAPI
// Board  : iTOP-4412
//

#ifndef V1_SERIALAPI_H
#define V1_SERIALAPI_H

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     SerialAPI
 * Method:    Open
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_example_lab_serialapp_SerialAPI_1Interface__Open
        (JNIEnv *env, jobject obj, jint fd, jint Port, jint Rate);

/*
 * Class:     SerialAPI
 * Method:    Close
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_example_lab_serialapp_SerialAPI_1Interface__Close
        (JNIEnv *env, jobject obj, jint fd);

/*
 * Class:     SerialAPI
 * Method:    Read
 * Signature: ()I
 */
JNIEXPORT jintArray JNICALL Java_com_example_lab_serialapp_SerialAPI_1Interface__Read
        (JNIEnv *, jobject , jint fd);

/*
 * Class:     SerialAPI
 * Method:    Write
 * Signature: ()I
 */
JNIEXPORT jintArray JNICALL Java_com_example_lab_serialapp_SerialAPI_1Interface__Write
        (JNIEnv *env, jobject obj, jint fd, jintArray buf, jint buflen);
#ifdef __cplusplus
}
#endif
#endif //V1_SERIALAPI_H
