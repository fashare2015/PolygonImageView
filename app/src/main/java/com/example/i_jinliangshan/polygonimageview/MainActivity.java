package com.example.i_jinliangshan.polygonimageview;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        PolygonImageView piv = (PolygonImageView) findViewById(R.id.piv);
        piv.getEdgeNum();
        piv.setEdgeNum(6);
    }

    private void init() {
        final EditText et = (EditText)findViewById(R.id.et);
        final PolygonImageView piv = (PolygonImageView) findViewById(R.id.piv);
        et.setText(piv.getEdgeNum() + "");
        //Log.d("xxx", piv.getEdgeNum());
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                piv.setEdgeNum(Integer.parseInt(et.getText().toString() ) );
            }
        });


    }



}
