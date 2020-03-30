package com.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;


/**
 *
 *
 *
 */
public class ListUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListUtil.class);

    /**
     * 移除list中重复的对象。
     * List中的对象必须依据hashCode的协定正确重写hashCode及equals方法。
     *
     * @param list 数据类型。
     */
    public static <T> void removeDuplicate(List<T> list) {
        Set<T> set = new HashSet<T>(list);
        list.clear();
        list.addAll(set);
    }
    
    /**
     * 根据比较字段对List中的对象进行排序
     *
     * @param <T>
     * @param list
     * @param comparedField  比较字段
     * @param isSortedByDesc 排序方式,true为降序,false为升序
     */
    private static <T> void sort(List<T> list, final String[] orderByFields,
                                 final boolean isSortedByDesc) {
        if(list == null || list.size() == 0) {
            return;
        }
        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T objOne, T objTwo) {
                boolean isMap = false;
                if(orderByFields != null && orderByFields.length > 0) {
                    Class<?> cls = objOne.getClass();
                    if(objOne instanceof Map<?, ?>) {
                        // 比较对象为Map
                        isMap = true;
                    } else {
                        // 比较对象为JavaBean
                        isMap = false;
                    }
                    // 比较对象为JavaBean
                    for(String field : orderByFields) {
                        try {
                            Object beanOne;
                            Object beanTwo;
                            if(isMap) {
                                if(!((Map<?, ?>) objOne).containsKey(field)
                                        || !((Map<?, ?>) objTwo).containsKey(field)) {
                                    // 不存在字段,则抛异常
                                    throw new Exception("对象中不存在比较字段！");
                                }
                                beanOne =((Map<?, ?>) objOne).get(field);
                                beanTwo =((Map<?, ?>) objTwo).get(field);
                            } else {
                            	try {
                            		Field fileOne = cls.getDeclaredField(field);
								} catch (Exception e) {
									cls = cls.getSuperclass();
								}
                                Field fileOne = cls.getDeclaredField(field);
                                fileOne.setAccessible(true);
                                Field fileTwo = cls.getDeclaredField(field);
                                fileTwo.setAccessible(true);
                                beanOne = fileOne.get(objOne);
                                beanTwo = fileTwo.get(objTwo);
                            }
                            if(beanOne == null && beanTwo == null) {
                                // 两个字段值都为空,表示相等,继续比较下一个字段
                                continue;
                            } else if(beanOne == null && beanTwo != null) {
                                // 第一个字段值为空,第二个字段值不为空,返回比较结果大于0(beanOne > beanTwo)
                                if(isSortedByDesc) {
                                    return -1;
                                } else {
                                    return 1;
                                }
                            } else if(beanOne != null && beanTwo == null) {
                                // 第一个字段值不为空,第二个字段值为空,返回比较结果小于0(beanOne < beanTwo)
                                if(isSortedByDesc) {
                                    return 1;
                                } else {
                                    return -1;
                                }
                            } else {
                                // 字段值都转换为字符串进行比较
                                int compareResult = 0;
                                if(beanOne instanceof Date) {
                                    compareResult =((Date) beanOne).compareTo((Date) beanTwo);
                                } else {
                                    compareResult = beanOne.toString().compareTo(beanTwo.toString());
                                }
                                if(compareResult == 0) {
                                    // 两个字段值相等,继续比较下一个字段
                                    continue;
                                } else {
                                    // 排序方式为降序,对比较结果取反
                                    if(isSortedByDesc) {
                                        compareResult = 0 - compareResult;
                                    }
                                    // 两个字段值不相等,返回比较结果
                                    return compareResult;
                                }
                            }
                        } catch(Exception e) {
                            LOGGER.error("List排序异常,该排序字段不存在:{},异常信息:{}", field, e);
                            throw new RuntimeException("List排序异常,该排序字段不存在:" + field, e);
                        }
                    }
                } else {
                    // 没有比较字段,两个对象认为是相等的
                    return 0;
                }
                // 前面都没返回,最后认为对象是相等的
                return 0;
            }
        };
        Collections.sort(list, comparator);
    }

    /**
     * 根据比较字段对List中的对象以升序方式进行排序。
     *
     * @param <T>
     * @param list
     * @param orderByField 比较字段
     */
    public static <T> void sortByAsc(List<T> list, String[] orderByFields) {
        sort(list, orderByFields, false);
    }

    /**
     * 根据比较字段对List中的对象以降序方式进行排序。
     *
     * @param <T>
     * @param list
     * @param orderByField
     */
    public static <T> void sortByDesc(List<T> list, String[] orderByFields) {
        sort(list, orderByFields, true);
    }

    /**
     * 根据比较字段对List中的对象以升序方式进行排序，并移除重复对象。
     *
     * @param <T>
     * @param list
     * @param orderByFields
     */
    public static <T> void removeDuplicateAndSortByAsc(List<T> list,
                                                       String[] orderByFields) {
        removeDuplicate(list);
        sort(list, orderByFields, false);
    }

    /**
     * 根据比较字段对List中的对象以降序方式进行排序，并移除重复对象。
     *
     * @param <T>
     * @param list
     * @param orderByFields
     */
    public static <T> void removeDuplicateAndSortByDesc(List<T> list,
                                                        String[] orderByFields) {
        removeDuplicate(list);
        sort(list, orderByFields, true);
    }

    /**
     * 判断List是否为null或者空
     *
     * @param list
     * @return
     */
    public static <T> boolean isListEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断map是否为null或者空
     *
     * @param map
     * @return
     */
    public static <K, V> boolean isMapEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    /**
     * value是否存在于数组 targerArr中,第一个参数是要与数组比较的 用法：tArr = new String[]{"a", "b"};
     * in("aa", tArr) --> false
     *
     * @param <T>
     * @param value
     * @param targetArr
     * @return
     * @author zhuxia 2012-06-13
     */
    public static <T> boolean in(T value, T[] targetArr) {
        if(null == value || null == targetArr || 0 == targetArr.length) {
            return false;
        }
        for(T v : targetArr) {
            if(v == value || value.equals(v)) {
                return true;
            }
        }
        return false;
    }

    /**
     * value是否存在于 targerList中,第一个参数是要与List比较的
     *
     * @param <T>
     * @param value
     * @param targetList
     * @return
     * @author zhuxia 2012-06-13
     */
    public static <T> boolean in(T value, List<T> targetList) {
        if(null == value || null == targetList || 0 == targetList.size()) {
            return false;
        }
        for(T v : targetList) {
            if(v == value || value.equals(v)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断 targerList中是否存在属性值为vlaue的情况，通过getMethodName方法取得对象属性值
     *
     * @param <T>
     * @param value
     * @param targetList
     * @param getMethodName 注意是没有参数的get方法,如OfferProd.java 中的 getProdId方法不带括号
     * @return
     * @author zhuxia 2012-06-14
     */
    public static <T> boolean in(Object value, List<T> targetList, String getMethodName) {
        if(null == getMethodName || "".equals(getMethodName)) {
            return false;
        }
        if(null == value || null == targetList || 0 == targetList.size()) {
            return false;
        }
        try {
            Method method = null;
            for(T t : targetList) {
                if(null == t) {
                    continue;
                }
                if(null == method) {
                    for(Method m : t.getClass().getDeclaredMethods()) {
                        if(getMethodName.equals(m.getName()) && 0 == m.getParameterTypes().length) {
                            method = m;
                            break;
                        }
                    }
                    if(null == method) {
                        throw new RuntimeException(t.getClass().getSimpleName() + "中没有此方法：" + getMethodName);
                    }
                }

                Object obj = method.invoke(t);
                if(obj == value || value.equals(obj)) {
                    return true;
                }
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * 当object为空时，返回一个容量为0的ArrayList。
     * 用于for循环中使用，取代判空的语句，可以缩减代码层次。
     *
     * @param <T>
     * @param object
     * @return
     * @author sunhf
     */
    public static <T> List<T> nvlList(List<T> object) {
        return null == object ? new ArrayList<T>(0) : object;
    }

    /**
     * 创建一个T类型的ArrayList
     *
     * @param <T>
     * @return
     * @author zhuxia 2012-06-13
     */
    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    /**
     * 创建一个T类型的ArrayList；并使用入参初始化
     *
     * @param <T>
     * @param objects
     * @return
     * @author sunhf
     */
    public static <T> List<T> newArrayList(T... objects) {
        List<T> list = new ArrayList<T>();
        if(null == objects) {
            return list;
        }
        for(T object : objects) {
            list.add(object);
        }
        return list;
    }





    public static <K> List listAToListB(List<K> list, Class cls) {
        if(ListUtil.isListEmpty(list)) {
            return null;
        }
        List rLists = ListUtil.newArrayList();
        for(K temp : list) {
            try {
                Object o = Class.forName(cls.getName()).newInstance();
                BeanUtils.copyProperties(temp, o);
                rLists.add(o);
            } catch(InstantiationException e) {
                LOGGER.error("", e);
            } catch(IllegalAccessException e) {
                LOGGER.error("", e);
            } catch(ClassNotFoundException e) {
                LOGGER.error("", e);
            }
        }
        return rLists;
    }

    public static <E> List<E> mapToList(Map<Object, E> map) {
        List<E> list = ListUtil.newArrayList();
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()) {
            Object key = iterator.next();
            E e = map.get(key);
            list.add(e);
        }
        return list;
    }
    
	public static <T,K> void copyObjList(List<T> sourceList,List<K> targetList,Class<K> x){
		try{
			for(T t : sourceList){
				K k = x.newInstance();
				BeanUtils.copyProperties(t,k);
				targetList.add(k);
			}
		}catch(Exception e){
			 LOGGER.error("", e);
		}
	}
	
	/**
	 * 找List<Long>最大值
	 * @param list
	 */
	public static void sortLongDesc(List<Long> list){
		Collections.sort(list, new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				if (o1.longValue() < o2.longValue())
					return 1;
				if (o1.longValue() > o2.longValue())
					return -1;
				return 0;
			}
		});
	} 
}
