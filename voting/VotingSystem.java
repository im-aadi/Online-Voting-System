import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VotingSystem {
    public boolean castVote(int userId, String candidate) {
        // Check if user has already voted
        String checkQuery = "SELECT hasVoted FROM users WHERE id = ?";
        String updateUserQuery = "UPDATE users SET hasVoted = ? WHERE id = ?";
        String insertVoteQuery = "INSERT INTO votes (candidate, user_id) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateUserQuery);
             PreparedStatement voteStmt = conn.prepareStatement(insertVoteQuery)) {
            
            // Check if the user has voted
            checkStmt.setInt(1, userId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getBoolean("hasVoted")) {
                System.out.println("User has already voted.");
                return false;
            }
            
            // Update user to mark as voted
            updateStmt.setBoolean(1, true);
            updateStmt.setInt(2, userId);
            updateStmt.executeUpdate();
            
            // Insert vote
            voteStmt.setString(1, candidate);
            voteStmt.setInt(2, userId);
            voteStmt.executeUpdate();
            
            System.out.println("Vote cast successfully!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showResults() {
        String query = "SELECT candidate, COUNT(*) AS vote_count FROM votes GROUP BY candidate";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String candidate = rs.getString("candidate");
                int voteCount = rs.getInt("vote_count");
                System.out.println(candidate + ": " + voteCount + " votes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
