package inc.typhon.zenithquiz.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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
        quizList.add(new Quiz("Physics",
                "Idk man",
                "https://e7.pngegg.com/pngimages/481/800/png-clipart-molecular-biology-cell-biology-physics-cover-book-miscellaneous-class.png",
                false,
                60,
                10,
                1668689611
                ));
        quizList.add(new Quiz("Chemistry",
                "Idk man",
                "https://e7.pngegg.com/pngimages/481/800/png-clipart-molecular-biology-cell-biology-physics-cover-book-miscellaneous-class.png",
                true,
                60,
                10,
                1668688611
        ));
        quizList.add(new Quiz("IT Quiz",
                "Idk man",
                "https://e7.pngegg.com/pngimages/481/800/png-clipart-molecular-biology-cell-biology-physics-cover-book-miscellaneous-class.png",
                false,
                60,
                10,
                1668688611
        ));
        quizList.add(new Quiz("Botany Quiz",
                "Idk man",
                "https://e7.pngegg.com/pngimages/481/800/png-clipart-molecular-biology-cell-biology-physics-cover-book-miscellaneous-class.png",
                false,
                60,
                10,
                1668688611
        ));
        quizList.add(new Quiz("I have no idea",
                "Idk man",
                "https://e7.pngegg.com/pngimages/481/800/png-clipart-molecular-biology-cell-biology-physics-cover-book-miscellaneous-class.png",
                true,
                60,
                500,
                1668688611
        ));
        quizList.add(new Quiz("Andrew tate",
                "Idk man",
                "https://e7.pngegg.com/pngimages/481/800/png-clipart-molecular-biology-cell-biology-physics-cover-book-miscellaneous-class.png",
                false,
                60,
                100,
                1668688611
        ));
        listView = findViewById(R.id.listView_available_quiz);
        QuizListAdapter adapter = new QuizListAdapter(this, R.layout.available_quiz_item, quizList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}