package JAVA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String [] args){

/********************************************************************************************************************************************************************************************************************/
        // Creating a List of Students

        List<Student> students = Arrays.asList(
            new Student(1, "Rohit", "Kumar", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
            new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
            new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164)
        );

/********************************************************************************************************************************************************************************************************************/

        // Filtering Students by First Name

        // 1. Retrieves students whose first names start with 'A'

        List<Student> studentWithA = students.stream()
                                             .filter(x->x.getFirstName().startsWith("A"))
                                             .collect(Collectors.toList());

        
/********************************************************************************************************************************************************************************************************************/

        // Grouping objects by Department

        Map<String,List<Student>> studentsInEachDepartment = students.stream()
                                                               .collect(Collectors.groupingBy(Student :: getDepartmentName));

        
/********************************************************************************************************************************************************************************************************************/

        // Counting Total 

        long count = students.stream().count();
        int countInt = (int)students.stream().count();

/********************************************************************************************************************************************************************************************************************/

        // Finding Maximum Value
        
        // If you want non primitive (object) return

        List<Integer> numbers = List.of(5, 2, 9, 1, 7);
        Optional<Integer> maxNum = numbers.stream().max(Integer::compareTo);
        maxNum.ifPresent(System.out::println); // Output: 9


        // For primitive streams - need to map the Integers to ints - or Long to long - mapToInt , mapToLong

        int maxInt = numbers.stream().mapToInt(i -> i).max().orElse(-1);
        System.out.println(maxInt); // Output: 9

        // For string

        List<String> words = List.of("apple", "banana", "cherry");
        int maxLength = words.stream().mapToInt(String::length).max().orElse(-1);
        System.out.println(maxLength); // Output: 6 (length of "banana")


        int maxAge = students.stream()
                              .mapToInt(Student :: getAge)
                              .max().orElse(-1);

/********************************************************************************************************************************************************************************************************************/
                      
        // Distinct Values

        // Q. How can we find all unique department names?

        List<String> uniqueDepartments = students.stream()
                                                 .distinct()
                                                 .map(Student :: getDepartmentName)         // .map(x->x.getDepartmentName())
                                                 .collect(Collectors.toList());


/********************************************************************************************************************************************************************************************************************/

        // Count Students in Each Department:

        Map<String,Long> countsDepartmentWise = students.stream()
                                                        .collect(Collectors.groupingBy(Student :: getDepartmentName, Collectors.counting()));

 
/********************************************************************************************************************************************************************************************************************/

        // Find Students Below Age 30

        List<Student> below30Age = students.stream()
                                           .filter(x->x.getAge()<30)
                                           .collect(Collectors.toList());
        
        // Find Student's name Below Age 30

        List<String> below30AgeStudentsNames = students.stream()
                                                       .filter(x->x.getAge()<30)
                                                       .map(Student :: getFirstName)
                                                       .collect(Collectors.toList());


 /********************************************************************************************************************************************************************************************************************/

        // Find Students with Rank Between 50 and 100:

        List<Student> studentRankBetweenRange = students.stream()
                                                        .filter(x->(x.getRank()>50 && x.getRank()<100))
                                                        .collect(Collectors.toList());


/********************************************************************************************************************************************************************************************************************/

         // Averaging 

        // Average Age of Male and Female Students

        Map<String, Double> mapAvgAge = students.stream()
                                                .collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(Student::getAge)));

        // Average Rank in Each Department

        Map<String,Double> mapAvgDepartmentWise = students.stream()
                                                          .collect(Collectors.groupingBy(Student :: getDepartmentName,Collectors.averagingInt(Student :: getRank)));
                                                       

/********************************************************************************************************************************************************************************************************************/
        // Find Department with Maximum Number of Students:

        Entry<String,Long> entry = students.stream()
                                           .collect(Collectors.groupingBy(Student :: getDepartmentName,Collectors.counting()))
                                           .entrySet().stream()
                                           .max(Map.Entry.comparingByValue()).get();


