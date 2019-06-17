package codeclobber.com.ytsbrowser.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.constants.Constant;
import codeclobber.com.ytsbrowser.fragments.BaseFragment;
import codeclobber.com.ytsbrowser.fragments.movieDetailTabs.DetailFragment;
import codeclobber.com.ytsbrowser.fragments.movieDetailTabs.MovieSuggestionFragment;
import codeclobber.com.ytsbrowser.models.Movie;
import codeclobber.com.ytsbrowser.models.movieDetailResponse.MovieDetailResponse;
import codeclobber.com.ytsbrowser.utils.UIUtil;
import jp.wasabeef.blurry.Blurry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieDetailActivity extends AppCompatActivity {

    private String[] TABS_TITLES;
    private Movie movie;
    private View mMainLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private View mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movie movie = getIntent().getParcelableExtra("object");
        if (movie == null) return;

        TABS_TITLES = getResources().getStringArray(R.array.movie_detail_tabs);
        setupToolbar(movie.getTitle());
        initViews();
        callGetMovieDetailAPI(movie.getId());

        final ImageView mBackCover = (ImageView) findViewById(R.id.img_backCover);
        Glide.with(this).asBitmap().load(movie.getMediumCoverImage()).into(new SimpleTarget<Bitmap>(100, 100) {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Blurry.with(MovieDetailActivity.this).radius(2).sampling(8).from(resource).into(mBackCover);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    // MARK: Helper Methods
    private void setupToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(title);
            UIUtil.toolbarSetShadow(this, toolbar, R.dimen.toolbar_elevation_null);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private void initViews() {
        mTabLayout = (TabLayout) findViewById(R.id.tb_tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.vp_viewPager);
        mMainLayout = findViewById(R.id.mainLayout);
        mProgressbar = findViewById(R.id.progressbar);
    }

    private void setViewPagerAdapter() {
        if (mViewPager != null) {
            mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
            mViewPager.setOffscreenPageLimit(3);
            if (mTabLayout != null)
                mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    private void showRetrySnackBar() {
        Snackbar.make(mMainLayout, "Unable to get movie detail", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callGetMovieDetailAPI(getMovie().getId());
                    }
                })
                .setActionTextColor(UIUtil.getColor(this, R.color.accent))
                .show();
    }

    // MARK: API Calls
    private void callGetMovieDetailAPI(int id) {
        mProgressbar.setVisibility(View.VISIBLE);
        Constant.URL.getAPIService(this)
                .getMovieDetails(id, true, true)
                .enqueue(new Callback<MovieDetailResponse>() {
                    @Override
                    public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                        mProgressbar.setVisibility(View.GONE);
                        // Checking if activity is running
                        if (isFinishing()) return;

                        // Checking if there's any error
                        if (response.errorBody() != null ||
                                response.body() == null ||
                                response.body().getData() == null ||
                                response.body().getData().getMovie() == null) {
                            showRetrySnackBar();
                            return;
                        }
                        setMovie(response.body().getData().getMovie());
                        setViewPagerAdapter();
                    }

                    @Override
                    public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                        mProgressbar.setVisibility(View.GONE);
                        showRetrySnackBar();
                    }
                });
    }

    // MARK: Getter and Setter
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new DetailFragment();
                case 1:
                    return new MovieSuggestionFragment();
                default:
            }
            return new BaseFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TABS_TITLES[position];
        }

        @Override
        public int getCount() {
            return TABS_TITLES.length;
        }
    }
}
