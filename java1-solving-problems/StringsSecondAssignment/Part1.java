
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
        
        while(true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
     
    public void testAllGenes(){
        printAllGenes("AAATGTTGCTAACGGGGCTAAGCTGCCTGATGTAAGCATGTAAAAGTATGCGTTTATAGCG");
    }
}

