/**
 * 
 */
package p3;

import java.util.Comparator;

/**
 * 
 */
public class CompareByTitle implements Comparator<ProgramTimeSlot> {

	@Override
	public int compare(ProgramTimeSlot o1, ProgramTimeSlot o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}

}
