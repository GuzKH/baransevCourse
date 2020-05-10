import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collection {

    public static void main(String[] args){
        //1 massive
       // String[] langs = {"Java", "C#", "Python", "PHP"};

        // 2&3 collection
        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

        // 2 collection
//        languages.add("Java");
//        languages.add("C#");
//        languages.add("Python");
//        languages.add("PHP");

        // 1 massive
//        langs[0] = "Java";
//        langs[1] = "C#";
//        langs[2] = "Python";
//        langs[3] = "PHP";

        // 1 massive
//        for (int i = 0; i < langs.length; i++){
//            System.out.println("I wanna learn: " + langs[i]);
//        }
        // 2 collection
        for (String l : languages){
            System.out.println("I wanna learn: " + l);
        }

        // 3 collection
//        for (int i =0; i < languages.size(); i++){
//            System.out.println("I wanna learn: " + languages.get(i));
//        }


    }
}
