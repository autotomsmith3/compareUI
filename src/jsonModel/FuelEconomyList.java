package jsonModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuelEconomyList {

	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("city")
	@Expose
	private String city;
	@SerializedName("hwy")
	@Expose
	private String hwy;
	@SerializedName("combined")
	@Expose
	private String combined;
	@SerializedName("mileageUnit")
	@Expose
	private String mileageUnit;
	@SerializedName("range")
	@Expose
	private String range;
	@SerializedName("rangeUnit")
	@Expose
	private String rangeUnit;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHwy() {
		return hwy;
	}

	public void setHwy(String hwy) {
		this.hwy = hwy;
	}

	public String getCombined() {
		return combined;
	}

	public void setCombined(String combined) {
		this.combined = combined;
	}

	public String getMileageUnit() {
		return mileageUnit;
	}

	public void setMileageUnit(String mileageUnit) {
		this.mileageUnit = mileageUnit;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getRangeUnit() {
		return rangeUnit;
	}

	public void setRangeUnit(String rangeUnit) {
		this.rangeUnit = rangeUnit;
	}

}