package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Jumlah Soalan : "+totalQuestion);

        loadNewQuestion();




    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.CYAN);
        ansB.setBackgroundColor(Color.CYAN);
        ansC.setBackgroundColor(Color.CYAN);
        ansD.setBackgroundColor(Color.CYAN);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.WHITE);

        }

    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        if(currentQuestionIndex==0)
        {
            ImageView imgView = (ImageView)findViewById(R.id.imageView3);
            imgView.setBackgroundResource(R.drawable.cat);
        }

        else if(currentQuestionIndex==1)
        {
            ImageView imgView = (ImageView)findViewById(R.id.imageView3);
            imgView.setBackgroundResource(R.drawable.house);
        }

        else if(currentQuestionIndex==2)
        {
            ImageView imgView = (ImageView)findViewById(R.id.imageView3);
            imgView.setBackgroundResource(R.drawable.woman);
        }
        else if(currentQuestionIndex==3)
        {
            ImageView imgView = (ImageView)findViewById(R.id.imageView3);
            imgView.setBackgroundResource(R.drawable.three);
        }

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Lulus";
        }else{
            passStatus = "Gagal";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Skor anda ialah  "+ score+" daripada "+ totalQuestion)
                .setPositiveButton("Ulang Semula",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

}