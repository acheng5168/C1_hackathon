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
import com.summit.summitproject.prebuilt.model.Friend;
import com.summit.summitproject.prebuilt.model.FriendAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays a user's name, the last 4 numbers of a credit card, and their recent transactions
 * for that card.
 * <br>
 * Expects the following pieces of data to be supplied via the {@link android.content.Intent}:
 * <ul>
 * <li>User's name -- via {@link LeaderboardActivity#KEY_NAME}</li>
 * <li>The last four numbers of a credit card -- via {@link LeaderboardActivity#KEY_CARD_NUM}</li>
 * <li>Recent transactions for the credit card -- via {@link LeaderboardActivity#KEY_TRANSACTIONS}</li>
 * </ul>
 */
public class LeaderboardActivity extends AppCompatActivity implements FriendAdapter.FriendClickedListener {

    /**
     * Used to extract the user's name from the launch {@link android.content.Intent}
     */
    public static final String KEY_NAME = "NAME";

    /**
     * Used to extract a credit card last 4 from the launch {@link android.content.Intent}
     */
    public static final String KEY_CARD_NUM = "CARD_NUM";

    /**
     * Used to extract a credit card's transaction from the launch {@link android.content.Intent}
     */
    public static final String KEY_TRANSACTIONS = "TRANSACTIONS";

    // Data passed in via the Intent

    private String name;

    private String cardNum;

    // UI Widgets

    private TextView title;

    private TextView subtitle;

    private RecyclerView transactionsList;

    /**
     * Takes the transactions data and instructs the transactionsList on how they should be
     * rendered.
     */
    private RecyclerView.Adapter friendsAdapter;

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

//        name = getIntent().getStringExtra(KEY_NAME);
//        cardNum = getIntent().getStringExtra(KEY_CARD_NUM);
//        transactions = (List<Transaction>) getIntent().getSerializableExtra(KEY_TRANSACTIONS);
//
//        title = findViewById(R.id.summary_title);
//        subtitle = findViewById(R.id.summary_subtitle);
        transactionsList = findViewById(R.id.leaderboard_list);
//
//        // Substitute in the user's name and card last 4 in the text widgets
//        title.setText(getString(R.string.summary_title, name));
//        subtitle.setText(getString(R.string.summary_subtitle, cardNum));
//
        // Prepare the list data

        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Sam Edwards", "handstandsam", 2.0, true, R.drawable.sam));
        friends.add(new Friend("Arman Parastaran", "pararaman", 15.0, true, R.drawable.arman));
        friends.add(new Friend("Kenneth Shinn", "kshinn", 65.0, true, R.drawable.kenneth));
        friends.add(new Friend("Alan Cheng", "acheng5168", -5.0, true, R.drawable.alan));
        friends.add(new Friend("Shane Aung", "shaneng", -300.0, true, R.drawable.shane));

        friendsAdapter = new FriendAdapter(friends, this);
        transactionsList.setLayoutManager(new LinearLayoutManager(this));
        transactionsList.setAdapter(friendsAdapter);


        final View redButton = findViewById(R.id.redButton);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIndex = tab.getPosition();
                if (tabIndex == 0) {
                    //Activity
                    redButton.setVisibility(View.GONE);
                    transactionsList.setVisibility(View.VISIBLE);

                } else {
                    //Analytics
                    transactionsList.setVisibility(View.GONE);
                    redButton.setVisibility(View.VISIBLE);
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
    public void onFriendClicked(Friend transaction) {
        Toast.makeText(this, getString(R.string.transaction_selected, transaction.getName()), Toast.LENGTH_LONG).show();
    }
}
