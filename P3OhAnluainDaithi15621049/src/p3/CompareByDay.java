/**
 * 
 */
package p3;

import java.util.Comparator;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class CompareByDay implements Comparator<ProgramTimeSlot> {

	@Override
	public int compare(ProgramTimeSlot o1, ProgramTimeSlot o2) {
		// TODO Auto-generated method stub
		return o1.getDay().compareTo(o2.getDay());
	}

}
