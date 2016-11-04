package ru.ifmo.android_2016.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
* Created by alexey.nikitin on 13.09.16.
*/


public class CalculatorActivity extends Activity implements View.OnClickListener {
    Button[] button = new Button[10];
/*    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;*/

    Button buttonMul;
    Button buttonDiv;
    Button buttonDiff;
    Button buttonSum;
    Button buttonEcual;
    Button buttonDel;

    TextView tvResult;

    String cnt = "";
    Long num1 = Long.valueOf(0), num2 = Long.valueOf(0);
    Boolean isSecond = false, isEnd = false;
    String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button[0] = (Button) findViewById(R.id.button0);
        button[1] = (Button) findViewById(R.id.button1);
        button[2] = (Button) findViewById(R.id.button2);
        button[3] = (Button) findViewById(R.id.button3);
        button[4] = (Button) findViewById(R.id.button4);
        button[5] = (Button) findViewById(R.id.button5);
        button[6] = (Button) findViewById(R.id.button6);
        button[7] = (Button) findViewById(R.id.button7);
        button[8] = (Button) findViewById(R.id.button8);
        button[9] = (Button) findViewById(R.id.button9);

        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonDiff = (Button) findViewById(R.id.buttonDiff);
        buttonSum = (Button) findViewById(R.id.buttonSum);
        buttonMul = (Button) findViewById(R.id.buttonMul);
        buttonEcual = (Button) findViewById(R.id.buttonEcual);
        buttonDel = (Button) findViewById(R.id.buttonDel);

        tvResult = (TextView) findViewById(R.id.tvResult);

        for(int i = 0; i < 10; i++)
            button[i].setOnClickListener(this);


        buttonSum.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiff.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonEcual.setOnClickListener(this);
        buttonDel.setOnClickListener(this);

        tvResult.setText(cnt);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("cnt", cnt);
        outState.putLong("num1", num1);
        outState.putLong("num2", num2);
        outState.putBoolean("isSecond", isSecond);
        outState.putString("op", op);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cnt = savedInstanceState.getString("cnt");
        num1 = savedInstanceState.getLong("num1");
        num2 = savedInstanceState.getLong("num2");
        isSecond = savedInstanceState.getBoolean("isSecond");
        op = savedInstanceState.getString("op");
        tvResult.setText(cnt);
    }

    void addTo(Integer a) {
        if(isEnd) {
            cnt = "";
            tvResult.setText(cnt);
            num1 = Long.valueOf(a);
            isEnd = false;

        }
        else if(isSecond)
            num2 = num2 * 10 + a;
        else
            num1 = num1 * 10 + a;
    }

    void doThing() {
        switch (op) {
            case "+":
                num1 += num2;
                break;
            case "-":
                num1 -= num2;
                break;
            case "*":
                num1 *= num2;
                break;
            case "/":
                if(num2 != 0)
                num1 /= num2;
                break;
        }
        num2 = Long.valueOf(0);
        op = "";
        isSecond = false;
    }

    @Override
    public void onClick(View v) {
        String oper = "";
        switch (v.getId()) {
            case R.id.button0:
                oper = "0";
                addTo(0);
                break;
            case R.id.button1:
                oper = "1";
                addTo(1);
                break;
            case R.id.button2:
                oper = "2";
                addTo(2);
                break;
            case R.id.button3:
                oper = "3";
                addTo(3);
                break;
            case R.id.button4:
                oper = "4";
                addTo(4);
                break;
            case R.id.button5:
                oper = "5";
                addTo(5);
                break;
            case R.id.button6:
                oper = "6";
                addTo(6);
                break;
            case R.id.button7:
                oper = "7";
                addTo(7);
                break;
            case R.id.button8:
                oper = "8";
                addTo(8);
                break;
            case R.id.button9:
                oper = "9";
                addTo(9);
                break;

            case R.id.buttonSum:
                isEnd = false;
                if(num1 != 0) {
                    if(isSecond)
                        doThing();
                    op = oper = "+";
                    isSecond = true;
                }
                break;
            case R.id.buttonDiff:
                isEnd = false;
                if(num1 != 0) {
                    if(isSecond)
                        doThing();
                    op = oper = "-";
                    isSecond = true;
                }
                break;
            case R.id.buttonMul:
                isEnd = false;
                if(num1 != 0) {
                    if(isSecond)
                        doThing();
                    op = oper = "*";
                    isSecond = true;
                }
                break;
            case R.id.buttonDiv:
                isEnd = false;
                if(num1 != 0) {
                    if(isSecond)
                        doThing();
                    op = oper = "/";
                    isSecond = true;
                }
                break;
            case R.id.buttonDel:
                num1 = num2 = Long.valueOf(0);
                op = "";
                isSecond = false;
                cnt = "";
                tvResult.setText(cnt);
            default:
                break;
        }
        if(v.getId() == R.id.buttonEcual) {
            boolean isZero = false;
            switch (op) {
                case "+":
                    num1 += num2;
                    break;
                case "-":
                    num1 -= num2;
                    break;
                case "*":
                    num1 *= num2;
                    break;
                case "/":
                    if(num2 == 0)
                        isZero = true;
                    if(num2 != 0)
                        num1 /= num2;
                    break;
            }
            if(!isZero) {
                tvResult.setText(cnt + " = " + num1.toString());
//                num1 = num2 = Long.valueOf(0);
            }
            else
                tvResult.setText(cnt + "=INF");
            cnt = num1.toString();
            num2 = Long.valueOf(0);
            isEnd = true;
            op = "";
            isSecond = false;
        }
        else {
            tvResult.setText(cnt + oper);
            cnt += oper;
        }
    }
}

