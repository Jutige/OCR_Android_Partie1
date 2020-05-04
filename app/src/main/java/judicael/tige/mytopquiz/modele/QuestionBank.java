package judicael.tige.mytopquiz.modele;

import java.util.Collections;
import java.util.List;

/**
 * Créé par Judicaël le 02/05/2020
 */
public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;


    public QuestionBank(List<Question> questionList){
        mQuestionList=questionList;
        Collections.shuffle(mQuestionList);
        mNextQuestionIndex=0;
    }
    public Question getQuestion() {
        if (mNextQuestionIndex == mQuestionList.size()) {
            mNextQuestionIndex = 0;
        }
        return mQuestionList.get(mNextQuestionIndex++);
    }

    public List<Question> getQuestionList() {
        return mQuestionList;
    }

    public void setQuestionList(List<Question> questionList) {
        mQuestionList = questionList;
    }

    public int getMnextQuestionIndex() {
        return mNextQuestionIndex;
    }

    public void setMnextQuestionIndex(int mnextQuestionIndex) {
        mNextQuestionIndex = mnextQuestionIndex;
    }
}
