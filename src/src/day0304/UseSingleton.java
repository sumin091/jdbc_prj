package day0304;

public class UseSingleton {

	public static void main(String[] args) {
		
//		Singleton single= new UseSingleton();
		Singleton single= Singleton.getInstance();
		Singleton single2= Singleton.getInstance();
		Singleton single3= Singleton.getInstance();
		
		System.out.println(single);
		System.out.println(single2);
		System.out.println(single3);
		
	}//main

}//class
