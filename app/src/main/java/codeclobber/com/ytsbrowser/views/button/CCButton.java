package codeclobber.com.ytsbrowser.views.button;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by wahib on 11/22/16.
 */

public class CCButton extends Button {

    public CCButton(Context context) {
        super(context);
        init();
    }

    public CCButton(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public CCButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CCButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        this.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/Avenir_Book.otf"));
    }

}
