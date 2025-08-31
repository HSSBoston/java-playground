import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part1 {
    public String countryInfo(CSVParser parser, String country){
        String info = "";
        for (CSVRecord rd : parser){
            String countryName = rd.get("Country");
            if (countryName.equals(country)){
                String exports = rd.get("Exports");
                String value = rd.get("Value (dollars)");
                info = country+": "+exports+": "+value;
                return info;
            }else{
                info = "NOT FOUND";
            }
        }
        return info;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord rd:parser){
            String exports = rd.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)){
                String country = rd.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord rd:parser){
            String exports = rd.get("Exports");
            if (exports.contains(exportItem)){
                count += 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord rd:parser){
            String value = rd.get("Value (dollars)");
            if (value.length() > amount.length()){
                String country = rd.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }
    
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "flowers", "cotton");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }
}
