package codeclobber.com.ytsbrowser.fragments.movieDetailTabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.activities.MovieDetailActivity;
import codeclobber.com.ytsbrowser.adapters.RVMoviesAdapter;
import codeclobber.com.ytsbrowser.constants.Constant;
import codeclobber.com.ytsbrowser.fragments.BaseFragment;
import codeclobber.com.ytsbrowser.models.Movie;
import codeclobber.com.ytsbrowser.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieSuggestionFragment extends BaseFragment {

    RecyclerView mRecyclerView;
    ProgressBar mProgressBar;
    TextView mNoSimilarMovies;

    public MovieSuggestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_suggestion, container, false);
        initViews(view);

        callGetMoviesSuggestion();
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
    void initViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        mNoSimilarMovies = (TextView) view.findViewById(R.id.tv_no_similar_movies);

        mRecyclerView.setHasFixedSize(true);
        int columns = getActivity().getResources().getInteger(R.integer.movies_column_span);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columns));
    }



    // MARK: API Calls
    void callGetMoviesSuggestion() {
        Movie movie = ((MovieDetailActivity) getActivity()).getMovie();
        Constant.URL.getAPIService(getActivity())
                .getMovieSuggestions(movie.getId())
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        if (!isRunning()) return;
                        mProgressBar.setVisibility(View.GONE);

                        // Checking if activity is running
                        if (getActivity().isFinishing()) return;

                        // Checking if there's any error
                        if (response.errorBody() != null ||
                                response.body() == null ||
                                response.body().getData() == null ||
                                response.body().getData().getMovies() == null) {
                            return;
                        }

                        if (response.body().getData().getMovies().size() == 0) {
                            mNoSimilarMovies.setVisibility(View.VISIBLE);
                        } else {
                            mRecyclerView.setAdapter(new RVMoviesAdapter(getActivity(), response.body().getData().getMovies()));
                        }

                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        if (!isRunning()) return;
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }


}
