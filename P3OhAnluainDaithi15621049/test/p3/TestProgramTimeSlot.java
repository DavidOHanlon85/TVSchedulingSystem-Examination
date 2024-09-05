package p3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestProgramTimeSlot {
	
	// Input Data
	
	String validNameChannelLow, validNameChannelMid, validNameChannelHigh;
	DayOfWeek monday, friday, saturday, sunday;
	
	LocalTime validLocalTimeLow, validLocalTimeHigh;
	
	Category na, entertainment, news, drama, comedy;
	
	ProgramTimeSlot p;

	@BeforeEach
	void setUp() throws Exception {
		
	validNameChannelLow = "x";
	validNameChannelMid = "x".repeat(15);
	validNameChannelHigh = "x".repeat(30);
	
	monday = DayOfWeek.MONDAY;
	friday = DayOfWeek.FRIDAY;
	saturday = DayOfWeek.SATURDAY;
	sunday = DayOfWeek.SUNDAY;
	
	validLocalTimeLow = LocalTime.of(06, 00);
	validLocalTimeHigh = LocalTime.of(23, 59);
	
	na = Category.NA;
	entertainment = Category.ENTERTAINMENT;
	news = Category.NEWS;
	drama = Category.DRAMA;
	comedy = Category.COMEDY;
	
	p = new ProgramTimeSlot(friday, validLocalTimeHigh, validNameChannelHigh, validNameChannelHigh, comedy);
	
		
	}

	@Test
	void testProgramTimeSlotDefaultConstructor() {
		assertNotNull(p);
	}

	@Test
	void testProgramTimeSlotConstructorValid() {
		assertEquals(friday, p.getDay());
		assertEquals(validLocalTimeHigh, p.getStartTime());
		assertEquals(validNameChannelHigh, p.getChannel());
		assertEquals(validNameChannelHigh, p.getName());
		assertEquals(comedy, p.getCategory());
	}
	
	@Test
	void testProgramTimeSlotConstructorInvalid() {
		
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			p = new ProgramTimeSlot(sunday, validLocalTimeHigh, validNameChannelHigh, validNameChannelHigh, comedy);
		});
		
		assertEquals("INVALID DAY", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, () -> {
			p = new ProgramTimeSlot(friday, validLocalTimeHigh, null, validNameChannelHigh, comedy);
		});
		
		assertEquals("INVALID CHANNEL", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, () -> {
			p = new ProgramTimeSlot(friday, validLocalTimeHigh, validNameChannelHigh, null, comedy);
		});
		
		assertEquals("INVALID NAME", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, () -> {
			p = new ProgramTimeSlot(friday, validLocalTimeHigh, validNameChannelHigh, validNameChannelHigh, null);
		});
		
		assertEquals("INVALID CATEGORY", exp.getMessage());
	}

	@Test
	void testSetGetDayValid() {
		p.setDay(monday);
		assertEquals(monday, p.getDay());
		
		p.setDay(friday);
		assertEquals(friday, p.getDay());
	}

	@Test
	void testSetGetDayInvalid() {
		
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			p.setDay(saturday);
		});
		
		assertEquals("INVALID DAY", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, () -> {
			p.setDay(sunday);
		});
		
		assertEquals("INVALID DAY", exp.getMessage());
		
	}

	@Test
	void testSetGetStartTimeValid() {
		p.setStartTime(validLocalTimeHigh);
		assertEquals(validLocalTimeHigh, p.getStartTime());
		
		p.setStartTime(validLocalTimeLow);
		assertEquals(validLocalTimeLow, p.getStartTime());
		
	}

//	@Test
//	void testSetStartTime() {
//		fail("Not yet implemented");
//	}

	@Test
	void testSetGetChannelValid() {
		p.setChannel(validNameChannelHigh);
		assertEquals(validNameChannelHigh, p.getChannel());
		
		p.setChannel(validNameChannelMid);
		assertEquals(validNameChannelMid, p.getChannel());
		
		p.setChannel(validNameChannelLow);
		assertEquals(validNameChannelLow, p.getChannel());
	}

	@Test
	void testSetGetChannelInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			p = new ProgramTimeSlot(friday, validLocalTimeHigh, null, validNameChannelHigh, comedy);
		});
		
		assertEquals("INVALID CHANNEL", exp.getMessage());
	}

	@Test
	void testSetGetNameValid() {
		p.setName(validNameChannelHigh);
		assertEquals(validNameChannelHigh, p.getName());
		
		p.setName(validNameChannelMid);
		assertEquals(validNameChannelMid, p.getName());
		
		p.setName(validNameChannelLow);
		assertEquals(validNameChannelLow, p.getName());
	}

	@Test
	void testSetGetNameInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			p = new ProgramTimeSlot(friday, validLocalTimeHigh, validNameChannelHigh, null, comedy);
		});
		
		assertEquals("INVALID NAME", exp.getMessage());
	}

	@Test
	void testSetGetCategoryValid() {
		p.setCategory(comedy);
		assertEquals(comedy, p.getCategory());
		
		p.setCategory(na);
		assertEquals(na, p.getCategory());
		
		p.setCategory(entertainment);
		assertEquals(entertainment, p.getCategory());
		
		p.setCategory(drama);
		assertEquals(drama, p.getCategory());
		
		p.setCategory(news);
		assertEquals(news, p.getCategory());
		
	}

	@Test
	void testSetGetCategoryInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, () -> {
			p = new ProgramTimeSlot(friday, validLocalTimeHigh, validNameChannelHigh, validNameChannelHigh, null);
		});
		
		assertEquals("INVALID CATEGORY", exp.getMessage());
	}

//	@Test
//	void testPrintDetails() {
//		fail("Not yet implemented");
//	}

}
