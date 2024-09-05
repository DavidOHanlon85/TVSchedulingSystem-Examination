/**
 * 
 */
package p3;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class ProgramTimeSlot {

	// Instance variable

	private DayOfWeek day;
	private LocalTime startTime;
	private String channel;
	private String name;
	private Category category; 
	
	// Constructors

	/**
	 * Default Constructor
	 * 
	 * @throws IllegalArgumentException
	 */
	public ProgramTimeSlot() throws IllegalArgumentException {

	}

	/**
	 * Constructor with args
	 * 
	 * @param day
	 * @param startTime
	 * @param channel
	 * @param name
	 * @param category
	 * @throws IllegalArgumentException
	 */
	public ProgramTimeSlot(DayOfWeek day, LocalTime startTime, String channel, String name, Category category)
			throws IllegalArgumentException {
		this.setDay(day);
		this.setStartTime(startTime);
		this.setChannel(channel);
		this.setName(name);
		this.setCategory(category);
	}
	
	// Getters and Setters

	/**
	 * @return the day
	 */
	public DayOfWeek getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(DayOfWeek day) throws IllegalArgumentException {
		if (day ==DayOfWeek.MONDAY || day == DayOfWeek.TUESDAY || day == DayOfWeek.WEDNESDAY || day == DayOfWeek.THURSDAY || day == DayOfWeek.FRIDAY) {
			this.day = day;
		} else {
			throw new IllegalArgumentException("INVALID DAY");
		}
		
	}

	/**
	 * @return the startTime
	 */
	public LocalTime getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * 
	 * @param channel
	 * @throws IllegalArgumentException
	 */
	public void setChannel(String channel) throws IllegalArgumentException {
		if (channel == null) {
			throw new IllegalArgumentException("INVALID CHANNEL");
		} else {
			this.channel = channel;
		}
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("INVALID NAME");
		}else {
			this.name = name;
		}
		
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) throws IllegalArgumentException{
		if (category == null) {
			throw new IllegalArgumentException("INVALID CATEGORY");
		}else {
			this.category = category;
		}
		
	}
	
	// Methods
	
	public void printDetails() {
		
		System.out.printf("%s - %s Ch: %s\n", getDay(), getStartTime(), getChannel());
		System.out.printf("%s (%s)\n", getName(), getCategory());
	}
	
	public void printForNowandNext() {
		
		System.out.printf("Now and Next - %s\n", getChannel());
		System.out.printf("Now: %s, %s\n", getName(), getStartTime());
		System.out.printf("Next: %s, %s\n", getName(), getStartTime());
		
		
	}
	

}
