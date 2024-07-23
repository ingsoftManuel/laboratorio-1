package cue.edu.co.vote.controller;

import cue.edu.co.vote.database.CandidateDAO;
import cue.edu.co.vote.model.Candidate;
import cue.edu.co.vote.view.VotingView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;


public class MainController {
    private CandidateDAO candidateDAO;
    private VotingView votingView;

    public MainController(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO;
        this.votingView = new VotingView();
    }

    public List<Candidate> getAllCandidates() throws SQLException {
        return candidateDAO.getAllCandidates();
    }

    public void handleVoteButton(Candidate candidate) {
        Stage votingStage = new Stage();
        votingView.start(votingStage, candidate);
    }
}
