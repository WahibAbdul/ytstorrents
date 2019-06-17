package codeclobber.com.ytsbrowser.fragments.movieDetailTabs;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.activities.MovieDetailActivity;
import codeclobber.com.ytsbrowser.activities.WebViewActivity;
import codeclobber.com.ytsbrowser.activities.YoutubePlayerActivity;
import codeclobber.com.ytsbrowser.adapters.RVCastAdapter;
import codeclobber.com.ytsbrowser.constants.Constant;
import codeclobber.com.ytsbrowser.fragments.BaseFragment;
import codeclobber.com.ytsbrowser.models.Movie;
import codeclobber.com.ytsbrowser.models.Torrent;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends BaseFragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
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
    @SuppressLint("DefaultLocale")
    private void initViews(View view) {
        // Initialize views
        ImageView trailerCover = (ImageView) view.findViewById(R.id.img_trailerCover);
        ImageButton playTrailer = (ImageButton) view.findViewById(R.id.imgBtn_play);

        ImageView thumbnail = (ImageView) view.findViewById(R.id.img_coverImage);
        TextView likes = (TextView) view.findViewById(R.id.tv_likes);
        TextView rating = (TextView) view.findViewById(R.id.tv_rating);
        TextView pg_rating = (TextView) view.findViewById(R.id.tv_pg_rating);

        TextView detail = (TextView) view.findViewById(R.id.tv_detail);
        TextView year = (TextView) view.findViewById(R.id.tv_year);
        TextView genre = (TextView) view.findViewById(R.id.tv_genre);

        TextView quality720 = (TextView) view.findViewById(R.id.tv_720_p);
        TextView quality1080 = (TextView) view.findViewById(R.id.tv_1080_p);
        TextView quality3d = (TextView) view.findViewById(R.id.tv_3d);


        // Make IMDB Button clickable
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openIMDBPage();
            }
        };
        rating.setOnClickListener(listener);
        view.findViewById(R.id.icon_imdb).setOnClickListener(listener);


        view.findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDownloadDialog();
            }
        });

        // Set Values
        final Movie movie = ((MovieDetailActivity) getActivity()).getMovie();


        Glide.with(getActivity()).load(movie.getMediumCoverImage()).into(thumbnail);
        rating.setText(String.format("%s", movie.getRating()));
        pg_rating.setText(movie.getMpaRating());


        year.setText(String.format("%d", movie.getYear()));
        StringBuilder builder = new StringBuilder("");
        for (String s : movie.getGenres()) {
            builder.append(s);
            builder.append(" / ");
        }
        if (builder.length() > 1)
            genre.setText(builder.substring(0, builder.length() - 2));
        else
            genre.setVisibility(View.GONE);
        detail.setText(movie.getDescriptionFull());

        quality720.setVisibility(View.GONE);
        quality1080.setVisibility(View.GONE);
        quality3d.setVisibility(View.INVISIBLE);

        for (Torrent t : movie.getTorrents()) {
            if (t.getQuality().equalsIgnoreCase("720p")) {
                quality720.setVisibility(View.VISIBLE);
            } else if (t.getQuality().equalsIgnoreCase("1080p")) {
                quality1080.setVisibility(View.VISIBLE);
            } else if (t.getQuality().equalsIgnoreCase("3D")) {
                quality3d.setVisibility(View.VISIBLE);
            }
        }

        Glide.with(getActivity()).load(movie.getMediumScreenshotImage1()).into(trailerCover);

        if (movie.getYtTrailerCode() != null && !movie.getYtTrailerCode().isEmpty()) {
            playTrailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    String url = Constant.URL.YOUTUBE_BASE_URL + movie.getYtTrailerCode();
//                    Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
//                    intent.putExtra("object", "http://www.youtube.com/watch?v="+movie.getYtTrailerCode());
//                    getActivity().startActivity(intent);
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + movie.getYtTrailerCode())));

                    String code = movie.getYtTrailerCode();
                    if (code != null && !code.isEmpty()) {
                        Intent intent = new Intent(getActivity(), YoutubePlayerActivity.class);
                        intent.putExtra("object", code);
                        intent.putExtra("title", movie.getTitleLong());
                        getActivity().startActivity(intent);
                    }
                }
            });
        } else {
            playTrailer.setVisibility(View.GONE);
        }


        likes.setText(String.format("%d", movie.getLikeCount()));

        if (movie.getCast().size() > 0) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(new RVCastAdapter(getContext(), movie.getCast()));
        } else {
            view.findViewById(R.id.tv_cast_title).setVisibility(View.INVISIBLE);
        }


    }

    private void openIMDBPage() {
        final Movie movie = ((MovieDetailActivity) getActivity()).getMovie();
        String code = movie.getImdbCode();
        if (code != null && !code.isEmpty()) {
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            String url = Constant.URL.IMDB_MOVIE_BASE_URL + code;
            if (!code.contains("tt"))
                url = Constant.URL.IMDB_MOVIE_BASE_URL + "tt" + code;
            intent.putExtra("url", url);
            intent.putExtra("title", "IMDB");
            getActivity().startActivity(intent);
        }
    }

    private void showDownloadDialog() {

        final Movie movie = ((MovieDetailActivity) getActivity()).getMovie();
        List<Torrent> torrents = movie.getTorrents();

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_download_torrent, null);
        View _720pView = view.findViewById(R.id.ll_720p);
        View _1080pView = view.findViewById(R.id.ll_1080p);
        View _3dView = view.findViewById(R.id.ll_3d);

        if (torrents.size() == 1) {
//            view.findViewById(R.id.ll_1080p_separator).setVisibility(View.GONE);
            _1080pView.setVisibility(View.GONE);
            view.findViewById(R.id.ll_3d_separator).setVisibility(View.GONE);
            _3dView.setVisibility(View.GONE);
        } else if (torrents.size() == 2) {
//            view.findViewById(R.id.ll_1080p_separator).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.ll_3d_separator).setVisibility(View.GONE);
            _3dView.setVisibility(View.GONE);
        } else if (torrents.size() == 3) {
//            view.findViewById(R.id.ll_1080p_separator).setVisibility(View.INVISIBLE);
//            view.findViewById(R.id.ll_3d_separator).setVisibility(View.INVISIBLE);
        } else {
            return;
        }

        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();

        // Show values of torrent
        for (int i = 0; i < torrents.size(); i++) {
            final Torrent torrent = torrents.get(i);
            if (torrent.getQuality().equalsIgnoreCase("720p")) { // 720p Data
                ((TextView) view.findViewById(R.id.tv_720p_filesize)).setText(torrent.getSize());
                (view.findViewById(R.id.btn_download_720p)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        downloadTorrent(torrent);
                    }
                });
            } else if (torrent.getQuality().equalsIgnoreCase("1080p")) { //1080p Data
                ((TextView) view.findViewById(R.id.tv_1080p_filesize)).setText(torrent.getSize());
                (view.findViewById(R.id.btn_download_1080p)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        downloadTorrent(torrent);
                    }
                });
            } else if (torrent.getQuality().equalsIgnoreCase("3D")) { //3D Data
                ((TextView) view.findViewById(R.id.tv_3d_filesize)).setText(torrent.getSize());
                (view.findViewById(R.id.btn_download_3d)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        downloadTorrent(torrent);
                    }
                });
            }
        }
        dialog.show();
    }

    private void downloadTorrent(Torrent torrent) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(torrent.getUrl()));
        startActivity(browserIntent);
    }


}
