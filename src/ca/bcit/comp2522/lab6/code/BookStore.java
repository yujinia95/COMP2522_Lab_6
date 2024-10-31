package ca.bcit.comp2522.lab6.code;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * BookStore class extends literature. Holding different types of books(Novel, ComicBook, Literature, Magazine).
 * numbOfBooks() Getting a size of ArrayList items.
 * addItem() Adding single item to the ArrayList items.
 * printItems() Printing all items in the ArrayList items.
 * addNovelsToCollection() Adding novel item from item ArrayList to novelCollection.
 * readNovelsFromCollection() Reading list of novels from the novelCollection.
 * printBookTitle() Printing name of books that it matches with parameter.
 * printTitlesAlphaOrder() Sorting book titles in alphabetical order.
 * displayInfo() Locating in static nested class 'BookStoreInfo'. Printing a name of bookstore, and total number of
 *               items.
 * averageTitleLength() Locating in inner class 'NovelStatistics'. Getting average title length.
 * mostCommonPublishedYear() Locating in inner class 'NovelStatistics'. Getting most common published year in item list.
 * compare() Locating in Local Class 'NovelStatistics'. Sorting books by title length.
 * main Driver, testing BookStore class.
 * compare() Locating in main. Sorting books by title length.
 *
 * @param <T> extending Literature class
 *
 * @author Yujin Jeong
 * @author Jason Wilder
 * @version 1.0
 */

public class BookStore<T extends Literature>
{
    private final List<T> items;

    /**
     * Creating a constructor.
     */
    public BookStore()
    {
        items = new ArrayList();
    }

    /**
     * Getting a size of ArrayList items.
     *
     * @return total items
     */
    public int numbOfBooks()
    {
        return this.items.size();
    }

    /**
     * Adding single item to the ArrayList items.
     *
     * @param item single book
     */
    public void addItem(final T item)
    {
        this.items.add(item);
    }

    /**
     * Printing all items in the ArrayList items.
     */
    public void printItems()
    {
        System.out.println("\n===List of items===");

        for(final T item : items)
        {
            System.out.println(item.getTitle());
        }

    }

    /**
     * Adding novel item from item ArrayList to novelCollection.
     *
     * @param novelCollection collection for novel
     */
    public void addNovelsToCollection(final List<? super Novel> novelCollection)
    {
        for(final T item : items)
        {
            if(item instanceof Novel)
            {
                novelCollection.add((Novel) item);
            }
        }

    }

    /**
     * Reading list of novels from the novelCollection.
     *
     * @param novelCollection collection for only novels
     */
    public void readNovelsFromCollection(final List<? extends Novel> novelCollection)
    {
        for(final Novel novel : novelCollection)
        {
            System.out.println(novel.getTitle());

        }

    }

    /**
     * Practicing how to use 'Lambda Expression'.
     * Printing name of books that it matches with parameter.
     *
     * @param title that desired to search.
     */
    public void printBookTitle(final String title)
    {
        System.out.println("\n===Printing books has" + " \"" + title + "\" " + "in the title===");
        this.items.forEach((item) ->
                           {
                               if(item.getTitle().toLowerCase().contains(title.toLowerCase()))
                               {
                                   System.out.println(item.getTitle());
                               }

                           });
    }

    /**
     * Practicing how to use 'Method References'.
     * Sorting book titles in alphabetical order and printing them.
     */
    public void printTitlesAlphaOrder()
    {
        System.out.println("\n===Sorted List in alphabetical order===");
        this.items.sort(Comparator.comparing(Literature::getTitle, String::compareToIgnoreCase));
        this.items.forEach((item) ->
                           {
                               System.out.println(item.getTitle());
                           });
    }

    /**
     * Practicing how to use 'Static Nested Class'.
     * BookStoreInfo has a method:
     *      displayInfo() printing a name of bookstore, and total number of items.
     */
    static class BookStoreInfo
    {
        /**
         * Printing a name of bookstore, and total number of items.
         *
         * @param storeName name of store
         * @param itemCount number of books
         */
        public void displayInfo(final String storeName,
                                final int itemCount)
        {
            System.out.println("BookStore: " + storeName + ", Number of items: " + itemCount);
        }
    }

