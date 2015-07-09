package com.example.jo_seisho.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;


public class MainActivity extends ActionBarActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
    Button bp, bm, bt, bd, bc, bq, bb;
    TextView screen;
    //暫存每次運算元輸入時的數字字串
    String temp = "";
    //存放資料的鏈結
    LinkedList<String> quest = new LinkedList<>();
    LinkedList<String> output = new LinkedList<>();
    LinkedList<String> stack = new LinkedList<>();
    LinkedList<Double> answer = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //介面元件取得
        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        b5 = (Button)findViewById(R.id.button5);
        b6 = (Button)findViewById(R.id.button6);
        b7 = (Button)findViewById(R.id.button7);
        b8 = (Button)findViewById(R.id.button8);
        b9 = (Button)findViewById(R.id.button9);
        b0 = (Button)findViewById(R.id.button0);
        bp = (Button)findViewById(R.id.btnPlus);
        bm = (Button)findViewById(R.id.btnMinus);
        bt = (Button)findViewById(R.id.btnTimes);
        bd = (Button)findViewById(R.id.btnDivide);
        bc = (Button)findViewById(R.id.btnClear);
        bq = (Button)findViewById(R.id.btnEqual);
        bb = (Button)findViewById(R.id.btnDot);
        screen = (TextView)findViewById(R.id.monitor);

        //用自訂方法偵測按鈕
        b1.setOnClickListener(btnDoListener);
        b2.setOnClickListener(btnDoListener);
        b3.setOnClickListener(btnDoListener);
        b4.setOnClickListener(btnDoListener);
        b5.setOnClickListener(btnDoListener);
        b6.setOnClickListener(btnDoListener);
        b7.setOnClickListener(btnDoListener);
        b8.setOnClickListener(btnDoListener);
        b9.setOnClickListener(btnDoListener);
        b0.setOnClickListener(btnDoListener);
        bp.setOnClickListener(btnDoListener);
        bm.setOnClickListener(btnDoListener);
        bt.setOnClickListener(btnDoListener);
        bd.setOnClickListener(btnDoListener);
        bc.setOnClickListener(btnDoListener);
        bq.setOnClickListener(btnDoListener);
        bb.setOnClickListener(btnDoListener);
    }
    private Button.OnClickListener btnDoListener = new Button.OnClickListener(){

        @Override
        public void onClick(View v){

            String s = screen.getText().toString();
            Log.v("show", quest.toString());
            switch (v.getId()){

                case R.id.button:
                    screen.setText(s + "1");
                    temp = temp + "1";
                    break;

                case R.id.button2:
                    screen.setText(s + "2");
                    temp = temp + "2";
                    break;

                case R.id.button3:
                    screen.setText(s + "3");
                    temp = temp + "3";
                    break;

                case R.id.button4:
                    screen.setText(s + "4");
                    temp = temp + "4";
                    break;

                case R.id.button5:
                    screen.setText(s + "5");
                    temp = temp + "5";
                    break;

                case R.id.button6:
                    screen.setText(s + "6");
                    temp = temp + "6";
                    break;

                case R.id.button7:
                    screen.setText(s + "7");
                    temp = temp + "7";
                    break;

                case R.id.button8:
                    screen.setText(s + "8");
                    temp = temp + "8";
                    break;

                case R.id.button9:
                    screen.setText(s + "9");
                    temp = temp + "9";
                    break;

                case R.id.button0:
                    screen.setText(s + "0");
                    temp = temp + "0";
                    break;

                case R.id.btnDot:
                    screen.setText(s +".");
                    temp = temp + ".";
                    break;

                case R.id.btnClear:
                    screen.setText("");
                    temp = "";
                    quest.clear();
                    break;

                case R.id.btnPlus:
                    screen.setText(s + "+");
                    quest.add(temp);
                    quest.add("+");
                    temp = "";
                    break;


                case R.id.btnMinus:
                    screen.setText(s + "-");
                    quest.add(temp);
                    quest.add("-");
                    temp = "";
                    break;


                case R.id.btnTimes:
                    screen.setText(s + "*");
                    quest.add(temp);
                    quest.add("*");
                    temp = "";
                    break;


                case R.id.btnDivide:
                    screen.setText(s + "/");
                    quest.add(temp);
                    quest.add("/");
                    temp = "";
                    break;


                case R.id.btnEqual:
                    if(quest.size() == 0){
                        break;
                    }
                    else{
                        quest.add(temp);

                        //實作中序轉後序
                        //---------------------------------------------
                        //LinkedList<String> output = new LinkedList<>();
                        //LinkedList<String> stack = new LinkedList<>();

                        for(String k : quest){
                            if(Priority(k) == 0){
                                //數字
                                output.add(k);
                            }
                            else{
                                //運算子時
                                if(stack.size() == 0){
                                    stack.push(k);
                                }
                                else if(Priority(k) <= Priority(stack.getLast())){
                                    output.add(stack.pop());
                                    stack.push(k);
                                }else{
                                    stack.push(k);
                                }
                            }
                        }
                        //stacksize紀錄最剛開始的stack的大小
                        int stacksize = stack.size();
                        //stack中的運算子一一pop放入output
                        for(int i = 0; i < stacksize; i++){
                            output.add(stack.pop());
                        }
                        //----------------------------------------------
                        //screen.setText("a:" + output + stack );

                        //計算後序式,以堆疊方式
                        //LinkedList<Double> answer = new LinkedList<>();
                        for(String t: output){
                            if(Priority(t) != 0){
                                double num2 = answer.pop();
                                double num1 = answer.pop();
                                double num3 = cal(t, num1, num2);
                                answer.push(num3);
                            }else{
                                answer.push(Double.valueOf(t));
                            }
                        }
                        if(answer.getFirst()%1 == 0){
                            screen.setText(String.format("%.0f", answer.getFirst()));
                        }
                        else{
                            screen.setText(String.format("%f", answer.getFirst()));
                        }
                        temp = String.format("%f", answer.getFirst());
                        //所有東西初始化
                        quest.clear();
                        output.clear();
                        stack.clear();
                        answer.clear();
                    }

                    //screen.setText("A:" + answer);
            }

        }
    };

    public static int Priority(String ope){
        if(ope.equals("*")|| ope.equals("/")){
            return 2;
        }else if(ope.equals("+")|| ope.equals("-")){
            return 1;
        }else{
            return 0;
        }
    }
    public  static double cal(String operator, double num1, double num2){
        double result = 0;

        switch(operator){
            case "+":
                result =  num1 + num2;
                break;
            case "-":
                result =  num1 - num2;
                break;
            case "*":
                result =  num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
