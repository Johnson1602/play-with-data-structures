package set;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> word1 = new ArrayList<>();
        ArrayList<String> word2 = new ArrayList<>();
        BSTSet<String> bstSet1 = new BSTSet<>();
        BSTSet<String> bstSet2 = new BSTSet<>();

        System.out.println("Pride and Prejudice");
        if (FileOperation.readFile("You need to specify file path for Pride and Prejudice", word1)) {
            System.out.println("Total words: " + word1.size());
        }
        for (int i = 0; i < word1.size(); i++) {
            bstSet1.add(word1.get(i));
        }
        System.out.println("Total different words: " + bstSet1.getSize());

        System.out.println();

        System.out.println("A Tale of Two Cities");
        if (FileOperation.readFile("You need to specify file path for A Tale of Two Cities", word2)) {
            System.out.println("Total words: " + word2.size());
        }
        for (int i = 0; i < word2.size(); i++) {
            bstSet2.add(word2.get(i));
        }
        System.out.println("Total different words: " + bstSet2.getSize());
    }

}
