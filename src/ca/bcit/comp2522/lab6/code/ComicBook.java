package ca.bcit.comp2522.lab6.code;

/**
 * Creating a ComicBook class and extending Literature.
 * getTitle() Implementing abstract method from Literature. Getting a title.
 * getYear() Implementing abstract method from Literature. Getting a title.
 *
 * @author Yujin Jeong
 * @author Jason Wilder
 * @version 1.0
 */
public class ComicBook extends Literature
{

    private final String title;
    private final int    year;

    /**
     * Creating a constructor
     *
     * @param title title of book
     */
    public ComicBook(final String title,
                     final int year)
    {
        this.title = title;
        this.year  = year;
    }

    /**
     * Implementing abstract method from Literature. Getting a title.
     *
     * @return book title
     */
    @Override
    public String getTitle()
    {
        return title;
    }

    /**
     * Implementing abstract method from Literature. Getting a year.
     *
     * @return published year
     */
    @Override
    public int getYear()
    {
        return year;
    }
}
