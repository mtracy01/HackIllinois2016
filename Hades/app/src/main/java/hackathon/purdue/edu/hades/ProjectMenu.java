package hackathon.purdue.edu.hades;



import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import hackathon.purdue.edu.hades.mainMenu.ContentFragment;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;


public class ProjectMenu extends ActionBarActivity {

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
        /* getSupportFragmentManager().beginTransaction()
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

        //TODO: Implement this: setActionBar();
        //TODO: And this: createMenuList();
    }
}
