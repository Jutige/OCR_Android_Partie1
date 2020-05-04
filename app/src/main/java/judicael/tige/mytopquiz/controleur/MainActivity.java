package judicael.tige.mytopquiz.controleur;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xml.sax.ext.Locator2;

import judicael.tige.mytopquiz.R;
import judicael.tige.mytopquiz.modele.User;

import static judicael.tige.mytopquiz.R.layout.activity_main;

public class  MainActivity extends AppCompatActivity {

    private TextView mMainText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;
    public static final int GAME_ACTIVITY_VALUE=42;

    private SharedPreferences mPreferences;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        mUser = new User();
        System.out.println("MainActivity::OnCreate()");

        //Les préférences sont des information stockées sur le téléphone.
        //le mode privée permet de les rendre uniquement accessibles par l'appli courante
        mPreferences = getPreferences(MODE_PRIVATE);
        System.out.println("Init des préférences");
        String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME,null);
        int ancScore = mPreferences.getInt(PREF_KEY_SCORE,-1);
        System.out.println("Score"+ancScore);
        System.out.println("récup des préférences : "+PREF_KEY_FIRSTNAME+firstname);

        //Manipulation des éléments graphiques par le controleur
        mMainText = (TextView) findViewById(R.id.activity_main_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mPlayButton.setEnabled(false);
        if (firstname !=null){
            mNameInput.setText(firstname);
            mPlayButton.setEnabled(true);
            if (ancScore!=-1){
                mMainText.setText("Welcome "+firstname+" !. Votre précédent score est "+ancScore);
            }
        }

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length()!=0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = mNameInput.getText().toString();
                mUser.setFirstname(firstname);

                mPreferences.edit().putString(PREF_KEY_FIRSTNAME,mUser.getFirstname()).apply();
                Intent gameActivityIntent=new Intent(MainActivity.this,GameActivity.class);
//                startActivity(gameActivityIntent);
                startActivityForResult(gameActivityIntent,GAME_ACTIVITY_VALUE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        System.out.println("onActivityResult en cours");
        System.out.println("RequestCode "+requestCode);
        System.out.println("resultCode "+resultCode);
        System.out.println("RESULT_OK" +RESULT_OK);

        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("RequestCode "+requestCode);
        System.out.println("resultCode "+resultCode);
        System.out.println("RESULT_OK" +RESULT_OK);
        if (GAME_ACTIVITY_VALUE == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            System.out.println("Valeur du score dans MainActivity "+score);
            mPreferences.edit().putInt(PREF_KEY_SCORE,score).apply();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("MainActivity::OnStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MainActivity::OnResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity::OnPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("MainActivity::OnStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("GameActivity::OnDestroy()");
    }
}
