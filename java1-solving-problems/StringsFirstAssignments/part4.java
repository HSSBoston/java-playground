import edu.duke.*;

public class part4 {
    public void findYoutube(){
        FileResource fr =  new FileResource("manylinks.html");
        for(String word : fr.words()){
            String lower = word.toLowerCase();
            if (lower.contains("youtube.com")){
                int firstQuotation = lower.indexOf("\"");
                int lastQuotation = lower.lastIndexOf("\"");
                String link = word.substring(firstQuotation+1, lastQuotation);
                System.out.println(link);
            }
        }
    }
}
