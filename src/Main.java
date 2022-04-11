import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your String");
        String savedString = reader.readLine();
        System.out.println("Choose what to do with your String: \n" +
                "1) Divide even and odd symbols into two \n" +
                "2) Count all numbers in String \n" +
                "3) Only show numbers \n" +
                "4) Delete symbols that reappear \n" +
                "5) Count each symbol in String \n" +
                "6) Turn over the String from start to end and the opposite \n" +
                "7) Calculate the amount of substring in a String \n" +
                "8) Reverse the word order in a String \n" +
                "9) Sort String array in the alphabet order (your string that you have typed previously will be ignored) \n" +
                "10) Calculate the shortest word length \n" +
                "11) Calculate number of words in a string \n" +
                "12) Add a space after a symbol, using it's index \n" +
                "13) Check if given string is a palindrome (case sensitive) \n" +
                "14) Edit the substring in a string \n" +
                "15) Calculate the range between to substrings in a string (case sensitive)");
        int input = -1;
        try {
            input = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            throw new IOException("Enter the right number! Exception caught");
        }
        switch (input) {
            case 1:
                divideByParity(savedString);
                break;
            case 2:
                countNumbers(savedString);
                break;
            case 3:
                useOnlyNumbers(savedString);
                break;
            case 4:
                deleteRepeats(savedString);
                break;
            case 5:
                countEachSymbol(savedString);
                break;
            case 6:
                reverseStringOrder(savedString);
                break;
            case 7:
                System.out.println("Enter substring");
                String substring = reader.readLine();
                showAmountOfSubstring(savedString, substring);
                break;
            case 8:
                reverseWordOrder(savedString);
                break;
            case 9:
                System.out.println("Enter amount of Strings");
                int amount;
                try{
                    amount = Integer.parseInt(reader.readLine());
                }
                catch (IOException e)
                {
                    throw new IOException("Enter right amount of strings, please");
                }
                String[] saveArray = new String[amount];
                for (int i = 0; i < amount; i++)
                {
                    saveArray[i] = reader.readLine();
                }
                alphabetSort(saveArray);
                break;
            case 10:
                calculateTheShortestWordLength(savedString);
                break;
            case 11:
                calculateNumberOfWords(savedString);
                break;
            case 12:
                System.out.println("Enter index at which you would like to insert a space");
                int index;
                try {
                    index = Integer.parseInt(reader.readLine());
                }
                catch (IOException e)
                {
                    throw new IOException("Enter right index, please");
                }
                addSpaceToString(savedString, index);
                break;
            case 13:
                checkIfPalindrome(savedString);
                break;
            case 14:
                System.out.println("Input beginning and ending indexes respectively");
                int beginIndex;
                int endIndex;
                String substringInsertion;
                try{
                    beginIndex = Integer.parseInt(reader.readLine());
                    endIndex = Integer.parseInt(reader.readLine());
                    System.out.println("Input the substring");
                    substringInsertion = reader.readLine();
                }
                catch (IOException e)
                {
                    throw new IOException("Input right indexes!");
                }
                insertSubstring(savedString, beginIndex, endIndex, substringInsertion);
                break;
            case 15:
                System.out.println("Input first and second substrings");
                String substring1 = reader.readLine();
                String substring2 = reader.readLine();
                calculateRangeBetweenSubstrings(savedString, substring1, substring2);
                break;
            default:
                System.out.println("Please, enter the right number");
        }

    }

    public static void divideByParity(String input) {
        StringBuilder oddSymbols = new StringBuilder();
        StringBuilder evenSymbols = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0)
                oddSymbols.append(input.charAt(i));
            else
                evenSymbols.append(input.charAt(i));
        }
        String[] arrayOfStrings = new String[2];
        arrayOfStrings[0] = oddSymbols.toString();
        arrayOfStrings[1] = evenSymbols.toString();
        System.out.println(arrayOfStrings[0] + " and second: " + arrayOfStrings[1]);
    }

    public static void countNumbers(String input) {
        int numberCounter = 0;
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '0':
                    numberCounter++;
                    break;

            }
        }
        System.out.println("Amount of numbers is: " + numberCounter);
    }

    public static void useOnlyNumbers(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '0':
                    output.append(input.charAt(i));
                    break;

            }
        }
        System.out.println("String, consisting only used numbers: " + output);
    }

    public static void deleteRepeats(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String character = input.charAt(i) + "";
            if (output.toString().contains(character) && !character.equals(" "))
                continue;
            output.append(character);
        }
        System.out.println("Resulting string is: " + output);
    }

    public static void countEachSymbol(String input) {
        StringBuilder differentSymbols = new StringBuilder();
        int[] symbolAmount = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            String character = input.charAt(i) + "";
            character = character.toLowerCase();
            if (differentSymbols.toString().contains(character)) {
                symbolAmount[differentSymbols.indexOf(character)]++;
            } else
                differentSymbols.append(character);
        }
        System.out.println("The counted number of each character is: \n");
        for (int i = 0; i < differentSymbols.length(); i++) {
            symbolAmount[i]++;
            System.out.println("Character '" + differentSymbols.charAt(i) + "' is met this amount of times: " + symbolAmount[i]);
        }
    }

    public static void reverseStringOrder(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            output.append(input.charAt(i));
        }
        System.out.println(output);
    }

    public static void showAmountOfSubstring(String input, String substring) {
        int amountOfSubstring = 0;
        input = input.toLowerCase();
        substring = substring.toLowerCase();
        for (int i = 0; i < input.length(); i++)
        {
            if (input.charAt(i) == substring.charAt(0))
            {
                for (int j = 1; j < substring.length(); j++)
                {
                    if (!(input.charAt(i + j) == substring.charAt(j)))
                        break;
                    else if (j == substring.length() - 1)
                        amountOfSubstring++;
                }
            }
        }
        System.out.println("Amount of substring in a string is: " + amountOfSubstring);
    }

    public static void reverseWordOrder(String input) {
        List<String> listOfWords = new LinkedList<String>();
        int lastSpace = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                if (lastSpace != -1) {
                    listOfWords.add(input.substring(lastSpace + 1, i + 1));
                } else {
                    listOfWords.add(input.substring(0, i + 1));
                }
                lastSpace = i;
            } else if (i == input.length() - 1) {
                listOfWords.add(input.substring(lastSpace + 1, i + 1) + " ");
            }
        }
        System.out.println("Words in reverse order are as follows: \n");
        for (int i = listOfWords.size() - 1; i > -1; i--) {
            System.out.print(listOfWords.get(i));
        }
    }

    public static void alphabetSort(String[] inputArray) {
        String temp;
        for (int i = 0; i < inputArray.length - 1; i++)
        {
            inputArray[i] = inputArray[i].toLowerCase();
            inputArray[i+1] = inputArray[i+1].toLowerCase();
            for(int k = 0; k < inputArray.length - 1 - i; k++)
            {
                for (int j = 0; j < inputArray[k].length(); j++)
                {

                    if (inputArray[k].charAt(j) > inputArray[k + 1].charAt(j))
                    {
                        temp = inputArray[k];
                        inputArray[k] = inputArray[k + 1];
                        inputArray[k + 1] = temp;

                    }
                    else if (inputArray[k].charAt(j) == inputArray[k + 1].charAt(j))
                    {
                        continue;
                    }
                    break;

                }
            }

        }
        System.out.println("Resulting String array is as follows: ");
        for (String s : inputArray) {
            System.out.print(s + " ");
        }
    }
    public static void calculateTheShortestWordLength(String input) {
        int shortestLength = 100;
        int lastSpace = -1;
        String shortestWord = "";
        for (int i = 0; i < input.length(); i++)
        {
            if (input.charAt(i) == ' ')
            {
                if (lastSpace == -1)
                {
                    shortestLength=input.substring(0, i + 1).length();
                    shortestWord=input.substring(0, i + 1);
                }
                else if (i == input.length() - 1)
                {
                    if(input.substring(lastSpace + 1, i).length()<shortestLength)
                    {
                        shortestLength=input.substring(lastSpace + 1, i).length();
                        shortestWord=input.substring(lastSpace + 1, i);
                    }
                }
                else
                {
                    if(input.substring(lastSpace + 1, i + 1).length()<shortestLength)
                    {
                        shortestLength=input.substring(lastSpace + 1, i + 1).length();
                        shortestWord=input.substring(lastSpace + 1, i + 1);
                    }
                }
                lastSpace = i;
            }
            else if (i == input.length() - 1) {
                if(input.substring(lastSpace + 1, i + 1).length() < shortestLength)
                {
                    shortestWord=input.substring(lastSpace + 1, i + 1);
                    shortestLength=input.substring(lastSpace + 1, i + 1).length();
                }
            }
        }
        System.out.println("The shortest word length is " + shortestLength + ", and the word is " + shortestWord);
    }

    public static void calculateNumberOfWords(String input)
    {
        int numberOfWords = 0;
        for(int i = 0; i < input.length(); i++)
        {
            if (input.charAt(i) == ' ')
            {
                if(i!=input.length()-1)
                    numberOfWords++;
            }

        }
        numberOfWords++;
        System.out.println("Number of words is: " + numberOfWords);
    }

    public static void addSpaceToString(String input, int index)
    {
        String substring1;
        String substring2;
        substring1 = input.substring(0, index) + " ";
        substring2 = input.substring(index);
        input = substring1 + substring2;
        System.out.println("Resulting String is: " + input);
    }

    public static void checkIfPalindrome(String input)
    {
        boolean isPalindrome = true;
        for (int i=0; i<input.length()/2; i++)
        {
            if(input.charAt(i)==input.charAt(input.length()-1-i))
                continue;
            isPalindrome = false;
            break;
        }
        if(isPalindrome)
            System.out.println("This string IS indeed a palindrome!");
        else
            System.out.println("This string IS NOT a palindrome...");
    }

    public static void insertSubstring(String inputString, int beginIndex, int endIndex, String substring)
    {
        String substring1;
        String substring2;
        substring1 = inputString.substring(0, beginIndex-1);
        substring2 = substring + inputString.substring(endIndex);
        inputString = substring1 + substring2;
        System.out.println("Resulting String is: " + inputString);
    }

    public static void calculateRangeBetweenSubstrings(String input, String substring1, String substring2)
    {
        int j = 0;
        boolean inProgress = false;
        int endIndexFirstSubstring = 100;
        int firstIndexSecondSubstring = 100;
        for(int i = 0; i < input.length(); i++)
        {
            if(input.charAt(i)==substring1.charAt(j))
            {
                inProgress = true;
                if(j==substring1.length()-1)
                {
                    endIndexFirstSubstring = i;
                    inProgress = false;
                    j = 0;
                    break;
                }
                else
                {
                    j++;
                }
            }
            else if(inProgress)
            {
                j = 0;
                inProgress = false;
            }
        }
        for(int i = endIndexFirstSubstring; i < input.length(); i++)
        {
            if(input.charAt(i)==substring2.charAt(j))
            {
                if(!inProgress)
                    firstIndexSecondSubstring = i;
                inProgress = true;
                if(j==substring1.length()-1)
                {
                    break;
                }
                else
                {
                    j++;
                }
            }
            else if(inProgress)
            {
                firstIndexSecondSubstring = 100;
                j = 0;
                inProgress = false;
            }
        }

        System.out.println("The range between substrings is: " + (firstIndexSecondSubstring - endIndexFirstSubstring - 1));
    }
}