/*
 * Copyright 2017, TopicQuests
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
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
	 * Create with a different configuration file
	 * @param configFilePath
	 */
	public ProviderEnvironment(String configFilePath) {
		ConfigPullParser p = new ConfigPullParser(configFilePath);
		configProps = p.getProperties();
		System.out.println("Client config "+configProps);
		client = new Client(this);
		instance = this;				
	}
	
	/**
	 * 
	 */
	private ProviderEnvironment() {
		this("provider-config.xml");
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
