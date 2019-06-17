package codeclobber.com.ytsbrowser.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.adapters.RVMoviesAdapter;
import codeclobber.com.ytsbrowser.constants.Constant;
import codeclobber.com.ytsbrowser.models.MoviesResponse;
import codeclobber.com.ytsbrowser.utils.UIUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mMainLayout;

    private int mCurrentPageNumber = 1;
    private int mLastPageNumber = 1;
    private boolean isAPIRunning = false;
    private MoviesResponse mTopRatedMoviesResponse = new MoviesResponse();
    private RVMoviesAdapter mRVMoviesAdapter;

    public TopRatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_rated, container, false);
        initViews(view);
        mSwipeRefreshLayout.setRefreshing(true);

        callGetTopRatedMovies(mCurrentPageNumber);
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
    private void initViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mMainLayout = view.findViewById(R.id.mainLayout);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(UIUtil.getColor(getContext(), R.color.accent));

        // Setup listeners
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentPageNumber = 1;
                callGetTopRatedMovies(mCurrentPageNumber);
            }
        });

    }

    private void showRetrySnackBar() {
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
        Snackbar.make(mMainLayout, "Unable to get latest movies", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mCurrentPageNumber = 1;
                        mSwipeRefreshLayout.setRefreshing(true);
                        callGetTopRatedMovies(mCurrentPageNumber);
                    }
                })
                .setActionTextColor(UIUtil.getColor(getContext(), R.color.accent))
                .show();
    }

    private void setupRecyclerView() {
        int columnSpan = getActivity().getResources().getInteger(R.integer.movies_column_span);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), columnSpan);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRVMoviesAdapter = new RVMoviesAdapter(getContext(), mTopRatedMoviesResponse.getData().getMovies());
        mRecyclerView.setAdapter(mRVMoviesAdapter);

        mLastPageNumber = mTopRatedMoviesResponse.getData().getMovieCount() / Constant.URL.RESPONSE_LIMIT;

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
                    callGetTopRatedMovies(mCurrentPageNumber);
                }


            }
        });

    }



    private void callGetTopRatedMovies(int pageNo) {
        if (isAPIRunning) {
            return;
        }
        isAPIRunning = true;
        Constant.URL.getAPIService(getActivity())
                .getTopRatedMovies(pageNo, Constant.URL.RESPONSE_LIMIT, Constant.URL.Sort.RATING)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        if (!isRunning()) return;
                        isAPIRunning = false;
                        mSwipeRefreshLayout.setRefreshing(false);
                        // Checking if there's any error
                        if (response.errorBody() != null || response.body() == null) {
                            if (mCurrentPageNumber == 1) {
                                showRetrySnackBar();
                                return;
                            } else
                                return;
                        }

                        // Checking response data
                        if (response.body().getData() == null || response.body().getData().getMovies() == null)
                            return;

                        // Getting Movies from Response
                        if (mCurrentPageNumber == 1) {
                            mTopRatedMoviesResponse = response.body();
                            setupRecyclerView();
                        } else {
                            int previousCount = mTopRatedMoviesResponse.getData().getMovieCount();
                            int itemAdded = response.body().getData().getMovies().size();
                            mTopRatedMoviesResponse.getData().getMovies().addAll(response.body().getData().getMovies());
                            mRVMoviesAdapter.notifyItemRangeInserted(previousCount, itemAdded);
                        }


                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        if (!isRunning()) return;
                        isAPIRunning = false;
                        // Showing SnackBar is data loading failed first time
                        if (mCurrentPageNumber == 1) {
                            showRetrySnackBar();
                        }
                    }
                });
    }

}
