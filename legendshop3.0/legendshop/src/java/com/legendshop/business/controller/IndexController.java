/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.service.IndexService;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.PropertiesUtil;

/**
 * The Class GroupController.
 */
@Controller
public class IndexController extends BaseController {
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(IndexController.class);
		
	@Autowired
	private IndexService indexService;

	/**
	 * 前台首页.
	 * 
	 * @param request  
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Index starting calling");
		try {
			return indexService.getIndex(request, response);
		} catch (Exception e) {
			log.error("invoking index", e);
			if (!PropertiesUtil.isSystemInstalled()) {
				// redirect to the install page
				redirectToInstallPage(request);
			}
			throw new BusinessException("/index", EntityCodes.SYSTEM);
		}
	}

}