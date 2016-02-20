package hackathon.purdue.edu.hades;

import android.widget.Toast;

import com.facebook.login.LoginManager;

/**
 * Created by Matthew on 2/20/2016.
 */
public class FacebookHelper {

    private static String LOG_TAG = "FacebookHelper";

    //Log out of facebook
    public static void logout() {

        //Logout of Facebook

        LoginManager loginManager = LoginManager.getInstance();
        loginManager.logOut();

        //Destroy GPS thread and client (if they exist)

        /*if (Data.client != null) {
            Data.client.closeSocket();
            Data.client = null;
        }*/
        //Toast.makeText(Data.mainAct.getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
    }
}