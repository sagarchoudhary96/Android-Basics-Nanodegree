package choudhary96.sagar.p6_tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class ParksFragment extends Fragment {

    public ParksFragment() {
        //requires Blank Constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_list, container, false);

        // Locations List
        final ArrayList<Location> parks = new ArrayList<Location>();
        parks.addAll(Arrays.asList(
                 new Location("Lodhi Garden", "It is spread in an area of 90 acres and is known for its architecture of 15th century. It is located on Lodhi road between khan market and Safdarjung’s tomb. Because of its architecture and historical value this park is now under protection by Archaeological Survey of India. Park has tombs of Muhammad shah and Sikandar Lodhi. Centre of the garden has ‘Bara Gumbad’ and on the opposite side of this had ‘Shisha Gumbad’ . this park is under good maintainence and attracts lot of tourists. Also, this place is favorite place for the morning joggers.",28.5930999,77.2174906, R.drawable.lodhi_garden)
                ,new Location("Garden Of Five Senses", "This was constructed by ‘Delhi tourism transportation development corporation’ inaugurated in February 2003 and is not just a garden but has a lots of space for various recreational activities. It is located at Said-ul-Azaib village. There are series of fountains, walking trails, water lilies pool, about hundreds of varieties of plants and lots of other attractions. It is named garden of five senses as the beauty of the garden stimulates the senses of sight, sound, touch, smell and taste.",28.5139397,77.1947293, R.drawable.garden_of_five_senses)
                ,new Location("Deer Park", "Also known as A.N Jha deer park this park is located in Hauz Khas south Delhi and  was named after famous social worker Aditya Nath Jha. This park has tombs of Mughal era and Duck Park, rabbit enclosures, art market, rose garden, deer park. This park is perfect for morning walks and picnic full of greenery and natural beauty away from city’s pollution.",28.5564177,77.195802, R.drawable.deer_park)
                ,new Location("Mughal Garden", "This park spread in 6 hectares of area is situated inside the Rastrapati Bhavan and is open to the public only for the month of February and March. It is a circular garden which is designed in Mughal style and has some British style as well with fountains, terraces etc. It has wide varieties of flowers of rose, marigold, and bougainvillea. This is an extremely beautiful garden.",28.6144384,77.196526, R.drawable.mughal_garden)
                ,new Location("Nehru Park", "Located in Chanakyapuri this park is about in 80 acres of area. Various music concerts and events, yoga classes etc are regularly organized in this park. Best park to visit during sunny winters and evening of summers.",28.5918307,77.1933613,R.drawable.nehru_park)
                ,new Location("Talkatora Garden", "It is located on Willingdon Crescent in New Delhi.  It is named so as there was a taal(tank) at west end of garden which was surrounded by hilly ground giving it the shape of  katora ‘bowl’. Talkatora stadium is located next to this park. This garden is also of historic significance as it has a place where Mughuls defeated Marathas in 1738 fight.",28.6233689,77.1941366,R.drawable.talkatora_garden)
        ));


        LocationAdapter locationAdapter = new LocationAdapter(getActivity(), parks);
        ListView listView = (ListView)rootView.findViewById(R.id.list);
        listView.setAdapter(locationAdapter);
        // Go to detailed view, passing in:
        // Name, Description, Google Maps URI, ImageID
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location currentLocation = parks.get(position);
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
