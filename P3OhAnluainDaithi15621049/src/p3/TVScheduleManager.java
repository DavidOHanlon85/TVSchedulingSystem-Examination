package p3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TVScheduleManager {

	private static final int MINCHOICE = 1;
	private static final int QUITCHOICE = 8;

	public static List<ProgramTimeSlot> schedule = new ArrayList<ProgramTimeSlot>();

	/**
	 * Entry point to program Menu driven system loop until quit choice is selected
	 * Should not be necessary to modify
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("TV Schedule System");

		int choice = 0;
		do {
			System.out.println();
			showMenu();
			choice = getChoice();

			System.out.println("choice = " + choice);
			processMenuChoice(choice, schedule);
		} while (choice != QUITCHOICE);

		System.out.println("Thanks, goodbye");

	}

	/**
	 * A launcher method which drives menu choice selection to perform a number of
	 * tasks processing the received schedule data
	 * 
	 * @param choice   menu choice value
	 * @param schedule a list of ProgramTimeSlot objects to be processed
	 */
	private static void processMenuChoice(int choice, List<ProgramTimeSlot> schedule) {

		// TODO Implement and call required methods to achieve desired functionality
		
		BreakingNews b = new BreakingNews();
		Thread t = new Thread(b);

		try {
			switch (choice) {
			case 1:
				readData("SampleTVData.csv");
				break;
			case 2:
				// task not yet implemented
				List<ProgramTimeSlot> sortByChannel = new ArrayList<ProgramTimeSlot>(schedule);
				// Collections.sort(sortByChannel, new CompareByLocalTime());
				sortListbyChannelAndChronologically(sortByChannel);
				displayDetailsInList(sortByChannel);
				break;
			case 3:
				// task not yet implemented
				List<ProgramTimeSlot> showsOnABC1 = new ArrayList<ProgramTimeSlot>(schedule);
				showsOnABC1 = filterByChannel(showsOnABC1, "ABC1");
				sortByDayAndTime(showsOnABC1);
				displayDetailsInList(showsOnABC1);
				break;
			case 4:
				// task not yet implemented
				List<ProgramTimeSlot> showsOnPremiumEventsOnFriday = new ArrayList<ProgramTimeSlot>(schedule);
				List<ProgramTimeSlot> filteredByChannel = filterByChannel(showsOnPremiumEventsOnFriday, "Premium Events");
				List<ProgramTimeSlot> filteredByChannelAndDay = filterByDay(filteredByChannel, DayOfWeek.FRIDAY);
				displayDetailsInList(filteredByChannelAndDay);
				break;
			case 5:
				// task not yet implemented
				List<ProgramTimeSlot> showsOnThursdayWithLunchInName = new ArrayList<ProgramTimeSlot>(schedule);
				List<ProgramTimeSlot> results = filterBySearchTerm(filterByDay(showsOnThursdayWithLunchInName, DayOfWeek.THURSDAY), "lunch");
				Collections.sort(results, new CompareByTitle());
				displayDetailsInList(results);
				
				break;
			case 6:
				// task not yet implemented
				List<ProgramTimeSlot> displayNowAndNext = new ArrayList<ProgramTimeSlot>(schedule);
				
				LocalDate date = LocalDate.now();
				
				System.out.println(date.getDayOfWeek());
				
				String searchChannel = "ABC1";
				List<ProgramTimeSlot> results2 = filterByChannel(displayNowAndNext, "ABC1");
				List<ProgramTimeSlot> results3 = filterByDay(displayNowAndNext, date.getDayOfWeek());
				
//				for (ProgramTimeSlot pro : results2) {
//					pro.printDetails();
//				}
				
				LocalTime t2 = LocalTime.now();
				LocalTime now = LocalTime.of(t2.getHour(), t2.getMinute());
				System.out.println(now);
				
				System.out.println("Now and Next - " + searchChannel);
				
				for (int i = 0; i < results2.size(); i++) {
					if (results2.get(i).getStartTime().plusMinutes(30).isAfter(now)) {
						System.out.println("Now: " + results2.get(i).getName() + " " + results2.get(i).getStartTime());
					}
				}
				
				for (int i = 0 ; i < results2.size(); i++) {
					if (results2.get(i).getStartTime().minusMinutes(30).isBefore(now)) {
						System.out.println("Next: " + results2.get(i).getName() + " " + results2.get(i).getStartTime());
					}
				}
						
				//filterByChannel(displayNowAndNext, "ABC1");
				displayDetailsInList(results3);
				
				//getNowAndNext(results2);
				
				
				break;
			case 7:
				// task not yet implemented
				t.start();
				break;
			case QUITCHOICE:
				System.out.println("Quitting");
				t.interrupt();
				break;
			default:
				System.out.println("Unrecognised menu choice");
			}
		} catch (Exception e) {
			System.out.println("Exception caught in task " + choice);
			System.out.println("Exception message: " + e.getMessage());
			System.out.println("Try again");
		}

	}

	private static void getNowAndNext(List<ProgramTimeSlot> results2) {
		
		LocalTime t = LocalTime.now();
		
		LocalTime now = LocalTime.of(t.getHour(), t.getMinute());
		
		System.out.println();
		
		
	}
	/**
	 * This method filter a list by searchTerm
	 * @param input
	 * @param searchTerm
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static List<ProgramTimeSlot> filterBySearchTerm(List<ProgramTimeSlot> input, String searchTerm) throws IllegalArgumentException {
		if (input == null || input.size() == 0) {
			throw new IllegalArgumentException("AL LIST NULL/ EMPTY");
		}
		
		if (searchTerm == null || searchTerm.length() == 0){
			throw new IllegalArgumentException("SEARCHTERM NULL/ EMPTY");
		}
		
		List<ProgramTimeSlot> results = new ArrayList<ProgramTimeSlot>();
		
		String searchFor = searchTerm.toUpperCase();
		
		for (ProgramTimeSlot p : input) {
			if(p.getName().toUpperCase().contains(searchFor)) {
				results.add(p);
			}
		}
		
		return results;
	}

	/**
	 * This method filters by Day Of Week
	 * 
	 * @param filterByChannel
	 * @param friday
	 * @return 
	 */
	public static List<ProgramTimeSlot> filterByDay(List<ProgramTimeSlot> input, DayOfWeek day) throws IllegalArgumentException {

		if (input == null || input.size() == 0) {
			throw new IllegalArgumentException("AL LIST NULL/ EMPTY");
		}
		
		List<ProgramTimeSlot> results = new ArrayList<ProgramTimeSlot>();
		
		for (ProgramTimeSlot p : input) {
			if (p.getDay().equals(day)){
				results.add(p);
			}
		}
		
		return results;
		
	}

	/**
	 * This method sorts a list by day and time
	 * 
	 * @param showsOnABC1
	 */
	public static void sortByDayAndTime(List<ProgramTimeSlot> showsOnABC1) throws IllegalArgumentException{
		
		if (showsOnABC1 == null || showsOnABC1.size() == 0) {
			throw new IllegalArgumentException("AL LIST NULL/ EMPTY");
		}
		
		Collections.sort(showsOnABC1, new CompareByLocalTime());
		Collections.sort(showsOnABC1, new CompareByDay());
	}

	/**
	 * This method filters programs by channel
	 * 
	 * @param showsOnABC1
	 * @param string
	 * @return
	 */
	public static List<ProgramTimeSlot> filterByChannel(List<ProgramTimeSlot> showsOnABC1, String channel)
			throws IllegalArgumentException {
		if (showsOnABC1 == null || showsOnABC1.size() == 0) {
			throw new IllegalArgumentException("AL LIST NULL/ EMPTY");
		}
		
		List<ProgramTimeSlot> results = new ArrayList<ProgramTimeSlot>();
		
		for (ProgramTimeSlot p : showsOnABC1) {
			if (p.getChannel().equalsIgnoreCase(channel)) {
				results.add(p);
			}
		}

		return results;
	}

	/**
	 * Sorts list by Channel and Chronologically
	 * 
	 * @param sortByChannel
	 * @throws IllegalArgumentException
	 */
	public static void sortListbyChannelAndChronologically(List<ProgramTimeSlot> sortByChannel)
			throws IllegalArgumentException {
		if (sortByChannel == null || sortByChannel.size() == 0) {
			throw new IllegalArgumentException("AL LIST NULL/ EMPTY");
		}

		Collections.sort(sortByChannel, new SoryByChannel());
		Collections.sort(sortByChannel, new CompareByDay());
	}

	/**
	 * This method displats all details in a list to console
	 * 
	 * @param sortByChannel
	 */
	public static void displayDetailsInList(List<ProgramTimeSlot> sortByChannel) throws IllegalArgumentException {
		if (sortByChannel == null || sortByChannel.size() == 0) {
			throw new IllegalArgumentException("AL LIST NULL/ EMPTY");
		}

		for (ProgramTimeSlot p : sortByChannel) {
			p.printDetails();
			System.out.println();
		}

	}

	/**
	 * 
	 * @param filename
	 * @throws Exception
	 */
	private static void readData(String filename) throws Exception {

		schedule.clear();// empty existing data

		// TODO complete read method to populate static schedule List as appropriate

		File file = new File("SampleTVData.csv");
		int attemptedReads = 0;

		String line;

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			line = br.readLine(); // remove header
			line = br.readLine(); // read first line

			while (line != null) {

				try {
					attemptedReads++;

					String[] splitDetails = line.split(",");

					ProgramTimeSlot p = new ProgramTimeSlot();

					if (splitDetails[0].equalsIgnoreCase("Monday")) {
						p.setDay(DayOfWeek.MONDAY);
					} else if (splitDetails[0].equalsIgnoreCase("Tuesday")) {
						p.setDay(DayOfWeek.TUESDAY);
					} else if (splitDetails[0].equalsIgnoreCase("Wednesday")) {
						p.setDay(DayOfWeek.WEDNESDAY);
					} else if (splitDetails[0].equalsIgnoreCase("Thursday")) {
						p.setDay(DayOfWeek.THURSDAY);
					} else if (splitDetails[0].equalsIgnoreCase("Friday")) {
						p.setDay(DayOfWeek.FRIDAY);
					} else {
						throw new Exception("INVALID DAY");
					}

					String[] splitTime = splitDetails[1].split(":");

					p.setStartTime(LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1])));

					p.setChannel(splitDetails[2]);

					p.setName(splitDetails[3]);

					if (splitDetails[4].equalsIgnoreCase("NA")) {
						p.setCategory(Category.NA);
					} else if (splitDetails[4].equalsIgnoreCase("Entertainment")) {
						p.setCategory(Category.ENTERTAINMENT);
					} else if (splitDetails[4].equalsIgnoreCase("News")) {
						p.setCategory(Category.NEWS);
					} else if (splitDetails[4].equalsIgnoreCase("Drama")) {
						p.setCategory(Category.DRAMA);
					} else if (splitDetails[4].equalsIgnoreCase("Comedy")) {
						p.setCategory(Category.COMEDY);
					} else {
						throw new Exception("INVALID CATEGORY");
					}

					schedule.add(p);

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				line = br.readLine();

			}

			System.out.println("Attempted Reads: " + attemptedReads);
			System.out.println("Total Reads : " + schedule.size());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Get validated int choice between MIN and QUIT static variables Should not be
	 * necessary to modify
	 * 
	 * @return
	 */
	@SuppressWarnings("resource")
	private static int getChoice() {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Menu choice");
		try {
			choice = sc.nextInt();
			if (choice < MINCHOICE || choice > QUITCHOICE) {
				throw new Exception();// throw exception, caught immediately and retry
			}
		} catch (Exception e) {
			System.out.println("Invalid choice, please try again");
			return getChoice();// recursive call to try again and return that value
		}
		return choice;
	}

	/**
	 * Display menu options
	 */
	public static void showMenu() {
		System.out.println("1) Read Schedule Data");
		System.out.println("2) Display All Show Data - sorted by Channel, Day, Time");
		System.out.println("3) Display Show Data, specifically for ABC1 - sorted by Day, Time");
		System.out.println("4) Display Show Data, specifically for Premium Events Channel on Friday - sorted by Time");
		System.out.println(
				"5) Display Show Data for any shows on Thursday where the title contains the word Lunch - sorted alphabetically by title");
		System.out.println("6) Now and Next, on channel ABC1");
		System.out.println(
				"7) Breaking news, 12:45 Tue. Insert News Bulletin (ABC1). Delay all shows after News Bulletin by 30 mins (update start times)");
		System.out.println("8) Quit");
	}

}
