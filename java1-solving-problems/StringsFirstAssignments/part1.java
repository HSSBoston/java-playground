
/**
 * Write a description of test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part1 {
    public String findSimpleGene(String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1){
            return "";
        }
    
        result = dna.substring(startIndex, stopIndex+3);
        if (result.length()%3 != 0){
            return "";
        }
        
        return result;
    }
    
    public void testSimpleGene(){
        String gene1 = "AAATGCCCTAACTAGATTAAGAAACC";
        String gene2 = "CAGTAA";
        String gene3 = "ATGCGAA";
        String gene4 = "CGAATTCG";
        String gene5 = "ATGGCTAA";
        System.out.println(findSimpleGene(gene1));
        System.out.println(findSimpleGene(gene2));
        System.out.println(findSimpleGene(gene3));
        System.out.println(findSimpleGene(gene4));
        System.out.println(findSimpleGene(gene5));
    }
}
