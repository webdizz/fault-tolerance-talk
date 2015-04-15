package name.webdizz.jeeconf.fault.tolerance.timeout;

public interface HeavyOperation {

    String doHeavyWeightOperation() throws InterruptedException;
}
