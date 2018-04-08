package dongyv.xch.observer;

import dongyv.xch.model.Users;

public interface Registers {
	public Users register();
	public Users getUser();
	public void setUser(Users users);
}
