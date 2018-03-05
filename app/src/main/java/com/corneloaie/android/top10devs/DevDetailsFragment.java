package com.corneloaie.android.top10devs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Cornel-PC on 05/03/2018.
 */

public class DevDetailsFragment extends Fragment {
    private static final String ARGS_EXTRA_USER_ID =
            "com.corneloaie.android.top10devs.userID";
    private Developer mDeveloper;

    public static DevDetailsFragment newInstance(int userID) {
        Bundle args = new Bundle();
        args.putInt(ARGS_EXTRA_USER_ID, userID);
        DevDetailsFragment fragment = new DevDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int userID = getArguments().getInt(ARGS_EXTRA_USER_ID);
        mDeveloper = DevsList.get(getActivity()).getDeveloper(userID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_dev, container, false);
        TextView textView1 = view.findViewById(R.id.userNameTextView);
        ImageView imageView = view.findViewById(R.id.profileDetailImageView);
        TextView textView2 = view.findViewById(R.id.locationTextView);
        TextView textView3 = view.findViewById(R.id.bronzeTextView);
        TextView textView4 = view.findViewById(R.id.silverBadgeTextView);
        TextView textView5 = view.findViewById(R.id.goldBadgeTextView);

        textView1.setText(mDeveloper.getDisplayName());
        textView2.setText(mDeveloper.getLocation());
        textView3.setText("Bronze: " + mDeveloper.getBronzeBadge());
        textView4.setText("Sivler: " + mDeveloper.getSilverBadge() + "");
        textView5.setText("Gold: " + mDeveloper.getGoldBadge() + "");
        Glide.with(getActivity()).load(mDeveloper.getProfileImage())
                .into(imageView);

        return view;
    }


}
