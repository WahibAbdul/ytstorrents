package codeclobber.com.ytsbrowser.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.interfaces.NavMenuClickCallback;


/**
 * Created by wahib on 11/24/16.
 */

public class RVLeftMenuAdapter extends RecyclerView.Adapter<RVLeftMenuAdapter.ViewHolder> {

    private Context mContext;
    private int[] mMenuNames;
    private int[] mMenuIcons;
    private int mSelectedPosition = 0;
    private NavMenuClickCallback mCallback;

    public RVLeftMenuAdapter(Context context, int[] menuNames, int[] menuIcons, NavMenuClickCallback callback) {
        mContext = context;
        mMenuIcons = menuIcons;
        mMenuNames = menuNames;
        mCallback = callback;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mContext.getString(mMenuNames[position]));
        holder.icon.setImageResource(mMenuIcons[position]);
        if (mSelectedPosition == position) {
            // Make background selected
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.background.setBackgroundColor(mContext.getResources().getColor(R.color.selectedMenu, null));
            } else {
                holder.background.setBackgroundColor(mContext.getResources().getColor(R.color.selectedMenu));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.background.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent, null));
            } else {
                holder.background.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMenuNames.length;
    }

    public void changeSelectedMenu(int position) {
        mSelectedPosition = position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View background;
        ImageView icon;
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.background);
            icon = (ImageView) itemView.findViewById(R.id.img_menu_item);
            title = (TextView) itemView.findViewById(R.id.tv_menu_item);
            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int index = getAdapterPosition();
                    mCallback.itemClick(index);
                    int previousIndex = mSelectedPosition;
                    mSelectedPosition = index;
                    notifyItemChanged(previousIndex);
                    notifyItemChanged(mSelectedPosition);
                }
            });
        }
    }
}
