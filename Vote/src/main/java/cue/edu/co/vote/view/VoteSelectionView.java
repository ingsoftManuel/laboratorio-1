package cue.edu.co.vote.view;

import cue.edu.co.vote.controller.MainController;
import cue.edu.co.vote.database.CandidateDAO;
import cue.edu.co.vote.model.Candidate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class VoteSelectionView {

    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        // Configurar tres columnas con igual ancho
        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS);
            column.setPercentWidth(33.33);
            gridPane.getColumnConstraints().add(column);
        }

        Scene scene = new Scene(gridPane, 800, 600);

        CandidateDAO candidateDAO = new CandidateDAO();
        MainController mainController = new MainController(candidateDAO);

        try {
            List<Candidate> candidates = mainController.getAllCandidates();
            int column = 0;
            int row = 0;
            for (Candidate candidate : candidates) {
                VBox candidateBox = new VBox(10);
                candidateBox.setPadding(new Insets(10));
                candidateBox.setAlignment(Pos.CENTER);
                Label candidateName = new Label("Name: " + candidate.getName());
                Label candidateJornada = new Label("Jornada: " + candidate.getJornada());
                ImageView candidatePhoto = new ImageView(new Image(candidate.getPhotoPath()));
                candidatePhoto.setFitHeight(100);
                candidatePhoto.setFitWidth(100);
                Button voteButton = new Button("Vote for " + candidate.getName());

                voteButton.setOnAction(event -> {
                    VotingView votingView = new VotingView();
                    votingView.start(primaryStage, candidate);
                });

                candidateBox.getChildren().addAll(candidateName, candidateJornada, candidatePhoto, voteButton);

                gridPane.add(candidateBox, column, row);

                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 40);
        backButton.setOnAction(event -> {
            MainView mainView = new MainView();
            mainView.start(primaryStage);
        });

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(gridPane, backButton);

        Scene rootScene = new Scene(root, 800, 600);
        primaryStage.setScene(rootScene);
        primaryStage.setTitle("Select Candidate");
        primaryStage.show();
    }
}
