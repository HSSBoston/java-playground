
public class vowelPercentage {
    public double findVowelPercentage(String input){
        int i = 0;
        double count = 0;
        String letter = "";
        while (i < input.length()){
            if (i == input.length()-1){
                letter = input.substring(i);
            }else{
                letter = input.substring(i,i+1);
            }
            if (letter.equalsIgnoreCase("a") || letter.equalsIgnoreCase("e") ||
            letter.equalsIgnoreCase("i") || letter.equalsIgnoreCase("o") || letter.equalsIgnoreCase("u")){
                count += 1;
            }
            i += 1;
        }
        if (input.length() == 0){
            return -1;
        }
        
        double percentage = count/input.length();
        
        return percentage;
    }
    
    public void test(){
        System.out.println(findVowelPercentage("bbb"));
        System.out.println(findVowelPercentage("aaa"));
    }
}
