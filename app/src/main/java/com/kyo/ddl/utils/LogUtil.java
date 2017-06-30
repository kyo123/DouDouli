package com.kyo.ddl.utils;

import android.util.Log;

/**
 * LEVEL = VERBOSE时打印所有调试信息
 * 当项目上线时，改为 LEVEL = NOTHING
 * 关闭所有打印信息
 * @author 81091
 *
 */
public class LogUtil {
	public static final int VERBOSE = 1;
	public static final int DEBUG = 2;
	public static final int INFO = 3;
	public static final int WARN = 4;
	public static final int ERROR = 5;
	public static final int NOTHING = 6;
	public static final int LEVEL = VERBOSE;//打印所有等级的信息
//	public static boolean isOpen=true;
	
	public static void v(String tag,String msg){
		if(LEVEL <= VERBOSE){
//			if(!isOpen)
				Log.v(tag,msg);
		}
	} 
	public static void d(String tag,String msg){
		if(LEVEL <= DEBUG){
//			if(isOpen)
				Log.d(tag,msg);
		}
	} 
	public static void i(String tag,String msg){
		if(LEVEL <= INFO){
//			if(!isOpen)
				Log.i(tag,msg);
		}
	} 
	public static void w(String tag,String msg){
		if(LEVEL <= WARN){
//			if(!isOpen)
				Log.w(tag,msg);
		}
	}

	public static void e(String tag,String msg){
		//13859186253
		if(LEVEL <= ERROR){
//			if(!isOpen)
				Log.e(tag,msg);
		}
	} 

}
