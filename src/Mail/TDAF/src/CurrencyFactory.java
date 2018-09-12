
public class CurrencyFactory {
	public static Currency createCurrency (String country){
		if (country.equalsIgnoreCase("India")){
			return new Rupee();
		}else if (country.equalsIgnoreCase("Singapore")){
			return new SGDDollar();
		}else if (country.equalsIgnoreCase("US")){
			return new USDollar();
		}else if (country.equalsIgnoreCase("Chinese")){
			return new ChineseYuan();
		}
		return new Nosuchcurrency();
		//throw new IllegalArgumentException("No such currency");
	}

}
