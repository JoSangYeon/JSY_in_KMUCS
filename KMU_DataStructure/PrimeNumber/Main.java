package PrimeNumber;

public class Main
{
	public static void main(String[] args)
	{
		Eratos e1 = new Eratos(10);
		Eratos e2 = new Eratos(50);
		Eratos e3 = new Eratos(100);
		e1.show();
		e2.show();
		e3.show();
	}
}
/*
[ 2 3 5 7 ]
[ 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 ]
[ 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 ]
*/