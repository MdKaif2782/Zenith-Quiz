package inc.typhon.zenithquiz.Quiz;

public class Quiz {
    private String quizName;
    private String quizDescription;
    private String quizImage;
    private String quizId;
    private boolean isSeniorQuiz;
    private int quizTimePerQuestion;
    private int quizTotalQuestions;
    private long quizScheduledTime;

    public Quiz(String quizName, String quizDescription, String quizImage, boolean isSeniorQuiz, int quizTimePerQuestion, int quizTotalQuestions, long quizSheduledTime) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizImage = quizImage;
        this.isSeniorQuiz = isSeniorQuiz;
        this.quizTimePerQuestion = quizTimePerQuestion;
        this.quizTotalQuestions = quizTotalQuestions;
        this.quizScheduledTime = quizSheduledTime;
    }

    public int getQuizTotalQuestions() {
        return quizTotalQuestions;
    }

    public void setQuizTotalQuestions(int quizTotalQuestions) {
        this.quizTotalQuestions = quizTotalQuestions;
    }

    public long getQuizScheduledTime() {
        return quizScheduledTime;
    }

    public void setQuizScheduledTime(long quizScheduledTime) {
        this.quizScheduledTime = quizScheduledTime;
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
