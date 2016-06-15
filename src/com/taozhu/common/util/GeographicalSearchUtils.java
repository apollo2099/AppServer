package com.taozhu.common.util;

public class GeographicalSearchUtils {

	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double getDistance(double latA, double lonA, double latB, double lonB) {
		double distance = 0;
		double radLat = rad(latA) - rad(latB);
		double radLon = rad(lonA) - rad(lonB);
		distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(radLat / 2), 2)
				+ Math.cos(rad(latA)) * Math.cos(rad(latB))
				* Math.pow(Math.sin(radLon / 2), 2)));
		distance = distance * EARTH_RADIUS;
		distance = Math.round(distance * 10000);
		distance = distance / 10000;
		return distance;
	}
}
