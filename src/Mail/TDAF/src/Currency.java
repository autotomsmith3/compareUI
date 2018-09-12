
//In the Java programming language, an interface is a reference type, 
//similar to a class, that can contain only constants, method signatures, 
//and nested types. There are no method bodies. Interfaces cannot be 
//instantiated—they can only be implemented by classes or extended by 
//other interfaces.
//implements is for implementing an interface
//The difference between an interface and a regular class is that in an interface 
//you can not specify an specific implementation (only its "interface"). 
//More specific, this means you can only specify methods, but not implement them. 

public interface Currency {
	String getSymbol();
}

//Concrete Rupee Class code
class Rupee implements Currency{
	//@Override
	public String getSymbol(){
		return "Rs";
	}
}

//Concrete SGD class Code
class SGDDollar implements Currency {
	//@Override       
	public String getSymbol() {
		return "SGD";       	
	}
}

//Concrete US Dollar code
class USDollar implements Currency {
	//@Override       
	public String getSymbol() {
		return "USD";       
		}
	}

class ChineseYuan implements Currency{
	//@Override
	public String getSymbol(){
		return "Yuan";
	}
}
class Nosuchcurrency implements Currency{
		//@Override
		public String getSymbol(){
			return "No Such Currency";
		}	
}

