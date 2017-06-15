/**
 * 
 */
package org.topicquests.node.provider;

import java.util.Map;

import org.topicquests.support.config.ConfigPullParser;
import org.topicquests.support.util.LoggingPlatform;
import org.topicquests.support.util.Tracer;

import net.minidev.json.JSONObject;

/**
 * @author park
 *
 */
public class ProviderEnvironment {
	private LoggingPlatform log = LoggingPlatform.getInstance("logger.properties");
	private Map<String,Object>configProps;
	private Client client;
	private static ProviderEnvironment instance = null;

	/**
	 * 
	 */
	private ProviderEnvironment() {
		ConfigPullParser p = new ConfigPullParser("provider-config.xml");
		configProps = p.getProperties();
		System.out.println("Client config "+configProps);
		client = new Client(this);
		instance = this;		
	}
		
	/**
	 * The only way for multiple users in the same JVM to boot
	 * this platform.
	 * @return
	 */
	public static ProviderEnvironment getInstance() {
		if (instance == null)
			instance = new ProviderEnvironment();
		return instance;
	}

	public Client getClient() {
		return client;
	}
	
	/**
	 * Create app-specific client
	 * @param index
	 * @param mappings
	 * @return
	 */
	public Client createClient(String index, JSONObject mappings) {
		return new Client(this, index, mappings);
	}
	/////////////////////////////
	// Utilities
	/////////////////////////////
	
	public Map<String,Object> getProperties() {
		return configProps;
	}
	
	public Object getProperty(String key) {
		return configProps.get(key);
	}

	public String getStringProperty(String key) {
		return (String)configProps.get(key);
	}

	public void logDebug(String msg) {
		log.logDebug(msg);
	}
	
	public void logError(String msg, Exception e) {
		log.logError(msg,e);
	}
	
	public void record(String msg) {
		log.record(msg);
	}

	public Tracer getTracer(String name) {
		return log.getTracer(name);
	}
}
