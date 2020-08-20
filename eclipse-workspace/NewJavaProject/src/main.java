import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String args[]) {
		readFile();
	}
	
	public static void readFile() {
		ArrayList<String> resultList=new ArrayList<String>();
		 try {
		      File myObj = new File("C:\\Users\\Rinjin Lama\\Desktop\\Projects\\Prop.txt");
		      Scanner myReader = new Scanner(myObj);
		      int counter=0;
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
			    String dataParse[]=data.split("\\|");
			    calculation(dataParse,counter,resultList);
			    counter++;
		      }
		      myReader.close();
			    
		      writeFile(resultList);
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		 
	}
	public static void calculation(String[] dataParse,int counter, ArrayList<String> resultList) {
		
		if(counter==0) {
			 resultList.add(String.format("%s %s %s %s %s %s %s %s %s %s %s %s", dataParse[0],
					  dataParse[1], 
					  dataParse[2], 
					  dataParse[3], 
					  dataParse[4],
					  dataParse[5], 
					  dataParse[6],
					  dataParse[7], 
					  dataParse[8],
					  dataParse[9],
					  dataParse[10],
					  dataParse[11],
					  dataParse[12]));	
		}else {
			
			String netIncome=netIncome(dataParse[8],dataParse[7]);
			String taxes=getTaxes(netIncome);
			String profitAfterTax=getProfitAfterTax(netIncome,taxes);
			String percentageGained=getPercentageGained(profitAfterTax,dataParse[7]);
			
			NumberFormat formatter = NumberFormat.getCurrencyInstance();

			resultList.add(String.format("%s %s %s %s %s %s %s %s %s %s %s %s %s", dataParse[0],
					  dataParse[1], 
					  dataParse[2], 
					  dataParse[3], 
					  dataParse[4],
					  dataParse[5], 
					  dataParse[6],
					  dataParse[7], 
					  dataParse[8],
					  netIncome,
					  taxes,
					  profitAfterTax,
					  percentageGained
					 ));	
		}
		
		
	}
	
	public static String netIncome(String priceSold,String totalCost) {
		
		double dPriceSold=Double.parseDouble(priceSold.replace("\"", "").replace(",",""));
		double dTotalCost=Double.parseDouble(totalCost.replace("\"", "").replace(",",""));
	    DecimalFormat df2 = new DecimalFormat("#.##");
		double netIncome=dPriceSold-dTotalCost;
		return String.valueOf(String.valueOf(df2.format(netIncome)));
	}

	
	public static String getTaxes(String netIncome) {
		
		double dnetIncome=Double.parseDouble(netIncome);
		double taxes=dnetIncome * 35/100;
	    DecimalFormat df2 = new DecimalFormat("#.##");
		return String.valueOf(df2.format(taxes));
	}
	
	public static String getProfitAfterTax(String netIncome,String tax) {
		
		double dnetIncome=Double.parseDouble(netIncome);
		double dtax=Double.parseDouble(tax);
	    DecimalFormat df2 = new DecimalFormat("#.##");
		return String.valueOf(df2.format(dnetIncome - dtax));
	}
	
	public static String getPercentageGained(String profitAfterTax,String totalCost) {
		
		double dprofitAfterTax=Double.parseDouble(profitAfterTax);
		double dtotalCost=Double.parseDouble(totalCost.replace("\"", "").replace(",",""));
	    DecimalFormat df2 = new DecimalFormat("#.##");
		return String.valueOf(df2.format(dprofitAfterTax/dtotalCost *100) + "%");
	}
	

	
	public static void writeFile(List<String> resultList) {
		
		 try {
			 BufferedWriter  myWriter = new BufferedWriter(new FileWriter("C:\\Users\\Rinjin Lama\\Desktop\\Projects\\Answer.txt"));
		      for (String result : resultList) {
		    	  myWriter.write(result);
		    	  myWriter.newLine();
			   }
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
	
	
}
