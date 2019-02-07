package tech.bts.cardgame;

public class GreetingOptions {

   private String name;
   private String title;
   private boolean formal;
   private boolean exclamation;
   private boolean askQuestion;

    public GreetingOptions(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFormal() {
        return formal;
    }

    public boolean isExclamation() {
        return exclamation;
    }

    public boolean isAskQuestion() {
        return askQuestion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFormal(boolean formal) {
        this.formal = formal;
    }

    public void setExclamation(boolean exclamation) {
        this.exclamation = exclamation;
    }

    public void setAskQuestion(boolean askQuestion) {
        this.askQuestion = askQuestion;
    }
}

