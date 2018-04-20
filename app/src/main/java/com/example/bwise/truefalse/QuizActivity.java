package com.example.bwise.truefalse;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends Activity {

    //get references to the widgets
    private TextView scoreTextView;
    private TextView questionTextView;
    private Button trueButton;
    private Button falseButton;

    //declare instance variables
    private boolean answer;
    private int score = 0;
    private int questionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        scoreTextView = findViewById(R.id.scoreTextView);
        questionTextView = findViewById(R.id.questionTextView);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);

        //load the next question
        updateQuestion();

        //if user selects "true" as their answer - find answer+compare
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer == true) {
                    score++;
                    updateScore(score);

                    //check if the question was the last quesiton
                    if (questionNumber == QuizBook.questions.length) {
                        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
                // if the answer was incorrect follow same process
                else {
                    if (questionNumber == QuizBook.questions.length) {
                        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
            }
        });

        //if user selects "false" as their answer - find answer+compare
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer == false) {
                    score++;
                    updateScore(score);

                    //check if the question was the last quesiton
                    if (questionNumber == QuizBook.questions.length) {
                        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
                // if the answer was incorrect follow same process as for correct
                else {
                    if (questionNumber == QuizBook.questions.length) {
                        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", score);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
            }
        });
    }

    //advance to the next question in the array+keep the reference to the answer
    private void updateQuestion() {

        questionTextView.setText(QuizBook.questions[questionNumber]);
        answer = QuizBook.answers[questionNumber];
        questionNumber++;
    }

    //add to the score
    private void updateScore(int point) {
        scoreTextView.setText("" + score);
    }

    public void clickExit(View view) {
        askToClose();
    }

    @Override
    public void onBackPressed() {
        askToClose();
    }

    private void askToClose(){
        //create dialog box to ask for closing confirmation
        AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(true);
        //set the onClick for the dialog box buttons
        builder.setPositiveButton("Get Me Outta Here!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
