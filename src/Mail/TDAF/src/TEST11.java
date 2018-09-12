
public class TEST11 {   
    public static String name;
    private static String gender;
    public String getName(){
    	return name;
    }
    public String getGender(String gender){
    	return gender;
    }
    
    public static void main(String arg[]){
    	String bName;
    	String cName;
    	name="Lucas";
    	gender="M";
    	TEST11 aa= new TEST11();
    	bName=aa.getGender(gender);
    	cName=aa.getName();
    	System.out.println(bName);
    	
    }
    
} 