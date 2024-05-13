package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    public static List<Item> generateRandomSolution(KnapsackProblem.KnapsackInstance instance, long seed) {
        List<Item> items = instance.getItems();
        List<Item> selectedItems = new ArrayList<>();
        long remainingCapacity = instance.getCapacity();

        // Create a Random object with the generated seed
        Random random = new Random(seed);


        for (Item item : items) {
            // Randomly decide whether to include the item or not
            boolean includeItem = random.nextBoolean();

            if (includeItem && item.weight() <= remainingCapacity) {
                // Include the item if it fits within the remaining capacity
                selectedItems.add(item);
                remainingCapacity -= item.weight();
            }
        }

        return selectedItems;
    }

    public static List<Item> findBestRandomSolution(KnapsackInstance instance, int numIterations) {
        List<Item> bestSolution = null;
        long bestProfit = Long.MIN_VALUE;

        for (int i = 0; i < numIterations; i++) {

            Random seedGenerator = new Random();
            long seed = seedGenerator.nextLong();

            List<Item> solution = generateRandomSolution(instance, seed);
            long totalProfit = solution.stream().mapToLong(Item::profit).sum();

            if (totalProfit > bestProfit) {
                bestProfit = totalProfit;
                bestSolution = solution;
            }
        }

        return bestSolution;
    }

    public static void printSolution(List<Item> solution) {
        if (solution == null || solution.isEmpty()) {
            System.out.println("No solution found.");
            return;
        }

        long totalProfit = solution.stream().mapToLong(Item::profit).sum();
        long totalWeight = solution.stream().mapToLong(Item::weight).sum();

        System.out.println("Best Solution:");
        System.out.println("Total Profit: " + totalProfit);
        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Selected Items:");
        for (Item item : solution) {
            System.out.println("Item ID: " + item.id() + ", Profit: " + item.profit() + ", Weight: " + item.weight());
        }
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
            KnapsackInstance instance1 = readKnapsackInstanceFromFile("C:\\oapdisk\\cmpeMaterials\\s4\\Computer Programming IV\\Knapsack\\src\\main\\java\\org\\example\\test.in");
            KnapsackInstance instance2 = readKnapsackInstanceFromFile("C:\\oapdisk\\cmpeMaterials\\s4\\Computer Programming IV\\Knapsack\\src\\main\\java\\org\\example\\test.in");

//            System.out.println("Knapsack Capacity: " + instance1.getCapacity());
//            System.out.println("Items:");
//            for (Item item : instance1.getItems()) {
//                System.out.println("Item ID: " + item.id() + ", Profit: " + item.profit() + ", Weight: " + item.weight());
//            }
//
//            System.out.println("\n\n");

            // Generate 1 million random solutions and find the best one
            List<Item> bestSolution = findBestRandomSolution(instance1, 1000000);
            // Print the best solution
            printSolution(bestSolution);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}

