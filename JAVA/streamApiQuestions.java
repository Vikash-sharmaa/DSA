package JAVA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class streamApiQuestions {
    public static void main(String[] args) {
/********************************************************************************************************************************************************************************************************************/

    // 1. Given a sentence, find the word that has the highest length

        String str = "I am learning stream api in java";
        String res= Arrays.stream(str.split(" ")).max(Comparator.comparing(String :: length)).get();
        System.out.println(res);

/********************************************************************************************************************************************************************************************************************/

    // 2. Given a sentence, find Nth highest length (2nd) word 

        res=Arrays.stream(str.split(" "))
                    .sorted(Comparator.comparingInt(String :: length).reversed())
                    .skip(1)
                    .findFirst()
                    .get();
        System.out.println(res);

/********************************************************************************************************************************************************************************************************************/

    // 3. Find the 2nd highest length word in the given sentence

        int len=Arrays.stream(str.split(" "))
                    .sorted(Comparator.comparingInt(String :: length).reversed())
                    .skip(1)
                    .findFirst()
                    .get().length();
        System.out.println(len);

        //XXXXXXXXXXXXXXXXXXX

        len = Arrays.stream(str.split(" "))
                .map(String :: length)
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .get();
        System.out.println(len);
/********************************************************************************************************************************************************************************************************************/

    // Given a sentence, find the occurrence of each word

    Map<String,Long> mapRes = Arrays.stream(str.split(" "))
            .collect(Collectors.groupingBy(x->x , Collectors.counting()));
    for(Map.Entry<String,Long> entry : mapRes.entrySet()){
        System.out.println(entry.getKey() + "'s count is : " +entry.getValue());
    }

    // 3. Remove duplicates from the string and return in the same order

            str = "dabcadefg";
            str.chars().distinct().mapToObj(x->(char)x).forEach(System.out :: print);
            System.out.println();


            res=Arrays.stream(str.split("")).distinct().collect(Collectors.joining(""));
            System.out.println(res);

    /********************************************************************************************************************************************************************************************************************/

        // Given a sentence, find the words with a specified number of vowels (2)

        Arrays.stream(str.split(" "))
                .filter(word -> word.replaceAll("[^aeiouAEIOU]", "").length() == 2)
                .forEach(System.out::println);

        // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


/********************************************************************************************************************************************************************************************************************/

        // Divide given integer list into lists of even and odd numbers

        int[] a = {1,2,3,4,5,6,7,8,9};

        // Map<Boolean,List<Integer>> mapRes = Arrays.stream(a)
        //         .collect(Collectors.partitioningBy(x->x%2));

/********************************************************************************************************************************************************************************************************************/


        // Given a word , find occurence of each characters

        str="missisppi";

        Map<Character,Long> ocuurenceMap = str.chars()
                                            .mapToObj(x->(char)x)
                                            .collect(Collectors.groupingBy(x->x,Collectors.counting()));

        for(Map.Entry<Character,Long> entry : ocuurenceMap.entrySet()){
            System.out.println(entry.getKey()+" occured "+entry.getValue() +" times !");
        }


        Map<String,Long> occurenceMapStr = Arrays.stream(str.split(""))
                        .collect(Collectors.groupingBy(x->x,Collectors.counting()));
        

/********************************************************************************************************************************************************************************************************************/

        // Given an array, find the sum of unique elements

        int sum = Arrays.stream(a)
                        .distinct()
                        .sum();
        System.out.println(sum);


/********************************************************************************************************************************************************************************************************************/

        // Given a string , find the first non-repeating character

        String str1="Hello World";
        Arrays.stream(str.split(""))
                .filter(x->str1.indexOf(x)==str1.lastIndexOf(x))
                .findFirst()
                .get();
        
        // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

        char ans = str1.chars() // IntStream of character codes
                        .mapToObj(c -> (char) c) // Stream<Character>
                        .collect(Collectors.groupingBy(
                            Function.identity(),
                            LinkedHashMap::new,
                            Collectors.counting()
                        ))
                        .entrySet()
                        .stream()
                        .filter(m -> m.getValue() == 1)
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .get(); // or .orElse(' ') if you want a fallback

                                      

        }




}



