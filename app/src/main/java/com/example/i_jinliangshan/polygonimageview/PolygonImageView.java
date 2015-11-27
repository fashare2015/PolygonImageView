package com.example.i_jinliangshan.polygonimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by i_jinliangshan on 2015/11/23.
 */
public class PolygonImageView extends ImageView {
    private final static int DEFAULT_EDGE_NUM = 3;
    private int edgeNum;
    float height, width, radius;
    // private Paint p;
    // 全局画笔要记得 p.reset();  //!!!!!

    public PolygonImageView(Context context){   this(context, null);    }

    public PolygonImageView(Context context, AttributeSet attrs){   this(context, attrs, 0);    }

    public PolygonImageView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PolygonImageView, 0, 0);

        edgeNum = ta.getInt(R.styleable.PolygonImageView_edge_num, -1);
        if(edgeNum < DEFAULT_EDGE_NUM)
            edgeNum = DEFAULT_EDGE_NUM;

        ta.recycle();

        //p = new Paint();    // 创建画笔
    }

    public int getEdgeNum(){
        return edgeNum;
    }

    public void setEdgeNum(int tmpEdgeNum){
        edgeNum = tmpEdgeNum;
        if(edgeNum < DEFAULT_EDGE_NUM)
            edgeNum = DEFAULT_EDGE_NUM;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        height = getHeight();
        width = getWidth();
        radius = Math.min(height, width)/2;
        Log.d("height", height + "");
        Log.d("width", width + "");
        Log.d("radius", radius + "");

        final Rect rectImageView = new Rect(0, 0, (int)height, (int)width);

        Drawable drawable = getDrawable();
        if (null != drawable) {
            Bitmap bmpBack = ((BitmapDrawable) drawable).getBitmap();
            final Rect rectBack = new Rect(0, 0, bmpBack.getWidth(), bmpBack.getHeight());
            //p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            //p.reset();              //重置画笔!!!!!
            canvas.drawBitmap(bmpBack, rectBack, rectImageView, new Paint());

            Paint p = new Paint();
            p.setColor(Color.BLUE);// 设置蓝色
            p.setStyle(Paint.Style.FILL);//设置填满
            p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            //canvas.drawPath(getPolygonPath(), p);
            canvas.drawBitmap(getPloygonBitmap(), rectImageView, rectImageView, p);

        }else{
            super.onDraw(canvas);
        }

    }

    private Bitmap getPloygonBitmap() {

        Bitmap output = Bitmap.createBitmap((int)width, (int)height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawARGB(0, 0, 0, 0);

        Paint p = new Paint();
        //p.reset();  //!!!!!
        p.setColor(Color.BLUE);// 设置蓝色
        p.setStyle(Paint.Style.FILL);//设置填满
        canvas.drawPath(getPolygonPath(), p);

        return output;
    }

    private class MyPoint{
        public float x, y;
        double angle;
        public MyPoint(){}
        public MyPoint(float x, float y){
            this.x = x;
            this.y = y;
            this.angle = 0;
        }

        public MyPoint(double angle){
            this.x = (float)Math.cos(angle) * radius;
            this.y = (float)Math.sin(angle) * radius;
            this.angle = angle;
        }

        public MyPoint getNextPoint(MyPoint cur){
            return new MyPoint(cur.angle + 2*Math.PI/edgeNum);
        }
    }

    private Path getPolygonPath(){

        Path path=new Path();
        MyPoint cur = new MyPoint(Math.PI/2);

        MyPoint center = new MyPoint(height/2, width/2);

        path.moveTo(cur.x + center.x, -cur.y + center.y);       //y轴方向相反
        for(int i=1; i<edgeNum; i++){
            cur = cur.getNextPoint(cur);
            path.lineTo(cur.x + center.x, -cur.y + center.y);
        }
        path.close();

        return path;


    }

}
