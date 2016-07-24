package org.microMVC.web.adviceImpl;

import java.lang.reflect.Method;
import java.util.Date;

import org.microMVC.web.aop.AroundAdvice;

/**   
* @Description: ����֪ͨʵ���࣬Ŀ�귽��ִ��ǰ��ִ�к��ֱ�ص�before��after������ʵ�ֶ�Ŀ�귽������ǿ
* @author tanjq
* @version V1.0   
*/
public class AroundAdviceImpl implements AroundAdvice {
	private Date enterMethodTime;
	private Date leaveMethodTime;
	
	@Override
	public void before(Class<?> targetClass, Object proxyObj, Method method,
			Object[] args) {
		enterMethodTime = new Date();
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getSimpleName());
		sb.append(" targetObj: ");
		sb.append(targetClass.getSimpleName());
		sb.append(" method: ");
		sb.append(method.getName());
		System.out.println(sb.toString());
	}
	
	@Override
	public void after(Class<?> targetClass, Object proxyObj, Method method,
			Object[] args) {
		
		leaveMethodTime = new Date();
		long timeElapsed = getTimeDiff(enterMethodTime,leaveMethodTime);
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getSimpleName());
		sb.append(" targetObj: ");
		sb.append(targetClass.getSimpleName());
		sb.append(" method: ");
		sb.append(method.getName());
		System.out.println(sb.toString());
		System.out.println("Time Elapsed: "+timeElapsed+" ms");
	}
	
	/**
	 * getTimeDiff: ���㷽��ִ�е�ʱ��
	 * @param startDate: ִ��Ŀ�귽���Ŀ�ʼʱ��
	 * @param endDate�� Ŀ�귽��ִ����Ľ���ʱ��
	 * @return: long�� ʱ���
	 */
	private long getTimeDiff(Date startDate,Date endDate)
	{
		return endDate.getTime()-startDate.getTime();
	}
}
