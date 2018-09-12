
/*
public class Person {   
    public String name;
    private String gender;
    public String getName(){
    	return name;
    }
    public String getGender(){
    	return gender;
    }
}


public class Male extends Person{
  	public Male(String fullName) {
  		System.out.println("Hello Mr. "+fullName);
  	}
}

public class Female extends Person{
	public Female (String fullName){
		System.out.println("Hello Ms. "+fullName);
	}
}

public class Gay extends Person {
	public Gay (String fullName){
		System.out.println("Hello, "+fullName+" are you gay?");
	}
}

*/

public class SalutationFactory {

    public static void main(String [] args){
    	String employee ="Dev";
    	String maden="M";
    	PersonFactory factory=new PersonFactory();
		//factory.getPerson(args[0],args[1]);
		factory.getPerson("Luke", "M");
		factory.getPerson("Lucy", "f");
		factory.getPerson("Jason", "g");
		factory.getPerson(employee, maden);
	}
   
/*	public Person getPerson(String name, String gender){
		if (gender.equalsIgnoreCase("M"))
			return new Male(name);
		else if(gender.equalsIgnoreCase("F"))
			return new Female(name);
		else
			//return null;
			return new Gay(name);
	}
	*/
}

