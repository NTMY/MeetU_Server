package org.meetu.dao;

import org.junit.Test;
import org.meetu.model.LocationCurr;

public class LocationCurrDaoTest {
	LocationCurrDao dao = new LocationCurrDao();
	
	
	@Test
	public void testInsert() {
		LocationCurr loc = new LocationCurr();
		dao.insert(loc);
	}
	
	@Test
	public void testQueyAll() {
		
	}
}
