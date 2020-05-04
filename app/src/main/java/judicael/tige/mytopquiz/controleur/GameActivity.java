package judicael.tige.mytopquiz.controleur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
import judicael.tige.mytopquiz.R;
import judicael.tige.mytopquiz.modele.Question;
import judicael.tige.mytopquiz.modele.QuestionBank;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView gTextQuestion;
    private Button gButtonAnswer1;
    private Button gButtonAnswer2;
    private Button gButtonAnswer3;
    private Button gButtonAnswer4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestion;

    private boolean mEnabledTouchEvents;

    public static final String BUNDLE_EXTRA_SCORE = " BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    public QuestionBank generateQuestions () {
        Question question1 = new Question("Who is the creator of Android?",
                                          Arrays.asList("Andy Rubin",
                                                        "Steve Wozniak",
                                                        "Jake Wharton",
                                                        "Paul Smith"),
                                          0);

        Question question2 = new Question("When did the first man land on the moon?",
                                          Arrays.asList("1958",
                                                        "1962",
                                                        "1967",
                                                        "1969"),
                                          3);

        Question question3 = new Question("What is the house number of The Simpsons?",
                                          Arrays.asList("42",
                                                        "101",
                                                        "666",
                                                        "742"),
                                          3);

        System.out.println("les questions ont été générées");
        return new QuestionBank(Arrays.asList(question1,
                                              question2,
                                              question3));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        System.out.println("GameActivity::OnCreate()");
        mQuestionBank=this.generateQuestions();

        if (savedInstanceState!=null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestion = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }else {
            mScore = 0;
            mNumberOfQuestion = 3;
        }

        mEnabledTouchEvents = true;

        System.out.println("Après génération des questions");
    //    System.out.println(mQuestionBank.getQuestion().toString());
        gTextQuestion = (TextView) findViewById(R.id.game_question_txt);
        gButtonAnswer1 = (Button) findViewById(R.id.game_answer1_btn);
        gButtonAnswer2 = (Button) findViewById(R.id.game_answer2_btn);
        gButtonAnswer3 = (Button) findViewById(R.id.game_answer3_btn);
        gButtonAnswer4 = (Button) findViewById(R.id.game_answer4_btn);

        gButtonAnswer1.setTag(0);
        gButtonAnswer2.setTag(1);
        gButtonAnswer3.setTag(2);
        gButtonAnswer4.setTag(3);

        gButtonAnswer1.setOnClickListener(this);
        gButtonAnswer2.setOnClickListener(this);
        gButtonAnswer3.setOnClickListener(this);
        gButtonAnswer4.setOnClickListener(this);

        mCurrentQuestion=mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    private void displayQuestion(Question currentQuestion) {
        gTextQuestion.setText(currentQuestion.getQuestion());
        gButtonAnswer1.setText(currentQuestion.getChoiceList().get(0));
        gButtonAnswer2.setText(currentQuestion.getChoiceList().get(1)) ;
        gButtonAnswer3.setText(currentQuestion.getChoiceList().get(2)) ;
        gButtonAnswer4.setText(currentQuestion.getChoiceList().get(3)) ;
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
        if (responseIndex == mCurrentQuestion.getAnwserINdex()){
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            Toast.makeText(this,"Wrong answer",Toast.LENGTH_SHORT).show();
        }
        mEnabledTouchEvents = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnabledTouchEvents = true;

                if (--mNumberOfQuestion ==0 ){
                    //fin du jeu
                    endGame();
                    System.out.println("Score du jeu :"+mScore);
                }else{
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        },2000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnabledTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bien joué !")
                .setMessage("Ton score est de "+mScore)
                .setPositiveButton("OKKK", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE,mScore);
                        setResult(RESULT_OK,intent);
;                       finish();
                    }
                })
                .setNegativeButton("Nooon", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create()
                .show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(BUNDLE_EXTRA_SCORE,mScore);
        outState.putInt(BUNDLE_STATE_QUESTION,mNumberOfQuestion);
        System.out.println("GameActivity::OnSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("GameActivity::OnStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("GameActivity::OnResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("GameActivity::OnPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("GameActivity::OnStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("GameActivity::OnDestroy()");
    }
}
