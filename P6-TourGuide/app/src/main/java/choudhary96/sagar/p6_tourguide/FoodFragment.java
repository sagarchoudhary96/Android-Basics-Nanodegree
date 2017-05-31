package choudhary96.sagar.p6_tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by admin on 30-06-2016.
 */
public class FoodFragment extends Fragment {

    public FoodFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        // Locations List
        final ArrayList<Location> foods = new ArrayList<Location>();
        foods.addAll(Arrays.asList(
                new Location("Paranthe Wali Gali", "Located in the Chandni Chowk area of New Delhi, the Paranthe wali gali as the name suggests is renowned for the huge number of shops selling paratha that is a fried Indian bread. Note that the parathas are strictly vegetarian! The parathas are very budget-friendly and you can eat to your taste buds content!",28.65608,77.2283142, R.drawable.parathe_waali_gali)
                ,new Location("Dilli Haat", "To have a culinary culture ride head to Dilli Haat in INA. One of the top places to taste dishes from all states of India, Dilli Haat is a treat to behold. From North-Indian thali to Rajasthan spicy dishes or from Hyderabadi Biryani to sea food from Lakshadweep, you name it and you will get it. Cuisines from Hyderabad, Kashmir, Maharashtra, Orissa, Bengal, the list is endless in Dilli Haat!",28.5731438,77.2063742, R.drawable.dilli_haat)
                ,new Location("Prince’s Paan Shop", "Not everyone would be comfortable to delve deep in to the by lanes of Chandni Chowk, to satisfy the taste buds of such individuals Prince’s Paan & Chaat Corner is the solution. The apt up-market place for those who want to taste delicious street food in Delhi without venturing much. Owing to its location in the posh Greater Kailash I, this place has got great expats visiting it. The cost is steep at Rs. 250 for two but so is the taste!",28.5494864,77.2340124, R.drawable.prince_paan)
                ,new Location("Park Balluchi", "Park Balluchi’s Hauz Khas premises is located in the middle of Deer Park, affording diners stunning views and opportunities to observe the local wildlife from within the glass-walled restaurant. Park Balluchi’s menu features a wide range of authentic Mughlai and Afghan barbecue dishes, such as murg potli – minced lamb wrapped in marinated chicken breast – and grilled chicken marinated in saffron. For vegetarians, there is mewa paneer tukra: an elaborate dish of paneer stuffed with nuts, dates and raisins.",28.560301,77.1600309, R.drawable.park_balluchi)
                ,new Location("Khan Chacha", "The most sought after place to gorge on non-vegetarian food, Khan Chacha’s located in Khan Market will serve the best rolls, kebabs, rotis and much more! Not just non-vegetarian food, the vegetarian fare on offer is also something worth trying. Prices are little on the higher end, but with such amazing taste, it is worth every bite!",28.6006183,77.2251063, R.drawable.khan_chacha)
                ,new Location("Al-Bake", "The list of amazing street food is incomplete without mentioning Shawarma at Al-Bake in New Friends Colony Market. A small joint with few tables lined for customers, each shawarma roll costs not more than Rs. 60 and it is more than satisfying!. With such amazing street food no wonder Delhi is a people favorite! Let the feasting begin!",28.561832,77.2660439, R.drawable.al_bake)
        ));
        LocationAdapter locationAdapter = new LocationAdapter(getActivity(), foods);
        ListView listView = (ListView)rootView.findViewById(R.id.list);
        listView.setAdapter(locationAdapter);
        // Go to detailed view, passing in:
        // Name, Description, Google Maps URI, ImageID
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location currentLocation = foods.get(position);
                Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
                detailIntent.putExtra("location_name", currentLocation.getmLocationName());
                detailIntent.putExtra("location_desc", currentLocation.getmLocationDescription());
                detailIntent.putExtra("location_geoURI", currentLocation.getGMapsURI());
                detailIntent.putExtra("location_imageID", currentLocation.getmImageResourceId());
                startActivity(detailIntent);
            }
        });
        return rootView;
    }
}
