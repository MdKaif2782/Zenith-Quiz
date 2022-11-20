package inc.typhon.zenithquiz.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.common.base.Stopwatch;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.List;

import inc.typhon.zenithquiz.R;

public class Quiz_exam extends AppCompatActivity {
    private FirebaseFirestore db;
    private String quizID;
    private Quiz quiz;
    private List<QuizQuestion> quizQuestions;
    private RadioButton option1, option2, option3, option4;
    private TextView question;
    private Button nextButton;
    private LinearProgressIndicator progressIndicator;
    private int questionNumber = 0;
    private ImageView quizImage;
    private int score = 0;
    private RadioGroup radioGroup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_exam);
        question = findViewById(R.id.question_text);
        quizImage = findViewById(R.id.question_image);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextButton = findViewById(R.id.next_button);
        radioGroup = findViewById(R.id.radio_group_quiz);
        progressIndicator = findViewById(R.id.progressBar_quiz);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        quizID = getIntent().getStringExtra("quizID");
        System.out.println("Quiz ID : " + quizID);
        db.collection("quiz").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                for (int i = 0; i < task.getResult().size(); i++) {
                    Quiz quiz = task.getResult().getDocuments().get(i).toObject(Quiz.class);
                    assert quiz != null;
                    if (quiz.getQuizId().equals(quizID)){
                        this.quiz = quiz;
                        quizQuestions = quiz.getQuizQuestions();
                        System.out.println("Quiz Name : " + quiz.getQuizName());
                        System.out.println("Quiz Description : " + quiz.getQuizDescription());
                        setQuestions(questionNumber);
                    }
                }
            }
        });

    }

    public void setQuestions(int questionNumber) {
        if (questionNumber < quizQuestions.size()) {
            QuizQuestion quizQuestion = quizQuestions.get(questionNumber);
            if (quizQuestion.getQuestionImage() != null) {
                Glide.with(this).load(quizQuestion.getQuestionImage()).into(quizImage);
            }else {
                quizImage.setImageDrawable(null);
            }
            question.setText(quizQuestion.getQuestion());
            option1.setText(quizQuestion.getOption1());
            option2.setText(quizQuestion.getOption2());
            option3.setText(quizQuestion.getOption3());
            option4.setText(quizQuestion.getOption4());
            progressIndicator.setProgressCompat(0, true);
            progressIndicator.setMax(60);
            new Thread(() -> {
                for (int i = 0; i < 60; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressIndicator.setProgressCompat(i, true);
                    if (i == 59) {
                        progressIndicator.setProgressCompat(0, true);
                        onNextButtonClicked(progressIndicator);
                    }
                }
            }).start();
        }else {
            quiz.addScore(mAuth.getCurrentUser().getUid(), score);
            System.out.println(quiz.getParticipants().get(quiz.getParticipants().size()-1).getId());
            System.out.println(quiz.getParticipants().get(quiz.getParticipants().size()-1).getScore());
            db.collection("quiz").document(quiz.getQuizId()).set(quiz);
            Toast.makeText(this, "Quiz Completed", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Your Score: "+score, Toast.LENGTH_LONG).show();
            finish();
        }

    }

    public void onNextButtonClicked(View view) {
        String answerGiven = radioGroup.getCheckedRadioButtonId() == R.id.option1 ? option1.getText().toString() : radioGroup.getCheckedRadioButtonId() == R.id.option2 ? option2.getText().toString() : radioGroup.getCheckedRadioButtonId() == R.id.option3 ? option3.getText().toString() : radioGroup.getCheckedRadioButtonId() == R.id.option4 ? option4.getText().toString() : null;
        if (answerGiven != null) {
            if (answerGiven.equals(quizQuestions.get(questionNumber).getCorrectAnswer())) {
                score++;
            }
            questionNumber++;
            setQuestions(questionNumber);
            radioGroup.clearCheck();

        }
    }
}