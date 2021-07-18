package Tools;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static ExtentReports report;
	
	public static ExtentReports createExtentReport(String name) {//creating extent report
		
				//Declaring path of the folder where the report is to be stored
				String path=System.getProperty("user.dir")+"\\Extent Report\\"+name+".html";
				
				//Creating reporter
				ExtentSparkReporter reporter=new ExtentSparkReporter(path);
				reporter.config().setReportName(name+" Report");
				reporter.config().setDocumentTitle("Extent Report");
				
				//creating report
				report=new ExtentReports();
				report.attachReporter(reporter);
				report.setSystemInfo("Tester", "GenC Trainees: Bug Hunters");
				
				return report;
				
	}
}
