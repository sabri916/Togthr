package co.togthrapp.togthr.DatabaseModel;

import java.util.List;

/**
 * Created by sabri on 16/12/17.
 */

public class VoteModel extends BaseTimelineItem {

    private String voteQuestion;

    public VoteModel(String author, String type, String voteQuestion, List<String> tags) {
        this.author = author;
        this.type = type;
        this.voteQuestion = voteQuestion;
        this.tags = tags;
    }

    public String getVoteQuestion() {
        return voteQuestion;
    }

    public void setVoteQuestion(String voteQuestion) {
        this.voteQuestion = voteQuestion;
    }
}
