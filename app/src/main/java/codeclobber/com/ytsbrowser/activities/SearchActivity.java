package codeclobber.com.ytsbrowser.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.adapters.RVMoviesAdapter;
import codeclobber.com.ytsbrowser.constants.Constant;
import codeclobber.com.ytsbrowser.interfaces.YTSApiService;
import codeclobber.com.ytsbrowser.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private EditText mSearchBarField;
    private RecyclerView mRecyclerView;
    private YTSApiService mAPIService;


    private Call<MoviesResponse> mSearchCall = null;
    private MoviesResponse mSearchedMoviesResponse = new MoviesResponse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        setupToolbar();

        mAPIService = Constant.URL.getAPIService(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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
    private void initViews() {
        mSearchBarField = (EditText) findViewById(R.id.et_toolbar_search);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setupRecyclerView();

        mSearchBarField.requestFocus();
        mSearchBarField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String query = mSearchBarField.getText().toString();
                if (query.length() >= 3) {
                    callSearchMoviesAPI(query);
                } else {
                    if (mSearchCall != null) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        mSearchCall.cancel();
                    }
                }
            }
        });
    }

    private void setupToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
    }

    private void callSearchMoviesAPI(String query) {
        if (mSearchCall != null) {
            mSearchCall.cancel();
        }
        mSearchCall = Constant.URL.getAPIService(this).searchMovies(query, Constant.URL.RESPONSE_LIMIT);
        mProgressBar.setVisibility(View.VISIBLE);
        mSearchCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                // Checking if activity is running
                if (isFinishing()) return;

                mProgressBar.setVisibility(View.INVISIBLE);

                // Checking if there's any error
                if (response.errorBody() != null ||
                        response.body() == null ||
                        response.body().getData() == null ||
                        response.body().getData().getMovies() == null) return;

                mSearchedMoviesResponse = response.body();
                mRecyclerView.setAdapter(new RVMoviesAdapter(SearchActivity.this, mSearchedMoviesResponse.getData().getMovies(), true));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Checking if activity is running
                if (isFinishing()) return;
                if (call.isCanceled()) {
                    // Call is cancel
                } else {
                    // Some other error occurred
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }


}
