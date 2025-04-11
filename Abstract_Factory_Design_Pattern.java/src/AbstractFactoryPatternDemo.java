import GUIfactory.Factory;
import GUIfactory.MacFactory;
import GUIfactory.WindowsFactory;

public class AbstractFactoryPatternDemo {
    public static void main(String[] args) throws Exception {
        Factory macFactory = new MacFactory();
        Factory windowsFactory = new WindowsFactory();

        macFactory.getButton().Render();
        macFactory.getCheckBox().Check();;

        windowsFactory.getButton().Render();;
        windowsFactory.getCheckBox().Check();;
    }
}
