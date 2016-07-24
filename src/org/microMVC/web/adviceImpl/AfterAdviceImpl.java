package org.microMVC.web.adviceImpl;

import java.lang.reflect.Method;

import org.microMVC.web.aop.AfterAdvice;

/**   
* @Description: ǰ��֪ͨʵ���࣬Ŀ�귽��ִ��ǰ���Ȼص�ִ�д����е�after������ʵ�ֶ�Ŀ�귽������ǿ
* @author tanjq
* @version V1.0   
*/
public class AfterAdviceImpl implements AfterAdvice {

	@Override
	public void after(Class<?> targetClass, Object proxyObj, Method method,
			Object[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getSimpleName());
		sb.append(" targetObj: ");
		sb.append(targetClass.getSimpleName());
		sb.append(" method: ");
		sb.append(method.getName());
		System.out.println(sb.toString());
	}
}
