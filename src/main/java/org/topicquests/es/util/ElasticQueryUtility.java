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
package org.topicquests.es.util;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author jackpark
 *
 */
public class ElasticQueryUtility {

	/**
	 * 
	 */
	public ElasticQueryUtility() {
	}
	
	public String getMatchQuery(String key, String value) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery(key, value));
		String q = searchSourceBuilder.toString();
		return q;
	}
	
	public String getMatchQuery(String key, String value, int start, int count) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery(key, value));
		searchSourceBuilder.from(start);
		if (count > -1)
			searchSourceBuilder.size(count);
		String q = searchSourceBuilder.toString();
//		System.out.println("GETMATCHQUERY "+q);
		return q;
	}

	public QueryBuilder startKeywordQuery(String key, String value) {
		QueryBuilder qb = QueryBuilders.termQuery(key, value);
		
		return qb;
	}
	
	public QueryBuilder startMatchQuery(String key, String value) {
		QueryBuilder qb = QueryBuilders.matchQuery(key, value);
		return qb;
	}

	
	public QueryBuilder andStuffQuery(QueryBuilder qb, JSONObject mustTerms, JSONObject mustMatches) 
	{
		BoolQueryBuilder root = QueryBuilders.boolQuery();
		Iterator<String>itr;
		String key;
		QueryBuilder qb1;
		if (mustTerms != null) {
			itr = mustTerms.keySet().iterator();
			while (itr.hasNext()) {
				key = itr.next();
				qb1 = QueryBuilders.matchQuery(key, mustTerms.get(key));//termQuery
				root.must(qb1);
			}
		}
		if (mustMatches != null) {
			itr = mustMatches.keySet().iterator();
			while (itr.hasNext()) {
				key = itr.next();
				qb1 = QueryBuilders.matchQuery(key, mustMatches.get(key));
				root.must(qb1);
			}
		}
		return root;
	}
	////////////////////
	// {
	//		"query": {
	//			"bool": {
	//				"must": [{
	//					"match": {
	//						"crgtyp": {
	//							"query": "testType",
	//							"type": "boolean"
	//						}
	//					}
	//				}, {
	//					"match": {
	//						"testKey": {
	//							"query": "testVal",
	//							"type": "boolean"
	//						}
	//					}
	//				}]
	//			}
	//		}
	//	}
	/////////////////////////
	/**
	 * @param qb
	 * @param key
	 * @param value
	 * @return
	 */
	public QueryBuilder andKeywordQuery(QueryBuilder qb, String key, String value) {
		QueryBuilder result = QueryBuilders.boolQuery()
				.must(matchQuery(key, value)) //toJSONString
				.must(qb);
		return result;
	}

	public QueryBuilder andMatchQuery(QueryBuilder qb, String key, String value) {
		QueryBuilder result = QueryBuilders.boolQuery()
				.must(matchQuery(key, value))
				.must(qb);
		return result;
	}

	public QueryBuilder orKeywordQuery(QueryBuilder qb, String key, String value) {
		QueryBuilder result = QueryBuilders.boolQuery()
				.should(termQuery(key, value))
				.should(qb);
		return result;
	}
	
	public QueryBuilder orMatchQuery(QueryBuilder qb, String key, String value) {
		QueryBuilder result = QueryBuilders.boolQuery()
				.should(matchQuery(key, value))
				.should(qb);
		return result;
	}

	/**
	 * Query based on a match, sort, and range
	 * @param key
	 * @param value
	 * @param sortField
	 * @param increasing
	 * @param start
	 * @param count
	 * @return
	 */
	public String getMatchSortQuery(String key, String value, String sortField, boolean increasing, int start, int count) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery(key, value));
		if (increasing)
			searchSourceBuilder.sort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC));
		else
			searchSourceBuilder.sort(SortBuilders.fieldSort(sortField).order(SortOrder.DESC));
		searchSourceBuilder.from(start);
		if (count > -1)
			searchSourceBuilder.size(count);
		String q = searchSourceBuilder.toString();
//		System.out.println("GETMATCHQUERY "+q);
		return q;
	}

	public String createQueryFromQuery(QueryBuilder qb, int start, int count) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(qb);
		searchSourceBuilder.from(start);
		if (count > -1)
			searchSourceBuilder.size(count);
		return searchSourceBuilder.toString();
	}
	public String createQueryFromStringQuery(String qb, int start, int count) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(qb);
		searchSourceBuilder.from(start);
		if (count > -1)
			searchSourceBuilder.size(count);
		return searchSourceBuilder.toString();
	}

}
