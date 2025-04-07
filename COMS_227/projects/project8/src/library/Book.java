package library;

import java.util.Date;

/**
 * A Book is a library item that can be checked out for 21 days and renewed at most twice.
 * If overdue, the fine is .25 per day.
 */
public class Book extends LibraryItem implements Item
{
  
  /**
   * Number of times the item has been renewed for the current patron.
   */
  private int renewalCount;
  
  /**
   * Constructs a book with the given title.
   * @param givenTitle
   */
  public Book(String givenTitle)
  {
	super(givenTitle, null, null);
    renewalCount = 0;
  }

  @Override
  public void checkOut(Patron p, Date now) {
	  super.checkOut(p, now, 21);
  }
  
  public void checkIn()
  {
    if (isCheckedOut())
    {
      setPatron(null);
      setDueDate(null);
      renewalCount = 0;
    }
  }

  @Override
  public void renew(Date now)
  {
    if (isCheckedOut() && !isOverdue(now) && renewalCount < 2)
    {
      checkOut(getPatron(), getDueDate());
      renewalCount += 1;
    }    
  }

  
  public double getFine(Date now)
  {
    if (isCheckedOut() && isOverdue(now))
    {
      double daysLate = super.getFine(now);
      
      // compute the fine, 25 cents per day
      return daysLate * .25;
    }
    else
    {
      return 0;
    }
  }
}
