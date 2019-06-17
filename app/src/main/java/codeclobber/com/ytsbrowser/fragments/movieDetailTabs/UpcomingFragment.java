package codeclobber.com.ytsbrowser.fragments.movieDetailTabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.fragments.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends BaseFragment {


    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        return view;
    }

}
