package books;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public abstract class AbstractBook {
    protected String title;
    protected String author;
    protected Boolean borrowed=false;



    public AbstractBook(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public boolean borrowed() {
        borrowed = true;
        return borrowed;
    }

    public boolean returned() {
        borrowed = false;
        return  borrowed;
    }


    public Boolean isBorrowed() {

        return borrowed;
    }


    public String getAuthor() {
        return author;
    }


    public String getTitle() {
        return title;
    }

    public abstract String toString();

}
