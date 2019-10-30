package cn.zdh.customview.xfermode.lighteh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import cn.zdh.customview.R;

/**
 * 书架照射效果
 */
public class LightBookView_LIGHTEN extends View {
    private Paint paint;
    private Bitmap bitmapDST,bitmapSRC;


    public LightBookView_LIGHTEN(Context context) {
        super(context);
        init();
    }

    public LightBookView_LIGHTEN(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint=new Paint();

        bitmapDST=BitmapFactory.decodeResource(getResources(), R.mipmap.book_bg);
        bitmapSRC=BitmapFactory.decodeResource(getResources(), R.mipmap.book_light);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //创建一个新的画布
        int id = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);

        //画目标图
        canvas.drawBitmap(bitmapDST,0,0,paint);
        //设置xfmodel模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));


        //画覆盖图(通过改变左 上两个坐标点 改变光源的照射点)
        canvas.drawBitmap(bitmapSRC,0,0,paint);
        paint.setXfermode(null);

        canvas.restoreToCount(id);


    }
}
