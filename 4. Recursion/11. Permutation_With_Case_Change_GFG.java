

import java.util.ArrayList;

class Main {

    // Recursive function to generate permutations with case changes
    static void permu(String ip, String op, ArrayList<String> res) {
        // Base case: If input string is empty, add the output string to the result list
        if (ip.length() == 0) {
            res.add(op);
            return;
        }

        char ch = ip.charAt(0); // Extract the first character of the input string

        // Recursive calls:
        // 1. Include the character in lowercase (original form)
        permu(ip.substring(1), op + ch, res);

        // 2. Include the character in uppercase
        permu(ip.substring(1), op + Character.toUpperCase(ch), res);
    }

    public static void main(String[] args) {
        String s = "abc"; // Input string

        ArrayList<String> res = new ArrayList<>(); // List to store results

        permu(s, "", res); // Call recursive function with initial output as empty string

        // Print each generated permutation
        res.forEach(x -> System.out.println(x));
    }
}
