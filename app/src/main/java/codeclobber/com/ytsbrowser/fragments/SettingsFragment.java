package codeclobber.com.ytsbrowser.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.constants.Constant;
import codeclobber.com.ytsbrowser.utils.PreferencesUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment {


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R.id.switch_notifications);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                new PreferencesUtil(getActivity()).saveBoolean(Constant.PreferencesKey.IS_NOTIFICATION_ENABLED, b);
            }
        });

        view.findViewById(R.id.ll_premiumRow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Upgrade to Premium", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
