package dongyv.xch.sqlAnnocation.MyAnno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented //说明该注解将被包含在javadoc中
@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.TYPE,ElementType.METHOD})//注解
public @interface Update {
	public String table();
	public String[] keys();
	public String[] values();
	
}
