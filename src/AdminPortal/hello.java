package AdminPortal;

public class hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean go=true;
		for (int i = 1; i < 60; i++) {
			if ((go) && (i % 5 == 0)) {
				System.out.println("I="+i);
			}
		}
		System.out.println("complete!");
	}

}
