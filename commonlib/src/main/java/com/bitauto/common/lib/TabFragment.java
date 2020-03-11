package com.bitauto.common.lib;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitauto.android.commonlib.R;

/**
 * Created by yuan on 2020/3/11.
 * Email:yuanwb@yiche.com
 */
public class TabFragment extends Fragment {

    public static TabFragment create(Bundle bundle) {
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView textView = view.findViewById(R.id.tab_fragment_tv);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString("title");
            textView.setText(title);
        }
    }

}
