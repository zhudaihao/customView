package cn.zdh.customview.xfermode.src_out;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import cn.zdh.customview.R;

public class EraserView_SRCOUT extends View {
    private Paint paint;
    private Bitmap bitmapText, bitmapDST, bitmapSRC;

    //轨迹
    private Path path;
    private float px, py;


    public EraserView_SRCOUT(Context context) {
        super(context);
        init();
    }

    public EraserView_SRCOUT(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //关闭view 硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, paint);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(45);


        bitmapText = BitmapFactory.decodeResource(getResources(), R.mipmap.guaguaka_text1);

        bitmapSRC = BitmapFactory.decodeResource(getResources(), R.mipmap.guaguaka);
        //创建一个可变位图  的bitmap对象
        bitmapDST = Bitmap.createBitmap(bitmapSRC.getWidth(), bitmapSRC.getHeight(), Bitmap.Config.ARGB_8888);

        path = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("zdh","--------------onDraw");

        //先画文字
        canvas.drawBitmap(bitmapText, 0, 0, paint);

        //创建一个新的画布
        int i = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);


        //从可变位图获取画布对象
        Canvas canvas1 = new Canvas(bitmapDST);

        //把手指轨迹画到目标bitmap上---》bitmapDST
        canvas1.drawPath(path, paint);


        //画目标图
        canvas.drawBitmap(bitmapDST, 0, 0, paint);
        //SRC_OUT 保持源像素不覆盖目标像素。
        // (目标图片透明部分被覆盖，显示覆盖图片  不是透明部分被覆盖 显示目标图片（但是颜色为白色）但是可以看到 下层的图像（bitmapText） )
        //同步不断 改变 目标图的不透明部分实现显示 底层图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));


        //画源图（覆盖图）
        canvas.drawBitmap(bitmapSRC, 0, 0, paint);
        paint.setXfermode(null);

        //合并图层
        canvas.restoreToCount(i);


        //测试画线
//        textLine(canvas);


    }

    private void textLine(Canvas canvas) {
        paint.setColor(Color.RED);
        //画 手触摸的轨迹
        canvas.drawPath(path, paint);
    }


    /**
     * 监听 手势触摸改变目标图的 不透明部分
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                px = event.getX();
                py = event.getY();

//                Log.e("zdh", "-------ACTION_DOWN---" + px + "----------" + py);

                return true;

            case MotionEvent.ACTION_MOVE:
                //获取手指移动的结束点xy坐标

                //除以2目的 数据更精准
                float endX = (px + event.getX()) / 2;
                float endY = (py + event.getY()) / 2;
                path.quadTo(px, py, endX, endY);


                //记录这次移动 后的x y坐标
                px = event.getX();
                py = event.getY();

//                Log.e("zdh", "-------ACTION_MOVE---" + px + "----------" + py);
//                Log.e("zdh", "-------ACTION_MOVE>>>>>---" + endX + "----------" + endY);
                return true;

            case MotionEvent.ACTION_UP:

                break;


        }

        //不断刷新UI
        postInvalidate();

        return super.onTouchEvent(event);
    }
}
