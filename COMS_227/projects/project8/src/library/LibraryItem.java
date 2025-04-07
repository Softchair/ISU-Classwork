package library;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class LibraryItem {
	
	private String title;
	
	private Date dueDate;
	
	private Patron checkedOutTo;
	
	public LibraryItem(String givinTitle, Date givinDueDate, Patron checkedOutTo) {
		title = givinTitle;
		dueDate = givinDueDate;
		this.checkedOutTo = checkedOutTo;
	}
	
	public boolean isCheckedOut(){
	  return dueDate != null;
	}
	
	public int compareTo(Item other){
	  return title.compareTo(other.getTitle());
	}
	
	public boolean isOverdue(Date now){
	  if (!isCheckedOut()){
	    return false;
	    }
	  return now.after(dueDate);
	}
	
	public void checkIn(){
	  if (isCheckedOut())
	  {
	    checkedOutTo = null;
	    dueDate = null;
	  }
	}
	
	public double getFine(Date now) {
		// how late is it, in milliseconds
	    double elapsed = now.getTime() - getDueDate().getTime();
	      
	    // how late is it, in days
	    int millisPerDay = 24 * 60 * 60 * 1000;
	    int daysLate = (int) Math.ceil(elapsed / millisPerDay);
	    
	    return daysLate;
	}
	
	protected void checkOut(Patron p, Date now, int days)
	  {
	    if (!isCheckedOut())
	    {
	      int checkOutDays = days;
	      
	      // use a GregorianCalendar to figure out the Date corresponding to
	      // midnight, 21 days from now
	      GregorianCalendar cal = new GregorianCalendar();
	      cal.setTime(now);
	      cal.add(Calendar.DAY_OF_YEAR, checkOutDays);
	      
	      // always set to 11:59:59 pm on the day it's due
	      cal.set(Calendar.HOUR_OF_DAY, 23);
	      cal.set(Calendar.MINUTE, 59);
	      cal.set(Calendar.SECOND, 59);     
	      
	      // convert back to Date object
	      dueDate = cal.getTime();
	      
	      checkedOutTo = p;      
	    }
	  }
	
	public abstract void checkOut(Patron p, Date now);
	
	public String getTitle() {
		return title;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public Patron getPatron() {
	  return checkedOutTo;
	}
	
	public void setPatron(Patron temp) {
		checkedOutTo = temp;
	}
	
	public void setDueDate(Date temp) {
		dueDate = temp;
	}

}
