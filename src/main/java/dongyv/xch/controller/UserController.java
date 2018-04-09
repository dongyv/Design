package dongyv.xch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dongyv.xch.client.Client;
import dongyv.xch.model.DongRegister;
//import dongyv.xch.model.DongRegister;
import dongyv.xch.model.Users;
import dongyv.xch.model.YvRegister;
import dongyv.xch.observer.MainObsver;
import dongyv.xch.observer.RegisterObsver;
import dongyv.xch.observer.Registers;
import dongyv.xch.service.UserService;
import dongyv.xch.util.NumberUtil;

@Controller
@RequestMapping(value="/user")
public class UserController{
	public List<Users> lists = new ArrayList<>();
	public RegisterObsver rgsobv = new MainObsver();
	private volatile int i = 0;
	@Autowired
	public UserService userService;

	@RequestMapping(value="/register",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String register(HttpServletRequest reuqest,@RequestParam("user")String user) {
		Registers register;
		Users users = new Users();
		users.setUser(user);
		if(i%2==0) {
			register = new YvRegister();
		}else {
			register = new DongRegister();
		}
		register.setUser(users);
		i++;
		Users user1 = register.register();//含flag属性
		
		userService.insert(user1);
		noty(user1,register);//通知方法
		int id = NumberUtil.getNumber(4);
		System.out.println(id);
		try {
			Client.sendMsg(String.valueOf(id));//模拟发送 订单id
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "注册成功";
	}
	
	@RequestMapping(value="/toRegister")
	public String toRegister() {
		return "index";
	}
	
	private void noty(Users user,Registers register) {
		System.out.println(register.getUser().getUser());
		rgsobv.addUser(register);
		List<Registers> regiter = rgsobv.lists;
		for(Registers register1:regiter) {
			System.out.println("当前运行第"+register1.getUser().getUser());
		}
		rgsobv.noty(user);//通知具体用户方法
	}
}
