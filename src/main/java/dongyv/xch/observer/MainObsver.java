package dongyv.xch.observer;

import dongyv.xch.model.Users;

public class MainObsver extends RegisterObsver{
	
	@Override
	public void exectute(Users user) {//用户注册后进行更新
		// TODO Auto-generated method stub
		System.out.println("用户"+user.getUser()+"访问当前");
	}
	

}
