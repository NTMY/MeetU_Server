package org.meetu.util;

import java.util.Arrays;

/**
 * 地理范围工具类
 * */
public class RangeCalculator {

    private static final double PI = 3.14159265;  
    private static final double EARTH_RADIUS = 6378137;  
    private static final double RAD = Math.PI / 180.0;  
	
     
    //24,901/360 = 69.17 miles / degree  
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
		//The circumference of the earth is 24,901 miles. 
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

	
	
    /** 
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米 
     * @param lng1 
     * @param lat1 
     * @param lng2 
     * @param lat2 
     * @return 
     */  
    public static double getDistance(double lng1, double lat1, double lng2, double lat2)  
    {  
       double radLat1 = lat1*RAD;  
       double radLat2 = lat2*RAD;  
       double a = radLat1 - radLat2;  
       double b = (lng1 - lng2)*RAD;  
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +  
        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
       s = s * EARTH_RADIUS;  
       s = Math.round(s * 10000) / 10000;  
       return s;  
    }  
	
    
	public static void main(String[] args) {
		double[] around = getSquare(50.000000,100.000000,1000);
		System.out.println(Arrays.toString(around));
	}
    
}
