package org.meetu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 字符集过滤器
 * */
public class CharsetFilter implements Filter {

	private static final Log logger = LogFactory.getLog(CharsetFilter.class);

	@Override
	public void destroy() {
		logger.info(this.getClass().getSimpleName() + " destroyed =====");
	}

	/**
	 * 统一设置请求信息字符集
	 * */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("GBK");
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info(this.getClass().getSimpleName() + " init =====");
	}

}
