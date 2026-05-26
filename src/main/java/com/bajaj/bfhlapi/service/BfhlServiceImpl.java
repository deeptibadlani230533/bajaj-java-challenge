package com.bajaj.bfhlapi.service;

import com.bajaj.bfhlapi.dto.RequestDTO;
import com.bajaj.bfhlapi.dto.ResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "deepti_badlani_11032005";
    private static final String EMAIL = "deeptibadlani230533@acropolis.in";
    private static final String ROLL_NUMBER = "0827CS231074";

    @Override
    public ResponseDTO processData(RequestDTO requestDTO) {
        System.out.println("Processing data: " + requestDTO.getData());

        List<String> data = requestDTO.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        List<String> alphaCharsFromInput = new ArrayList<>();  // raw alphabetic strings for concat
        long totalSum = 0;

        for (String item : data) {
            if (item == null || item.isEmpty()) {
                continue;
            }

            // Check if item is a pure numeric string (integer, possibly negative)
            if (isNumeric(item)) {
                long num = Long.parseLong(item);
                totalSum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            }
            // Check if item is a pure alphabetic string (single or multi char)
            else if (isAlphabetic(item)) {
                alphabets.add(item.toUpperCase());
                alphaCharsFromInput.add(item);
            }
            // Otherwise it's a special character element
            else {
                specialCharacters.add(item);
            }
        }

        // Build concat_string
        String concatString = buildConcatString(alphaCharsFromInput);

        System.out.println("odd_numbers: " + oddNumbers);
        System.out.println("even_numbers: " + evenNumbers);
        System.out.println("alphabets: " + alphabets);
        System.out.println("special_characters: " + specialCharacters);
        System.out.println("sum: " + totalSum);
        System.out.println("concat_string: " + concatString);

        ResponseDTO response = new ResponseDTO();
        response.setSuccess(true);
        response.setUserId(USER_ID);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(totalSum));
        response.setConcatString(concatString);

        return response;
    }

    // Returns true if string is a valid integer (no decimal point, optional leading minus)
    private boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
        int start = 0;
        if (s.charAt(0) == '-') {
            if (s.length() == 1) return false;
            start = 1;
        }
        for (int i = start; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    // Returns true if every character in the string is a letter (A-Z or a-z)
    private boolean isAlphabetic(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    /**
     * concat_string logic:
     * 1. Take all alphabetic strings from input, join them to form one big string.
     * 2. Reverse that string.
     * 3. Apply alternating caps starting from the LAST character position
     *    (i.e., index 0 of reversed string gets alternating starting from uppercase).
     *
     * Example: input has "a", "R"
     *   combined = "aR"
     *   reversed = "Ra"
     *   alternating (from index 0 upward: upper, lower) = "Ra"  ✓
     *
     * Example: input has "a", "y", "b"
     *   combined = "ayb"
     *   reversed = "bya"
     *   alternating = "ByA"  ✓
     */
    private String buildConcatString(List<String> alphaItems) {
        if (alphaItems.isEmpty()) {
            return "";
        }

        // Step 1: Join all alphabetic strings
        StringBuilder sb = new StringBuilder();
        for (String item : alphaItems) {
            sb.append(item);
        }
        String combined = sb.toString();

        // Step 2: Reverse
        String reversed = new StringBuilder(combined).reverse().toString();

        // Step 3: Alternating caps — index 0 = uppercase, index 1 = lowercase, ...
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
}
