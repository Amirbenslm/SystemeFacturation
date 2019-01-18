package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class JasperClass {

	private String pathfile;

	public JasperClass(ArrayList<Article> data, String document, Map<String, Object> parameters, List<TableauTaxe> listeTaxes, boolean etatFactureNouveau) {
		try {
			
			 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ");
		 		Date date = new Date();
            
         
            Map<String, Object> tableauxJSPR=new HashMap<String, Object>();

       
            /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(data);
            JRBeanCollectionDataSource taxesJRBean = new JRBeanCollectionDataSource(listeTaxes);
            
            List<Map<String,?>> dataSource=new ArrayList<Map<String,?>>();
            
           
            tableauxJSPR.put("ItemDataSource",itemsJRBean);
           tableauxJSPR.put("TaxeDataSource",taxesJRBean );
            parameters.put("date",dateFormat.format(date));
          
          
            dataSource.add(parameters);
           
			JRDataSource jrdatasource=new JRBeanCollectionDataSource(dataSource);
			
            /* Using compiled version(.jasper) of Jasper report to generate PDF */
        
		/*	JasperReport jasperreport=JasperCompileManager.compileReport("application/src/application/a2.jrxml");
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperreport,null,jrdatasource);
			 JasperViewer.viewReport(jasperPrint,false);*/
            JasperDesign jasperDesign = JRXmlLoader.load("SystemeFacturation/configuration/a2.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			
			//JasperReport jasperReport = (JasperReport)JRLoader.loadObjectFromFile("C:\\SystemeFacturation\\configuration\\a2.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,tableauxJSPR, jrdatasource);
            
           
            
            /* outputStream to create PDF */
           // OutputStream outputStream = new FileOutputStream(new File(outputFile));
            if(etatFactureNouveau==true){
         pathfile="SystemeFacturation/"+document+"/"+dateFormat.format(date)+"#"+parameters.get("num_fac")+".pdf";}
            else{pathfile="SystemeFacturation/"+document+"/"+dateFormat.format(date)+"#A"+parameters.get("num_fac")+".pdf";
            	
            }
           JasperExportManager.exportReportToPdfFile(jasperPrint,pathfile);
           Desktop d = Desktop.getDesktop();
   		
			d.open(new File(pathfile));
           //JasperViewer.viewReport(jasperPrint,false);
           /* Write content to PDF file */
           // JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

           
        } catch (JRException ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);

            ex.printStackTrace();
        } /*catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }*/ catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}
	}
	

}
