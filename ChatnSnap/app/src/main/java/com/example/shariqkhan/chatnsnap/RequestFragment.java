package com.example.shariqkhan.chatnsnap;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {

    private RecyclerView mFriendRequestList;
    private DatabaseReference mUserDatabaseRef;
    private FirebaseAuth mAuth;
    private RecyclerView.LayoutManager layoutManager;
    public View mView;


    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_request, container, false);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth == null) {
            Intent intent = new Intent(getContext(), RegisterActivity.class);
            startActivity(intent);
            getActivity().getSupportFragmentManager().popBackStack();

        }
        mUserDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Friend_requests").child(mAuth.getCurrentUser().getUid());

        mUserDatabaseRef.keepSynced(true);


        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<RequestModel, FriendRequestViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RequestModel, FriendRequestViewHolder>(
                RequestModel.class, R.layout.friend_request_single_layout, FriendRequestViewHolder.class, mUserDatabaseRef) {
            @Override
            protected void populateViewHolder(FriendRequestViewHolder viewHolder, RequestModel model, int position) {

                String id = model.getId();
                String type = model.getReqType();
                Log.e("id", id);
                Log.e("type", type);

            }
        };

    }

    public static class FriendRequestViewHolder extends RecyclerView.ViewHolder {
        View view;

        Button deciderButton;
        private TextView User_name;
        private CircleImageView circleImageView;


        public FriendRequestViewHolder(View itemView) {
            super(itemView);
            view = itemView;


            User_name = (TextView) view.findViewById(R.id.frined_request_display_username);
            circleImageView = (CircleImageView) view.findViewById(R.id.frined_request_user_image);
            deciderButton = (Button) view.findViewById(R.id.friend_request_decider_button);
            //User_name = (TextView) view.findViewById(R.id.user_single_name);


            //circleImageView=(CircleImageView)view.findViewById(R.id.user_small_image);
        }

    }
}

