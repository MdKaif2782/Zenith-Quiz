package inc.typhon.zenithquiz.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import inc.typhon.zenithquiz.R;

public class Quiz_exam extends AppCompatActivity {
    private FirebaseFirestore db;
    private int quizPosition;
    private Quiz quiz;
    private List<QuizQuestion> quizQuestions;
    private RadioButton option1, option2, option3, option4;
    private TextView question;
    private Button nextButton;
    private LinearProgressIndicator progressIndicator;
    private int questionNumber = 0;
    private ImageView quizImage;

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
        progressIndicator = findViewById(R.id.progressBar_quiz);

        db = FirebaseFirestore.getInstance();
        quizPosition = getIntent().getIntExtra("quizPosition", 0);
        db.collection("quiz").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                quiz = task.getResult().getDocuments().get(quizPosition).toObject(Quiz.class);
                assert quiz != null;
                quizQuestions = quiz.getQuizQuestions();
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
        }else {
            finish();
        }

    }

    public void onNextButtonClicked(View view) {
        questionNumber++;
        setQuestions(questionNumber);
    }
}