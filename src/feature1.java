

import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class feature1 {
public static void main(String[] args)throws IOException {
	
	//Path currentRelativePath = Paths.get("");
	//String s = currentRelativePath.toAbsolutePath().toString();
	//System.out.println("Current relative path is: " + s);
//
	try{
		BufferedReader br = new BufferedReader(new FileReader("stream_payment.csv")); 
		 //BufferedReader br = new BufferedReader(new FileReader("ExcelSource.csv"));
		//BufferedReader br = new BufferedReader(new FileReader("batch_payment.csv")); 
		
		 String strLine;  
		 String returnValue ; 
		 int ss;
		 String fromBuyer="", 
		 toSeller="";
		 String searchString ="" ;
		 
		 //Read File Line By Line
		 int i=0;  
		 
		 while ((strLine = br.readLine()) != null)   {
			 if (i==0){
				 i++; 
				 continue;
			 }
			 // if (i==10000) break;
		     String[] ar=strLine.split(",");  
		     if (!strLine.contains(", ") )
		    		continue;
		     fromBuyer = ar[1].trim();
		     toSeller = ar[2].trim();
		     returnValue =searchFile(fromBuyer, toSeller);		    	
		     if (returnValue != "trusted")
		    		writeOutput1("Unverified" );
		     if (returnValue == "trusted")
		    		writeOutput1("trusted" );
		 }		      
		 br.close();
		 }catch (Exception e){  e.printStackTrace(); 
		 System.err.println("Erro3r: " + e.getMessage()  );  
		    }finally{ 
		    }
}

public  static String searchFile(String searchWrd1, String searchWrd2) throws FileNotFoundException {
try {
    FileReader fileIn = new FileReader("batch_payment.csv");
    BufferedReader reader = new BufferedReader(fileIn);
    String line;char containsString= 'N';
    while((line = reader.readLine()) != null) {
        if((line.contains(searchWrd1))) {
        //    System.out.println("Number of instances of String " + searchWrd);
        	if ((line.contains(searchWrd2))) {
        		containsString='Y';
        		break;
        	}
        }
    }
    if(containsString == 'Y')
    	return("trusted" );
    return("Unverified" );
}catch (IOException e)	{
    System.out.println(e); return("Unverified" );
						}
}


public static void writeOutput1(String writeLine) throws IOException{
	try{
		FileWriter fileWritter = new FileWriter("output1.txt",true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(writeLine + "\n");
        bufferWritter.close(); 
	   }catch (Exception e) { e.printStackTrace();     
			System.err.println("Error: " + e.getMessage());
	     }finally{ 
	    }
	    
}
}
