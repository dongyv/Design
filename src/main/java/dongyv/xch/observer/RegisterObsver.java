package dongyv.xch.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dongyv.xch.model.Users;

//注册的用户
public abstract class RegisterObsver {
	public List<Registers> lists = new ArrayList<>();
	public void addUser(Registers registers) {
		lists.add(registers);
	}
	public void removeUser(Registers registers) {
		lists.remove(registers);
		
	}
	
	//被观察者通知
	public void noty(Users users) {
		Iterator<Registers> iterator = lists.iterator();
		while(iterator.hasNext()) {
			Registers userregister = iterator.next();
			exectute(userregister.getUser());
		}
	}
	
	public abstract void exectute(Users users);
}
