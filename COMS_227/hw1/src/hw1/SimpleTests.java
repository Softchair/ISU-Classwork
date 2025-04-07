package hw1;

/**
 * Some basic usage examples for the TV class.
 */
public class SimpleTests
{
  public static void main(String[] args)
  {
//    // try volumeUp
	  TV tv = new TV(2, 10);
	  
	  tv.channelUp();
	  tv.setChannel(9);
	  tv.resetStart(4);
	  tv.goToPreviousChannel();
	  System.out.println(tv.getChannel() + " expected 4");
	  
	  
//	  tv = new TV(0, 5);
//	  System.out.println(tv.display());
    
//    System.out.println(tv.getVolume()); // expected 0.5
//    tv.volumeUp();
//    tv.volumeUp();
//    System.out.println(tv.getVolume()); // expected 0.64
//    tv.volumeUp();
//    tv.volumeUp();
//    tv.volumeUp();
//    tv.volumeUp();
//    tv.volumeUp();
//    tv.volumeUp();
//    System.out.println(tv.getVolume()); // expected 1.0
//    System.out.println();
//    
//    // try getting and setting channel
//    tv = new TV(2, 10);
//    System.out.println(tv.getChannel());  // expected 2
//    tv.setChannel(8);
//    System.out.println(tv.getChannel());  // expected 8
//    tv.setChannel(42);
//    System.out.println(tv.getChannel());  // expected 10
//    tv.setChannel(1);
//    System.out.println(tv.getChannel());  // expected 2
//    System.out.println();
    
     //try channelUp
//    tv = new TV(2, 10);
//    tv.setChannel(8);
//    tv.channelUp();
//    System.out.println(tv.getChannel() + " expected 9");  // expected 9
//    tv.channelUp();
//    System.out.println(tv.getChannel() + " expected 10");  // expected 10
//    tv.channelUp();
//    System.out.println(tv.getChannel() + " expected 11");  // expected 11
//    tv.channelUp();
//    System.out.println(tv.getChannel() + " expected 2");  // expected 2
//    System.out.println();

//    // try channelDown
//    tv.setChannel(4);
//    tv.channelDown();
//    System.out.println(tv.getChannel() + " expected 3");  // expected 3
//    tv.channelDown();
//    System.out.println(tv.getChannel() + " expected 2");  // expected 2
//    tv.channelDown();
//    System.out.println(tv.getChannel() + " expected 11");  // expected 11
//    System.out.println();
    
//    // try resetNumChannels
//    tv = new TV(2, 10);
//    tv.setChannel(8);
//    tv.resetNumChannels(5);
//    System.out.println(tv.getChannel() + " expected 6");  // should now be 6
//   
//    // try resetStart
//    tv = new TV(2, 10);
//    tv.setChannel(8);
//    tv.resetStart(10);
//    System.out.println(tv.getChannel() + " expected 10");  // should now be 10
 
//    tv = new TV(2, 10);
//    tv.setChannel(8);
//    tv.resetStart(-5);
//    System.out.println(tv.getChannel() + " expected 4");  // should now be 4


  }
}
