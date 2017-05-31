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
 * Created by admin on 30-06-2016.
 */
public class PlacesFragment extends android.support.v4.app.Fragment {
    public PlacesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_list, container, false);

        //Places list
        final ArrayList<Location> places = new ArrayList<Location>();
        places.addAll(Arrays.asList(
                 new Location("Red Fort","Delhi's most famous monument, the Red Fort, stands as a powerful reminder of the Mughal emperors who ruled India. Its walls, which stretch for over two kilometers (1.2 miles), were built in 1638 to keep out invaders. However, they failed to stop the fort being captured by the Sikhs and the British. To take your imagination back to the ancient era, a one hour sound and light show of the fort's history is held each evening.",28.6561639,77.2388316,R.drawable.red_fort)
                ,new Location("Jama Masjid", "Jama Masjid is another marvelous treasure of the Old City, and is the largest mosque in India. Its courtyard can hold an incredible 25,000 devotees. The mosque took 13 years to build, and was completed in 1650. A strenuous climb to the top of its southern tower will reward you with a stunning view across the rooftops of Delhi. Be sure to dress appropriately when visiting the mosque or you won't be allowed in. This means covering your head, legs and shoulders. Attire is available there.", 28.6506839,77.2312534, R.drawable.jama_masjid)
                ,new Location("AksharDham", "A relatively new attraction, this massive temple complex was built by the BAPS Swaminarayan Sanstha spiritual organization and opened in 2005. It's dedicated to showcasing Indian culture. As well as the astonishing architecture of the pink stone and white marble shrine, the complex includes sprawling garden, sculptures, and boat ride. Allow plenty of time to explore it thoroughly -- at least half a day. Do note that cell phones and cameras are not permitted inside.",28.6126782,77.2750732, R.drawable.akshardham)
                ,new Location("Qutub Minar", "Qutub Minar, the tallest brick minaret in the world, is an incredible example of early Indo–Islamic architecture. It was built in 1206, but the reason remains a mystery. Some believe that it was made to signify victory and the beginning of Muslim rule in India, while others say it was used to call the faithful to prayer. The tower has five distinct stories, and is covered with intricate carvings and verses from the holy Quran. There are also a number of other historic monuments on the site.",28.5244328,77.1832672, R.drawable.qutub_minar)
                ,new Location("Gandhi Smriti", "A visit to Gandhi Smriti will show you the exact spot where Mahatma Gandhi, affectionately referred to as the Father of the Nation, was assassinated on January 30, 1948. He lived in the house for 144 days up until the time of his death. The room that he slept in, kept exactly how he left it, and the prayer ground where he held a mass congregation every evening are both open to the public. Plenty of photos, sculptures, paintings, and inscriptions are also on display. You can also visit his memorial at Raj Ghat.", 28.6017747,77.2121506, R.drawable.gandhi_smriti)
                ,new Location("India Gate", "The towering archway of India Gate at the center of New Delhi is a war memorial, built in memory of the Indian soldiers who lost their lives fighting for the British Army in World War I. At night it glows warmly under floodlights, and the gardens that line its boulevard are a popular place to enjoy a warm summer's evening.",28.6129167,77.227321, R.drawable.india_gate)
                ,new Location("Lotus Temple", "The Bahai Temple is commonly referred to as the Lotus Temple, as it's shaped like a lotus flower. It's particularly pretty at night, when it's attractively lit up. Made out of white marble, the temple belongs to the Bahai Faith, which proclaims the unity of all people and religions. Everybody is welcome to worship there. The tranquil gardens and ponds surrounding the temple are also a great place for a relaxing picnic",28.5534967,77.2566377, R.drawable.lotus_temple)
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
