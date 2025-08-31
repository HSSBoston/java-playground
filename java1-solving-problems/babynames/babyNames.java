import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class babyNames {
    public void readOneFile(int year){
        String fname = "data/yob" + year + "txt.";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser){
            String name = rec.get(0);
            String gender = rec.get(1);
            String numBorn = rec.get(2);
        }
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int numOfGirlNames = 0;
        int numOfBoyNames = 0;
        int numOfNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            numOfNames += 1;
            if (rec.get(1).equals("F")){
                numOfGirlNames += 1;
            }else{
                numOfBoyNames += 1;
            }
        }
        
        System.out.println("total births: " + totalBirths + "\n" +
                            "number of girl names: " + numOfGirlNames + "\n" +
                            "number of boy names: " + numOfBoyNames + "\n" +
                            "number of names: " + numOfNames);
    }
    
    public int getRank(String name, String gender, FileResource fr){
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                rank += 1;
                if (rec.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int rank, String gender, FileResource fr){
        int rankCount = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                rankCount += 1;
                if (rankCount == rank){
                    return rec.get(0);
                }else{
                    continue;
                }
            }else{
                break;
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int Year, int newYear, String gender){
        FileResource fr = new FileResource();
        FileResource frNew = new FileResource();
        
        int rank = getRank(name, gender, fr);
        String newName = getName(rank, gender, frNew);
        
        System.out.println(name+" born in "+Year+" would be "+newName+" if born in "+newYear);
    }
    
    public int yearOfHighestRank(String name, String gender){
        int rank = 0;
        int year = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int currentRank = getRank(name, gender,fr);
            if (currentRank < rank){
                String fileName = f.getName();
                int currentYear = Integer.parseInt(fileName.substring(3,7));
                rank = currentRank;
                year = currentYear;
            }
        }
        if (rank == 0){
            return -1;
        }else{
            return year;
        }
    }
    
    public double getAverageRank(String name, String gender){
        int rankSum = 0;
        int num = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int currentRank = getRank(name, gender,fr);
            if (currentRank == -1){
                continue;
            }else{
                rankSum += currentRank;
                num += 1;
            }
        }
        if (num == 0){
            return -1.0;
        }else{
            return (double) rankSum / num;
        }
    }
    
    public int getTotalBirthsRankedHigher(String name, String gender, FileResource fr){
        int totalBirths = 0;
        int rankOfName = getRank(name, gender,fr);
        if (rankOfName == -1){
            return -1;
        }else{
            for (CSVRecord rec : fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    int currentRank = getRank(rec.get(0),gender,fr);
                    if(currentRank < rankOfName){
                        totalBirths += Integer.parseInt(rec.get(2));
                    }
                }
            }
        }
        return totalBirths;        
    }
    
    public void test(){
        FileResource fr = new FileResource();
        //totalBirths(fr);
        //System.out.println("Rank: " + getRank("Mason","M",fr));
        //System.out.println("Name of rank 6 for girls in 2012 is " + getName(6, "F", fr));
        //whatIsNameInYear("Isabella", 2012, 2014, "F");
        //System.out.println(yearOfHighestRank("Mason", "M"));
        //System.out.println(getAverageRank("Mason", "M"));
        System.out.println(getTotalBirthsRankedHigher("Ethan", "M", fr));
    }
}
