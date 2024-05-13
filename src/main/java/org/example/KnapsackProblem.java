package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KnapsackProblem {

    public record Item(long id, long profit, long weight) { }

    public static class KnapsackInstance {
        private final List<Item> items;
        private final long capacity;

        public KnapsackInstance(List<Item> items, long capacity) {
            this.items = items;
            this.capacity = capacity;
        }

        public List<Item> getItems() {
            return items;
        }

        public long getCapacity() {
            return capacity;
        }
    }

    public static boolean isTrivial(KnapsackInstance instance) {
        long totalWeight = instance.getItems().stream().mapToLong(Item::weight).sum();
        return totalWeight <= instance.getCapacity();
    }

    public static KnapsackInstance readKnapsackInstanceFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));

        int numItems = scanner.nextInt();
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < numItems; i++) {
            long id = scanner.nextLong();
            long profit = scanner.nextLong();
            long weight = scanner.nextLong();
            items.add(new Item(id, profit, weight));
        }

        long capacity = scanner.nextLong();

        return new KnapsackInstance(items, capacity);
    }

    public static void main(String[] args) {
        try {
            KnapsackInstance instance = readKnapsackInstanceFromFile("C:\\oapdisk\\cmpeMaterials\\s4\\Computer Programming IV\\Knapsack\\src\\main\\java\\org\\example\\test.in");
            System.out.println("Knapsack Capacity: " + instance.getCapacity());
            System.out.println("Items:");
            for (Item item : instance.getItems()) {
                System.out.println("Item ID: " + item.id() + ", Profit: " + item.profit() + ", Weight: " + item.weight());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}

