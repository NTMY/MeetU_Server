package org.meetu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.cache.Cache;
import org.meetu.dao.SysParamDao;
import org.meetu.model.AppVer;
import org.meetu.model.SysParam;
import org.meetu.service.IAppVerService;
import org.meetu.service.ISysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * MEETU工程服务器启动初始化资源启动类
 * */
//@Resource
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 5443898246981950653L;

	private Log logger = LogFactory.getLog(InitServlet.class);
	
	@Autowired
	private ISysParamService sysService;
	
	@Autowired
	private IAppVerService appVerService;
	
	/**
	 * Constructor of the object.
	 */
	public InitServlet() {
		super();
	}

	/**
	 * Initialization of the servlet. <br>
	 * <li>初始化缓存map</li>
	 * <li>读取properties配置文件</li>
	 * <li>刷新服务器参数map也调用此接口</li>
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger.info("InitServlet init初始化资源开始");
		// 让其获得@Autowired注入的对象,不需要getters/setters
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
		List<SysParam> sysParamList = sysService.queryAll();
		for(SysParam p : sysParamList) {
			Cache.getCacheMap().put(p.getKey(), p.getValue());//全局缓存(单例)
		}
		
		//加载app top版本信息
		List<AppVer> appVerTopList = appVerService.queryTop();
		for(AppVer appVer : appVerTopList) {
			Cache.getCacheMap().put(appVer.getPk().getOS(), appVer);
		}
		logger.info("InitServlet init初始化资源结束");
	}
	
	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}



}
