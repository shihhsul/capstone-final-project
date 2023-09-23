import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FishInputter {

        private static class FishFileHandler {
                private static final String FILE_PATH = "fish_data.txt";

                public static void saveFishToFile(String fishData) {
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                                writer.write(fishData);
                                writer.newLine();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                String exit = "";

                System.out.println("Enter exit");
                exit = scanner.nextLine();

                while (!exit.equals("exit")) {
                        StringBuilder fishData = new StringBuilder();

                        System.out.print("Enter common name: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter scientific name: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter species group: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter care level: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter average size: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter lifespan: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter phLow: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter phHigh: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter tempLow: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter tempHigh: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter hardLow: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter hardHigh: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter swimming level: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Is the fish aggressive towards itself? (true/false): ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Is the fish aggressive towards others? (true/false): ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter ideal number: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Does the fish like live plants? (true/false): ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter food type: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter food options: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter substrate: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter light: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter current: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter decorations: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter minimum tank size: ");
                        fishData.append(scanner.nextLine()).append(",");
                        System.out.print("Enter picture URL: ");
                        fishData.append(scanner.nextLine());

                        // Remove the trailing comma
                        fishData.deleteCharAt(fishData.length() - 1);

                        FishFileHandler.saveFishToFile(fishData.toString());

                        System.out.print("Enter exit");
                        exit = scanner.nextLine();
                }
        }
}
