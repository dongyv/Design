package dongyv.xch.model;

import dongyv.xch.observer.Registers;

public class DongRegister implements Registers{
	private Users users;
	
	@Override
	public Users getUser() {
		// TODO Auto-generated method stub
		return users;
	}
	
	@Override
	public void setUser(Users users) {
		// TODO Auto-generated method stub
		this.users = users;
	}

	@Override
	public Users register() {
		// TODO Auto-generated method stub
		users.setFlag(0);
		return users;
		
	}

}