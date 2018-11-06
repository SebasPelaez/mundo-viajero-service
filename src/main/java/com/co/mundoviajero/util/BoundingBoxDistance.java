package com.co.mundoviajero.util;

/**
 * Esta clase nos permite determinar un radio de N kilometros para una ubicacion
 * especifica del mapa, denotada por Latitud y Longitud.
 * Esta rutina, fue desarrollada por Federico A. Ramponi Official Web site:
 * https://stackoverflow.com/questions/238260/how-to-calculate-the-bounding-box-for-a-given-lat-lng-location/238558#238558
 */
public class BoundingBoxDistance {

	public static class MapPoint {
		private double longitude;
		private double latitude;

		public MapPoint(double latitude, double longitude) {
			this.longitude = longitude;
			this.latitude = latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}
	}

	public static class BoundingBox {
		private MapPoint minPoint;
		private MapPoint maxPoint;

		public BoundingBox(MapPoint minPoint, MapPoint maxPoint) {
			this.minPoint = minPoint;
			this.maxPoint = maxPoint;
		}

		public MapPoint getMinPoint() {
			return minPoint;
		}

		public void setMinPoint(MapPoint minPoint) {
			this.minPoint = minPoint;
		}

		public MapPoint getMaxPoint() {
			return maxPoint;
		}

		public void setMaxPoint(MapPoint maxPoint) {
			this.maxPoint = maxPoint;
		}
	}

	// Semi-axes of WGS-84 geoidal reference
	private static double WGS84_a = 6378137.0; // Major semiaxis [m]
	private static double WGS84_b = 6356752.3; // Minor semiaxis [m]

	// 'halfSideInKm' is the half length of the bounding box you want in kilometers.
	public static BoundingBox GetBoundingBox(MapPoint point, double halfSideInKm) {
		// Bounding box surrounding the point at given coordinates,
		// assuming local approximation of Earth surface as a sphere
		// of radius given by WGS84
		double lat = Deg2rad(point.getLatitude());
		double lon = Deg2rad(point.getLongitude());
		double halfSide = 1000 * halfSideInKm;

		// Radius of Earth at given latitude
		double radius = WGS84EarthRadius(lat);
		// Radius of the parallel at given latitude
		double pradius = radius * Math.cos(lat);

		double latMin = lat - halfSide / radius;
		double latMax = lat + halfSide / radius;
		double lonMin = lon - halfSide / pradius;
		double lonMax = lon + halfSide / pradius;

		MapPoint p1 = new MapPoint(Rad2deg(latMin), Rad2deg(lonMin));
		MapPoint p2 = new MapPoint(Rad2deg(latMax), Rad2deg(lonMax));
		BoundingBox b = new BoundingBox(p1, p2);

		return b;
	}

	// degrees to radians
	private static double Deg2rad(double degrees) {
		return Math.PI * degrees / 180.0;
	}

	// radians to degrees
	private static double Rad2deg(double radians) {
		return 180.0 * radians / Math.PI;
	}

	// Earth radius at a given latitude, according to the WGS-84 ellipsoid [m]
	private static double WGS84EarthRadius(double lat) {
		// http://en.wikipedia.org/wiki/Earth_radius
		double An = WGS84_a * WGS84_a * Math.cos(lat);
		double Bn = WGS84_b * WGS84_b * Math.sin(lat);
		double Ad = WGS84_a * Math.cos(lat);
		double Bd = WGS84_b * Math.sin(lat);
		return Math.sqrt((An * An + Bn * Bn) / (Ad * Ad + Bd * Bd));
	}

}
