package au.com.ozblog.jr.invoice.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * For Australian tax requirement see,
 * http://www.ato.gov.au/businesses/content.aspx
 * ?doc=/content/00221985.htm&page=14
 * 
 * @author levip
 * 
 */


public class Invoice {
	private int invoiceID;
	private Date invoiceDate = null;
	private int customerID;

	private List<InvoiceItem> items = new ArrayList<InvoiceItem>();

	public Invoice(int invoiceID, Date invoiceDate, int customerID) {
		this.invoiceID = invoiceID;
		this.invoiceDate = invoiceDate;
		this.customerID = customerID;
	}

	public Invoice(int invoiceID, Date invoiceDate, int customerID,
			List<InvoiceItem> items) {
		super();
		this.invoiceID = invoiceID;
		this.invoiceDate = invoiceDate;
		this.customerID = customerID;
		this.items = items;
	}

	public int getInvoiceID() {
		return invoiceID;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public int getCustomerID() {
		return customerID;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

	/**
	 * Adds an item to the invoice.
	 * 
	 * @param item
	 *            the item to add
	 */
	public void addItem(InvoiceItem item) {
		items.add(item);
	}

}
