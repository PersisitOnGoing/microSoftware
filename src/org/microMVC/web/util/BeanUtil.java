package org.microMVC.web.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**   
* @Description: ������
* @author tanjq
* @version V1.0   
*/
public class BeanUtil {

	/**
	 * copyBeanProperties: ����bean������
	 * @param sourceBean
	 * @param destinationBean: void
	 */
	public static void copyBeanProperties(Object sourceBean,
			Object destinationBean) {
		Class<?> parentClass = sourceBean.getClass();
		while (parentClass != null) {
			final Field[] fields = parentClass.getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					field.set(destinationBean, field.get(sourceBean));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			parentClass = parentClass.getSuperclass();
		}
	}

	/**
	 * setBeanProperty: ����bean�Ĳ���
	 * @param obj�� beanʵ��
	 * @param fieldName�� ������
	 * @param fieldValue: ����ֵ
	 */
	public static void setBeanProperty(Object obj, String fieldName,
			Object fieldValue) {
		Class<?> parentClass = obj.getClass();
		while (parentClass != null) {
			final Field[] fields = parentClass.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					try {
						field.setAccessible(true);
						field.set(obj, fieldValue);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
			parentClass = parentClass.getSuperclass();
		}
	}

	/**
	 * invokeMethod: ��������Ŀ�귽��
	 * @param obj�� Ŀ����
	 * @param methodName�� Ŀ�귽����
	 * @return: Object�� ���صĽ����һ�㷵�ص���microMVC��װ��HandlerContext
	 */
	public static Object invokeMethod(Object obj, String methodName) {
		Object result = null;
		try {
			Method method = obj.getClass().getMethod(methodName);
			result = method.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
