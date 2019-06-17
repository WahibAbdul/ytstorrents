package codeclobber.com.ytsbrowser.constants;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import codeclobber.com.ytsbrowser.interfaces.YTSApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wahib on 2/12/17.
 */

public class Constant {

    // Inner Classes
    public static class URL {

        public final static int RESPONSE_LIMIT = 30;
        public final static String YOUTUBE_BASE_URL = "https://youtu.be/";
        public final static String IMDB_MOVIE_BASE_URL = "http://www.imdb.com/title/";
        public final static String IMDB_CAST_BASE_URL = "http://www.imdb.com/name/";
        final static String BASE_URL = "https://yts.lt";

        public static YTSApiService getAPIService(final Activity activity) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.URL.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(YTSApiService.class);
        }

        public static boolean isNetworkAvailable(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }

        // Inner Classes
        public static class PARAM {
            public final static String PAGE = "page";
            public final static String LIMIT = "limit";
            public final static String SORT_BY = "sort_by";
            public final static String QUERY_TERM = "query_term";
            public final static String MOVIE_ID = "movie_id";
            public final static String WITH_IMAGES = "with_images";
            public final static String WITH_CAST = "with_cast";
            public final static String MIN_RATING = "minimum_rating";
            public final static String GENRE = "genre";
            public final static String QUALITY = "quality";
        }

        public static class Sort {
            public final static String RATING = "rating";
        }
    }

    public static class PreferencesKey {
        public static final String IS_NOTIFICATION_ENABLED = "isNotificationEnabled";
        public static final String IS_PREMIUM_USER = "isPremiumUser";
    }

    public static class NotificationTopic {
        public final static String TESTING = "Testing";
        public final static String LIVE = "Live";
    }

}
