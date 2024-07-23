package cue.edu.co.vote.view;

import cue.edu.co.vote.database.VoteDAO;
import cue.edu.co.vote.database.VoterDAO;
import cue.edu.co.vote.model.Candidate;
import cue.edu.co.vote.model.Vote;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class VotingView {

    public void start(Stage primaryStage, Candidate candidate) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setAlignment(Pos.CENTER);

        Label candidateName = new Label("Voting for: " + candidate.getName());
        Label candidateJornada = new Label("Jornada: " + candidate.getJornada());

        Label voterIdLabel = new Label("Your ID:");
        TextField voterIdField = new TextField();

        Button voteButton = new Button("Submit Vote");
        voteButton.setPrefSize(200, 40);

        voteButton.setOnAction(event -> {
            String voterId = voterIdField.getText();

            if (!voterId.isEmpty()) {
                try {
                    VoterDAO voterDAO = new VoterDAO();
                    VoteDAO voteDAO = new VoteDAO();

                    int voterIdInt = voterDAO.getVoterId(voterId);
                    if (voterIdInt == -1) {
                        showAlert("Vote Error", "Invalid voter ID.");
                        return;
                    }

                    if (!voteDAO.hasVoted(voterIdInt, candidate.getJornada())) {
                        Vote vote = new Vote(voterIdInt, candidate.getId(), candidate.getJornada());
                        voteDAO.insertVote(vote);

                        showAlertAndReturn("Vote Successful", "Thank you for voting!", primaryStage);
                    } else {
                        showAlert("Vote Error", "You have already voted.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                showAlert("Input Error", "Please enter your ID.");
            }
        });

        root.getChildren().addAll(candidateName, candidateJornada, voterIdLabel, voterIdField, voteButton);

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 40);
        backButton.setOnAction(event -> {
            VoteSelectionView voteSelectionView = new VoteSelectionView();
            voteSelectionView.start(primaryStage);
        });

        root.getChildren().add(backButton);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vote");
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Stage alertStage = new Stage();
        VBox alertRoot = new VBox(10);
        alertRoot.setPadding(new Insets(20, 20, 20, 20));
        alertRoot.setAlignment(Pos.CENTER);

        Label messageLabel = new Label(message);
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> alertStage.close());

        alertRoot.getChildren().addAll(messageLabel, okButton);

        Scene alertScene = new Scene(alertRoot, 300, 200);
        alertStage.setTitle(title);
        alertStage.setScene(alertScene);
        alertStage.show();
    }

    private void showAlertAndReturn(String title, String message, Stage primaryStage) {
        Stage alertStage = new Stage();
        VBox alertRoot = new VBox(10);
        alertRoot.setPadding(new Insets(20, 20, 20, 20));
        alertRoot.setAlignment(Pos.CENTER);

        Label messageLabel = new Label(message);
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            alertStage.close();
            MainView mainView = new MainView();
            mainView.start(primaryStage);
        });

        alertRoot.getChildren().addAll(messageLabel, okButton);

        Scene alertScene = new Scene(alertRoot, 300, 200);
        alertStage.setTitle(title);
        alertStage.setScene(alertScene);
        alertStage.show();
    }
}