    /**
     * Practicing how to use 'Inner Class'.
     * NovelStatistics has a method:
     *      averageTitleLength() getting average title length.
     *      mostCommonPublishedYear() getting most common published year.
     */
    class NovelStatistics
    {
        /**
         * Getting average title length.
         *
         * @return average title length
         */
        public double averageTitleLength()
        {
            int totalLength = 0;

            for(final T item : items)
            {
                totalLength += item.getTitle().length();
            }
            return (double) totalLength / items.size();
        }

        /**
         * Getting most common published year in item list.
         *
         * @return published year that most common
         */
        public int mostCommonPublishedYear()
        {
            int commonYear = 0;
            int maxCount   = 0;
            int count;
            int year;

            for(final T item : items)
            {
                year  = item.getYear();
                count = 0;

                for(final T otherItem : items)
                {
                    if(otherItem.getYear() == item.getYear())
                    {
                        count++;
                    }
                }
                if(count > maxCount)
                {
                    maxCount   = count;
                    commonYear = year;
                }
            }

            return commonYear;
        }
    }

    /**
     * Practicing how to use 'Local Class'.
     * compareTitleLength has a method:
     *      compare() sorting books by title length.
     */
    class compareTitleLength implements Comparator<Literature>
    {
        @Override
        public int compare(Literature o1,
                           Literature o2)
        {
            return Integer.compare(o1.getTitle().length(), o2.getTitle().length());
        }
    }

    /**
     * Main driver, testing BookStore class.
     *
     * @param args unused
     */
    public static void main(final String[] args)
    {
        final BookStore<Literature> store;
        final BookStoreInfo         info;
        final List<Novel>           novelCollection;

        store           = new BookStore();
        info            = new BookStoreInfo();
        novelCollection = new ArrayList();

        //Inner Class
        final BookStore.NovelStatistics statistics;
        statistics = store.new NovelStatistics();

        //Adding books
        store.addItem(new Novel("To Kill a Mockingbird", 1960));
        store.addItem(new Novel("Fahrenheit 451", 1953));
        store.addItem(new Novel("The Long Goodbye", 1953));
        store.addItem(new ComicBook("Crayon Shin-chan", 1990));
        store.addItem(new ComicBook("Asterix", 1959));
        store.addItem(new Magazine("Vogue", 1892));
        store.addItem(new Magazine("Elle", 1945));

        //Printing a name of bookstore, and total number of items.
        System.out.println("===Name of bookstore, and total number of books===");
        info.displayInfo("Meow", store.numbOfBooks());

        //Printing a list of all items.
        store.printItems();

        //Printing title of books that has "the" in the title.
        store.printBookTitle("the");

        //Sorting book titles in alphabetical order and printing them.
        store.printTitlesAlphaOrder();

        //Printing average title length in novels.
        System.out.println(String.format("\n===Average title length in novels=== \n%.2f", statistics.averageTitleLength()));

        //Sorting only novels from ArrayList and printing them.
        System.out.println("\n===Novels in the collection===");
        store.addNovelsToCollection(novelCollection);
        store.readNovelsFromCollection(novelCollection);

        //Printing the most common published year of book in novel collection
        System.out.println("\n===Most common published year of book===");
        System.out.println(statistics.mostCommonPublishedYear());

        /*
         * Practicing how to use 'Anonymous Class'.
         *      compare(): sorting books by title length, then printing out.
         */
        System.out.println("\n===Sorted by title length(Using Anonymous class)===");
        store.items.sort(new Comparator<Literature>()
        {
            @Override
            public int compare(Literature o1,
                               Literature o2)
            {
                return Integer.compare(o1.getTitle().length(), o2.getTitle().length());
            }
        });
        store.items.forEach((item) ->
                            {
                                System.out.println(item.getTitle());
                            });
    }
}
