/**
 * 
 */
package p3;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class BreakingNews implements Runnable {

	@Override
	public void run() {

		ProgramTimeSlot p = new ProgramTimeSlot(DayOfWeek.TUESDAY, LocalTime.of(12, 15), "ABC1",
				"BREAKING NEWS", Category.NEWS);
		
		TVScheduleManager.schedule.add(p);
		
		List<ProgramTimeSlot> results = TVScheduleManager.filterByDay((TVScheduleManager.filterByChannel(TVScheduleManager.schedule, "ABC1")), DayOfWeek.TUESDAY);
		
		Collections.sort(results, new CompareByLocalTime());
		
		//results.add(p);
		
//		for (ProgramTimeSlot pro : results2) {
//			p.printDetails();
//		}
		
		List<ProgramTimeSlot> results2 = new ArrayList<ProgramTimeSlot>();
//		
//		for (ProgramTimeSlot prog : results) {
//			if (prog.getStartTime().isAfter(LocalTime.of(12, 45))) {
//				LocalTime startTime = prog.getStartTime();
//				p.setStartTime(startTime.plusMinutes(30));
//				results2.add(prog);
//			}
//		}
		
//		for (int i = 0; i < results.size(); i++) {
//			if (p.getStartTime().isAfter(LocalTime.of(12, 45))) {
//				LocalTime StartTime = p.getStartTime();
//				p.setStartTime(StartTime.plusMinutes(30));
//				
//			}
//		}
		
		System.out.println("Breaking news, 12:45 Tues. Insert News Bulletin for ABC1. Delay all shoes after News Bulletin by 30 mins.");
		System.out.println("New Show Entry add successfully");
		System.out.println("Time updated by 30 mins after added show");
		System.out.println();
		
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getStartTime().isAfter(LocalTime.of(12, 14))) {
				LocalTime startTime = results.get(i).getStartTime();
				results.get(i).setStartTime(startTime.plusMinutes(30));
				results2.add(results.get(i));
			}
			
		}
		
		results2.remove(1);
		

		for (ProgramTimeSlot pro : results2) {
			pro.printDetails();
		}
		
		//TVScheduleManager.displayDetailsInList(results);

	}

}
