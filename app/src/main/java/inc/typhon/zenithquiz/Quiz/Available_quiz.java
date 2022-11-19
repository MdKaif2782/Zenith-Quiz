package inc.typhon.zenithquiz.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import inc.typhon.zenithquiz.Adapters.QuizListAdapter;
import inc.typhon.zenithquiz.R;

public class Available_quiz extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_quiz);
        List<Quiz> quizList = new ArrayList<>();

        List<QuizQuestion> quizQuestionList = new ArrayList<>();
        QuizQuestion quizQuestion = new QuizQuestion(
                "What is the capital of India?",
                "New Delhi",
                "Mumbai",
                "Chennai",
                "Kolkata",
                "New Delhi",
                "https://firebasestorage.googleapis.com/v0/b/zenith-quiz.appspot.com/o/quiz%2Fquiz1.jpg?alt=media&token=8b8b8b8b-8b8b-8b8b-8b8b-8b8b8b8b8b8b",
                "1",
                "1",
                1
                );
        quizQuestionList.add(quizQuestion);
        quizQuestionList.add(new QuizQuestion(
                "What is the capital of India?",
                "New Delhi",
                "Mumbai",
                "Chennai",
                "Kolkata",
                "New Delhi",
                "https://firebasestorage.googleapis.com/v0/b/zenith-quiz.appspot.com/o/quiz%2Fquiz1.jpg?alt=media&token=8b8b8b8b-8b8b-8b8b-8b8b-8b8b8b8b8b8b",
                "1",
                "1",
                1
        ));
        quizQuestionList.add(new QuizQuestion(
                "What is the capital of India?",
                "New Delhi",
                "Mumbai",
                "Chennai",
                "Kolkata",
                "New Delhi",
                "https://firebasestorage.googleapis.com/v0/b/zenith-quiz.appspot.com/o/quiz%2Fquiz1.jpg?alt=media&token=8b8b8b8b-8b8b-8b8b-8b8b-8b8b8b8b8b8b",
                "1",
                "1",
                1
        ));
        quizQuestionList.add(new QuizQuestion(
                "What is the capital of India?",
                "New Delhi",
                "Mumbai",
                "Chennai",
                "Kolkata",
                "New Delhi",
                "https://firebasestorage.googleapis.com/v0/b/zenith-quiz.appspot.com/o/quiz%2Fquiz1.jpg?alt=media&token=8b8b8b8b-8b8b-8b8b-8b8b-8b8b8b8b8b8b",
                "1",
                "1",
                1
        ));
        Quiz test = new Quiz();
        test.setQuizName("Test");
        test.setQuizDescription("This is a test");
        test.setQuizImage("https://firebasestorage.googleapis.com/v0/b/zenith-quiz.appspot.com/o/quiz%2Fquiz1.jpg?alt=media&token=8b8b8b8b-8b8b-8b8b-8b8b-8b8b8b8b8b8b");
        test.setQuizId("1");
        test.setSeniorQuiz(true);
        test.setQuizTimePerQuestion(10);
        test.setQuizTotalQuestions(10);
        test.setQuizScheduledTime(1590000000000L);
        test.setQuizQuestions(quizQuestionList);
        quizList.add(test);
        quizList.add(new Quiz(
                "Test",
                "This is a test 2",
                "https://firebasestorage.googleapis.com/v0/b/zenith-quiz.appspot.com/o/quiz%2Fquiz1.jpg?alt=media&token=8b8b8b8b-8b8b-8b8b-8b8b-8b8b8b8b8b8b",
                "1",
                true,
                10,
                10,
                1590000000000L,
                quizQuestionList
        ));
        quizList.add(new Quiz(
                "Test",
                "This is a test 3",
                "https://firebasestorage.googleapis.com/v0/b/zenith-quiz.appspot.com/o/quiz%2Fquiz1.jpg?alt=media&token=8b8b8b8b-8b8b-8b8b-8b8b-8b8b8b8b8b8b",
                "1",
                true,
                10,
                10,
                1590000000000L,
                quizQuestionList
        ));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (Quiz quiz : quizList) {
            db.collection("quiz").document(quiz.getQuizId()).set(quiz);
        }




        listView = findViewById(R.id.listView_available_quiz);
        QuizListAdapter adapter = new QuizListAdapter(this, R.layout.available_quiz_item, quizList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}