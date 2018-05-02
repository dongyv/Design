package dongyv.xch.rpc;

public class HelloServiceImpl implements HelloService{

	@Override
	public String Hello(String name) {
		// TODO Auto-generated method stub
		return "hello "+name;
	}

}
