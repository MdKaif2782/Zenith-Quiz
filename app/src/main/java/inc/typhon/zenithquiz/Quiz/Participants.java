package inc.typhon.zenithquiz.Quiz;

public class Participants {
    private String id;
    private int score;

    public Participants(String id, int score) {
        this.id = id;
        this.score = score;
    }
    public Participants() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
