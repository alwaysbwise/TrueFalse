package com.example.bwise.truefalse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends Activity {

    //get references to the widgets
    TextView textViewFinalScore;
    TextView textViewGrade;
    Button buttonRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        textViewFinalScore = findViewById(R.id.textViewFinalScore);
        textViewGrade = findViewById(R.id.textViewGrade);
        buttonRetry = findViewById(R.id.buttonRetry);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");

        textViewFinalScore.setText("You scored " + score + " out of " + QuizBook.questions.length);

        if (score >= 9){
            textViewGrade.setText("Excellent!");
        }else if (score >= 7 && score < 9){
            textViewGrade.setText("Not Bad..");
        }else {
            textViewGrade.setText("Hit the books!");
        }

        buttonRetry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(ResultsActivity.this, QuizActivity.class));
                ResultsActivity.this.finish();
            }
        });
    }
}
