package inc.typhon.zenithquiz.Quiz;

public class Quiz {
    private String quizName;
    private String quizDescription;
    private String quizImage;
    private String quizId;
    private boolean isSeniorQuiz;
    private int quizTimePerQuestion;

    public Quiz(String quizName, String quizDescription, String quizImage, String quizId, boolean isSeniorQuiz, int quizTimePerQuestion) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizImage = quizImage;
        this.quizId = quizId;
        this.isSeniorQuiz = isSeniorQuiz;
        this.quizTimePerQuestion = quizTimePerQuestion;
    }

    public String getQuizName() {
        return quizName;
    }

    public int getQuizTimePerQuestion() {
        return quizTimePerQuestion;
    }

    public void setQuizTimePerQuestion(int quizTimePerQuestion) {
        this.quizTimePerQuestion = quizTimePerQuestion;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public String getQuizImage() {
        return quizImage;
    }

    public void setQuizImage(String quizImage) {
        this.quizImage = quizImage;
    }

    public boolean isSeniorQuiz() {
        return isSeniorQuiz;
    }

    public void setSeniorQuiz(boolean seniorQuiz) {
        isSeniorQuiz = seniorQuiz;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }
}
