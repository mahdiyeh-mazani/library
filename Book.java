package books;

public class Book extends AbstractBook {
    private String subtitle;


    public Book(String title, String author, String subtitle) {
        super(title, author);
        this.subtitle = subtitle;

    }

    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public String toString() {
        String q = "*[ " + title + " - " + subtitle + " ]* by: " + author;
        return q;
    }
}
