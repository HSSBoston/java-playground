
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;
        int start = 0;
        while(true){
            int currentIndex = stringb.indexOf(stringa,start);
            if (currentIndex == -1){
                break;
            }
            count = count + 1;
            start = currentIndex + stringa.length();
        }
        return count;
    }
    
    public void testHowMany(){
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC")); // 3
        System.out.println(howMany("AA","ATGAAAAGCTAAGATAA")); // 4
        System.out.println(howMany("GTA","AAAAAAAAA")); // 0
    }
}
