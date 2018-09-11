package Cassandra;

import java.security.SecureRandom;

public class GenerateToken {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[40];
		random.nextBytes(bytes);
		String token = bytes.toString();
		System.out.println("Token is: "+token);
	}

}
