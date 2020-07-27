package map;

import set.FileOperation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        testLLM();
    }

    public static void testLLM() {
        ArrayList<String> words = new ArrayList<>();
        LinkedListMap<String, Integer> map = new LinkedListMap<>();
        System.out.println("Pride and Prejudice");
        if (FileOperation.readFile("Your file path", words)) {
            System.out.println("Total words: " + words.size());
            for (String word: words) {
                if (map.contains(word)) {
                    int count = map.get(word);
                    map.set(word, ++count);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of \"pride\": " + map.get("pride"));
            System.out.println("Frequency of \"prejudice\": " + map.get("prejudice"));
        }
    }

}
