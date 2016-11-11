

//import java.util.Scanner;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.PrintWriter;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException;
//import java.rmi.RemoteException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class feature2 {
public static void main(String[] args)throws IOException {
	
	//Path currentRelativePath = Paths.get("");
	//String s = currentRelativePath.toAbsolutePath().toString();
	//System.out.println("Current relative path is: " + s);

	try{
		BufferedReader br = new BufferedReader(new FileReader("stream_payment.csv")); 
		 //BufferedReader br = new BufferedReader(new FileReader("ExcelRead.csv"));
		//BufferedReader br = new BufferedReader(new FileReader("batch_payment.csv")); 
		
		 String strLine;  
		 String returnValue ; 
		 int ss;
		 String fromBuyer="", 
		 toSeller="";
		 String searchString ="" ;
		 
		 //Read File Line By Line
		 int i=0;  String secondReturn ="";
 
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
		     if (returnValue == "trusted")
		    		writeOutput1("trusted" );	    	
		     if (returnValue != "trusted"){
		    	 System.out.println(fromBuyer +" " + toSeller);
		    	 	secondReturn = checkSecondDegreeFriend(fromBuyer, toSeller);
			    	 System.out.println("SecondReturn " + secondReturn);
		    	 	if (secondReturn == "trusted")
		    	 		writeOutput1("trusted-2" );
		    	 	else
		    	 		writeOutput1("Unverified" );
		     }
		 }		      
		 br.close();
		 }catch (Exception e){  e.printStackTrace(); 
		 System.err.println("Erro3r: " + e.getMessage()  );  
		    }finally{ 
		    }
}


public  static String checkSecondDegreeFriend(String searchWrd1, String searchWrd2) throws FileNotFoundException 
{  
	try {String secondWord= "NEW"; String fromBuyer=""; String toSeller="";
	    FileReader fileIn = new FileReader("batch_payment.csv");
		//FileReader fileIn = new FileReader("ExcelSource.csv");
	    BufferedReader reader = new BufferedReader(fileIn);
	    String line;char containsString= 'N';
	    while((line = reader.readLine()) != null) {
		     String[] ar=line.split(",");  
		    // System.out.println(line);
		     if (!line.contains(", ")  || line.contains("id2, amount"))
		    		continue;
		     fromBuyer = ar[1].trim();
		     toSeller = ar[2].trim(); 
		    // System.out.println(fromBuyer +" "+ toSeller);
		     if (secondWord.equals("NEW")) { 
		    	// System.out.println("inside");
			     if (fromBuyer ==searchWrd1  || fromBuyer == searchWrd2){
			    	 secondWord = toSeller;
			     }
			     else 
			     if (toSeller ==searchWrd1   ||  toSeller ==searchWrd2 ){
			    	 secondWord = fromBuyer;
			     }
		     }
		     else 
		    	// System.out.println("now");
		    	 if (secondWord == fromBuyer || secondWord == toSeller){
		    		 containsString='Y';  
		        		break;
		    	 }
		}
	   // System.out.println("Done");
	    reader.close();fileIn.close();
	    if(containsString == 'Y')
	    	return("trusted" );
	    return("Unverified" );
	}catch (IOException e)	{
	    System.out.println(e); return("Unverified" );
							}
}


public  static String searchFile(String searchWrd1, String searchWrd2) throws FileNotFoundException {
try {
    FileReader fileIn = new FileReader("batch_payment.csv");
	//FileReader fileIn = new FileReader("ExcelSource.csv");
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
    }   reader.close();fileIn.close();
    if(containsString == 'Y')
    	return("trusted" );
    return("Unverified" );
}catch (IOException e)	{
    System.out.println(e); return("Unverified" );
						}
}


public static void writeOutput1(String writeLine) throws IOException{
	try{
		FileWriter fileWritter = new FileWriter("output2.txt",true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(writeLine + "\n");
        bufferWritter.close(); 
	   }catch (Exception e) { e.printStackTrace();     
			System.err.println("Error: " + e.getMessage());
	     }finally{ 
	    }
	    
}

}
