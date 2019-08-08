package com.summit.summitproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.summit.summitproject.prebuilt.model.FeedAction;
import com.summit.summitproject.prebuilt.model.FeedAdapter;
import com.summit.summitproject.prebuilt.model.Friend;
import com.summit.summitproject.prebuilt.model.FriendAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity implements FriendAdapter.FriendClickedListener {

    private RecyclerView leaderboard_list;
    private RecyclerView feed_list;

    /**
     * Takes the transactions data and instructs the transactionsList on how they should be
     * rendered.
     */
    private RecyclerView.Adapter friendsAdapter;
    private RecyclerView.Adapter feedAdapter;

    /**
     * Called the first time an Activity is created, but before any UI is shown to the user.
     * Prepares the layout and assigns UI widget variables.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        findViewById(R.id.profileButton)
                .setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(LeaderboardActivity.this, ProfileActivity.class));
                                        }
                                    }
                );

        leaderboard_list = findViewById(R.id.leaderboard_list);
        feed_list = findViewById(R.id.feed_list);
        feed_list.setVisibility(View.GONE);


        // Prepare the list data

        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Sam Edwards", "handstandsam", 17.0, true, R.drawable.sam));
        friends.add(new Friend("Arman Parastaran", "pararaman", 7.0, true, R.drawable.arman));
        friends.add(new Friend("Kenneth Shinn", "kshinn", 3.0, true, R.drawable.kenneth));
        friends.add(new Friend("Alan Cheng", "acheng5168", -5.0, true, R.drawable.alan));
        friends.add(new Friend("Shane Aung", "brown_cowboy", 1.0, true, R.drawable.shane));

        friendsAdapter = new FriendAdapter(friends, this);
        leaderboard_list.setLayoutManager(new LinearLayoutManager(this));
        leaderboard_list.setAdapter(friendsAdapter);

        // Prepare the feed data
        List<FeedAction> feed = new ArrayList<>();
        feed.add(new FeedAction("Kenneth Shinn", "June 26 2019", "AAPL", 10.2, 9.6, true));
        feed.add(new FeedAction("Arman Parastaran", "June 26 2019", "AMD", 12.3, 18.6, true));
        feed.add(new FeedAction("Shane Aung", "June 26 2019", "AAPL", 10.2, 9.6, false));
        feed.add(new FeedAction("Alan Cheng", "June 26 2019", "GOOG", 100.7, 127.74, false));
        feed.add(new FeedAction("Sam Edwards", "June 26 2019", "TSLA", 464.87, 326.74, false));


        feedAdapter = new FeedAdapter(feed, this);
        feed_list.setLayoutManager(new LinearLayoutManager(this));
        feed_list.setAdapter(feedAdapter);




        final View feed_list = findViewById(R.id.feed_list);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIndex = tab.getPosition();
                if (tabIndex == 0) {
                    //leaderboard
                    feed_list.setVisibility(View.GONE);
                    leaderboard_list.setVisibility(View.VISIBLE);

                } else {
                    //feed
                    leaderboard_list.setVisibility(View.GONE);
                    feed_list.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * Called when the user clicks on any of the transactions in the list. From here, you could
     * open up a new screen to further show transaction details.
     */
    @Override
    public void onFriendClicked(Friend friend) {
        Toast.makeText(this, getString(R.string.transaction_selected, friend.getName()), Toast.LENGTH_LONG).show();
    }
}
