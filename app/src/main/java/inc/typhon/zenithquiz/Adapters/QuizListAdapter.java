package inc.typhon.zenithquiz.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

import inc.typhon.zenithquiz.Quiz.Quiz;
import inc.typhon.zenithquiz.R;

public class QuizListAdapter extends ArrayAdapter<Quiz> {
    public QuizListAdapter(@NonNull Context context, int resource, @NonNull List<Quiz> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Quiz quiz = getItem(position);
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.available_quiz_item, null);
        }
        ImageView quiz_image = convertView.findViewById(R.id.imageView_quiz);
        TextView quiz_name = convertView.findViewById(R.id.textView_quiz_name);
        TextView quiz_duration = convertView.findViewById(R.id.textView_duration);
        TextView quiz_question_count = convertView.findViewById(R.id.textView_quiz_question_count);
        TextView level = convertView.findViewById(R.id.textView_quiz_level);
        TextView schedule = convertView.findViewById(R.id.textView_quiz_schedule);
        TextView quiz_time_remaining = convertView.findViewById(R.id.textView_timer);


        Glide.with(getContext()).load(quiz.getQuizImage()).into(quiz_image);
        quiz_name.setText(quiz.getQuizName());
        int duration = quiz.getQuizTimePerQuestion() * quiz.getQuizTotalQuestions();
        //formatting the duration
        int hours = duration / 3600;
        int minutes = (duration % 3600) / 60;
        int seconds = duration % 60;
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        quiz_duration.setText(time);
        quiz_question_count.setText(String.valueOf(quiz.getQuizTotalQuestions()));
        level.setText(quiz.isSeniorQuiz() ? "Senior" : "Junior");
        long epoch = quiz.getQuizScheduledTime();
        //format as 11 Jan 2020 12:00:00
        String date = new java.text.SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new java.util.Date(epoch * 1000));
        schedule.setText(date);
        long timeRemaining = quiz.getQuizScheduledTime() - System.currentTimeMillis() / 1000;
        if (timeRemaining > 0) {
            //formatting the time remaining
            int hoursRemaining = (int) timeRemaining / 3600;
            int minutesRemaining = (int) ((timeRemaining % 3600) / 60);
            int secondsRemaining = (int) timeRemaining % 60;
            String timeRemainingString = String.format("%02d:%02d:%02d", hoursRemaining, minutesRemaining, secondsRemaining);
            quiz_time_remaining.setText(timeRemainingString);
        } else if (timeRemaining<0 && timeRemaining>(-quiz.getQuizTimePerQuestion()*quiz.getQuizTotalQuestions())){
            quiz_time_remaining.setText("Started");
        } else {
            quiz_time_remaining.setText("Ended");
        }

        return convertView;
    }
}
