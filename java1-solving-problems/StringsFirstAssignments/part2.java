
/**
 * Write a description of test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String result = "";
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
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
        String gene1 = "atgtaa";
        String gene2 = "CAGTAA";
        String gene3 = "ATGCGAA";
        String gene4 = "CGAATTCG";
        String gene5 = "ATGGCTAA";
        System.out.println(findSimpleGene(gene1, "atg", "taa"));
        System.out.println(findSimpleGene(gene2, "ATG", "TAA"));
        System.out.println(findSimpleGene(gene3, "ATG", "TAA"));
        System.out.println(findSimpleGene(gene4, "ATG", "TAA"));
        System.out.println(findSimpleGene(gene5, "ATG", "TAA"));
    }
}
