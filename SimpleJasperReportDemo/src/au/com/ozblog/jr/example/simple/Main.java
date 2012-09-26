package au.com.ozblog.jr.example.simple;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.swing.JRViewer;

public class Main {

	private JFrame frame;
	private JasperPrint jprint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws JRException 
	 */
	public Main() throws JRException {
		run();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JRViewer viewer = new JRViewer(jprint);
		frame.add(viewer);
	}
	
	private void run() throws JRException {
		generateReport();
		outputPdf();
	}

	private void generateReport() throws JRException {
		long start = System.currentTimeMillis();

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("REPORT_TITLE", "Shipping Order");
		parameters.put("TEST_PARAMETER", "Levi Putna");

		// JasperReport jasperReport =
		JasperReport jasperReport = JasperCompileManager.compileReport("reports/simple.jrxml");
		 jprint = JasperFillManager.fillReport(jasperReport, parameters);
//		jprint = JasperFillManager.fillReport("reports/simple.jasper",
//				parameters);
		System.err.println("Filling time : "
				+ (System.currentTimeMillis() - start));

	}

	private void outputPdf() throws JRException {
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToPdfFile(jprint, "out.pdf");
		System.err.println("PDF creation time : "
				+ (System.currentTimeMillis() - start));
	}

	public void xml() throws JRException {
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToXmlFile(jprint, "out.xml", false);
		System.err.println("XML creation time : "
				+ (System.currentTimeMillis() - start));
	}

	public void html() throws JRException {
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToHtmlFile(jprint, "out.html");
		System.err.println("HTML creation time : "
				+ (System.currentTimeMillis() - start));
	}

	public void xls() throws JRException {
		long start = System.currentTimeMillis();

		Map dateFormats = new HashMap();
		dateFormats.put("EEE, MMM d, yyyy", "ddd, mmm d, yyyy");

		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "out.xls");
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.FORMAT_PATTERNS_MAP,
				dateFormats);

		exporter.exportReport();

		System.err.println("XLS creation time : "
				+ (System.currentTimeMillis() - start));
	}

}
