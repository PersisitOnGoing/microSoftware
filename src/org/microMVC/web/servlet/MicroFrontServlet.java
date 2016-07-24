package org.microMVC.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.microMVC.web.bean.HandlerContext;
import org.microMVC.web.factory.ObjectFactory;
import org.microMVC.web.util.Constants;

/**   
* @Description: ǰ�ο�������servlet������ʼ��ʱ���ͳ�ʼ��ǰ�ο��������������������Ϣ
* @author tanjq
* @version V1.0   
*/
public class MicroFrontServlet extends HttpServletBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public void init(ServletConfig config){
		initParams(config);
		ObjectFactory objFactory = initObjFactory(config);
		initActionMapping(config, objFactory);
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		HandlerContext actionContext = new HandlerContext(req, resp);
		HandlerContext.setContext(actionContext);
		String uri = getShortUri(req);
		
		try {
			if(isHandler(uri)){
				String str = req.getServletPath();
				System.out.println("== " + str + " ==");
			    String actionName = uri.substring(1, uri.length() - Constants.DEFAULT_ACTION_SUFFIX.length());
				String redirectPagePath = getActionMappings().execute(req.getParameterMap(), actionName);
				RequestDispatcher dispatcher = req.getRequestDispatcher(redirectPagePath);
				dispatcher.forward(req, resp);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getShortUri: �����������Ϣ��ȡuri��ʵ���Ͼ����ҵ���Ӧ��handler
	 * @param req
	 * @return: String
	 */
	private String getShortUri(HttpServletRequest req){
		String totalURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String uri = totalURI.substring(contextPath.length());
		return uri;
	}
	
	/**
	 * isHandler: �ж��ǲ���microMVC��Ҫ���������
	 * @param uri�� ����ĵ�ַ
	 * @return: boolean
	 */
	private boolean isHandler(String uri){
		if(uri.endsWith(Constants.DEFAULT_ACTION_SUFFIX))
			return true;
		return false;
	}

	@Override
	public void destroy() {

	}
	
}
