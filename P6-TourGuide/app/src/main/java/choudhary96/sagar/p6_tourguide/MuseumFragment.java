package choudhary96.sagar.p6_tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by admin on 04-07-2016.
 */
public class MuseumFragment extends android.support.v4.app.Fragment {

    public MuseumFragment() {
        //empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_list, container, false);

        //Places list
        final ArrayList<Location> places = new ArrayList<Location>();
        places.addAll(Arrays.asList(
                new Location("Archeological Museum","The Archeological Museum in Delhi is situated in the Red Fort. The museum has kept objects from 1000 BC and then come down from the Mauryan to Mughal dynasty. Objects such as paintings, potteries, calligraphy, antiques, textiles, weapons and costumes have been excavated and stored. There is a section of the museum which portrays the First War of Independence.",37.9890271,23.7305712,R.drawable.archeological_museum)
                ,new Location("National Museum", "National Museum of Delhi is located in Janpath. It was established in 1949 which records over 2, 00,000 arts from the history to the modern times, covering 5,000 years from the past and still ongoing. The objects are from India and as well as foreign, which cover armaments, arts, jewelry, paintings, etc. The relics of Indus Valley and treasures of the Silk Road in Central Asia are its most precious possessions.",28.611804,77.2173047, R.drawable.national_museum)
                ,new Location("Rail Museum","The Rail Museum is Delhi is located in Chanakya. The museums depict the transformation and development of Indian Railways from the past 130 years. There are some unique trains created such as the fairy Queen, The Viceregal dining car, the Patiala Monorail Tramway which is unique to the world, and so on. There are toy trains made for the visitors, so that they can take a quick tour through the museum.",28.5857018,77.1775765, R.drawable.rail_museum)
                ,new Location("AirForce Museum", "Air Force Museum is located in Palam which is in the outer suburbs of the city. It has a collection of airplanes and armory objects which shows the history of Indian Air Force. The galleries are also filled with pictures which show the history of the Indian Air Force, uniforms, weapons, etc. of different periods.",28.5740099,77.1145218, R.drawable.airforce_museum)
                ,new Location("Doll Museum", "Shankar International Dolls Museum is located in Bahadur Shah Zafar Marg. K. Shankar Pillai was a political cartoonist who had the largest collection of dolls, from all over the world. He had been inspired by a doll which he had received as a gift from the Hungarian Ambassador as a prize in his International Children’s Competition. Since then Indian dolls are exchanged for gifts received from abroad or sold to them.",28.6304127,77.239527, R.drawable.doll_museum)
                ,new Location("National Police Museum", "The National Police Museum is in Lodi Road. The museum makes people think about police, criminal, crimes from the old days to the modern times and the evolution. The items exhibited have been taken from all over the country and also some from parts of the world.",28.5857018,77.1775765, R.drawable.police_museum)
        ));

        LocationAdapter locationAdapter = new LocationAdapter(getActivity(), places);
        ListView listView = (ListView)rootView.findViewById(R.id.list);
        listView.setAdapter(locationAdapter);
        // Go to detailed view, passing in:
        // Name, Description, Google Maps URI, ImageID
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location currentLocation = places.get(position);
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
