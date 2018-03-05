package com.corneloaie.android.top10devs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.corneloaie.android.top10devs.volley.VolleyCallback;
import com.corneloaie.android.top10devs.volley.VolleyHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class DevsListFragment extends Fragment {
    private Callbacks mCallbacks;

    private RecyclerView mRecyclerView;
    private DeveloperAdapter mAdapter;
    private List<Developer> mDevsList;

    public static DevsListFragment newInstance() {

        Bundle args = new Bundle();
        DevsListFragment fragment = new DevsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_devs_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_list);
        getTopDevs(10);

        return view;
    }

    public void getTopDevs(final int noOfDevs) {
        VolleyCallback callback = new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject object) {
                try {
                    mDevsList = DevsList.get(getActivity()).getDevs();
                    JSONArray jsonArray = object.getJSONArray("items");
                    for (int i = 0; i < noOfDevs; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Developer developer = new Developer();
                        developer.setDisplayName(jsonObject.getString("display_name"));
                        developer.setLocation(jsonObject.getString("location"));
                        developer.setUserID(jsonObject.getInt("user_id"));
                        developer.setProfileImage(jsonObject.getString("profile_image"));
                        developer.setBronzeBadge(jsonObject.getJSONObject("badge_counts").getInt("bronze"));
                        developer.setSilverBadge(jsonObject.getJSONObject("badge_counts").getInt("silver"));
                        developer.setGoldBadge(jsonObject.getJSONObject("badge_counts").getInt("gold"));
                        mDevsList.add(developer);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                if (mAdapter == null) {
                    mAdapter = new DeveloperAdapter(mDevsList);
                    mRecyclerView.setAdapter(mAdapter);

                }

            }

            @Override
            public void onError(VolleyError error) {
                super.onError(error);
            }
        };
        VolleyHelper.getInstance().get("2.2/users?order=desc&sort=reputation&site=stackoverflow", callback, getActivity());
    }

    public interface Callbacks {
        void onDevSelected(Developer developer);
    }

    private class DeveloperHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Developer mDeveloper;
        private TextView mNameTextView;
        private ImageView mProfileImageView;

        public DeveloperHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_dev, parent, false));
            itemView.setOnClickListener(this);
            mNameTextView = itemView.findViewById(R.id.nameTextView);
            mProfileImageView = itemView.findViewById(R.id.profileImageView);

        }


        public void bind(Developer developer, Context context) {
            mDeveloper = developer;
            mNameTextView.setText(mDeveloper.getDisplayName());
            //    mProfileImageView.setImageURI(Uri.parse(mDeveloper.getProfileImage()));
            Glide.with(context).load(mDeveloper.getProfileImage())
                    .into(mProfileImageView);


        }

        @Override
        public void onClick(View view) {
            mCallbacks.onDevSelected(mDeveloper);
        }
    }

    private class DeveloperAdapter extends RecyclerView.Adapter<DeveloperHolder> {

        private List<Developer> mDevs;

        public DeveloperAdapter(List<Developer> crimes) {
            mDevs = crimes;
        }

        @Override
        public DeveloperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new DeveloperHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(DeveloperHolder holder, int position) {
            Developer developer = mDevs.get(position);
            holder.bind(developer, getContext());

        }

        @Override
        public int getItemCount() {
            return mDevs.size();
        }
    }

}
