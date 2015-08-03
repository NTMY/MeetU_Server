package org.meetu.util;

import java.util.Arrays;

/**
 * 地理范围工具类
 * */
public class RangeCalculator {

	public static void main(String[] args) {
		double[] around = getSquare(50.000000,100.000000,1000);
		System.out.println(Arrays.toString(around));
	}

	/**
	 * 生成以中心点为中心的四方形经纬度
	 * @param lat 纬度
	 * @param lon 经度
	 * @param raidus 半径（以米为单位）
	 * @return arr[minLat, minLng, maxLat, maxLng]
	 */
	public static double[] getSquare(double lat, double lon, int raidus) {

		Double latitude = lat;
		Double longitude = lon;

		Double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		return new double[] { minLat, minLng, maxLat, maxLng };
	}

}
