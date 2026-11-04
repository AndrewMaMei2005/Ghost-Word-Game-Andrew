
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class ghostgame {
//This method is to check if the character forms a real word
public static boolean getwordata(String[] words, String currentWord){
    for(String w : words){
        if(w.equalsIgnoreCase(currentWord)){
            return true;
        }
    }
    return false;
}
//Checks if any word begins with the letters entered before the challenge
public static boolean checkword(String[] words, String currentWord){
    String temp = currentWord;
    temp = temp.substring(0, temp.length() - 1);
    for(String w : words){
        if(w.length() >= temp.length()){
            String tempword = w.substring(0, temp.length());
            if(tempword.equalsIgnoreCase(temp)){
            System.out.print(w + " begins with those letters. ");
            return true;
            }
        }  
    }
    return false;
}
public static void main(String[] args){
    boolean file = true;
    String[] words = new String[279496];
    //This line of code try to open the file and put the words into the array if theres not such file then it will put a error that output Error reading file
    try {
        Scanner input = new Scanner(new File("words.txt"));

        int i = 0;

        while (input.hasNextLine()) {
            words[i] = input.nextLine();
            i++;
        }

        input.close();
        
    } catch (Exception e) {
        System.out.println("Error reading file");
        file = false;
    }
    //create scanner
    Scanner k = new Scanner(System.in);
    int currentPlayers = 0, Player = 1;
    String currentWord = "", letter = "";
    System.out.print("Please enter how many players is in this game: ");
    currentPlayers = k.nextInt();
    System.out.println();
    k.nextLine();
    while(true && file){
        System.out.print("Player "+ Player + " its your turn. The current letters are " + currentWord + 
                            " Enter a letter or * to challenge: ");
        letter = k.nextLine();
        //This while loop make the user to enter 1 character if he tries to put no character or 2 character it will keep asking until the user put the correct character
        while(letter.length() != 1){
            System.out.print("Player "+ Player + " Please enter a valid letter. The current letters are " + currentWord + 
                            " Enter a letter or * to challenge: ");
            letter = k.nextLine();
        }
        currentWord += letter;
        //this is to make the word uppercase
         currentWord = currentWord.toUpperCase();
        System.out.println();
        //When the user enter 4 or more characters and if those character form a word the player loses
        if(currentWord.length() >= 4 && getwordata(words, currentWord)){
            System.out.println(currentWord + " is a word so player "+ Player +" loses");
            break;
        }
        //this check if the word not empty and if the player challenge the last player
        else if(!currentWord.isEmpty() &&
                currentWord.charAt(currentWord.length() - 1) == '*'){
            boolean check = checkword(words, currentWord);
            //The challenger loses if a word begins with the current letters
            if (check){
                System.out.println("Player "+ Player + " loses.");
                break;
            }
            else {
                //im doing this because if the Player is equal 1 since the line above checks if the player who challenge lost in this case
                //the player didnt lose the challenge so if the player that challenged succesfully since is equal to the player 1 then 
                // the currentplayers like lets say theres 4 players then the player 1 challange then it should be the player 4 that loses
                if (Player == 1){
                    System.out.println("Player " + currentPlayers + " loses.");
                    break;
                }
                else{
                    System.out.println("Player" + (Player - 1) + " loses");
                    break;
                }
            }
        }
        if(Player == currentPlayers){
            Player = 1;
        }
        else Player++;
    }
}


}
