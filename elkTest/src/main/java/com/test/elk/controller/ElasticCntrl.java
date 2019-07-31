package com.test.elk.controller;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;

import com.test.elk.api.ElasticApi;
import com.test.elk.vo.Weather;

@Controller
public class ElasticCntrl {

	//@Autowired
	//ElasticApi elasticApi;

	private final String ELASTIC_INDEX = "test"; //database
//	private final String ELASTIC_TYPE = "world"; //table ==> type형식 elasticsearch 7 이상부터 사용하지 않음
	private final String ELASTIC_ENDPOINT = "_doc";
	private final String ELASTIC_ENDPOINT_CREATE = "_create";
	private final String ELASTIC_URL = ELASTIC_INDEX+"/"+ELASTIC_ENDPOINT;
	private final String ELASTIC_SEARCH = ELASTIC_INDEX+"/"+"_search";

	//@Test
	/**
	 * 자동 ID 생성 시 POST 사용 endpoint => _doc
	 * ID지정하여 생성 시 PUT 사용 endpoint => _create
	 * 
	 * ===============생성 방법==================
	 * PUT twitter/_doc/1?op_type=create
	 * PUT twitter/_create/1
	 * ------------------------------
	 * POST twitter/_doc/
	 * =======================================
	 */
	public void elastic_POST() {
		ElasticApi elasticApi = new ElasticApi();
		
		String id = "";
		
		String url = ELASTIC_URL;
		String method = "POST";
		
		if(!id.isEmpty()) {
			url = ELASTIC_URL+"/"+id+"?op_type=create";
//			url = ELASTIC_INDEX + "/" + ELASTIC_ENDPOINT_CREATE + "/" + id;
			method = "PUT";
		}
		
		
		Weather weather = new Weather();
		weather.setCity("Seoul");
		weather.setTemperature(10.2);
		weather.setSeason("Winter");

		Map<String, Object> result = elasticApi.callElasticApi(method, url, weather, null);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("resultBody"));
	}
	
	//@Test
	public void elastic_PUT() {
		ElasticApi elasticApi = new ElasticApi();
		String id = "122345";
//		String url = ELASTIC_INDEX + "/" + ELASTIC_TYPE+"/"+id;
		String url = ELASTIC_INDEX + "/" + ELASTIC_ENDPOINT+"/"+id;
		Weather weather = new Weather();
		weather.setCity("Tokyo");
		weather.setTemperature(12.3);
		weather.setSeason("Winter");

		Map<String, Object> result = elasticApi.callElasticApi("PUT", url, weather, null);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("resultBody"));
	}


	//@Test
	public void elastic_GET() {
		ElasticApi elasticApi = new ElasticApi();
		String id = "1";
//		String url = ELASTIC_INDEX + "/" + ELASTIC_TYPE+"/"+id;
		String url = ELASTIC_INDEX + "/" + ELASTIC_ENDPOINT+"/"+id;
		Map<String, Object> result = elasticApi.callElasticApi("GET", url, null, null);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("resultBody"));
	}


	//@Test
	public void elastic_DELETE() {
		ElasticApi elasticApi = new ElasticApi();
		String id = "122345";
//		String url = ELASTIC_INDEX + "/" + ELASTIC_TYPE+"/"+id;
		String url = ELASTIC_INDEX + "/" + ELASTIC_ENDPOINT+"/"+id;
		Map<String, Object> result = elasticApi.callElasticApi("DELETE", url, null, null);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("resultBody"));
	}
	
	
	
	public void elastic_data(Weather weather, String id, String method) {
		ElasticApi elasticApi = new ElasticApi();
		
		String url = ELASTIC_URL;
		System.out.println("id:"+id);
		if(method.equals("POST")) {
			if(!id.isEmpty()) {
//				url = ELASTIC_URL + "/" + id;
				url = ELASTIC_URL + id + "?op_type=create";
				method = "PUT";
			}
		}else if(method.equals("PUT")) {
			url = url+"/"+id;
		}else if(method.equals("GET")) {
			url = url+"/"+id;
			weather = !weather.equals(null) ? null : null;
		}else if(method.equals("DELETE")) {
			url = url+"/"+id;
			weather = !weather.equals(null) ? null : null;
		}
		
		Map<String, Object> result = elasticApi.callElasticApi(method, url, weather, null);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("resultBody"));
	}
	
	
	
	
	@Test
	public void main() {
		
		String id = "";
		String method = "";
		
		Weather weather = new Weather();
		weather.setCity("Incheon");
		weather.setTemperature(31.0);
		weather.setSeason("Summer");
		
		id = "FrLaQWwBAIL5G9LT7psp";
		method = "GET";
		
		elastic_data(weather, id, method);
		
		
//		elastic_POST();
//		elastic_GET();
	}
}
