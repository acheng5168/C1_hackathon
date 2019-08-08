package com.summit.summitproject;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.summit.summitproject.prebuilt.model.Transaction;
import com.summit.summitproject.prebuilt.model.TransactionAdapter;

import java.util.List;

/**
 * Displays a user's name, the last 4 numbers of a credit card, and their recent transactions
 * for that card.
 * <br>
 * Expects the following pieces of data to be supplied via the {@link android.content.Intent}:
 * <ul>
 * <li>User's name -- via {@link SummaryActivity#KEY_NAME}</li>
 * <li>The last four numbers of a credit card -- via {@link SummaryActivity#KEY_CARD_NUM}</li>
 * <li>Recent transactions for the credit card -- via {@link SummaryActivity#KEY_TRANSACTIONS}</li>
 * </ul>
 */
public class SummaryActivity extends AppCompatActivity implements TransactionAdapter.TransactionClickedListener {

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

    private List<Transaction> transactions;

    // UI Widgets

    private TextView title;

    private TextView subtitle;

    private RecyclerView transactionsList;

    /**
     * Takes the transactions data and instructs the transactionsList on how they should be
     * rendered.
     */
    private RecyclerView.Adapter transactionsAdapter;

    /**
     * Called the first time an Activity is created, but before any UI is shown to the user.
     * Prepares the layout and assigns UI widget variables.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        name = getIntent().getStringExtra(KEY_NAME);
        cardNum = getIntent().getStringExtra(KEY_CARD_NUM);
        transactions = (List<Transaction>) getIntent().getSerializableExtra(KEY_TRANSACTIONS);

        transactionsList = findViewById(R.id.transaction_list);

        final View redButton = findViewById(R.id.redButton);

        TabLayout tabLayout = findViewById(R.id.profile_tabLayout);
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


//        TabItem analyticTab = findViewById(R.id.analytics_tab);
//        analyticTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


//        TabItem activityTab = findViewById(R.id.activity_tab);
//        activityTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        // Substitute in the user's name and card last 4 in the text widget
        // Prepare the list data
        transactionsAdapter = new TransactionAdapter(transactions, this);
        transactionsList.setLayoutManager(new LinearLayoutManager(this));
        transactionsList.setAdapter(transactionsAdapter);
    }

    /**
     * Called when the user clicks on any of the transactions in the list. From here, you could
     * open up a new screen to further show transaction details.
     */
    @Override
    public void onTransactionClicked(Transaction transaction) {
        Toast.makeText(this, getString(R.string.transaction_selected, transaction.getMerchant()), Toast.LENGTH_LONG).show();
    }
}
