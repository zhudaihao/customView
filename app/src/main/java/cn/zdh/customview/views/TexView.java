package cn.zdh.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/***
 * 自定义  歌词TextView
 */
public class TexView extends AppCompatTextView {
    private Matrix matrix;
    private int width = 0;
    private int tran = 15;
    private LinearGradient linearGradient;
    private Paint paint;
    private  int tag = 0;

    public TexView(Context context) {
        super(context);
    }

    public TexView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint = getPaint();

        //获取布局宽
        width = getMeasuredWidth();
        if (width > 0) {
            float size = 0;
            //获取多少文件 算出 每个字的像素 点

            //获取所有文字
            String text = getText().toString();
            //获取所有文件长度
            int length = text.length();

            if (length > 0) {
                size = width /length  * 3;
            } else {
                size = width;
            }


            int[] colors = {0x33ffffff, 0xffffffff, 0x33ffffff};
            float[] position = {0f, 0.2f, 1f};
            linearGradient = new LinearGradient(-size, 0, 0, 0, colors, position, Shader.TileMode.CLAMP);
            matrix = new Matrix();
            paint.setShader(linearGradient);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取文件长度
        float measureText = paint.measureText(getText().toString());
        tag += tran;

        //通过改变 加的偏移值的正负  实现加减变化
        if (tag > measureText+1 ||tag<1) {
            tran = -tran;
        }

        matrix.setTranslate(tag, 0);
        linearGradient.setLocalMatrix(matrix);

        //定时循环调用draw 方法
        postInvalidateDelayed(30);

        Log.e("zdh","----------tag "+tag);

    }
}
