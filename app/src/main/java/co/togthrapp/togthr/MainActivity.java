package co.togthrapp.togthr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import co.togthrapp.togthr.Fragments.GalleryFragment;
import co.togthrapp.togthr.Fragments.ListsFragment;
import co.togthrapp.togthr.Fragments.StoreFragment;
import co.togthrapp.togthr.Fragments.TimelineFragment;
import co.togthrapp.togthr.base.FirebaseBaseActivity;

public class MainActivity extends FirebaseBaseActivity {

    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFragment(new TimelineFragment());

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_timeline:
                        TimelineFragment timelineFragment = new TimelineFragment();
                        openFragment(timelineFragment);
                        return true;

                    case R.id.nav_gallery:
                        GalleryFragment galleryFragment = new GalleryFragment();
                        openFragment(galleryFragment);
                        return true;

                    case R.id.nav_lists:
                        ListsFragment listsFragment = new ListsFragment();
                        openFragment(listsFragment);
                        return true;

                    case R.id.nav_store:
                        StoreFragment storeFragment = new StoreFragment();
                        openFragment(storeFragment);
                        return true;
                }
                return true;
            }
        });
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
