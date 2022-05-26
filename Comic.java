package books;

public class Comic extends AbstractBook {
    private String company;

    public Comic(String title, String author, String company) {
        super(title, author);
        this.company = company;


    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        String q = "@= " + title + " (" +company + ") =@ writer: " + author;
        return q;
    }


}
