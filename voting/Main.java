import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VotingSystem votingSystem = new VotingSystem();
        
        System.out.println("Welcome to the Online Voting System!");
        
        System.out.println("1. Register");
        System.out.println("2. Login");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        User user = new User(username, password);
        
        if (choice == 1 && user.register()) {
            System.out.println("Registration successful! Please login.");
        } else if (choice == 2 && user.login()) {
            System.out.println("Login successful!");
            System.out.print("Enter candidate name to vote for: ");
            String candidate = scanner.nextLine();
            votingSystem.castVote(user.getId(), candidate);
        } else {
            System.out.println("Invalid login or registration attempt.");
        }
        
        System.out.println("Do you want to view the results? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            votingSystem.showResults();
        }
        
        scanner.close();
    }
}

