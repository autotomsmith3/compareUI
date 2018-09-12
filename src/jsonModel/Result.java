package jsonModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

	@SerializedName("errors")
	@Expose
	private Boolean errors;
	@SerializedName("fuelEconomyList")
	@Expose
	private List<FuelEconomyList> fuelEconomyList = null;

	public Boolean getErrors() {
		return errors;
	}

	public void setErrors(Boolean errors) {
		this.errors = errors;
	}

	public List<FuelEconomyList> getFuelEconomyList() {
		return fuelEconomyList;
	}

	public void setFuelEconomyList(List<FuelEconomyList> fuelEconomyList) {
		this.fuelEconomyList = fuelEconomyList;
	}

}
