/**
 * 
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
