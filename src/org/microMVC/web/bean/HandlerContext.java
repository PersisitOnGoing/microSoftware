package org.microMVC.web.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**   
* @Description: ������SpringMVC�е�ModelAndView����Ҫ���ڷ�װ���ݺͷ��صĽ����
* 				�����д���HttpServletRequest��HttpServletResponse��result
* 				�ɽ����ݴ���request�����з���ҳ�档result����Ҫ���ص�ҳ��
* @author tanjq
* @version V1.0   
*/
public class HandlerContext {
	private static ThreadLocal<HandlerContext> ctx = new ThreadLocal<HandlerContext>();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String result;
	
	public HandlerContext(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public static void setContext(HandlerContext handlerContext) {
		ctx.set(handlerContext);
	}

	public static HandlerContext getContext() {
		return ctx.get();
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * setAttriInRequest: ��������뵽request����
	 * @param key�� ����request�ļ�
	 * @param value: ����request��ֵ
	 */
	public void setAttriInRequest(String key, String value) {
		request.setAttribute(key, value);
	}

	/**
	 * setAttriInSession: ��������뵽session����
	 * @param key�� ����session�ļ�
	 * @param value: ����session��ֵ
	 */
	public void setAttriInSession(String key, String value) {
		request.getSession().setAttribute(key, value);
	}

	public static void clearUp() {
		ctx.remove();
	}
}
