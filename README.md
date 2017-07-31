使用ValueAnimator作曲线运动，抛物线、正弦、心形线

**作抛物线运动：**

运动曲线：y=x^2/1000

    ValueAnimator animation = ValueAnimator.ofFloat(0f, 800f);
        animation.setDuration(2000);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                
                float translateX=(float)animation.getAnimatedValue();
                
                //y=x^2/1000
                mText.setTranslationX(translateX);
                mText.setTranslationY((2*translateX*translateX/1000));
            }
        });
        animation.start();
***

**作正弦线运动：**

运动曲线：y=400*sin(x/100)

    ObjectAnimator objectAnimator=ObjectAnimator.ofObject(new TextHolder(mText1),
        "xY",
        new SineTypeEvaluator(),
        new XYHolder(0f,400f),
        new XYHolder(628f,400f));
        
    objectAnimator.setDuration(2000);
    objectAnimator.start();
        
TextHolder：

    private class TextHolder{

        private TextView view;

        public TextHolder(TextView textView){
            view=textView;
        }

        public XYHolder getXY() {
            return new XYHolder(view.getX(),view.getY());
        }

        public void setXY(XYHolder xyHolder) {
            view.setX(xyHolder.getX());
            view.setY(xyHolder.getY());
        }
    }


SineTypeEvaluator：

    public class SineTypeEvaluator implements TypeEvaluator<XYHolder> {

        @Override
        public XYHolder evaluate(float fraction, XYHolder startValue, XYHolder endValue) {
            float xHolder=startValue.getX()+fraction*(endValue.getX()-startValue.getX());
            float yHolder=(float) (300*Math.sin(xHolder/100)+endValue.getY());
            return new XYHolder(xHolder,yHolder );
        }
    }
    
***

**作心形曲线运动：**

运动曲线：

x=a*(2*cos(t)-cos(2*t));

y=a*(2*sin(t)-sin(2*t));

变换为：

y=a*(-2*cos(t)+cos(2*t));

x=a*(-2*sin(t)+sin(2*t));

    ValueAnimator animation = ValueAnimator.ofInt(0, 360);
        animation.setDuration(6000);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int angle=(int)animation.getAnimatedValue();
                //x=a*(2*cos(t)-cos(2*t));
                //y=a*(2*sin(t)-sin(2*t))
                //变换
                //y=a*(-2*cos(t)+cos(2*t));
                //x=a*(-2*sin(t)+sin(2*t))
                double t=2*3.14/360*angle;//弧度制
                mText2.setTranslationY((float) (100*(-2*Math.cos(t)+Math.cos(2*t))));
                mText2.setTranslationX((float) (100*(-2*Math.sin(t)+Math.sin(2*t))));

                View view =new View(MainActivity.this);
                RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(5,5);
                view.setLayoutParams(layoutParams);
                view.setBackgroundResource(R.drawable.heart_line_shape);
                view.setTranslationY((float) (100*(-2*Math.cos(t)+Math.cos(2*t)))+dip2px(200,MainActivity.this));
                view.setTranslationX((float) (100*(-2*Math.sin(t)+Math.sin(2*t)))+dip2px(200,MainActivity.this));
                mainLayout.addView(view);
            }
        });
        animation.start();






