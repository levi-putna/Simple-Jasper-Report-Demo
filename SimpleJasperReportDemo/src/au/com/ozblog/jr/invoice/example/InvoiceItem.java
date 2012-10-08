package au.com.ozblog.jr.invoice.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class InvoiceItem {

	private int invoiceItem;
	private String description;
	private Money pricePerUnit;
	private BigDecimal unit;
	private double taxRate;

	public InvoiceItem() {
		// for hibernate
	}

	public InvoiceItem(int invoiceItem, String description) {
		super();
		this.invoiceItem = invoiceItem;
		this.description = description;
	}

	public String formatUnit(Double unit) {
		return unit.toString();
	}

	public Money getTotal() {
		return getTotalBeforeTax().plus(getTax());
	}

	public Money getTax() {
		return getTotalBeforeTax().percent(taxRate);
	}

	public Money getTotalBeforeTax() {
		return new Money(unit.multiply(pricePerUnit.getAmount()),
				pricePerUnit.getCurrency());
	}

	public BigDecimal getUnit() {
		return unit;
	}

	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}

	public Money getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Money pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public int getInvoiceItem() {
		return invoiceItem;
	}

	public String getDescription() {
		return description;
	}

	public void setInvoiceItem(int invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
