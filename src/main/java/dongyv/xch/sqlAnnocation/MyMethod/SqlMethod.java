package dongyv.xch.sqlAnnocation.MyMethod;

import dongyv.xch.sqlAnnocation.MyAnno.Delete;
import dongyv.xch.sqlAnnocation.MyAnno.Group;
import dongyv.xch.sqlAnnocation.MyAnno.Having;
import dongyv.xch.sqlAnnocation.MyAnno.Insert;
import dongyv.xch.sqlAnnocation.MyAnno.Select;
import dongyv.xch.sqlAnnocation.MyAnno.Update;
import dongyv.xch.sqlAnnocation.MyAnno.Where;

public class SqlMethod {
	@Select(param="name,text",table="user")
	@Where(keys={"name","text"},values={"1","2"})
	@Group(param="name")
	@Having(keys= {"name"},f= {">"},values= {"1"})
	public void select(String sql){//反射的实体类
		if(sql == null || sql.equals("")){  
            System.out.println("Annocation erro!");  
        }else{  
            System.out.println(sql );  
        } 
	}
	@Select(table="users")
	public String select1(String sql){//反射的实体类
		if(sql == null || sql.equals("")){  
			System.out.println("Annocation erro!");  
		}
		return sql;
	}
	
	@Select(table="goods")
	@Where(keys = { "id" }, values = { "#{name}" })
	public String selectGoods(String sql) {
		
		return sql;
	}
	
	@Update(table="user",keys={"name"},values={"3"})
	@Where(keys={"text"},values={"2"})
	public void update(String sql){
		if(sql == null || sql.equals("")){  
			System.out.println("Annocation erro!");  
		}else{  
			System.out.println(sql );  
		} 
	}
	
	@Delete(table="user")
	@Where(keys={"text"},values={"2"})
	public void delete(String sql){
		if(sql == null || sql.equals("")){  
			System.out.println("Annocation erro!");  
		}else{  
			System.out.println(sql );  
		} 
	}
	
	@Insert(table="user",keys={"name","text"},values={"4","4"})
	public void insert(String sql){
		if(sql == null || sql.equals("")){  
			System.out.println("Annocation erro!");  
		}else{  
			System.out.println(sql );  
		} 
	}
}
