package JAVA;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamQuestions {

/********************************************************************************************************************************************************************************************************************/
            // Fetch all numbers from a list that are greater than 5.

            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

            List<Integer> greaterThan5 = numbers.stream()
                .filter(n -> n > 5)
                .collect(Collectors.toList());

/********************************************************************************************************************************************************************************************************************/
            // Transform list of strings into uppercase

            List<String> words = Arrays.asList("apple", "banana", "cherry");

            List<String> resultUpperCase = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

/********************************************************************************************************************************************************************************************************************/
            // Flatten a list of lists of strings into a single list

            List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("one", "two"),
                Arrays.asList("three", "four")
            );

            List<String> result = listOfLists.stream()
                .flatMap(Collection::stream)  // Flatten each sub-list into a stream of strings
                .collect(Collectors.toList());



            // public static List<Item> getAllItems(List<Order> orders) {
            // return orders.stream()
            //     .map(Order::getItems)         // Map each Order to its List<Item>
            //     .flatMap(List::stream)        // Flatten each List<Item> into a single stream of Item
            //     .collect(Collectors.toList());// Collect into a List<Item>
            // }


/********************************************************************************************************************************************************************************************************************/

            // Remove Duplicates from a list of integers

            List<Integer> lis=Arrays.asList(1,1,2,3,3,4,4,4,4,5);

            List<Integer> res=lis.stream()
                                  .distinct()
                                  .collect(Collectors.toList());


/********************************************************************************************************************************************************************************************************************/

            // Sort a list of string in reverse alphabatical order

            List<String> names=Arrays.asList("Vikash","Akash","Naresh","Sumit");
            List<String> reverseNames=names.stream()
                                            .sorted(Comparator.reverseOrder())
                                            .collect(Collectors.toList());

/********************************************************************************************************************************************************************************************************************/

            // Print elements of a list while traversing 

            List<String> peekedList=names.stream()
                                          .map(String :: toUpperCase)
                                          .peek(System.out :: println)
                                          .collect(Collectors.toList());

/********************************************************************************************************************************************************************************************************************/

        // Fetch First 3 elements from a list of integers

        List<Integer> first3 = lis.stream()
                                    .limit(3)
                                    .collect(Collectors.toList());

/********************************************************************************************************************************************************************************************************************/

        // Skip the first 4 elements and fetch the remaining elements from a list of

        List<Integer> skip4 = lis.stream()
                                    .skip(4)
                                    .collect(Collectors.toList());

/********************************************************************************************************************************************************************************************************************/

        // Print each element of a list of strings with a prefix "Item:

        //    lis.stream()
        //         .forEach(x -> System.out.println(x + ": element"));


/********************************************************************************************************************************************************************************************************************/

        // Collect a list of integers into a
        // Order will not be same -> use distinct for uniqueness and not set if ordering matters

        Set<Integer> setList = lis.stream()
                                    .collect(Collectors.toSet());

/********************************************************************************************************************************************************************************************************************/

        // Reduce - find product of all elements of a list

        Optional<Integer> prod=lis.stream()
                                    .reduce((a,b)->a*b);

/********************************************************************************************************************************************************************************************************************/


        // Check if all the elements in a list are positive
        // Short-circuit operator

        Boolean positiveOrNot = lis.stream()
                                    .allMatch(x->x>0);

/********************************************************************************************************************************************************************************************************************/


        // Check if any of the elements is negative
        // Short-circuit operator

        Boolean negativeOrNot = lis.stream()
                                    .anyMatch(x->x<0);

/********************************************************************************************************************************************************************************************************************/


        // Check if no elements in a list is negative
        // Short-circuit operator

        Boolean noNegative = lis.stream()
                                .noneMatch(x->x<0);

/********************************************************************************************************************************************************************************************************************/
        
        // Find first element that starts with 'b'
        // Short circuit

        List<String> city = Arrays.asList("banaras","bombay","pune","allahabad");
        Optional<String> firstElement = city.stream()
                                            .filter(x->x.startsWith("b"))
                                            .findFirst();

