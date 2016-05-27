package org.topicquests.es.test.common;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.topicquests.node.provider.Client;
import org.topicquests.node.provider.ProviderEnvironment;

/**
 * @author Jack Park
 * This builds an integration test framework on top of the ElasticSearch
 * test framework
 * @see https://www.elastic.co/guide/en/elasticsearch/reference/current/integration-tests.html
 * @see https://github.com/elastic/elasticsearch/blob/master/TESTING.asciidoc
 */
@Ignore
public abstract class AbstractIntegrationTest extends ESIntegTestCase {

    protected ProviderEnvironment environment;
	protected Client client;

    @Before
    public void setUp() throws Exception {
    	super.setUp();
    	System.out.println("STARTING");
        environment = new ProviderEnvironment();
        client = environment.getClient();
    }

    @After
    public void tearDown() throws Exception {
    	super.tearDown();
        client = null;
        environment = null;
    }

}
