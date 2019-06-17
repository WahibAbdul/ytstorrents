package codeclobber.com.ytsbrowser;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by wahib on 2/14/17.
 */

public class YTSApp extends Application {

    public final static boolean IS_LIVE_ENVIRONMENT = true;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }
}
