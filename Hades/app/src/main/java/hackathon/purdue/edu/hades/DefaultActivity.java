package hackathon.purdue.edu.hades;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

/**
 * Created by vieck on 2/20/16.
 */
public class DefaultActivity extends AppCompatActivity {
    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mSharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        Toast.makeText(this,""+isLoggedIn(), Toast.LENGTH_LONG).show();


        if (isLoggedIn()) {
            Intent intent = new Intent(this, ProjectMenu.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean isLoggedIn() {
        boolean isLoggedIn = mSharedPreferences.getBoolean("loggedin",false);
        return isLoggedIn;
    }
}
