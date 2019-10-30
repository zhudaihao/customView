package cn.zdh.customview.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import cn.zdh.customview.R;

/**
 * 画笔 渲染
 */
public class MyShader extends View {
    //画笔
    private Paint paint;
    //位图
    private Bitmap bitmap;

    //图片的宽高
    private int width;
    private int height;

    //颜色数组
    private int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    public MyShader(Context context) {
        super(context);
        init();
    }


    public MyShader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化 画笔和bitmap对象
     */
    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        //设置宽
        paint.setStrokeWidth(20);
        //设置画笔 开始 结束 的样式
        paint.setStrokeCap(Paint.Cap.ROUND);


        bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.tp)).getBitmap();

        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }


    /**
     * 绘制
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景颜色
//        canvas.drawColor(Color.BLUE);
        //位图渲染
//        setBitmapShader(canvas);

        //线性渲染
//        setLinearGradient(canvas);

        //环形渲染
//        setRadialGradient(canvas);

        //扫描渲染
//        setSweepGradient(canvas);

        //组合渲染
//        setComposeShader(canvas);


    }


    /**
     * 模式：
     * Shader.TileMode.CLAMP表示 拉伸最后个像素 去铺满剩下的地方
     * Shader.TileMode.MIRROR 通过镜像翻转铺满剩下的地方
     * Shader.TileMode.REPEAT 重复图片平铺整个画面
     */


    /**
     * 位图渲染 （图片渲染形状)
     * 参数（bitmap对象，X方向的模式，Y方向的模式）
     * <p>
     * 注意 如果图片大小 大于画布大小模式效果没有，图片小于画布才
     */
    private void setBitmapShader(Canvas canvas) {
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        //把图片渲染对象设置到画笔里面
        paint.setShader(bitmapShader);

        /**
         *画到画布上
         */
        //画长方形（矩形，画笔）
//        canvas.drawRect(new Rect(0, 0, 1000, 1600), paint);


        //画正方形
//        canvas.drawRect(new Rect(0,0,width,width),paint);

        //画椭圆
//        canvas.drawOval(new RectF(0,0,width,height),paint);

        //通过画圆，实现圆角图片效果
//        canvas.drawCircle(width/2, width/2, width/2, paint);

        //或者通过shapeDrawable 也可以实现圆图
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        //设置位图渲染对象到画笔
        shapeDrawable.getPaint().setShader(bitmapShader);
        //设置边界
        shapeDrawable.setBounds(0, 0, width, width);
        //画到画布
        shapeDrawable.draw(canvas);


    }


    /**
     * 线性渲染（线性渐变）
     * 参数(渐变起始点X坐标，渐变起始点Y坐标，渐变结束点X坐标，渐变结束点Y坐标，渐变颜色组)
     *
     * @param canvas
     */
    private void setLinearGradient(final Canvas canvas) {
        LinearGradient linearGradient = new LinearGradient(50, 50, 500, 50, colors, null, Shader.TileMode.CLAMP);
        //设置线性渲染对象到 画笔里面
        paint.setShader(linearGradient);

        //画图型到画布上
//        canvas.drawRect(0, 0, 800, 800, paint);

        //画直线渐变效果
        canvas.drawLine(50, 50, 500, 50, paint);


    }


    /**
     * 环形渲染(辐射状)
     * 参数 （半径x坐标，半径Y坐标，半径（必须为正），渐变颜色数组，渐变范围数组【0...1】(一般为null)，,着色器平铺模式）
     */
    private void setRadialGradient(Canvas canvas) {
        //渲染范围
        float[] i = {0, (float) 0.1, (float) 0.3, (float) 0.5};
        RadialGradient radialGradient = new RadialGradient(300, 300, 200, colors, null, Shader.TileMode.CLAMP);
        paint.setShader(radialGradient);

        //画圆
        canvas.drawCircle(300, 300, 200, paint);

    }


    /**
     * 扫描渲染
     * <p>
     * 参数（渲染X坐标，渲染Y坐标，渲染颜色，渲染范围（一般为null））
     * 圆心向右 顺时针扫描
     */
    private void setSweepGradient(Canvas canvas) {
        float[] i = {0, (float) 0.1, (float) 0.3, (float) 0.5};
        SweepGradient sweepGradient = new SweepGradient(300, 300, colors, null);
        paint.setShader(sweepGradient);

        //画圆
        canvas.drawCircle(300, 300, 200, paint);

    }

    /**
     * 组合渲染
     *
     */
    private void setComposeShader(Canvas canvas) {
        //空白的心
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.tp);
        BitmapShader bitmapShader = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //填充心空白的颜色
        int[] color1={Color.GREEN,Color.BLUE};
        LinearGradient linearGradient = new LinearGradient(0, 0, bitmap1.getWidth(), bitmap1.getHeight(), color1, null, Shader.TileMode.CLAMP);


        //设置模式（一共有17种）
        ComposeShader composeShader = new ComposeShader( bitmapShader, linearGradient,PorterDuff.Mode.MULTIPLY);
        //组合渲染 设置到画笔上
        paint.setShader(composeShader);

        //绘制到矩形上
        canvas.drawRect(0, 0, bitmap1.getWidth(), bitmap1.getHeight(), paint);
    }



}
