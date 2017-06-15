/**
 * 
 */
package devtests;

import org.topicquests.node.provider.Client;
import org.topicquests.node.provider.ProviderEnvironment;

/**
 * @author park
 *
 */
public class FirstTest {
	private ProviderEnvironment environment;
	private Client client;
	/**
	 * 
	 */
	public FirstTest() {
		environment = ProviderEnvironment.getInstance();
		client = environment.getClient();
		System.out.println("Client "+client);
	}

}
