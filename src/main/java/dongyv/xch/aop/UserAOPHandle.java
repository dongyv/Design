package dongyv.xch.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserAOPHandle implements InvocationHandler{
	//保存对象
	private Object object;
	private AOPMethod method;
	
	public UserAOPHandle(Object object,AOPMethod method) {
		this.object = object;
		this.method = method;
	}
	/**
     * 这个方法会自动调用,Java动态代理机制
     * 会传入下面是个参数
     * @param Object proxy  代理对象的接口,不同于对象
     * @param Method method 被调用方法
     * @param Object[] args 方法参数
     * 不能使用invoke时使用proxy作为反射参数时,因为代理对象的接口,不同于对象
     * 这种代理机制是面向接口，而不是面向类的
     **/
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		 //方法返回值
        Object ret=null;
       /* //打印方法名称
        System.err.println("执行方法:"+method.getName()+"n参数类型为:");
        //打印参数
        for(Class type:method.getParameterTypes())
            System.err.println(type.getName());
        //打印返回类型
        System.err.println("返回数据类型:"+method.getReturnType().getName());
        //反射调用方法
        ret=method.invoke(object, args);
        //声明结束
        System.err.println("方法执行结束");
        //返回反射调用方法的返回值*/ 
      //修改的地方在这里哦
        this.method.before(proxy, method, args);
        ret=method.invoke(object, args);
        //修改的地方在这里哦
        this.method.after(proxy, method, args);
        return ret;
	}
	
}
