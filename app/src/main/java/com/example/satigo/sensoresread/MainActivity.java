package com.example.satigo.sensoresread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView texto1;
    ImageView pic;
    float x,y,z;
    float x1=150,y1=150;
    float velocidad=1;
    Bitmap paper = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(paper);
    Paint lapiz = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Limpiando Panel", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        texto1=(TextView)findViewById(R.id.textbox1);
        pic=(ImageView)findViewById(R.id.areaDibujo);
        SensorManager sensor= (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor acelerometro = sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor.registerListener(this,acelerometro,sensor.SENSOR_DELAY_FASTEST);
        lapiz.setStyle(Paint.Style.STROKE);
        lapiz.setColor(Color.BLUE);
        lapiz.setStrokeWidth(3);


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

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x=sensorEvent.values[0];
        y=sensorEvent.values[1];
        z=sensorEvent.values[2];
        onDraw();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public void onDraw(){
        //canvas.drawLine(100, 20, 200+x, 20+y, lapiz);

        float tx, ty;
        tx=x1 + (x*velocidad);
        ty=y1 + (y*velocidad);

        if(tx>300){
            tx=300;
        }
        if(tx<0){
            tx=0;
        }

        if(ty>290){
            ty=290;
        }
        if(ty<0){
            ty=0;
        }
        canvas.drawPoint(tx, ty,  lapiz);
        x1=tx;
        y1=ty;
        pic.setImageBitmap(paper);
    }



}
