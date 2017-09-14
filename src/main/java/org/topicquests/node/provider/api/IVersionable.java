/*
 * Copyright 2015, TopicQuests
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
package org.topicquests.node.provider.api;

/**
 * @author park
 *
 */
public interface IVersionable {
	public static final String
		VERSION_PROPERTY = "_ver"; //ITQCoreOntology.VERSION;
	
	/**
	 * Use a  <code>_version_</code> field
	 * @param version
	 */
	void setVersion(String version);
	
	/**
	 * Return the SOLR4 version as a String
	 * @return
	 */
	String getVersion();

}
