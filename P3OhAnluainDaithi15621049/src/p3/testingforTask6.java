package p3;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Chromaticity;

public class testingforTask6 {

	public static void main(String[] args) {

		LocalTime t = LocalTime.now();

		LocalTime now = LocalTime.of(t.getHour(), t.getMinute());

		System.out.println(now);
		
		LocalDate date = LocalDate.now();
		
		System.out.println(date.getDayOfWeek());
		
		List<ProgramTimeSlot> ProgramBefore = new ArrayList<ProgramTimeSlot>();
		
		
		for (ProgramTimeSlot p: TVScheduleManager.schedule) {
			if (now.isAfter(p.getStartTime())) {
				ProgramBefore.add(p);
			}
		}
		
		for (ProgramTimeSlot p: ProgramBefore) {
			p.getName();
		}
		
		
		TVScheduleManager.displayDetailsInList(ProgramBefore);
		

		
		
	}

}
