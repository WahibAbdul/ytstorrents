package codeclobber.com.ytsbrowser.views.textview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import codeclobber.com.ytsbrowser.R;


/**
 * Created by wahib on 11/22/16.
 */

public class CCTextViewRobotoLight extends TextView {
    public CCTextViewRobotoLight(Context context) {
        super(context);
        init(null);
    }

    public CCTextViewRobotoLight(Context context, AttributeSet attrs){
        super(context, attrs);
        init(attrs);
    }

    public CCTextViewRobotoLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CCTextViewRobotoLight(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        this.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Light.ttf"));

        int tintColor = -1;
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CCTextView);
            try {
                tintColor = ta.getColor(R.styleable.CCTextView_tint, tintColor);
            } finally {
                ta.recycle();
            }
        }
        tintDrawables(tintColor);
    }

    private void tintDrawables(int color) {
        if (color != -1) {
            Drawable[] drawables = getCompoundDrawables();
            for (Drawable drawable : drawables) {
                if (drawable != null) {
                    drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
                }
            }
        }
    }
}
