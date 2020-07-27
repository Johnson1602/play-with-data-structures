package map;

import set.FileOperation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String file = "Your file path";
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        System.out.println("Testing BST Map...");
        double bstMapTime = testMap(bstMap, file);
        System.out.println("Finish in: " + bstMapTime + " s.");

        System.out.println();

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        System.out.println("Testing Linked List Map...");
        double linkedListMapTime = testMap(linkedListMap, file);
        System.out.println("Finish in: " + linkedListMapTime + " s.");
    }

    public static double testMap(Map<String, Integer> map, String file) {

        Long startTime = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();
        System.out.println("Pride and Prejudice");
        if (FileOperation.readFile(file, words)) {
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

        Long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

}
