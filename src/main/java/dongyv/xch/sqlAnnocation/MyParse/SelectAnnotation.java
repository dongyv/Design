package dongyv.xch.sqlAnnocation.MyParse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dongyv.xch.sqlAnnocation.MyAnno.Delete;
import dongyv.xch.sqlAnnocation.MyAnno.Group;
import dongyv.xch.sqlAnnocation.MyAnno.Having;
import dongyv.xch.sqlAnnocation.MyAnno.Insert;
import dongyv.xch.sqlAnnocation.MyAnno.Select;
import dongyv.xch.sqlAnnocation.MyAnno.Update;
import dongyv.xch.sqlAnnocation.MyAnno.Where;

public class SelectAnnotation {
	
	public Object parseMethod(Class clazz,String name) {
		Object obj,invoke = null;
		try {
			obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
			if(name != null && !"".equals(name)) {
				for (Method method : clazz.getDeclaredMethods()) {//获取方法
					if(name.equals(method.getName())) {
						invoke = doSomeThing(obj,method);
					}
				}
			}else {
				for (Method method : clazz.getDeclaredMethods()) {//获取方法
					invoke = doSomeThing(obj,method);
				}
			}
			
		} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invoke;
	}
	
	private Object doSomeThing(Object target,Method method) {
			Object invoke = null;
			Select select = method.getAnnotation(Select.class);//获取注解
			Update update = method.getAnnotation(Update.class);//获取注解
			Delete delete = method.getAnnotation(Delete.class);//获取注解
			Insert insert = method.getAnnotation(Insert.class);//获取注解
			Group group = method.getAnnotation(Group.class);//获取注解
			Having having = method.getAnnotation(Having.class);//获取注解
			Where where = method.getAnnotation(Where.class);//获取注解
			String sql = "";
			StringBuffer sb = null ;
			if(select!=null){
				sb = selectSQL(select,where,group,having);
			}
			if(update!=null){
				sb = updateSQL(update,where,group,having);
			}
			if(delete!=null){
				sb = deleteSQL(delete,where,group,having);
			}
			if(insert!=null){
				sb = insertSQL(insert,where,group,having);
			}
			sql = sb.toString();
			try {
				invoke = method.invoke(target, sql);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return invoke;
		
	}
	
	private StringBuffer selectSQL(Select select,Where where,Group group,Having having){
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append(select.param());
		sb.append(" from ");
		sb.append(select.table());
		
		StringBuffer sb1 = whereGroup(sb,where,group,having);
		return sb1;
	}
	
	private StringBuffer updateSQL(Update update,Where where,Group group,Having having){
		StringBuffer sb = new StringBuffer();
		sb.append("update ");
		sb.append(update.table());
		sb.append(" set ");
		for(int i=0;i<update.keys().length;i++){
			sb.append(update.keys()[i]);
			sb.append(" = ");
			sb.append(update.values()[i]);
			if(i != update.keys().length-1){
				sb.append(" and ");
			}
		}
		StringBuffer sb1 = whereGroup(sb,where,group,having);
		return sb1;
	}
	
	private StringBuffer insertSQL(Insert insert,Where where,Group group,Having having){
		StringBuffer sb = new StringBuffer();
		sb.append("insert into (");
		for(int i=0;i<insert.keys().length;i++){
			sb.append(insert.keys()[i]);
			if(i!=insert.keys().length-1){
				sb.append(",");
			}
		}
		sb.append(")");
		sb.append("values(");
		for(int i=0;i<insert.values().length;i++){
			sb.append(insert.values()[i]);
			if(i!=insert.values().length-1){
				sb.append(",");
			}
		}
		sb.append(")");
		return sb;
	}
	
	private StringBuffer deleteSQL(Delete delete,Where where,Group group,Having having){
		StringBuffer sb = new StringBuffer();
		sb.append("delete ");
		sb.append(delete.table());
		StringBuffer sb1 = whereGroup(sb,where,group,having);
		return sb1;
	}
	
	private StringBuffer whereGroup(StringBuffer sb,Where where,Group group,Having having){
		if(where != null){
			sb.append(" where ");
			for(int i=0;i<where.keys().length;i++){
				sb.append(where.keys()[i]);
				sb.append(" = ");
				sb.append(where.values()[i]);
				if(i!=where.keys().length-1){
					sb.append(" and ");
				}
			}
		}
		if(group != null){
			sb.append(" group by ");
			sb.append(group.param());
			if(having != null) {
				sb.append(" having ");
				for(int i=0;i<having.keys().length;i++) {
					sb.append(having.keys()[i]);
					sb.append(""+having.f()[i]+"");
					sb.append(having.values()[i]);
				}
			}
		}
		return sb;
	}
	
}

