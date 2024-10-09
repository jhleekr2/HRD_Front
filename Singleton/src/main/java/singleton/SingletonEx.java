package singleton;

public class SingletonEx {

	public static void main(String[] args) {

		Singleton_01 s01 = null;

		// 에러, 싱글톤 객체를 외부에서 생성할 수 없다(private 생성자)
//			s01 = new Singleton_01();
//			System.out.println( s01.data );

		s01 = Singleton_01.getInstance();
		System.out.println(s01.data);

		Singleton_01 s02 = Singleton_01.getInstance();
		Singleton_01 s03 = Singleton_01.getInstance();

		System.out.println(s01);
		System.out.println(s02);
		System.out.println(s03);

		System.out.println("-----------------------");

		Singleton_02 s04 = Singleton_02.getInstance();
		Singleton_02 s05 = Singleton_02.getInstance();

		System.out.println(s04);
		System.out.println(s05);

		System.out.println("-----------------------");

		Singleton_03 s06 = Singleton_03.getInstance();
		Singleton_03 s07 = Singleton_03.getInstance();

		System.out.println(s06);
		System.out.println(s07);
	}

}
