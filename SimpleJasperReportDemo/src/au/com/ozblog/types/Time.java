package au.com.ozblog.types;

import java.math.BigDecimal;

public class Time {
	
	private BigDecimal seconds;
	
	public Time(BigDecimal seconds) {
		super();
		this.seconds = seconds;
	}

	public BigDecimal getSeconds() {
		return seconds;
	}

	public void setSeconds(BigDecimal seconds) {
		this.seconds = seconds;
	}

}
