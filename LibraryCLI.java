package books;

import java.util.Arrays;
import java.util.Scanner;

public class LibraryCLI {
    Library library;

    public LibraryCLI() {
        System.out.println("welcome");
    }

    public String run(String cmd) throws LibraryFullException {
        String[] q = cmd.split(" ");
        String w = null;
        String address;
        int o;
        int c;

        if (q[0].equals("exit")){return "exit";}
        else if (q[1].equals("create"))
        {
            address=q[3];
            o=Integer.parseInt(q[5]);
            c=Integer.parseInt(q[7]);

            library = new Library(address, o, c);
            System.out.println("done!");
        }

        else if (q[1].equals("get")) {
            if (q[2].equals("adr")) {
                return library.getAddress();
            }
            if (q[2].equals("opn")) {
                w = String.valueOf(library.getOpeningHours());
                if (library.getOpeningHours() == 0) {
                    w = "error";
                }
            }
            if (q[2].equals("cls")) {
                return String.valueOf(library.getClosingTime());
            }
            if (q[2].equals("hrs")) {
                return library.getHours();
            }
        }
        else if (q[1].equals("add")) {

            AbstractBook book = null;
            if (q[2].equals("book")) {
                book = new Book(q[4], q[6], q[8]);
            }
            if (q[2].equals("comic")) {
                book = new Comic(q[4], q[6], q[8]);
            }
            if (q[2].equals("novel")) {
                book = new Novel(q[4], q[6], q[8]);
            }
            library.addBook(book);
        }

        if (q[1].equals("remove")) {
            w = String.valueOf(library.removeBook(q[2]));
        }
        if (q[1].equals("find")) {
            w = String.valueOf(library.findBook(q[2]));
        }
        if (q[1].equals("barrow")) {
            if (library.isBorrowed(q[2]) == true || library.findBook(q[2]) == null) {
                return  "error";
            } else return String.valueOf(library.borrowBook(q[2]));
        }
        if (q[1].equals("return")) {
            if (library.isBorrowed(q[2]) == false || library.findBook(q[2]) == null)
                return  "error";
            else return String.valueOf(library.returnBook(q[2]));
        }
        if (q[1].equals("available")) {
            return Arrays.toString(library.getAvailableBooks());
        }
        if (q[1].equals("count")) {
            return String.valueOf(library.getBooksCount());
        }
        if (q[1].equals("status")) {
            return String.valueOf(library.isBorrowed(q[2]));
        }


        return w;
    }

    public static void main(String[] args) throws LibraryFullException {
        LibraryCLI libraryCLI = new LibraryCLI();

        Scanner scanner = new Scanner(System.in);

        String input;
        String output;

        while (true) {
            input = scanner.nextLine();
            output = libraryCLI.run(input);

            if (output != null)
                if (output.equals("exit")) break;
                else
                    System.out.println(output);
        }
    }
}
