package au.com.ozblog.jr.invoice.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "record")
public class InvoiceDao {
	private int invoiceID;
	private Date invoiceDate = null;
	private int customerID;
	
	private List<InvoiceItemDao> items = new ArrayList<InvoiceItemDao>();
}
