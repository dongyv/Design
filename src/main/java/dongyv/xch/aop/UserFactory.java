package dongyv.xch.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserFactory {
	/***
     * 获取对象方法
     * @param obj
     * @return
     */
    private static Object getUsersBase(Object obj){
        //获取代理对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new UserAOPHandle(obj, new AOPMethod() {
                	// 这里写方法执行前的AOP切入方法
					@Override
					public void before(Object proxy, Method method, Object[] args) {
						// TODO Auto-generated method stub
						System.err.println("我在" + method.getName() + "方法执行前执行");
					}
					// 这里写方法执行后的AOP切入方法
					@Override
					public void after(Object proxy, Method method, Object[] args) {
						// TODO Auto-generated method stub
						System.err.println("我在 " + method.getName() + "方法执行后执行");
					}
				}));
    }
    /***
     * 获取对象方法
     * @param <T>
     * @param obj
     * @return
     */
    @SuppressWarnings("unchecked")
    public static  <T> T getUsers(Object obj){
        return (T) getUsersBase(obj);
    }
    /***
     * 获取对象方法
     * @param <T>
     * @param className
     * @return
     */
    @SuppressWarnings("unchecked")
    public static   <T> T getUsers(String className){
        Object obj=null;
        try {
            obj= getUsers(Class.forName(className).newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)obj;
    }
    /***
     * 获取对象方法
     * @param <T>
     * @param clz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static   <T> T  getUsers(Class clz){
        Object obj=null;
        try {
            obj= getUsersBase(clz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)obj;
    }
}
