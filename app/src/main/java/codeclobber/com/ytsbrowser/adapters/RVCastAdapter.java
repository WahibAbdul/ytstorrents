package codeclobber.com.ytsbrowser.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.activities.WebViewActivity;
import codeclobber.com.ytsbrowser.constants.Constant;
import codeclobber.com.ytsbrowser.models.movieDetailResponse.Cast;

/**
 * Created by wahib on 2/12/17.
 */

public class RVCastAdapter extends RecyclerView.Adapter<RVCastAdapter.ViewHolder> {

    private Context mContext;
    private List<Cast> mCastList = new ArrayList<>();
    private boolean isListCard = false;

    public RVCastAdapter(Context context, List<Cast> castList) {
        this.mContext = context;
        this.mCastList = castList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_cast, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cast cast = mCastList.get(position);
        holder.title.setText(cast.getName());
        holder.characterName.setText(cast.getCharacterName());
        if (cast.getUrlSmallImage() == null || cast.getUrlSmallImage().isEmpty())
            Glide.with(mContext).load(R.drawable.person).into(holder.personImage);
        else
            Glide.with(mContext).load(cast.getUrlSmallImage()).into(holder.personImage);
    }

    @Override
    public int getItemCount() {
        return mCastList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, characterName;
        ImageView personImage;

        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            characterName = (TextView) itemView.findViewById(R.id.tv_characterName);
            personImage = (ImageView) itemView.findViewById(R.id.img_person);
            itemView.findViewById(R.id.backView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String code = mCastList.get(getAdapterPosition()).getImdbCode();
                    if (code != null && !code.isEmpty()) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        String url = Constant.URL.IMDB_CAST_BASE_URL + code;
                        if (!code.contains("nm"))
                            url = Constant.URL.IMDB_CAST_BASE_URL + "nm" +code;
                        intent.putExtra("url", url);
                        intent.putExtra("title", "IMDB");
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}
