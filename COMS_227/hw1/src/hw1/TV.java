/**
 * 
 */
package hw1;

/**
 * @author Camden
 * Model of the channel and volume controls for a TV. A TV has a current channel and volume level. The volume level ranges from 0.0 to 1.0. The possible range of channels is determined by two parameters that we will refer to as start and numChannels. In general there will always be numChannels consecutive channels, the lowest of which is start; that is, the range of values is from start through start + numChannels - 1, inclusive. The volume is adjusted with methods volumeUp and volumeDown, but never goes above 1.0 or below 0.0. The volume is incremented or decremented by the value of the constant VOLUME_INCREMENT. The channel is adjusted with methods channelDown and channelUp, but never goes below start or above start + numChannels - 1. The channel can also be set directly with the setChannel method. The method goToPreviousChannel sets the channel to most recent previous channel number.
 */
public class TV {
	
	
	/**
	 * Incremental increase or decrease in volume when the *volumeUp* or *volumeDown* methods are called
	 */
	public static final double VOLUME_INCREMENT = 0.07;
	
	/**
	 * Volume of the TV
	 */
	private double volume = 0.5;
	
	/**
	 * Channel that the TV is on
	 */
	private int channel;
	/**
	 * The first channel that you can watch
	 */
	private int start;
	/**
	 * The amount of channels in the TV
	 */
	private int numChannels;
	/**
	 * The channel the TV was just
	 */
	private int previousChannel;
	
	/**
	 * Constructs a new TV with *givenNumChannels* available channels, numbered consecutively beginning with *givenStart*. Initially the channel is *givenStart* and the volume is 0.5
	 * @param givenStart - minimum channel number
	 * @param givenNumChannels - number of channels
	 */
	public TV(int givenStart, int givenNumChannels) {
		channel = givenStart;
		start = givenStart;
		numChannels = givenNumChannels;
	}
	
	/**
	 * Changes the channel down by 1 and wraps around to *start + numChannels -1* if the current channel is equal to *start*
	 */
	public void channelDown() {
		previousChannel = channel;
		channel -= 1;
		if (channel < start) {
			channel = start + numChannels - 1;
		}
	}
	
	/**
	 * Changes the channel up by 1 and wraps around to start if the current channel can't go any higher.
	 */
	public void channelUp() {
		previousChannel = channel;
		int temp = (channel + 1  - start) % numChannels;
		channel = temp + start;
	}

	/**
	 * Returns a string representing the current channel and volume, where the volume is expressed as a percentage rounded to the nearest integer.
	 * @return a string of the exact form "Channel x Volume y%", where x is the channel and y is the volume times 100 rounded to the nearest integer.
	 */
	public String display() {
		int volumePercent = (int) Math.round(volume * 100);
		return "Channel " + channel + " Volume " + volumePercent + "%";
	}
	
	/**
	 * Returns the current channel for this TV
	 * @return current channel
	 */
	public int getChannel() {
		return channel;
	}
	
	/**
	 * Returns the current volume for this TV
	 * @return current volume
	 */
	public double getVolume() {
		return volume;
	}
	
	/**
	 * Sets the current channel to the previous channel
	 */
	public void goToPreviousChannel() {
		channel = Math.min(previousChannel, numChannels + start - 1);
		channel = Math.max(channel, start);
	}
	
	/**
	 * Resets the value of the *start* channel
	 * @param givenStart - the new starting channel
	 */
	public void resetStart (int givenStart) {
		start = givenStart;
		channel = Math.max(start, channel);
		channel = Math.min(numChannels + start - 1, channel);
	}
	
	/**
	 * Resets the number of channels
	 * @param givenNumChannels - the new number of channels
	 */
	public void resetNumChannels(int givenNumChannels) {
		numChannels = givenNumChannels;
		channel = Math.min(channel, numChannels + start - 1);
	}
	
	/**
	 * Sets the channel to the given number
	 * @param channelNumber - channel number to be set to
	 */
	public void setChannel(int channelNumber) {
		previousChannel = channel;
		int currentChannel = Math.min(start + numChannels - 1, channelNumber);
		currentChannel = Math.max(start, currentChannel);
		channel = currentChannel;
	}
	
	/**
	 * Lowers the volume by the VOLUME_INCREMENT, but not below 0.0
	 */
	public void volumeDown() {
		volume -= VOLUME_INCREMENT;
		volume = Math.max(volume, 0);
	}
	
	/**
	 * Increases the volume by the VOLUME_INCREMENT, but not above 1.0
	 */
	public void volumeUp() {
		volume += VOLUME_INCREMENT;
		volume = Math.min(volume, 1.0);
	}
	
}
