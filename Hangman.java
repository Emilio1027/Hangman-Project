package org.example;
import java.io.File;
import java.util.*;

public class Hangman {


    public static void main(String[] args)  {

        Scanner keyboard = new Scanner(System.in);
        List<String> dictionaryFile = getDictionary("d:/GENSPARK/PROJECTS/hangman/words.txt");

        header();
        String players = keyboard.nextLine();
        String word = getSecretWord(keyboard, dictionaryFile, players);
//             System.out.println(word);

        List<Character> playerCharacterGuess = new ArrayList<>();
        int count = 0;
        /*
        this loop is going to execute as long as count is under 6 and the world has not
        revealed, as long as both conditions are true, we will continue doing it over and over
         */
        while(count<6 && !isWordReveal(word,playerCharacterGuess)) {

            System.out.println(drawHangMan(count));
            System.out.println(getHiddenWord(word, playerCharacterGuess));
            if (!getPlayerGuess(keyboard, word, playerCharacterGuess)) {
                count++;
            }
        }
        if (count >= 6) {
            System.out.println("Sorry You lose!");
            System.out.println("The word was: " + word);

        }else{
            System.out.println("Congratulation You win!");
        }
    }

    private static void header() {
        System.out.println(" ____________________________ ");
        System.out.println("|_______WELCOME TO THE_______|");
        System.out.println("|________HANGMAN GAME________|");
        System.out.println("|____________________________|");

        System.out.println(" +_______+");
        System.out.println(" |");
        System.out.println(" O");
        System.out.print("\\");
        System.out.println(" /");
        System.out.println(" |");
        System.out.print("/ ");
        System.out.println("\\ ");

        System.out.println(" _____ ENTER GAME SELECTION _____");
        System.out.println("| 1.    ONE PLAYER               |");
        System.out.println("| 2.    TWO PLAYERS              |");
        System.out.println("|________________________________|");
    }

    public static List<String > getDictionary(String path){
        try{
            Scanner scanner = new Scanner(new File(path));
            List<String> wordsDictionaryFile = new ArrayList<>();
            while (scanner.hasNext()) {
                wordsDictionaryFile.add(scanner.nextLine());
        }
            return wordsDictionaryFile;
        }catch(Exception e) {
            System.out.println("Dictionary File was not found");
            return  null;
        }

    }

    public static String getSecretWord(Scanner keyboard, List<String>dictionary, String players) {
        String word;
        if (players.equals("1")) {
            Random rand = new Random();
            word = dictionary.get(rand.nextInt(dictionary.size()));
        }
            /*
            Otherwise, play 2nd player
            */
        else {
            System.out.println("Player 1, enter mystery word for player 2");
            word = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Player 2, enter your guessed letters for player's 1 mystery word");
        }
        return word;
    }

    public static String drawHangMan(Integer count) {
        StringBuilder builder = new StringBuilder();

        builder.append(" +-----+\n");
        builder.append(" |      \n");
        /*
        If player total tries misses more than 6 times the game is over player loses
        */
        if (count >= 1) {
            builder.append(" O\n");
        }
        if (count >= 2) {
            builder.append("\\ ");
            if (count >= 3) {
                builder.append("/\n");
            }
        }
        if (count >= 4) {
            builder.append(" |\n");
        }
        if (count >= 5) {
            builder.append("/ ");
            if (count >= 6) {
                builder.append("\\\n");
            }
        }
            builder.append("\n");
        return builder.toString();
    }

    public static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> prevGuess) {
        System.out.println("Guess a Character for a word:");
        String characterGuess = keyboard.nextLine();
        if (characterGuess.equals(word)){
            for (int i = 0 ; i< characterGuess.length(); i++){
                if (!prevGuess.contains(characterGuess.charAt(i))){
                    prevGuess.add(characterGuess.charAt(i));
                }
            }
            return true;
        }else if(characterGuess.length()>1){
            return false;
        }
        prevGuess.add(characterGuess.charAt(0));
        return word.contains(characterGuess);
    }

    public static String getHiddenWord(String word, List<Character> playerCharacterGuess) {

        String preGuesses = playerCharacterGuess.stream().map(String::valueOf).reduce("",(acc,c)->acc+c);
/*
this maps all the values of being a single character to a string, reduce allows you to take an entire list or array
and turn in to a single value our reduce will be passing an empty string " " the scope operator(::) is saying whatever
you are calling is a method of the String class, look in the String class and use the valueOf function, the acc is
short for the accumulator, the accumulator is the object being pass, the empty quotes " " is where we pass our
starting value, the accumulator is starting as an empty string and for every single item in our list we are saying
add the item to the end of the string ,the reduce() function is going to give the lambda -> every single time this
accumulator and a variable of this list and is asking what do you want to do with the accumulator and this variable
*/

        return word.replaceAll("[^ "+preGuesses.toLowerCase()+preGuesses.toUpperCase()+"]","-");
/*
replaceAll() will go through a string and any times it finds a character that matches our regular expression will
replace that character with a hyphen "-" regular expression is a way to represent some information, basically if we
give a series of characters in a regular expression then if encounters any of those characters it will replace that
character with the hyphen and will end up given us a brand-new word that has all the characters we desire to be replaced,
so we replace all the characters that are not contain in our prevGusses word, so we will have our preGuesses with one
single string that contains every single character that we have guess so far, any character that is not in that string
we want instead replace it with a hyphen
*/
        }

    public static boolean isWordReveal(String word, List<Character> playerCharacterGuess){
        for (int i = 0 ; i< word.length();i++){
            if (!playerCharacterGuess.contains(word.charAt(i))){
                return false;
            }
        }
        return true;
    }
}

