package com.utilites;

import java.io.FileWriter;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CSVConverterReport implements ITestListener {
		  private static final String FILE_NAME = ".//csvReport/test-report.csv";
		  private FileWriter writer;
		  
		  public void onStart(ITestContext context) {
			  try {
				  writer = new FileWriter(FILE_NAME);
				  writer.write("TestName,Status,ExecutionTime(ms),Description\n");
				  
			  }catch(IOException e) {
				  e.printStackTrace();
			  }
		  }
		  
		  public void onTestSuccess(ITestResult result) {
			  writeResult(result,"PASSED");
		  }
		  public void onTestFailure(ITestResult result) {
			  writeResult(result,"FAILED");
		  }
		  public void onTestSkipped(ITestResult result) {
			  writeResult(result,"SKIPPED");
		  }
		  public void writeResult(ITestResult result,String status) {
			  try {
				  String testName = result.getMethod().getMethodName();
				  long duration = result.getEndMillis() - result.getStartMillis();
				  String description = result.getMethod().getDescription();
				  
				  writer.write(testName+","+ status + ","+ duration+","+description+"\n");
			  }catch(IOException e) {
				  e.printStackTrace();
			  }
		  }
		  
		  public void onFinish(ITestContext context) {
			  try {
				  if(writer !=null) {
					  writer.close();
				  }
			  }catch(IOException e) {
				  e.printStackTrace();
		  }
	
		  }
	}
		
 
