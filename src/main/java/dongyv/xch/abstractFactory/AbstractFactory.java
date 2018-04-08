package dongyv.xch.abstractFactory;
//抽象工厂有俩个工厂类接口类
public interface AbstractFactory {
	public JdbcFactory factoryA();
	public FactoryB factoryB();
}
