import java.util.ArrayList;
import java.util.List;

class Candidate {
    private String name;
    private int rollNumber;
    private String position;
    private int votesReceived;

    // Constructor 1: Basic registration without initial votes
    public Candidate(String name, int rollNumber, String position) {
        this.name = name.toUpperCase(); // String Manipulation: Convert name to uppercase
        this.rollNumber = rollNumber;
        this.position = position.toUpperCase();
        this.votesReceived = 0;
    }

    // Constructor 2: Overloaded constructor with initial votes
    public Candidate(String name, int rollNumber, String position, int votesReceived) {
        this.name = name.toUpperCase(); // String Manipulation: Convert name to uppercase
        this.rollNumber = rollNumber;
        this.position = position.toUpperCase();
        this.votesReceived = votesReceived;
    }

    // Getters
    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getPosition() { return position; }
    public int getVotesReceived() { return votesReceived; }

    // Method to display candidate details
    public void displayCandidateDetails() {
        System.out.println("Candidate Registered Successfully");
        System.out.println("Candidate Name   : " + name);
        System.out.println("Roll Number      : " + rollNumber);
        System.out.println("Position         : " + position);
        System.out.println("Votes Received   : " + votesReceived);
        System.out.println();
    }
}

public class ElectionApplication {
    private List<Candidate> candidates = new ArrayList<>();

    // Method Overloading 1: Register using a Candidate object
    public void registerCandidate(Candidate candidate) {
        if (isDuplicate(candidate.getName())) {
            System.out.println("Duplicate Candidate Check:");
            System.out.println("Candidate Name   : " + candidate.getName());
            System.out.println("Status           : Duplicate Nomination\n");
            return;
        }
        candidates.add(candidate);
        candidate.displayCandidateDetails();
    }

    // Method Overloading 2: Register directly with individual details
    public void registerCandidate(String name, int rollNumber, String position, int votes) {
        Candidate candidate = new Candidate(name, rollNumber, position, votes);
        registerCandidate(candidate);
    }

    // String Manipulation: Comparing names ignoring case to prevent duplication
    private boolean isDuplicate(String name) {
        for (Candidate c : candidates) {
            if (c.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Method to display the winner for a specific position
    public void displayWinner(String position) {
        Candidate winner = null;
        int maxVotes = -1;

        for (Candidate c : candidates) {
            if (c.getPosition().equalsIgnoreCase(position)) {
                if (c.getVotesReceived() > maxVotes) {
                    maxVotes = c.getVotesReceived();
                    winner = c;
                }
            }
        }

        System.out.println("----------- ELECTION RESULTS -----------");
        if (winner != null) {
            System.out.println("Position          : " + position.toUpperCase());
            System.out.println("Winning Candidate : " + winner.getName());
            System.out.println("Total Votes       : " + winner.getVotesReceived());
        } else {
            System.out.println("No candidates registered for position: " + position);
        }
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("--------- STUDENT COUNCIL ELECTION ---------\n");
        ElectionApplication election = new ElectionApplication();// 1. Register candidates using overloaded registration methods & constructors
        election.registerCandidate(new Candidate("Ananya", 101, "President", 125));
        election.registerCandidate("Rahul", 102, "President", 98);

        // 2. Attempting to add a duplicate candidate name
        election.registerCandidate(new Candidate("Ananya", 103, "President", 0));

        // 3. Display election results
        election.displayWinner("President");
    }
}