/********************************************************************************************************************************************************************************************************************/


        // Find max and min in a list of integers

        Optional<Integer> maxi1 = lis.stream()
                                    .max(Integer::compareTo);
        Optional<Integer> maxi2 = lis.stream()
                                    .max((a,b)->a-b);

        Optional<Integer> mini1 = lis.stream()
                                    .min(Integer::compareTo);
        Optional<Integer> mini2 = lis.stream()
                                    .min((a,b)->a-b);

/********************************************************************************************************************************************************************************************************************/


        // Covert a list into array

        String[] listToArray = city.stream()
                                    .toArray(String[] :: new);

/********************************************************************************************************************************************************************************************************************/


        // Concatenate all strings in a list into a single string separated by commas.

        String resultStringSeperatedByComma = city.stream()
                                                    .collect(Collectors.joining(","));

        // Concatenate all strings in a list into a single string separated by commas having a prefix and suffix.

        String resultStringSeperatedByCommaInABracket = city.stream()
                                                    .collect(Collectors.joining(",","[","]"));

/********************************************************************************************************************************************************************************************************************/



        // used for aggregating and categorizing data into a Map where the keys are the result of applying the
        // classifier function and the values are lists of items corresponding to each key.

        // Group a list of people by their city

        /*
           Map<String, List<Person>> result = people.stream()
            .collect(Collectors.groupingBy(Person::getCity));
         */

/********************************************************************************************************************************************************************************************************************/

        

        Map<Boolean, List<Integer>> partitionEvenAndOdd = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));

            //false : [1, 3, 5]
            //true : [2, 4, 6]

/********************************************************************************************************************************************************************************************************************/

    /*
            Predicate: Customers whose lifetime spending exceeds a certain threshold (e.g., $10,000).
            True group: High-value customers (VIPs).
            False group: Regular customers 
        */

    //     Map<Boolean, List<Customer>> customerPartition = customerList.stream()
    // .collect(Collectors.partitioningBy(customer -> customer.getTotalSpend() > 10000));


/********************************************************************************************************************************************************************************************************************/



    // count the number of elements in a list
    // usually used with other collectors for segregation

        long count = city.stream().collect(Collectors.counting());



/********************************************************************************************************************************************************************************************************************/


    // The summarizingInt method in Java Streams is a collector that generates summary statistics for the 
    // elements of a stream, including count, sum, minimum, average, and maximum

    // Generate summary statistics (count, sum, min, average, max) for a list of

    IntSummaryStatistics stats = numbers.stream()
                                        .collect(Collectors.summarizingInt(Integer :: intValue));

    // Output : IntSummaryStatistics{count=5, sum=15, min=1, average=3.000000, max=5}
    // stats.getSum()






/********************************************************************************************************************************************************************************************************************/


    // The mapping method in Java Streams is used to apply a mapping function to the elements 
    // of a stream and then collect the results using another

    // Return Type: A Collector that applies a mapping function and collects the results.
    // Question: Extract and collect the lengths of all strings in a collector

    List<Integer> lengths = words.stream()
    .collect(Collectors.mapping(String::length, Collectors.toList()));


    /*

        List<String> customerEmails = orderList.stream()
            .collect(Collectors.mapping(order -> order.getCustomer().getEmail(), Collectors.toList()));

     */



/********************************************************************************************************************************************************************************************************************/


// Collect group by length of each string 

Map<Integer, Long> wordLengthCounts = words.stream()
    .collect(Collectors.groupingBy(String::length, Collectors.counting()));





/********************************************************************************************************************************************************************************************************************/


// filter and collect only even numbers 

List<Integer> evenNumbers = numbers.stream()
    .collect(Collectors.filtering(n -> n % 2 == 0, Collectors.toList()));



/********************************************************************************************************************************************************************************************************************/

/*
    ✅ What is collectingAndThen()?

        It’s a finisher collector:

        It performs a collection operation.
        Then applies a finishing function to the result.
 */

    List<String> namesOfFriends = Arrays.asList("Vikash", "Akash", "Naresh");

        List<String> unmodifiableNames = names.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),               // 1️⃣ collect into a List
                Collections::unmodifiableList     // 2️⃣ wrap into an unmodifiable list
            ));

/********************************************************************************************************************************************************************************************************************/

    // Convert a list of strings into a map where the key is the string and the value is its length.

    Map<String,Integer> mapStringToLength = city.stream()
                                                .collect(Collectors.toMap(x->x, String :: length));
}
