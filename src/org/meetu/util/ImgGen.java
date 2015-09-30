package org.meetu.util;

import org.meetu.constant.Constant;

public class ImgGen {
	/**
	 * 生成文件名
	 * @param userId
	 * @param resolution 分辨率,高清传HD
	 * @param extName扩展名
	 * */
	public static String genFileName(String userId,String resolution,String extName) {
		String ret = "USER_";
		if(resolution != null && resolution.equalsIgnoreCase("HD")) {
			ret = ret + "HD_";
		}
		ret = ret + userId + "." + extName;
		return ret;
	}
	
	/**
	 * 生成文件硬盘路径
	 * @param resolution分辨率 是否高清 HD
	 * */
	public static String genFileDiskPath(String resolution) {
		String ret = "";
		if(resolution != null && resolution.equalsIgnoreCase("HD")) {
			ret = Constant.PORTRAIT_HD_PATH;
		} else {
			ret = Constant.PORTRAIT_PATH;
		}
		return ret;
	}
}
