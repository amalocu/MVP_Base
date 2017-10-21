package co.com.etn.mvp_base.views.activities;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.Constants;
import co.com.etn.mvp_base.helper.CustomSharePreference;
import co.com.etn.mvp_base.views.BaseActivity;
import co.com.etn.mvp_base.views.adapter.DashBoardTabAdapter;

public class DashBoardActivity extends BaseActivity{

    private TabLayout dash_board_activity_tablayout;
    private ViewPager dash_board_activity_viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        loadView();
        loadToolBar();
        loadTabsAdapter();
    }

    private void loadToolBar() {
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle(Constants.EMPTY);
        setSupportActionBar(toolBar);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CustomSharePreference customSharePreference = new CustomSharePreference(this);
        customSharePreference.deleteObject("user");
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard,menu);
        return true;
    }

    private void loadTabsAdapter() {
        DashBoardTabAdapter dashBoardTabAdapter = new DashBoardTabAdapter(getSupportFragmentManager(), this);
        dash_board_activity_viewpager.setAdapter(dashBoardTabAdapter);
        dash_board_activity_tablayout.setupWithViewPager(dash_board_activity_viewpager);
        dash_board_activity_tablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        dash_board_activity_tablayout.setTabTextColors(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.colorPrimaryDark)));

    }

    private void loadView() {
        dash_board_activity_tablayout =(TabLayout)findViewById(R.id.dash_board_activity_tablayout);
        dash_board_activity_viewpager =(ViewPager) findViewById(R.id.dash_board_activity_viewpager);

    }
}
