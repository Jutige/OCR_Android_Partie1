package judicael.tige.mytopquiz.modele;

import java.util.List;

/**
 * Créé par Judicaël le 02/05/2020
 */
public class Question {
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnwserINdex;

    public Question (String question, List<String>choiceList,int anwserINdex){
        System.out.println("Question à créer");
        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnwserINdex(anwserINdex);
        System.out.println("Une question de créé !");
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        mChoiceList = choiceList;
    }

    public int getAnwserINdex() {
        return mAnwserINdex;
    }

    public void setAnwserINdex(int anwserINdex) {
        mAnwserINdex = anwserINdex;
    }
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiceList=" + mChoiceList +
                ", mAnswerIndex=" + mAnwserINdex +
                '}';
    }
}
