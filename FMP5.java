import java.util.*;

class Book {

    private int id;

    private String title;

    private int availableCopies;

    private int borrowedCopies;

    private LinkedList<String> waitingList = new LinkedList<>();

    private HashSet<String> reservationKeys = new HashSet<>();

    public Book(int id, String title, int availableCopies, int borrowedCopies) {
this.id = id;

        this.title = title;

        this.availableCopies = availableCopies;

        this.borrowedCopies = borrowedCopies;

    }

    public int getAvailable() { return availableCopies; }

    public int getBorrowed() { return borrowedCopies; }

    public void borrow(String patron) {

        if (availableCopies > 0) {

            availableCopies--;

            borrowedCopies++;

            System.out.println("Available " + availableCopies + ", borrowed " + borrowedCopies);

        } else {

            System.out.println("No copies available");

        }

    }

    public void reserve(String patron) {

        String key = patron.trim().toLowerCase();

        if (reservationKeys.contains(key)) {

            System.out.println("Duplicate rejected");

        } else {

            waitingList.add(patron);

            reservationKeys.add(key);

            System.out.println(patron + " added to waiting list");

        }

    }

    public void returnBook() {

        if (borrowedCopies > 0) {

            borrowedCopies--;

            if (!waitingList.isEmpty()) {

                String next = waitingList.poll();

                reservationKeys.remove(next.toLowerCase());

                borrowedCopies++;

                System.out.println("Copy assigned to " + next + "; available " + availableCopies + ", borrowed " + borrowedCopies + "; " + (!waitingList.isEmpty() ? waitingList.getFirst() + " waiting" : ""));

            } else {

                availableCopies++;

                System.out.println("Available " + availableCopies + ", borrowed " + borrowedCopies);

            }

        } else {

            System.out.println("No borrowed copies to return.");

        }

    }

    public void find() {

        System.out.println("Available " + availableCopies + ", borrowed " + borrowedCopies);

    }

    public void showWaiting() {

        if (waitingList.isEmpty()) {

            System.out.println("No waiting list.");

        } else {

            System.out.print("Waiting: ");

            Iterator<String> it = waitingList.iterator();

            while (it.hasNext()) {

                System.out.print(it.next() + (it.hasNext() ? ", " : ""));

            }

            System.out.println();

        }

    }

}

public class FMP5 {

    private static HashMap<Integer, Book> library = new HashMap<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        library.put(1, new Book(1, "Java Basics", 1, 0));

        library.put(2, new Book(2, "Algorithms", 0, 0));

        library.put(3, new Book(3, "Databases", 1, 0));

        int choice = -1;

        do {

            System.out.println("\n--- Library System ---");

            System.out.println("1 Borrow | 2 Reserve | 3 Return | 4 Find | 5 Show waiting | 0 Exit");

            System.out.print("Enter choice: ");

            try {

                choice = Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Invalid menu option.");

                continue;

            }

            if (choice == 0) break;

            if (choice < 1 || choice > 5) {

                System.out.println("Invalid menu option.");

                continue;

            }

            System.out.print("Enter book ID: ");

            int bookId;

            try {

                bookId = Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Malformed number.");

                continue;

            }

            Book book = library.get(bookId);

            if (book == null) {

                System.out.println("Book not found.");

                continue;

            }

            switch (choice) {

                case 1:

                    System.out.print("Enter patron name: ");

                    String bPatron = scanner.nextLine().trim();

                    if (!bPatron.isEmpty()) book.borrow(bPatron);

                    else System.out.println("Patron name cannot be empty.");

                    break;

                case 2:

                    System.out.print("Enter patron name: ");

                    String rPatron = scanner.nextLine().trim();

                    if (!rPatron.isEmpty()) book.reserve(rPatron);

                    else System.out.println("Patron name cannot be empty.");

                    break;

                case 3:

                    book.returnBook();

                    break;

                case 4:

                    book.find();

                    break;

                case 5:

                    book.showWaiting();

                    break;

            }

        } while (choice != 0);

        System.out.println("Exiting application.");

    }

}
 
