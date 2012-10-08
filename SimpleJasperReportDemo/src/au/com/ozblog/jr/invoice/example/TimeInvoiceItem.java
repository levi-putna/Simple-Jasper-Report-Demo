package au.com.ozblog.jr.invoice.example;

public class TimeInvoiceItem extends InvoiceItem {

	@Override
	public String formatUnit(Double unit) {
		return unit.toString() ;
	}

}
