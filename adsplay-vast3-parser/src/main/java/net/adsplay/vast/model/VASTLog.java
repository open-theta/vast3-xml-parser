package net.adsplay.vast.model;

public class VASTLog {
	public static void d(String tag, Object o){
		System.out.println(tag + " : " + o);
	}
	public static void i(String tag, Object o){
		System.out.println(tag + " : " + o);
	}
	public static void v(String tag, Object o){
		System.out.println(tag + " : " + o);
	}
	public static void w(String tag, Object o){
		System.out.println(tag + " : " + o);
	}
	public static void e(String tag, Object o){
		System.err.println(tag + " : " + o);
	}
	public static void e(String tag, Object o, Throwable e){
		System.err.println(tag + " : " + o + " : " + e.getMessage());
		e.printStackTrace();		
	}
}
