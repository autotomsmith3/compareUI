public class Factory{
	public static String countryName;
	public static void main(String args[]){
		//String country=args[0];
		String country="us";
		countryName="chinese";
		//Currency rupee=CurrencyFactory.createCurrency(country);
		Currency money;
		money=CurrencyFactory.createCurrency(country);
		System.out.println(countryName+"  "+money.getSymbol());		
		money=CurrencyFactory.createCurrency(countryName);
			System.out.println(countryName+"  "+money.getSymbol());
		country="1india";
		money=CurrencyFactory.createCurrency(country);
			System.out.println(country+"  "+money.getSymbol());
		country="chinese";
		money=CurrencyFactory.createCurrency(country);
			System.out.println(country+"  "+money.getSymbol());
		country="others";	
		money=CurrencyFactory.createCurrency(country);
			System.out.println(country+"  "+money.getSymbol());
		country="India";	
		money=CurrencyFactory.createCurrency(country);
			System.out.println(country+"  "+money.getSymbol());
	}	
}