/********************************************************************************************************************************************************************************************************************/

        // Sorting 

        // Sort Students by Rank

        List<Student> studentsByRank = students.stream()
                                               .sorted(Comparator.comparingInt(Student :: getRank))
                                               .collect(Collectors.toList());

        // Find Students in Delhi and Sort by Name

        List<Student> studentDelhiSorted = students.stream()
                                                   .filter(x->x.getCity().equals("Delhi"))
                                                   .sorted((a,b)->a.getFirstName().compareTo(b.getFirstName()))
                                                   .collect(Collectors.toList());
        
        List<Student> studentLocationAsc = students.stream()
                                                .filter(dt -> dt.getCity().equals("Delhi"))
                                                .sorted(Comparator.comparing(Student::getFirstName))
                                                .collect(Collectors.toList());

        // for descending order

        List<Student> studentLocationDesc = students.stream()
                                                .filter(dt -> dt.getCity().equals("Delhi"))
                                                .sorted(Comparator.comparing(Student::getFirstName).reversed())
                                                .collect(Collectors.toList());
        


        // sort by one sorting technique then by other 

        List<Student> studentDelhiSorted2 = students.stream()
                                                    .filter(x -> x.getCity().equals("Delhi"))  // Only Delhi students
                                                    .sorted(Comparator.comparingInt((Student s) -> s.getFirstName().length())  // Sort by length          
                                                    //comparingInt(Student::getFirstName().length()) avoids unnecessary boxing of integers Comparator.comparing() works with objects,
                                                    // while comparingInt() is optimized for int values.
                                                    // comparing will also work fine though
                                                    .thenComparing(Student::getFirstName))  // If equal, sort lexicographically
                                                    .collect(Collectors.toList());



/********************************************************************************************************************************************************************************************************************/

        // Find the Highest Rank (Lowest Value) in Each Department

        Map<String,Optional<Student>> highestRankInEachDepartment = students.stream()
                                                                  .collect(Collectors.groupingBy(Student :: getDepartmentName , Collectors.minBy(Comparator.comparing(Student :: getRank))));


/********************************************************************************************************************************************************************************************************************/

        // Find the student with the second highest rank

        Student highestRankStudent = students.stream()
                                             .sorted(Comparator.comparingInt(Student :: getRank).reversed())
                                             .skip(1)
                                             .findFirst()
                                             .get();

/********************************************************************************************************************************************************************************************************************/

        // Using limit() - getting top some

        // Find top 3 ranked student
        List<Student> top3Students = students.stream()
        .sorted(Comparator.comparingInt(Student::getRank)) // Sort by rank (ascending order)
        .limit(3) // Take the top 3 students
        .collect(Collectors.toList());

/********************************************************************************************************************************************************************************************************************/

// => Lambdas can not access outside values - but since topranks is effectively final it can use.
        // Include all having top 3 ranks 
         Set<Integer> topRanks = students.stream()
                                        .map(Student::getRank) // Extract ranks
                                        .distinct() // Remove duplicates
                                        .sorted() // Sort in ascending order
                                        .limit(3) // Get the first 3 unique ranks
                                        .collect(Collectors.toSet()); // Store in a Set for easy lookup

        List<Student> topRankedStudents = students.stream()
                                        .filter(s -> topRanks.contains(s.getRank())) // Keep only students with top 3 ranks
                                        .sorted(Comparator.comparingInt(Student::getRank)) // Sort by rank
                                        .collect(Collectors.toList());

        topRankedStudents.forEach(System.out::println);

/********************************************************************************************************************************************************************************************************************/

        // You need to run a method outside of a map()
        
        List<String> results = students.stream()
                                       .map(person -> process(person.getFirstName(), person.getAge()))
                                       .collect(Collectors.toList());

        results.forEach(System.out::println);

}


public static String process(String name, int age) {
    return name + " is " + age + " years old.";
}

}
