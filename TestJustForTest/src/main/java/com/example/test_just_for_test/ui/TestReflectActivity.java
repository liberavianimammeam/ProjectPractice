package com.example.test_just_for_test.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test_just_for_test.R;
import com.example.test_just_for_test.bean.TestReflectBean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflectActivity extends AppCompatActivity {

    private static final String TAG = "TestJustForTest_TestRA";
    Class<TestReflectBean> clasz;
    Object testBean;
    Field field1;
    Method method1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_reflect_activity);

        Log.i(TAG, "the filesDir is " + this.getFilesDir().getAbsolutePath() + "\n and the " + this.getExternalCacheDir().getAbsolutePath());

        try {
            clasz = (Class<TestReflectBean>) Class.forName("com.example.test_just_for_test.bean.TestReflectBean");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            testBean = clasz.newInstance();
//            Log.i(TAG, "onCreate: the testBean is " + testBean.testFun1() + "  and the test is " + testBean.getTest());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        try {
            field1 = clasz.getDeclaredField("test");
            field1.setAccessible(true);
            Log.i(TAG, "onCreate: the test is " + field1.get(testBean));
            field1.set(testBean, "testChange");
            Log.i(TAG, "onCreate: the test is " + field1.get(testBean));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Log.i(TAG, "onCreate: error at field");
            e.printStackTrace();
        }
//        clasz.

    }
}
