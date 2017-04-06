package me.gerald.hw2;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String expr = "";
    boolean err = false;
    TextView exprDisplay = null;

    void setDisplay(String s) {
        expr = s;
        exprDisplay.setText(s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exprDisplay = (TextView)findViewById(R.id.exprDisplay);
        ArrayList<Button> bts = new ArrayList<>();
        bts.add((Button)findViewById(R.id.bt0));
        bts.add((Button)findViewById(R.id.bt1));
        bts.add((Button)findViewById(R.id.bt2));
        bts.add((Button)findViewById(R.id.bt3));
        bts.add((Button)findViewById(R.id.bt4));
        bts.add((Button)findViewById(R.id.bt5));
        bts.add((Button)findViewById(R.id.bt6));
        bts.add((Button)findViewById(R.id.bt7));
        bts.add((Button)findViewById(R.id.bt8));
        bts.add((Button)findViewById(R.id.bt9));
        bts.add((Button)findViewById(R.id.btPlus));
        bts.add((Button)findViewById(R.id.btMinus));
        bts.add((Button)findViewById(R.id.btMul));
        bts.add((Button)findViewById(R.id.btDiv));
        bts.add((Button)findViewById(R.id.btDot));
        for(Button bt : bts)
            bt.setOnClickListener(this);
        exprDisplay.setText(expr);
        findViewById(R.id.btClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDisplay("");
                err = false;
            }
        });
        findViewById(R.id.btEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Expression expression = new ExpressionBuilder(expr).build();
                    double result = expression.evaluate();
                    setDisplay(String.valueOf(result));
                    err = false;
                } catch(Exception ex) {
                    setDisplay("Error");
                    err = true;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Button button = (Button)view;
        if(err) {
            err = false;
            setDisplay("" + button.getText());
        } else
            setDisplay(expr + button.getText());
    }
}
