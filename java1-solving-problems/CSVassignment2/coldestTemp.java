import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class coldestTemp {
    
    public CSVRecord lowest(CSVRecord currentRow, CSVRecord lowestSoFar,String column){
        if (lowestSoFar == null){
                lowestSoFar = currentRow;
        }else{
            if (currentRow.get(column).equals("N/A")){
                    lowestSoFar = lowestSoFar;
            }else{
                double current = Double.parseDouble(currentRow.get(column));
                double lowest = Double.parseDouble(lowestSoFar.get(column));
                if (current < -100){
                    lowestSoFar = lowestSoFar;
                }else if(current < lowest){
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        
        for (CSVRecord currentRow : parser){
            lowestSoFar = lowest(currentRow, lowestSoFar,"TemperatureF");
        }
        return lowestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("The lowest temp was "+lowest.get("TemperatureF")+" at "+lowest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature(){
        CSVRecord lowestSoFar = null;
        String lowestFile = "";
        String filePath = "";
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            lowestSoFar = lowest(currentRow, lowestSoFar,"TemperatureF");
            if (currentRow.get("TemperatureF") == lowestSoFar.get("TemperatureF")){
                lowestFile = f.getPath();
            }
        }
        return lowestFile;
    }
    
    public void testFileWithColdestTemperature(){
        String filePath = fileWithColdestTemperature();
        File file = new File(filePath);
        String fileName = file.getName();
        FileResource fr = new FileResource(file);
        
        System.out.println("Coldest day was in file "+fileName);
        System.out.println("Coldest temperature on that day was "+coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day:");
        for (CSVRecord row : fr.getCSVParser()){
            System.out.println(row.get("DateUTC")+": " +row.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        
        for (CSVRecord currentRow : parser){
            lowestSoFar = lowest(currentRow, lowestSoFar, "Humidity");
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = lowest(currentRow, lowestSoFar, "Humidity");
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        double count = 0;
        for (CSVRecord currentRow : parser){
            double temp = Double.parseDouble(currentRow.get("TemperatureF"));
            if(temp < -100){
                continue;
            }
            sum +=temp;
            count += 1;
        }
        return sum/count;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is "+averageTemperatureInFile(parser));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0;
        double count = 0;
        for (CSVRecord currentRow : parser){
            double temp = Double.parseDouble(currentRow.get("TemperatureF"));
            double humidity = Double.parseDouble(currentRow.get("Humidity"));
            if(temp < -100){
                continue;
            }
            
            if(humidity <= (double)value){
                continue;
            }
            sum +=temp;
            count += 1;
        }
        if (count == 0){
            return 0;
        }
        return sum/count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average1 = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (average1 == 0){
            System.out.println("No temperatures with that humidity");
        }else{
            System.out.println("Average temp when high humidity is "+average1);
        }
    }
}



