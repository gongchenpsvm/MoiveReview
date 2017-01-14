package itp341.chen.gong.a9chengong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by gongchen on 11/8/16.
 */
public class DetailActivity extends FragmentActivity {
    public static final String VIEW_POSITION = "VIEW_POSITION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fetch the clicked view's position and then pass into the detail fragment constructor
        Intent intent = getIntent();
        int position = intent.getIntExtra(VIEW_POSITION,-1);
        //Create detail fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);
        if (f == null ) {
            f = DetailFragment.newInstance(position);//Construct with position
        }
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, f);
        fragmentTransaction.commit();

        //Intent i = new Intent();
        //setResult(Activity.RESULT_OK,i);
    }
}
