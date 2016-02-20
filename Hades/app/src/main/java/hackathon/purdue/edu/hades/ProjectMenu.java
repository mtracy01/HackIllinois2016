package hackathon.purdue.edu.hades;



import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import hackathon.purdue.edu.hades.mainMenu.ContentFragment;
import hackathon.purdue.edu.hades.mainMenu.createProjectFragment;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;


public class ProjectMenu extends ActionBarActivity implements ViewAnimator.ViewAnimatorListener {
    private String LOG_TAG = "ProjectMenu";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    List<SlideMenuItem> list = new ArrayList<>();
    ViewAnimator viewAnimator;
    LinearLayout linearLayout;
    ContentFragment contentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_menu);

        contentFragment = ContentFragment.newInstance(R.drawable.menu_item_selector); //TODO: Fix this
        //TODO: Set initial fragment here
        /*getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, //Create new instance of fragment here);
                .commit();

         */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);
    }

    //Methods specialized for new menu here
    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.mipmap.ic_action_exit);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.ADDEMAIL, R.mipmap.ic_action_plusone);
        list.add(menuItem);
        //TODO: Automate adding new icons so that we find email accounts associated with user and add their icons and stuff
    }


    private void setActionBar() {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_action_expand);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerToggle.syncState();
            }
        });
        getSupportActionBar().setTitle("Games");

        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.v(LOG_TAG, "Menu Item is: " + menuItem.getItemId());
                if (menuItem.getItemId() == R.id.action_logout) {
                    FacebookHelper.logout();
                    startActivity(new Intent(ProjectMenu.this, LoginActivity.class));
                    overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top);
                }
                return true;
            }
        });
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.mipmap.ic_action_expand,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        Log.v(LOG_TAG, "" + position);
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            default:
                Log.v(LOG_TAG, "Name = " + slideMenuItem.getName());
                return replaceFragment(screenShotable, slideMenuItem.getName());
        }
    }

    @Override
    public void disableHomeButton() {
            getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {
            getSupportActionBar().setHomeButtonEnabled(true);
            drawerLayout.closeDrawers();
    }

    @Override
    public void addViewToContainer(View view) {
            linearLayout.addView(view);
    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, String name) {
        //Switch depending on the name of the Menu //TODO: Implement fragment switching
        switch (name) {
            case ContentFragment.ADDEMAIL:
                Log.v(LOG_TAG, "Games Menu");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, createProjectFragment.newInstance()).setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom).addToBackStack("Games").commit();
                getSupportActionBar().setTitle("New Project");
                break;
            /*case ContentFragment.GROUPS:

                Log.v(LOG_TAG, "Groups Menu");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, GroupsFragment.newInstance()).setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom).commit();
                getSupportActionBar().setTitle("Groups");
                break;
            case ContentFragment.FRIENDS:

                Log.v(LOG_TAG, "Friends Menu");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, FriendStatus.newInstance()).setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom).commit();
                getSupportActionBar().setTitle("Friends");
                break;
            case ContentFragment.SETTINGS:

                Log.v(LOG_TAG, "Settings Menu");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, SettingsFragment.newInstance()).setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom).commit();
                getSupportActionBar().setTitle("Settings");
                break;*/
        }
        return contentFragment;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
