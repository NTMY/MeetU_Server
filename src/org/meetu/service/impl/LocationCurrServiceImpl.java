package org.meetu.service.impl;

import static org.meetu.constant.Constant.STATUS_FAIL;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.cache.Cache;
import org.meetu.constant.Constant;
import org.meetu.dao.LocationCurrDao;
import org.meetu.dao.LocationHisDao;
import org.meetu.dao.LogMeetDao;
import org.meetu.dto.BaseDto;
import org.meetu.model.LocationCurr;
import org.meetu.model.LocationHis;
import org.meetu.model.LogMeet;
import org.meetu.service.ILocationCurrService;
import org.meetu.util.ListBean;
import org.meetu.util.RangeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class LocationCurrServiceImpl implements ILocationCurrService {

	private static Log logger = LogFactory
			.getLog(LocationCurrServiceImpl.class);
	/**
	 * */
	
	
	@Autowired
	private LocationCurrDao currDao;

	@Autowired
	private LocationHisDao hisDao;
	
	@Autowired
	private LogMeetDao meetDao;

	@Override
	public void insert(LocationCurr curr) {
		currDao.insert(curr);
	}

	@Override
	public void delete(LocationCurr curr) {
		currDao.delete(curr);
	}

	@Override
	public void deleteByUserId(Integer userId) {
		currDao.deleteByUserId(userId);
	}

	@Override
	public List<LocationCurr> queryAll(LocationCurr curr) {
		List<LocationCurr> list = currDao.queryAll(curr);
		return list;
	}

	@Override
	public List<LocationCurr> queryNear(LocationCurr curr) {
		List<LocationCurr> list = currDao.queryNear(curr);
		return list;
	}

	/**
	 * 
	 * */
	@Override
	public ListBean meetu(LocationCurr curr) {
		ListBean<LocationCurr> beans = new ListBean<LocationCurr>();// 返回的对象(转xml)

		LocationCurr oldCurr = new LocationCurr();
		oldCurr.setUserId(curr.getUserId());
		
		try {
			List<LocationCurr> isExistList = currDao.queryAll(oldCurr);
			if (isExistList == null) {
				beans.setErrCode(STATUS_FAIL);
				beans.setErrMsg("DB查询异常");
				return null;
			}

			// 如果在curr当前表中有1条此用户记录,则将老数据迁入his历史表,
			if (isExistList.size() == 1) {
				oldCurr = isExistList.get(0);
				currDao.delete(oldCurr);// 在curr当前表中删除
				LocationHis his = new LocationHis(oldCurr);
				hisDao.insert(his);// 在his历史表中添加
				// 如果curr当前表中有多条此人记录,则全部删除
			} else if (isExistList.size() > 1) {
				currDao.deleteByUserId(curr.getUserId());
			}

			// 在curr当前表中插入新数据
			currDao.insert(curr);
			// 查找附近的好友(利用当前表)
			double[] range = RangeCalculator.getSquare(curr.getLatitude(),
					curr.getLongitude(), Constant.MEETU_RANGE);
			curr.setRange(range);// 设置边界值(查询条件)
			List<LocationCurr> list = currDao.queryNear(curr);// 查询附近的人
			if(list==null) {
				beans.setErrCode(STATUS_FAIL);
				beans.setErrMsg("查询附近的人DB异常");
				return beans;
			}
			//记录相遇log
			for(int i=0;i<list.size();i++) {
				LogMeet logMeet = new LogMeet();
				//logMeet.setId(0);//MySQL设为id自增,Hibernate设置策略
				logMeet.setUserId(curr.getUserId());
				logMeet.setUserLongitude(curr.getLongitude());
				logMeet.setUserLatitude(curr.getLatitude());
				logMeet.setUserAddress(curr.getAddress());
				logMeet.setFriendId(list.get(i).getUserId());
				logMeet.setFriendLongitude(list.get(i).getLongitude());
				logMeet.setFriendLatitude(list.get(i).getLatitude());
				logMeet.setFriendAddress(list.get(i).getAddress());
				logMeet.setHappenTime(new Timestamp(new Date().getTime()));
				meetDao.insert(logMeet);
			}
			beans.setList(list);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
			beans = new ListBean<>();
			beans.setErrCode(STATUS_FAIL);
			beans.setErrMsg("查询附近的人出现异常");
			logger.error(e);
			logger.error("查询附近的人出现异常",e);
		}
		return beans;
	}

	
	
	/**
	 * 
	 * */
	@Override
	public BaseDto upload(LocationCurr curr) {
		BaseDto dto = new BaseDto();// 返回的对象(转xml)

		// 先看db当前表(curr)中该用户的数据情况
		LocationCurr oldCurr = new LocationCurr();
		oldCurr.setUserId(curr.getUserId());

		List<LocationCurr> isExistList = currDao.queryAll(oldCurr);
		try {
			if (isExistList == null) {
				dto.setErrCode(STATUS_FAIL);
				dto.setErrMsg("DB查询异常");
				return dto;
			}

			// 如果在curr当前表中有1条此用户记录,则将老数据迁入his历史表,(这是正常的流程)
			if (isExistList.size() == 1) {
				oldCurr = isExistList.get(0);
				currDao.delete(oldCurr);// 在curr当前表中删除
				LocationHis his = new LocationHis(oldCurr);
//				hisDao = null;//主动制造异常,测试回滚
				hisDao.insert(his);// 在his历史表中添加
				// 如果在curr当前表中有多条此用户数据,则不将老数据迁入历史表,直接在当前表删除(这种情况说明有问题)
			} else if (isExistList.size() > 1) {
				currDao.deleteByUserId(curr.getUserId());
			}
			// 在curr当前表中插入新数据
			currDao.insert(curr);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
			dto.setErrCode(STATUS_FAIL);
			dto.setErrMsg("上传用户LOCATION信息UPLOAD处理异常");
			logger.error(e);
			logger.error("上传用户LOCATION信息UPLOAD处理异常",e);
		}
		return dto;
	}

}
