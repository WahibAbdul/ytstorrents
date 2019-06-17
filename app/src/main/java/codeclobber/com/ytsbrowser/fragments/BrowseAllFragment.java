package codeclobber.com.ytsbrowser.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.adapters.RVMoviesAdapter;
import codeclobber.com.ytsbrowser.adapters.SpinnerAdapter;
import codeclobber.com.ytsbrowser.constants.Constant;
import codeclobber.com.ytsbrowser.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseAllFragment extends BaseFragment {

    Map<String, String> mSortByMap = new HashMap<>();
    String[] mRatingArray;
    String[] mQualityArray;
    String[] mGenreArray;
    String[] mSortKeysArray;
    String[] mSortValuesArray;

    Spinner mQualitySpinner, mOrderBySpinner, mRatingSpinner, mGenreSpinner;
    Button mSearchButton;
    View mMainLayout;
    ProgressBar mProgressBar;

    private boolean isAPIRunning = false;
    private int mCurrentPageNumber = 1;
    private int mLastPageNumber = 1;
    private Call<MoviesResponse> mCall;
    private RecyclerView mRecyclerView;
    private RVMoviesAdapter mRVMoviesAdapter;
    private MoviesResponse mMoviesResponse;


    public BrowseAllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_browse_all, container, false);
        initSpinnerValues();
        initViews(view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        setRunning(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setRunning(true);
    }

    // MARK: Helper Methods
    private void initSpinnerValues() {
        // Add data to Sort Map
        // title, year, rating, peers, seeds, download_count, like_count, date_added
        mSortByMap.put("Latest", "date_added");
        mSortByMap.put("Title", "title");
        mSortByMap.put("Year", "year");
        mSortByMap.put("Rating", "rating");
        mSortByMap.put("Peers", "peers");
        mSortByMap.put("Seeds", "seeds");
        mSortByMap.put("Downloads", "download_count");
        mSortByMap.put("Likes", "like_count");

        // Get arrays from String.xml
        mRatingArray = getActivity().getResources().getStringArray(R.array.rating_values);
        mQualityArray = getActivity().getResources().getStringArray(R.array.quality_values);
        mGenreArray = getActivity().getResources().getStringArray(R.array.genre_values);
        mSortKeysArray = getActivity().getResources().getStringArray(R.array.order_by_keys);
        mSortValuesArray = getActivity().getResources().getStringArray(R.array.order_by_values);
    }

    private void initViews(View view) {
        mQualitySpinner = (Spinner) view.findViewById(R.id.sp_quality);
        mOrderBySpinner = (Spinner) view.findViewById(R.id.sp_orderBy);
        mRatingSpinner = (Spinner) view.findViewById(R.id.sp_rating);
        mGenreSpinner = (Spinner) view.findViewById(R.id.sp_genre);
        mSearchButton = (Button) view.findViewById(R.id.btn_search);
        mMainLayout = view.findViewById(R.id.mainLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        mQualitySpinner.setAdapter(new SpinnerAdapter(getActivity(), R.layout.spinner_item_layout, mQualityArray));
        mRatingSpinner.setAdapter(new SpinnerAdapter(getActivity(), R.layout.spinner_item_layout, mRatingArray));
        mGenreSpinner.setAdapter(new SpinnerAdapter(getActivity(), R.layout.spinner_item_layout, mGenreArray));
        mOrderBySpinner.setAdapter(new SpinnerAdapter(getActivity(), R.layout.spinner_item_layout, mSortKeysArray));

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCall != null)
                    mCall.cancel();
                mProgressBar.setVisibility(View.VISIBLE);
                if (mMoviesResponse != null && mRVMoviesAdapter != null) {
                    mMoviesResponse.getData().getMovies().clear();
                    mRVMoviesAdapter.notifyDataSetChanged();
                }
                mCurrentPageNumber = 1;
                callFilterMoviesAPI(mCurrentPageNumber);
            }
        });

    }

    private void setupRecyclerView() {
        int columnSpan = getActivity().getResources().getInteger(R.integer.movies_column_span);
//        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), columnSpan);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRVMoviesAdapter = new RVMoviesAdapter(getContext(), mMoviesResponse.getData().getMovies(), true);
        mRecyclerView.setAdapter(mRVMoviesAdapter);

        mLastPageNumber = mMoviesResponse.getData().getMovieCount() / Constant.URL.RESPONSE_LIMIT;

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (isAPIRunning || mCurrentPageNumber >= mLastPageNumber)
                    return;

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 10
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE - 1) {
                    ++mCurrentPageNumber;
                    callFilterMoviesAPI(mCurrentPageNumber);
                }


            }
        });

    }


    private void showRetrySnackBar(String message) {
        Snackbar.make(mMainLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    // MARK: API Calls
    private void callFilterMoviesAPI(int pageNo) {
        if (isAPIRunning) {
            return;
        }
        isAPIRunning = true;

        String genre = mGenreArray[(int) mGenreSpinner.getSelectedItemId()].toLowerCase();
        String quality = mQualityArray[(int) mQualitySpinner.getSelectedItemId()];
        String rating = mRatingArray[(int) mRatingSpinner.getSelectedItemId()].replace("+", "");
        String sort = mSortValuesArray[(int) mOrderBySpinner.getSelectedItemId()];

        mCall = Constant.URL.getAPIService(getActivity()).filterMovies(pageNo, Constant.URL.RESPONSE_LIMIT, sort, quality, rating, genre);
        mCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (!isRunning()) return;
                mProgressBar.setVisibility(View.GONE);
                isAPIRunning = false;
                // Checking if there's any error
                if (response.errorBody() != null || response.body() == null) {
                    if (mCurrentPageNumber == 1) {
                        showRetrySnackBar("No movies found");
                        return;
                    } else
                        return;
                }

                // Checking response data
                if (response.body().getData() == null || response.body().getData().getMovies() == null) {
                    showRetrySnackBar("No movies found");
                    return;
                }

                // Getting Movies from Response
                if (mCurrentPageNumber == 1) {
                    mMoviesResponse = response.body();
                    setupRecyclerView();
                } else {
                    int previousCount = mMoviesResponse.getData().getMovieCount();
                    int itemAdded = response.body().getData().getMovies().size();
                    mMoviesResponse.getData().getMovies().addAll(response.body().getData().getMovies());
                    mRVMoviesAdapter.notifyItemRangeInserted(previousCount, itemAdded);
                }


            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                if (!isRunning()) return;
                isAPIRunning = false;
                mProgressBar.setVisibility(View.GONE);
                // Showing SnackBar is data loading failed first time
                if (mCurrentPageNumber == 1) {
                    showRetrySnackBar("Unable to connect");
                }
            }
        });


    }

}
