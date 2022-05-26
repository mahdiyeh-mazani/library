import books.AbstractBook;
import books.Book;
import books.Comic;
import books.Novel;
import org.junit.jupiter.api.Test;
import books.AbstractBook;
import books.Book;
import books.Comic;
import books.Novel;
import org.junit.jupiter.api.Test;

/* Ghesmat tashvighi test hash ba reflecion neveshte shode *_* */
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryTest {
    @Test
    public void bookTest() {
        // Test Book class here
        AbstractBook harrypotter = new Book("harrypotter", "Joanne Rowling", "harry,hermione,ronald");
        harrypotter.toString();
        Book got = new Book("A Game Of Thrones", "George Raymond Richard Martin", "british legend");
        got.toString();
        Comic deadpool = new Comic("Deadpool", "Rhett Reese", "marvel");
        deadpool.toString();
        Novel the40rulesofloves = new Novel("The 40 Rules Of Love", "Elif Safak", "history,novel");
        the40rulesofloves.toString();
        assertEquals(false, harrypotter.isBorrowed());
        assertEquals("harrypotter", harrypotter.getTitle());
        assertEquals("Joanne Rowling", harrypotter.getAuthor());
        assertEquals("british legend", got.getSubtitle());
        assertEquals("marvel", deadpool.getCompany());
        assertEquals(true, harrypotter.borrowed());
        assertEquals(false, harrypotter.returned());
        assertArrayEquals(new String[]{"history", "novel"}, the40rulesofloves.getGenres());
        assertEquals("history,novel", the40rulesofloves.getAllGenres());
        harrypotter.toString();
        got.toString();
        deadpool.toString();
        the40rulesofloves.toString();
        Book book = new Book("t", "a", "s");

        assertDoesNotThrow(() -> {
            Field subtitleField = Book.class.getDeclaredField("subtitle");
            assertTrue(Modifier.isPrivate(subtitleField.getModifiers()));
        });

        assertEquals("s", book.getSubtitle());
        assertEquals("*[ t - s ]* by: a", book.toString());
    }


    @Test
    public void libraryTest() throws LibraryFullException, NoSuchFieldException, IllegalAccessException {
        Field maxBooksField = Library.class.getDeclaredField("MaxBooks");
        maxBooksField.setAccessible(true);
        assertEquals(100000, (int) maxBooksField.get(Library.class));

        Library.debugMode(true);

        assertEquals(5, (int) maxBooksField.get(Library.class));

        Library library = new Library("The Address. St 2", 8, 13);

        assertEquals("The Address. St 2", library.getAddress());
        assertEquals(8, library.getOpeningHours());
        assertEquals(13, library.getClosingTime());
        assertEquals("8 - 13", library.getHours());

        AbstractBook bookA = new Novel(
                "Wish You Were Here",
                "Renee Carlino",
                "Romance,Fiction,New Adult"
        );

        AbstractBook bookB = new Novel(
                "Before We Were Strangers",
                "Renee Carlino",
                "Romance,Contemporary,New Adult"
        );

        AbstractBook bookC = new Book(
                "Deep Work",
                "Cal Newport",
                "Rules for Focused Success in a Distracted World"
        );

        AbstractBook bookD = new Comic(
                "Doctor Strange",
                "Steve Ditko",
                "Marvel"
        );

        AbstractBook bookE = new Book(
                "Test book 1",
                "Test author 1",
                "Test subtitle 1"
        );

        AbstractBook bookF = new Book(
                "Test book 2",
                "Test author 2",
                "Test subtitle"
        );

        assertEquals(0, library.getBooksCount());

        assertDoesNotThrow(() -> {
            library.addBook(bookA);
            library.addBook(bookB);
            library.addBook(bookC);
            assertEquals(3, library.getBooksCount());
            library.addBook(bookD);
            library.addBook(bookE);
        });

        assertEquals(5, library.getBooksCount());

        assertThrows(LibraryFullException.class, () -> {
            library.addBook(bookF);
        });

        assertEquals(5, library.getBooksCount());

        assertEquals(
                bookA,
                library.findBook("Wish You Were Here")
        );

        assertNull(library.findBook("Wish You Were There"));

        assertArrayEquals(
                new AbstractBook[]{ bookA, bookB, bookC, bookD, bookE },
                library.getAvailableBooks()
        );

        library.borrowBook("Wish You Were Here");
        library.borrowBook("This book does not exists");


        assertArrayEquals(
                new AbstractBook[]{ bookB, bookC, bookD, bookE },
                library.getAvailableBooks()
        );

        assertTrue(library.isBorrowed("Wish You Were Here"));
        assertFalse(library.isBorrowed("Deep Work"));
        assertFalse(library.isBorrowed("Wrong book title"));

        library.returnBook("Wish You Were Here");
        library.returnBook("Wish You Were There");

        assertFalse(library.isBorrowed("Wish You Were Here"));

        assertNull(library.removeBook("Test book 2"));

        assertEquals(
                bookE,
                library.removeBook("Test book 1")
        );

        assertEquals(
                bookA,
                library.removeBook("Wish You Were Here")
        );

        library.debugMode(false);

        Library.debugMode(true);
        AbstractBook harrypotter = new Book("harrypotter", "Joanne Rowling", "harry,hermione,ronald");
        Book got = new Book("A Game Of Thrones", "George Raymond Richard Martin", "british legend");
        Comic deadpool = new Comic("Deadpool", "Rhett Reese", "marvel");
        Novel the40rulesofloves = new Novel("The 40 Rules Of Love", "Elif Safak","history,novel");
        Library library1 = new Library("babol", 6, 8);

        library1.addBook(harrypotter);
        library1.addBook(deadpool);
        library1.addBook(the40rulesofloves);
        assertEquals("babol", library1.getAddress());
        assertEquals(6, library1.getOpeningHours());
        assertEquals(8, library1.getClosingTime());
        assertEquals("6 - 8", library1.getHours());
        assertEquals(harrypotter, library1.findBook("harrypotter"));
        assertNull(library1.findBook("A Game Of Thrones"));
        assertEquals(3, library1.getBooksCount());
        assertEquals(the40rulesofloves, library1.removeBook("The 40 Rules Of Love"));
        assertNull(library1.removeBook("A Game Of Thrones"));
        library1.borrowBook("harrypotter");
        library1.borrowBook("Deadpool");
        library1.returnBook("Deadpool");
        library1.isBorrowed("harrypotter");
        assertArrayEquals(new AbstractBook[]{deadpool,the40rulesofloves}, library1.getAvailableBooks());
        Library.debugMode(false);





//         Test library class here
    }


    @Test
    public void abstractBookTest() {
        AbstractBook abstractBook = new AbstractBook("t", "a") {
            @Override
            public String toString() {
                return String.format("$ %s - %s $", title, author);
            }
        };

        assertEquals("t", abstractBook.getTitle());
        assertEquals("a", abstractBook.getAuthor());
        assertEquals("$ t - a $", abstractBook.toString());

        assertFalse(abstractBook.isBorrowed());
        abstractBook.borrowed();
        assertTrue(abstractBook.isBorrowed());
        abstractBook.returned();
        assertFalse(abstractBook.isBorrowed());

        Field[] fields = AbstractBook.class.getDeclaredFields();

        Arrays.stream(fields).forEach(field -> {
            assertTrue(Modifier.isProtected(field.getModifiers()));
        });
    }

    @Test
    public void comicTest() {
        Comic comic = new Comic("t", "a", "c");

        assertDoesNotThrow(() -> {
            Field companyField = Comic.class.getDeclaredField("company");
            assertTrue(Modifier.isPrivate(companyField.getModifiers()));
        });

        assertEquals("c", comic.getCompany());
        assertEquals("@= t (c) =@ writer: a", comic.toString());
    }


    @Test
    public void novelTest() {
        Novel novel = new Novel("t", "a", "g1,g2,g3");

        assertDoesNotThrow(() -> {
            Field genresField = Novel.class.getDeclaredField("genres");
            assertTrue(Modifier.isPrivate(genresField.getModifiers()));
        });

        assertArrayEquals(new String[]{"g1", "g2", "g3"}, novel.getGenres());
        assertEquals("g1,g2,g3", novel.getAllGenres());
        assertEquals("<[ t /g1,g2,g3 ]> from: a", novel.toString());
    }

    @Test
    public void libraryFieldsTest() {
        assertDoesNotThrow(() -> {
            Field addressField = Library.class.getDeclaredField("address");
            assertTrue(Modifier.isPrivate(addressField.getModifiers()));

            Field openingHoursField = Library.class.getDeclaredField("openingHours");
            assertTrue(Modifier.isPrivate(openingHoursField.getModifiers()));

            Field closingTimeField = Library.class.getDeclaredField(
                    "closingTime");
            assertTrue(Modifier.isPrivate(closingTimeField.getModifiers()));

            Field MaxBooksField = Library.class.getDeclaredField("MaxBooks");
            assertTrue(Modifier.isPrivate(MaxBooksField.getModifiers()));
            assertTrue(Modifier.isStatic(MaxBooksField.getModifiers()));

            Field booksField = Library.class.getDeclaredField("books");
            assertTrue(Modifier.isPrivate(booksField.getModifiers()));

            Field booksCountField = Library.class.getDeclaredField("booksCount");
            assertTrue(Modifier.isPrivate(booksCountField.getModifiers()));
        });
    }

}
