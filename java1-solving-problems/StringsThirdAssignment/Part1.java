import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while (currIndex != -1){
            if ((currIndex - startIndex)%3==0){
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return -1;
    }
    
    public void testFindStopCodon(){
        System.out.println(findStopCodon("ATGTAGACTAATAGATAA",0,"TAA")); // 15
        System.out.println(findStopCodon("AATGTAA",1,"TAA")); // 4
        System.out.println(findStopCodon("ATGATAA",0,"TAA"));// 7
        System.out.println(findStopCodon("ATG",0,"TAA"));
        System.out.println(findStopCodon("",0,"TAA")); // 0
    }
    
    public String findGene(String dna, int start){
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG", start);
        if (startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG"); 
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }
        
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public void testFindGene(){
        System.out.println(findGene("ATGGATCTGCAATAA",0));
        System.out.println(findGene("AAATGTCGATAGTGACCTAACTAGCTAG",0));
        System.out.println(findGene("AAATAGATAATACCC",0));
        System.out.println(findGene("ATG",0));
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            count = count+1;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        System.out.println(count);
    }
     
    public void testAllGenes(){
        //printAllGenes("AAATGTTGCTAACGGGGCTAAGCTGCCTGATGTAAGCATGTAAAAGTATGCGTTTATAGCG");
        FileResource fr1 = new FileResource("GRch38dnapart.fa");
        String dna1 = fr1.asString();
        printAllGenes(dna1);
    }
    
    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        while(true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
    
    public void testGetAllGenes(){
        //StorageResource genes = getAllGenes("AAATGTTGCTAACGGGGCTAAGCTGCCTGATGTAAGCATGTAAAAGTATGCGTTTATAGCG");
        
        
    }
    
    public double cgRatio(String dna){
        dna = dna.toLowerCase();
        String c = "c";
        String g = "g";
        
        double count = 0;
        int startC = 0;
        int startG = 0;
        while(true){
            int currentIndexC = dna.indexOf(c,startC);
            if (currentIndexC == -1){
                while(true){
                    int currentIndexG = dna.indexOf(g,startG);
                    if (currentIndexG == -1){
                        break;
                    }
                    count = count + 1;
                    startG = currentIndexG + 1;
                }
                break;
            }
            count = count + 1;
            startC = currentIndexC + 1;
        }
        
        return count/ dna.length();
    }
    
    public int countCTG(String dna){
        int count = 0;
        int start = 0;
        while(true){
            int currentIndex = dna.indexOf("CTG", start);
            if (currentIndex == -1){
                break;
            }
            count = count+1;
            start = currentIndex + 3;
        }
        return count;
    }
    
    public void testCountCtg(){
        FileResource fr1 = new FileResource("GRch38dnapart.fa");
        String dna1 = fr1.asString();
        System.out.println(countCTG(dna1));
    }
    
    public void processGenes(StorageResource sr){
        int count9 = 0;
        int countCG = 0;
        String longest = "";
        for (String string : sr.data()){
            if (string.length() > 60){
                System.out.println(string+" is longer than 60 characters");
                count9 = count9 + 1;
            }
            if (cgRatio(string) > 0.35){
                System.out.println(string+" has a C-G ratio higher than 0.35");
                countCG = countCG + 1;
            }
            if (string.length() > longest.length()){
                longest = string;
            }
        }
        System.out.println("The longest string is length: "+longest.length());
        System.out.println("There are " + count9 + " strings that are longer than 60 characters");
        System.out.println("There are "+ countCG + " strings with C-G ratios higher than 0.35");
    }
    
    public void testProcessGenes(){
        //StorageResource sr = new StorageResource();
        //sr.add("ATGAAACATGATTGGCCTTAG");
        //sr.add("ATGTAA");
        //sr.add("AGCGCGCGCG");
        //sr.add("ATGAAATTTATACTAG");
        //sr.add("ATGTATAAATGCCTAA");
        /*FileResource fr1 = new FileResource("brca1line.fa");
        String dna1 = fr1.asString();
        sr.add(dna1);
        FileResource fr2 = new FileResource("brca1.fa");
        String dna2 = fr2.asString();
        sr.add(dna2);
        FileResource fr3 = new FileResource("brca1line.fa");
        String dna3 = fr3.asString();
        sr.add(dna3);
        FileResource fr4 = new FileResource("GRch38dnapart.fa");
        String dna4 = fr4.asString();
        sr.add(dna4);
        FileResource fr5 = new FileResource("mansmall.fa");
        String dna5 = fr5.asString();
        sr.add(dna5);*/
        
        FileResource fr1 = new FileResource("GRch38dnapart.fa");
        String dna1 = fr1.asString();
        StorageResource sr = getAllGenes(dna1);
        
        processGenes(sr);
    }
}


