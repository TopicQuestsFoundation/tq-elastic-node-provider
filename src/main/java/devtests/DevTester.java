/**
 * 
 */
package devtests;

/**
 * @author park
 *
 */
public class DevTester {

	/**
	 * 
	 */
	public DevTester() {
		System.out.println("DevTesterStarting");
		new FirstTest();
		
		System.out.println("DevTesterDone");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DevTester();
	}

}
