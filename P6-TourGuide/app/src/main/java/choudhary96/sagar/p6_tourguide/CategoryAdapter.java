package choudhary96.sagar.p6_tourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by admin on 30-06-2016.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    // Require context in order to use getPageTitle
    Context context;

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PlacesFragment();
            case 1:
                return new FoodFragment();
            case 2:
                return new ParksFragment();
            case 3:
                return new MuseumFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.category_places);
            case 1:
                return context.getString(R.string.category_food);
            case 2:
                return context.getString(R.string.category_parks);
            case 3:
                return context.getString(R.string.category_museums);
            default:
                return null;
        }
    }
}
