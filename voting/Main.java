import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VotingSystem votingSystem = new VotingSystem();
        
        System.out.println("Welcome to the Online Voting System!");
        
        boolean loggedIn = false;
        User user = null;
        
        while (!loggedIn) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline
            
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            user = new User(username, password);
            
            if (choice == 1) {
                if (user.register()) {
                    System.out.println("Registration successful! Please login.");
                } else {
                    System.out.println("Registration failed. Try again.");
                }
            } else if (choice == 2) {
                if (user.login()) {
                    System.out.println("Login successful!");
                    loggedIn = true;
                } else {
                    System.out.println("Login failed. Please try again.");
                }
            }
        }
        
        // Voting after successful login
        System.out.print("Enter candidate name to vote for: ");
        String candidate = scanner.nextLine();
        votingSystem.castVote(user.getId(), candidate);
        
        // Prompt to view results
        System.out.println("Do you want to view the results? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            votingSystem.showResults();
        }
        
        scanner.close();
    }
}
