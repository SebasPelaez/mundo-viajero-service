package com.co.mundoviajero.util;

import junit.framework.TestCase;
import com.co.mundoviajero.util.BoundingBoxDistance.BoundingBox;
import com.co.mundoviajero.util.BoundingBoxDistance.MapPoint;

public class BoundingBoxDistanceTest extends TestCase {

	public void testGetBoundingBox() {
		MapPoint mp1 = new MapPoint(6.2678539, -75.5690221);
		MapPoint mpCompare = new MapPoint(6.18461, -75.59913);
		double halfSideInKm = 20;
		
		BoundingBox boundingBox = BoundingBoxDistance.GetBoundingBox(mp1, halfSideInKm);
				
		assertTrue(mpCompare.getLatitude() >= boundingBox.getMinPoint().getLatitude());
		assertTrue(mpCompare.getLatitude() <= boundingBox.getMaxPoint().getLatitude());
		assertTrue(mpCompare.getLongitude() >= boundingBox.getMinPoint().getLongitude());
		assertTrue(mpCompare.getLongitude() <= boundingBox.getMaxPoint().getLongitude());
	}

}
