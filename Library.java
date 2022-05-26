package books;

public class Library {
    private String address=null;
    private int openingHours=0;
    private int closingTime=0;
    private AbstractBook[] books=new AbstractBook[MaxBooks];
    private int booksCount=0;
     private static int MaxBooks = 100000;

    public Library(String address, int openingHours,int closingTime) {
        this.address = address;
        this.openingHours = openingHours;
        this.closingTime = closingTime;

    }

    public static int debugMode(boolean mode) {
        if (mode)
        {MaxBooks = 5;}
        else {MaxBooks = 100000;}
        return MaxBooks;
    }

    public void addBook(AbstractBook book) throws LibraryFullException {
        if (booksCount == MaxBooks) throw new LibraryFullException();
        this.books[booksCount++] = book;
    }

    public String getAddress() {
        return address;
    }

    public int getOpeningHours() {
        return openingHours;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public String getHours()
    {
        return openingHours + " - " + closingTime;
    }

    public AbstractBook removeBook(String title) {
        AbstractBook[] Books = new AbstractBook[MaxBooks];
        AbstractBook q = null;
        int j = 0;
        for(int i = 0; i < booksCount; i++){
            if(!this.books[i].getTitle().equals(title)) {
                Books[j] = this.books[i];
                j++;
            }
            else q = this.books[i];
        }

        return q;


    }
    public AbstractBook findBook(String title){

        for(int i = 0; i < booksCount; i++){
            if(this.books[i].getTitle().equals(title)){
                return this.books[i];
            }

        }
        return null;

    }

    public boolean borrowBook(String title) {
        boolean q = false;
        for(int i = 0; i < booksCount; i++){
            if(this.books[i].getTitle().equals(title)){
               q=this.books[i].borrowed();

            }
        }

     return q;
    }

    public boolean isBorrowed(String title) {
        boolean q = false;
        for(int i = 0; i < booksCount; i++){
            if(this.books[i].getTitle().equals(title)){
                q=this.books[i].isBorrowed();
            }
        }
        return q;
    }

    public boolean returnBook(String title) {
        boolean q=true;
        for(int i = 0; i < booksCount; i++){
            if(this.books[i].getTitle().equals(title)){
                q=this.books[i].returned();
            }
        }
        return q;
    }

    public AbstractBook[] getAvailableBooks() {
        int i = 0;
        for(int k = 0; k < booksCount; k++){
            if(!this.books[k].isBorrowed()){i++;}
        }
        AbstractBook[] q = new AbstractBook[i];
        int j = 0;
        for(int z = 0; z < booksCount; z++){
            if(!this.books[z].isBorrowed()){
                q[j] = this.books[z];
                j++;
            }
        }
        return q;
    }
    public int getBooksCount()
    {
        return booksCount;
    }

}
