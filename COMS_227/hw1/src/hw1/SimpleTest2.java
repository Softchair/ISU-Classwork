package hw1;

/**
 * Some basic usage examples for the TV class.
 */
public class SimpleTest2
{
  public static void main(String[] args)
  {
    // try volumeUp
    TV tv = new TV(4, 5);
    tv.channelDown();
    tv.channelDown();
    tv.channelDown();
    tv.channelDown();
    tv.channelDown();
    tv.channelDown();
    tv.channelDown();
    tv.channelDown();
    tv.channelDown();
    tv.channelDown();
    tv.channelDown();
    tv.channelDown();
    System.out.println(tv.getChannel() + " expected 7"); // expected 7
    System.out.println();
  }
 }