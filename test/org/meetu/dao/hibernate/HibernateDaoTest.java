package org.meetu.dao.hibernate;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.meetu.dao.LocationHisDao;
import org.meetu.model.LocationHis;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateDaoTest extends BaseHibernateDaoTest {
	@Autowired
	private LocationHisDao hisDao;
	private LocationHis his = new LocationHis();


	@Autowired
	public void setCustInfoDao(LocationHisDao hisDao) {
		this.hisDao = hisDao;
	}

	@Before
	public void init() {
	}

	@Test
	public void getList() {
		try {
			his.setId(1);
			List<LocationHis> list = hisDao.queryAll(his);
			for(LocationHis his1 : list) {
				System.out.println(his1.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}