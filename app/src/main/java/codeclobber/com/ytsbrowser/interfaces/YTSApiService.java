package codeclobber.com.ytsbrowser.interfaces;

import codeclobber.com.ytsbrowser.constants.Constant;
import codeclobber.com.ytsbrowser.models.MoviesResponse;
import codeclobber.com.ytsbrowser.models.movieDetailResponse.MovieDetailResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wahib on 2/12/17.
 */

public interface YTSApiService {

    @GET("/api/v2/list_movies.json")
    Call<MoviesResponse> getLatestMovies(@Query(Constant.URL.PARAM.PAGE) int pageNo,
                                         @Query(Constant.URL.PARAM.LIMIT) int limit);

    @GET("/api/v2/list_movies.json")
    Call<MoviesResponse> getTopRatedMovies(@Query(Constant.URL.PARAM.PAGE) int pageNo,
                                           @Query(Constant.URL.PARAM.LIMIT) int limit,
                                           @Query(Constant.URL.PARAM.SORT_BY) String sort);

    @GET("/api/v2/list_movies.json")
    Call<MoviesResponse> filterMovies(@Query(Constant.URL.PARAM.PAGE) int pageNo,
                                      @Query(Constant.URL.PARAM.LIMIT) int limit,
                                      @Query(Constant.URL.PARAM.SORT_BY) String sort,
                                      @Query(Constant.URL.PARAM.QUALITY) String quality,
                                      @Query(Constant.URL.PARAM.MIN_RATING) String rating,
                                      @Query(Constant.URL.PARAM.GENRE) String genre);

    @GET("/api/v2/list_movies.json")
    Call<MoviesResponse> searchMovies(@Query(Constant.URL.PARAM.QUERY_TERM) String queryTerm,
                                      @Query(Constant.URL.PARAM.LIMIT) int limit);


    @GET("/api/v2/movie_details.json")
    Call<MovieDetailResponse> getMovieDetails(@Query(Constant.URL.PARAM.MOVIE_ID) int movieId,
                                              @Query(Constant.URL.PARAM.WITH_IMAGES) boolean withImage,
                                              @Query(Constant.URL.PARAM.WITH_CAST) boolean withCast);

    @GET("/api/v2/movie_suggestions.json")
    Call<MoviesResponse> getMovieSuggestions(@Query(Constant.URL.PARAM.MOVIE_ID) int movieId);
}
