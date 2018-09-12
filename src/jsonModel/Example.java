package jsonModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("serverTime")
	@Expose
	private String serverTime;
	@SerializedName("error")
	@Expose
	private Boolean error;
	@SerializedName("executionTimeMS")
	@Expose
	private Integer executionTimeMS;
	@SerializedName("result")
	@Expose
	private Result result;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServerTime() {
		return serverTime;
	}

	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public Integer getExecutionTimeMS() {
		return executionTimeMS;
	}

	public void setExecutionTimeMS(Integer executionTimeMS) {
		this.executionTimeMS = executionTimeMS;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
