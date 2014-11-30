package com.a04.cabpool;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.tyczj.mapnavigator.Navigator;

public class MapsActivity extends AbstractGUIActivity {

	private GoogleMap map;
	private Location myLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_maps);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		// enable "My Location" button on map
		map.setMyLocationEnabled(true);

		// geopoint, zoom
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.260879,
				-79.919225), 15));

		// other supported types include MAP_NORMAL, MAP_TYPE_TERRAIN,
		// MAP_TYPE_HYBRID
		// and MAP_TYPE_NONE
		
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		// get my location
		LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = lm.getBestProvider(criteria, true);
		
		Location location = lm.getLastKnownLocation(provider);
		
		// testing map navigation
		LatLng start = new LatLng(location.getLatitude(), location.getLongitude());
		LatLng end = new LatLng(43.260879, -79.919225);
		
		Navigator nav = new Navigator(map,start,end);
		nav.findDirections(true);
		
		
	}

}
