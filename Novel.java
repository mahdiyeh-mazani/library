package books;

import java.util.Arrays;

public class Novel extends AbstractBook {
  private   String[] genres;

    public  Novel(String title, String author, String genres) {
        super(title, author);
        this.genres = genres.split(",");

    }

    public String[] getGenres() {
        return genres;
    }

    public String getAllGenres() {


        return String.join(",",genres);
    }

    @Override
    public String toString() {
        String q = "<[ " + title + " /" + String.join(",",genres)+ " ]> from: " + author;
        return q;
    }

}
