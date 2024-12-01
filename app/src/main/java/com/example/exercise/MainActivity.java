package com.example.exercise;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editExerciseName, editDuration, editCaloriesBurned;
    private Button addExerciseButton;
    private ListView exerciseListView;
    private ArrayList<String> exerciseList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editExerciseName = findViewById(R.id.editExerciseName);
        editDuration = findViewById(R.id.editDuration);
        editCaloriesBurned = findViewById(R.id.editCaloriesBurned);
        addExerciseButton = findViewById(R.id.addExerciseButton);
        exerciseListView = findViewById(R.id.exerciseListView);

        exerciseList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseList);
        exerciseListView.setAdapter(adapter);

        addExerciseButton.setOnClickListener(v -> {
            String name = editExerciseName.getText().toString().trim();
            String duration = editDuration.getText().toString().trim();
            String calories = editCaloriesBurned.getText().toString().trim();

            if (name.isEmpty() || duration.isEmpty() || calories.isEmpty()) {
                Toast.makeText(this, "모든 정보를 입력해주세요!", Toast.LENGTH_SHORT).show();
                return;
            }

            String record = "운동: " + name + ", 시간: " + duration + "분, 칼로리: " + calories + "kcal";
            exerciseList.add(record);
            adapter.notifyDataSetChanged();

            editExerciseName.setText("");
            editDuration.setText("");
            editCaloriesBurned.setText("");
        });

        exerciseListView.setOnItemClickListener((parent, view, position, id) -> {
            exerciseList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "운동 기록이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        });
    }
}
