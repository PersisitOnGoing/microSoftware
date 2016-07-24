package org.microMVC.web.adviceImpl;

import java.lang.reflect.Method;

import org.microMVC.web.aop.BeforeAdvice;

/**   
* @Description: ����֪ͨʵ���࣬Ŀ�귽��ִ�к��ص�ִ�д����е�before������ʵ�ֶ�Ŀ�귽������ǿ
* @author tanjq
* @version V1.0   
*/
public class BeforeAdviceImpl implements BeforeAdvice {

	@Override
	public void before(Class<?> targetClass, Object proxyObj, Method method,
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
