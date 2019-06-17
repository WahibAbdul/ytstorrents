package codeclobber.com.ytsbrowser.views.edittext;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.EditText;

import codeclobber.com.ytsbrowser.R;


/**
 * Created by wahib on 11/22/16.
 */

public class CCEditTextRobotoRegular extends EditText {
    public CCEditTextRobotoRegular(Context context) {
        super(context);
        init(null);
    }

    public CCEditTextRobotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CCEditTextRobotoRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CCEditTextRobotoRegular(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        int tintColor = ContextCompat.getColor(getContext(), R.color.secondary_text);
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CCEditText);
            try {
                tintColor = ta.getColor(R.styleable.CCEditText_drawableTint, tintColor);
            } finally {
                ta.recycle();
            }
        }
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Regular.ttf"));

        Drawable[] drawables = getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.mutate().setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
            }
        }
    }
}
