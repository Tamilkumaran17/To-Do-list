import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private static final String FILE_NAME = "tasks.txt";
    private static ArrayList<String> tasks;

    public static void main(String[] args) {
        tasks = loadTasks();  // loading task from the saved file

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n---- To-Do List ----");
            displayTasks();

            System.out.println("\n1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    deleteTask(scanner);
                    break;
                case 3:
                    saveTasks();
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter the task to add: ");
        String newTask = scanner.nextLine();
        tasks.add(newTask);  //adding task
        System.out.println("Task added successfully.");
    }

    private static void deleteTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to delete.");
        } else {
            System.out.print("Enter the task number to delete: ");
            int taskNumber = scanner.nextInt();
            scanner.nextLine();

            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                String deletedTask = tasks.remove(taskNumber - 1);  //removing task
                System.out.println("Task deleted: " + deletedTask);
            } else {
                System.out.println("Invalid task number. Please try again.");
            }
        }
    }

    private static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.println(task);  //updating the new tasks to the file and saving the tasks
            }
            System.out.println("Tasks saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static ArrayList<String> loadTasks() {
        ArrayList<String> loadedTasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                loadedTasks.add(line);   //getting the tasks from saved file and return it
            }
            System.out.println("Tasks loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("No previous tasks found.");
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
        return loadedTasks;
    }
}
