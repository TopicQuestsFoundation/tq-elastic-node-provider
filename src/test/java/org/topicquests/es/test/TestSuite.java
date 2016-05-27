/**
 * 
 */
package org.topicquests.es.test;

import org.junit.runner.Result;
import org.topicquests.es.test.core.SearchIntegrationTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

/**
 * @author jackpark
 *
 */
public class TestSuite {

	/**
	 * 
	 */
	public TestSuite() {
		System.out.println("TestSuite-");
		Result result = JUnitCore.runClasses(SearchIntegrationTest.class);
		for (Failure failure: result.getFailures() ) {
			System.out.println(failure.toString());
		}
	}
//initializationError(org.topicquests.es.test.core.SearchIntegrationTest): org/apache/lucene/search/QueryCachingPolicy

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestSuite();
	}

}
