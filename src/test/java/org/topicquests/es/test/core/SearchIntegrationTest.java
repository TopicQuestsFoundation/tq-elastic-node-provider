/**
 * 
 */
package org.topicquests.es.test.core;

import org.topicquests.common.api.IResult;
import org.topicquests.es.test.common.AbstractIntegrationTest;

import io.searchbox.core.Search;

import org.junit.Test;
import org.elasticsearch.test.ESIntegTestCase;

/**
 * @author jackpark
 *
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class SearchIntegrationTest extends AbstractIntegrationTest {

	private static final String INDEX = "twitter";
	String query = "{\n" +
            "    \"query\": {\n" +
            "        \"filtered\" : {\n" +
            "            \"query\" : {\n" +
            "                \"query_string\" : {\n" +
            "                    \"query\" : \"test\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"filter\" : {\n" +
            "                \"term\" : { \"user\" : \"kimchy\" }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}";
	
	@Test
	public void firstSearch() throws Exception {
		System.out.println("GO");
		String theQuery = new Search.Builder(query).build().toString();
		System.out.println("AAA "+theQuery);
		IResult result = client.listObjectsByQuery(theQuery, INDEX);
		System.out.println("RES "+result.getErrorString()+" | "+result.getResultObject());
		assertEquals(result.getErrorString(), "");
	}

}
