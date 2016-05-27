/**
 * 
 */
package org.topicquests.es.util;

import java.util.List;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * @author jackpark
 * A collection of query builders using {@link JSONObject}
 */
public class JSONQueryUtil {

	/**
	 * 
	 */
	public JSONQueryUtil() {
	}
	
	public JSONObject createJO() {
		return new JSONObject();
	}
	
	public JSONArray createJA() {
		return new JSONArray();
	}
	
	public JSONObject core(String key, String value) {
		JSONObject jo = createJO();
		jo.put(key, value);
		return jo;
	}

	public JSONObject core(String key, JSONObject value) {
		JSONObject jo = createJO();
		jo.put(key, value);
		return jo;
	}
	
	public JSONObject core(String key, JSONArray value) {
		JSONObject jo = createJO();
		jo.put(key, value);
		return jo;
	}
	
	public JSONObject match(JSONObject value) {
		JSONObject jo = createJO();
		jo.put("match", value);
		return jo;
	}

	public JSONObject match(String key, String value) {
		JSONObject t = createJO();
		t.put(key, value);
		JSONObject jo = createJO();
		jo.put("match", t);
		return jo;
	}

	public JSONObject match(String key, Object value) {
		JSONObject t = createJO();
		t.put(key, value);
		JSONObject jo = createJO();
		jo.put("match", t);
		return jo;
	}

	/**
	 * @see https://www.elastic.co/guide/en/elasticsearch/reference/current/search-request-from-size.html
	 * @param value
	 * @return
	 */
	public JSONObject from(int value) {
		JSONObject jo = createJO();
		jo.put("from", value);
		return jo;
	}

	public JSONObject size(int value) {
		JSONObject jo = createJO();
		jo.put("size", value);
		return jo;
	}

	public JSONObject sortFieldAsending(String field) {
		JSONObject jo = createJO();
		jo.put(field, "asc");
		return jo;
	}

	public JSONObject sortFieldDescending(String field) {
		JSONObject jo = createJO();
		jo.put(field, "desc");
		return jo;
	}
	
	/**
	 * @see https://www.elastic.co/guide/en/elasticsearch/reference/current/search-request-sort.html
	 * @param fields
	 * @return
	 */
	public JSONObject simpleSort(JSONArray fields) {
		JSONObject jo = createJO();
		jo.put("sort", fields);
		return jo;
	}

	/**
	 * @see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-fuzzy-query.html
	 * @param field
	 * @param text
	 * @return
	 */
	public JSONObject simpleFuzzy(String field, String text) {
		JSONObject jo = createJO();
		jo.put(field, text);
		JSONObject result = createJO();
		result.put("fuzzy", jo);
		return result;
	}

	/**
	 * @see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-prefix-query.html
	 * @param field
	 * @param prefix
	 * @return
	 */
	public JSONObject simplePrefix(String field, String prefix) {
		JSONObject jo = createJO();
		jo.put(field, prefix);
		JSONObject result = createJO();
		result.put("prefix", jo);
		return result;
	}

	/**
	 * @see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-wildcard-query.html
	 * @param field
	 * @param text  includes a '*'
	 * @return
	 */
	public JSONObject simpleWildcard(String field, String text) {
		JSONObject jo = createJO();
		jo.put(field, text);
		JSONObject result = createJO();
		result.put("wildcard", jo);
		return result;
	}

	/**
	 * @see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-simple-query-string-query.html
	 * @param text
	 * @param fields cannot be <code>null</code>
	 * @return
	 */
	public JSONObject simpleQueryString(String text, List<String> fields) {
		JSONObject result = createJO();
		result.put("query", text);
		result.put("fields", fields);
		return result;
	}
	/*
	 * @see https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-match-query.html
	 * @param message
	 * @return
	 */
	public JSONObject matchMessage(String message) {
		JSONObject jo = createJO();
		jo.put("message", message);
		JSONObject result = createJO();
		result.put("match", jo);
		return result;
	}


	public JSONObject term(JSONObject value) {
		JSONObject jo = createJO();
		jo.put("match", value);
		return jo;
	}

	public JSONObject term(String key, String value) {
		JSONObject t = createJO();
		t.put(key, value);
		JSONObject jo = createJO();
		jo.put("term", t);
		return jo;
	}

	/** 
	 * an example complex query:
	 	{ "query": {
	 		"filtered": {
	 			"bool": {
	 				"must": {"term": {"foo":"bar"}},
	 				"must-not": {
	 					"query": {
	 						"match": {"baa":"blah"}
	 					}
	 				}
	 			}
	 		}
	 	  }
	 	}
	 */
	/**
	 * Must use on match queries including bool queries
	 * @param value
	 * @return
	 */
	public JSONObject query(JSONObject value) {
		JSONObject jo = createJO();
		jo.put("query", value);
		return jo;
	}

	public JSONObject query(JSONArray value) {
		JSONObject jo = createJO();
		jo.put("query", value);
		return jo;
	}

	/**
	 * Used when filter queries exist
	 * @param value
	 * @return
	 */
	public JSONObject filtered(JSONObject value) {
		JSONObject jo = createJO();
		jo.put("filtered", value);
		return jo;
	}

	/**
	 * Use on term queries including bool queries
	 * @param value
	 * @return
	 */
	public JSONObject filter(JSONObject value) {
		JSONObject jo = createJO();
		jo.put("filter", value);
		return jo;
	}

	public JSONObject bool(JSONObject value) {
		JSONObject jo = createJO();
		jo.put("bool", value);
		return jo;
	}

	public JSONObject must(JSONObject value) {
		JSONObject jo = createJO();
		jo.put("must", value);
		return jo;
	}

	public JSONObject must(JSONArray value) {
		JSONObject jo = createJO();
		jo.put("must", value);
		return jo;
	}

	public JSONObject mustNot(JSONObject value) {
		JSONObject jo = createJO();
		jo.put("must", value);
		return jo;
	}

	public JSONObject mustNot(JSONArray value) {
		JSONObject jo = createJO();
		jo.put("must", value);
		return jo;
	}
	
	public JSONObject should(JSONObject value) {
		JSONObject jo = createJO();
		jo.put("must", value);
		return jo;
	}

	public JSONObject should(JSONArray value) {
		JSONObject jo = createJO();
		jo.put("must", value);
		return jo;
	}


}
