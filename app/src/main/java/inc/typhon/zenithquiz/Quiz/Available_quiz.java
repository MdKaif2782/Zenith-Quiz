package inc.typhon.zenithquiz.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import inc.typhon.zenithquiz.Adapters.QuizListAdapter;
import inc.typhon.zenithquiz.R;

public class Available_quiz extends AppCompatActivity {
    private ListView listView;
    private QuizListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_quiz);
        listView = findViewById(R.id.listView_available_quiz);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Quiz> quizList = new ArrayList<>();
        db.collection("quiz").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (int i = 0; i < task.getResult().size(); i++) {
                    Quiz quiz = task.getResult().getDocuments().get(i).toObject(Quiz.class);
                    quizList.add(quiz);
                    System.out.println(quiz.getQuizName()+" \nIs Added");
                    QuizQuestion quizQuestion = quiz.getQuizQuestions().get(0);
                    System.out.println(quizQuestion.getQuestion());
                }
               adapter = new QuizListAdapter(this, R.layout.available_quiz_item, quizList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(Available_quiz.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(Available_quiz.this, Quiz_exam.class);
            intent.putExtra("quizPosition", position);
            startActivity(intent);
        });





    }
}