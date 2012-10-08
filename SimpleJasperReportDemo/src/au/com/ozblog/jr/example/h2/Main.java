package au.com.ozblog.jr.example.h2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

	/**
	 * @param args
	 * @throws JRException 
	 */
	public static void main(String[] args) throws JRException {
		Record record1 = new Record(1, "Levi", 32);
		Record record2 = new Record(2, "Test", 85);
		Record record3 = new Record(3, "Bob", 345);
		Record record4 = new Record(4, "Sam", 332);
		Record record5 = new Record(5, "tim", 343);
		Record record6 = new Record(6, "will", 32);
		Record record7 = new Record(7, "mic", 432);
		Record record8 = new Record(8, "frank", 279);
		Record record9 = new Record(9, "gim", 66235);

		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List result = session.createQuery("from Record").list();
		for (Record event : (List<Record>) result) {
			System.out.println(event.getName());
		}
		session.getTransaction().commit();
		session.close();
		
		report();
	}

	public static void report() throws JRException {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		Map<String, Object> parameters = getParameters(session);
		
		JasperReport jasperReport = JasperCompileManager
				.compileReport("reports/HibernateReport.jrxml");
		JasperPrint jprint = JasperFillManager.fillReport(jasperReport, parameters);
		JasperExportManager.exportReportToPdfFile(jprint, "h2.pdf");

		transaction.rollback();
		session.close();

	}

	private static Map<String, Object> getParameters(Session session) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(
				JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION,
				session);
		parameters.put("ReportTitle", "Address Report");
		parameters.put("OrderClause", "city");
		return parameters;
	}

}
