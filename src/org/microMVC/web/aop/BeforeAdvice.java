package org.microMVC.web.aop;

import java.lang.reflect.Method;


/**   
* @Description: ǰ��֪ͨ������һ���ص�������before
* @author tanjq
* @version V1.0   
*/
public interface BeforeAdvice {
	void before(Class<?> targetClass,Object proxyObj, Method method, Object[] args);
}
