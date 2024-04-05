package com.mycompany.duranoact3;

interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
    String getTitle();
}

class Book implements LibraryItem {
    private String title;
    private String author;
    private boolean borrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.borrowed = false;
    }

    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("Book \"" + title + "\" by " + author + " has been borrowed.");
        } else {
            System.out.println("Book \"" + title + "\" by " + author + " is already borrowed.");
        }
    }

    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("Book \"" + title + "\" by " + author + " has been returned.");
        } else {
            System.out.println("Book \"" + title + "\" by " + author + " is not borrowed.");
        }
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public String getTitle() {
        return title;
    }
}

class DVD implements LibraryItem {
    private String title;
    private String director;
    private boolean borrowed;

    public DVD(String title, String director) {
        this.title = title;
        this.director = director;
        this.borrowed = false;
    }

    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("DVD \"" + title + "\" directed by " + director + " has been borrowed.");
        } else {
            System.out.println("DVD \"" + title + "\" directed by " + director + " is already borrowed.");
        }
    }

    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("DVD \"" + title + "\" directed by " + director + " has been returned.");
        } else {
            System.out.println("DVD \"" + title + "\" directed by " + director + " is not borrowed.");
        }
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public String getTitle() {
        return title;
    }
}

abstract class LibraryUser {
    public abstract void borrowItem(LibraryItem item);
    public abstract void returnItem(LibraryItem item);
    public abstract void printItemsBorrowed();
}

class Student extends LibraryUser {
    private String name;
    private LibraryItem[] borrowedItems;
    private int borrowedItemCount;

    public Student(String name) {
        this.name = name;
        this.borrowedItems = new LibraryItem[5]; 
        this.borrowedItemCount = 0;
    }

    public void borrowItem(LibraryItem item) {
        if (borrowedItemCount < 5) {
            item.borrowItem();
            borrowedItems[borrowedItemCount++] = item;
        } else {
            System.out.println("Maximum borrowing limit reached for student " + name);
        }
    }

    public void returnItem(LibraryItem item) {
        item.returnItem();
        for (int i = 0; i < borrowedItemCount; i++) {
            if (borrowedItems[i].getTitle().equals(item.getTitle())) {
                borrowedItems[i] = borrowedItems[borrowedItemCount - 1];
                borrowedItems[borrowedItemCount - 1] = null;
                borrowedItemCount--;
                break;
            }
        }
    }

    public void printItemsBorrowed() {
        System.out.println("Student: " + name);
        System.out.println("Borrowed Items:");
        for (int i = 0; i < borrowedItemCount; i++) {
            System.out.println("- " + borrowedItems[i].getClass().getSimpleName() + ": " + borrowedItems[i].getTitle());
        }
        System.out.println();
    }
}

class Teacher extends LibraryUser {
    private String name;
    private LibraryItem[] borrowedItems;
    private int borrowedItemCount;

    public Teacher(String name) {
        this.name = name;
        this.borrowedItems = new LibraryItem[10]; 
        this.borrowedItemCount = 0;
    }

    public void borrowItem(LibraryItem item) {
        if (borrowedItemCount < 10) {
            item.borrowItem();
            borrowedItems[borrowedItemCount++] = item;
        } else {
            System.out.println("Maximum borrowing limit reached for teacher " + name);
        }
    }

    public void returnItem(LibraryItem item) {
        item.returnItem();
        for (int i = 0; i < borrowedItemCount; i++) {
            if (borrowedItems[i].getTitle().equals(item.getTitle())) {
                borrowedItems[i] = borrowedItems[borrowedItemCount - 1];
                borrowedItems[borrowedItemCount - 1] = null;
                borrowedItemCount--;
                break;
            }
        }
    }

    public void printItemsBorrowed() {
        System.out.println("Teacher: " + name);
        System.out.println("Borrowed Items:");
        for (int i = 0; i < borrowedItemCount; i++) {
            System.out.println("- " + borrowedItems[i].getClass().getSimpleName() + ": " + borrowedItems[i].getTitle());
        }
        System.out.println();
    }
}

public class duranoact3 {
    public static void main(String[] args) {
        LibraryItem book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        LibraryItem dvd1 = new DVD("The Little Things", "John Lee Hancock");

        LibraryUser student = new Student("Sally");
        LibraryUser teacher = new Teacher("Trevor");

        student.borrowItem(book1);
        teacher.borrowItem(dvd1);

        student.printItemsBorrowed();
        teacher.printItemsBorrowed();

        student.returnItem(book1);

        student.printItemsBorrowed();
        teacher.printItemsBorrowed();
    }
}