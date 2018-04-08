package dongyv.xch.abstractFactory;

//抽象工厂的实现类，其中的每一个工厂都返回他们的实现类
public class AbstaFactImpl implements AbstractFactory{

	@Override
	public JdbcFactory factoryA() {
		// TODO Auto-generated method stub
		return new JdbcFactAImpl();
	}

	@Override
	public FactoryB factoryB() {
		// TODO Auto-generated method stub
		return new FactBImpl();
	}

}
