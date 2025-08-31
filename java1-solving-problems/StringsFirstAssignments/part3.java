
/**
 * Write a description of part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        if (stringb.contains(stringa)){
           int first = stringb.indexOf(stringa);
           int second = stringb.indexOf(stringa, first+1);
           if (second == -1){
               return false;
           }
           return true;
        }
        else{
            return false;
        }
    }
    
    public String lastPart(String stringa, String stringb){
        if (stringb.contains(stringa)){
            int index = stringb.indexOf(stringa);
            return stringb.substring(index+stringa.length());
        }
        else{
            return stringb;
        }
    }
    
    public void testing(){
        System.out.println(twoOccurrences("na","banana"));
        System.out.println(twoOccurrences("ice", "ice cream"));
        System.out.println(twoOccurrences("atg","atgtcaatgtaa"));
        
        String stringa = "an";
        String stringb = "banana";
        String output = lastPart(stringa,stringb);
        System.out.println("The part of the string after "+ stringa+" in "+stringb+" is "+output);
        stringa = "zoo";
        stringb = "forest";
        output = lastPart(stringa,stringb); 
        System.out.println("The part of the string after "+ stringa+" in "+stringb+" is "+output);
    }
}
