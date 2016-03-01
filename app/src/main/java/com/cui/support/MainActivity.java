package com.cui.support;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private int mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
    private RelativeLayout mBottomView;
    private RecyclerView recyclerView;

    @Override
    protected void onResume() {
        super.onResume();
        int uiMode = getResources().getConfiguration().uiMode;
        int dayNightUiMode = uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (dayNightUiMode == Configuration.UI_MODE_NIGHT_NO) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_NO;
        } else if (dayNightUiMode == Configuration.UI_MODE_NIGHT_YES) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_YES;
        } else {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBottomView = (RelativeLayout) findViewById(R.id.bottom_sheet);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_sheet);
        BottomSheetDialogView.show_BottomView(this, mBottomView, recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_day_night_yes) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            recreate();
            return true;
        } else if (id == R.id.action_day_night_no) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            recreate();
            return true;
        } else if (id == R.id.action_bottom_sheet_dialog) {
            BottomSheetDialogView.show_BottomSheetDialogView(this, mDayNightMode);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
