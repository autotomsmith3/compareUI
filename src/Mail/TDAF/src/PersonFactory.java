
public class PersonFactory {

	public Person getPerson(String name, String gender){
		if (gender.equalsIgnoreCase("M")){
			return new Male(name);	
		}
		else if(gender.equalsIgnoreCase("F"))
			return new Female(name);
		else
			//return null;
			return new Gay(name);
	}	
	
	
}
