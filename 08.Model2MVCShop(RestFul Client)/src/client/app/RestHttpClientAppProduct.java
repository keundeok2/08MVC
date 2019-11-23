package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public class RestHttpClientAppProduct {

	public static void main(String[] args) throws Exception {
//		RestHttpClientAppProduct.addProductPost_JsonSimple();
//		RestHttpClientAppProduct.addProductPost_Codehaus();
//		RestHttpClientAppProduct.getProductTestGet_JsonSimple();
//		RestHttpClientAppProduct.getProductTestGet_Codehaus();
//		RestHttpClientAppProduct.getListProductTestGet_JsonSimple();
//		RestHttpClientAppProduct.getListProductTestGet_Codehaus();
//		RestHttpClientAppProduct.getListProductTestPost_JsonSimple();
//		RestHttpClientAppProduct.getListProductTestPost_Codehaus();
//		RestHttpClientAppProduct.updateProductTestPost_JsonSimple();
		RestHttpClientAppProduct.updateProductTestPost_Codehaus();
		
	}
	
	public static void addProductPost_JsonSimple() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		JSONObject json = new JSONObject();
		json.put("prodName", "restTestName");
		json.put("prodDetail", "restTestDetail");
		json.put("manuDate", "20191111");
		json.put("price", 1234);
		json.put("fileName", "restTestFileName");
		json.put("prodQuantity", 1234);
		HttpEntity httpEntity = new StringEntity(json.toString(), "utf-8");

		httpPost.setEntity(httpEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println(httpResponse);
		
		HttpEntity responseEntity = httpResponse.getEntity();
		InputStream is = responseEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		JSONObject jsonValue = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonValue);
	}
	
	public static void addProductPost_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		Product product = new Product();
		product.setProdName("restTestName2");
		product.setProdDetail("restTestDetail2");
		product.setPrice(1234);
		product.setFileName("restTestFileName2");
		product.setManuDate("20191111");
		product.setProdQuantity(123);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(product);
		
		HttpEntity httpEntity = new StringEntity(json, "utf-8");
		httpPost.setEntity(httpEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println(httpResponse);
		
		HttpEntity responseEntity = httpResponse.getEntity();
		InputStream is = responseEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is , "utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonObj);
		
		
	}
	
	public static void getProductTestGet_JsonSimple() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/product/json/getProduct/10193";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept","application/json");
		httpGet.setHeader("Content-Type","application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		System.out.println(httpResponse);
		
		HttpEntity httpEntity = httpResponse.getEntity();
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonObj);
	}
	
	public static void getProductTestGet_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/product/json/getProduct/10193";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept","application/json");
		httpGet.setHeader("Content-Type","application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		System.out.println(httpResponse);
		
		HttpEntity httpEntity = httpResponse.getEntity();
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonObj.toString(), Product.class);
		System.out.println(product);
	}
	
	public static void getListProductTestGet_JsonSimple() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/product/json/listProduct";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		System.out.println(httpResponse);
		
		HttpEntity responseEntity = httpResponse.getEntity();
		InputStream is = responseEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonObj);
		
	}
	
	public static void getListProductTestGet_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/product/json/listProduct";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity responseEntity = httpResponse.getEntity();
		
		InputStream is = responseEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Product> list = objectMapper.readValue(jsonObj.get("list").toString(), new TypeReference<List<Product>>() {
		});
		Search search = objectMapper.readValue(jsonObj.get("search").toString(), Search.class);
		Page resultPage = objectMapper.readValue(jsonObj.get("resultPage").toString(), Page.class);
		int totalCount = Integer.parseInt(jsonObj.get("totalCount").toString());
	}
	
	public static void getListProductTestPost_JsonSimple() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/product/json/listProduct";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("currentPage", 1);
		json.put("searchCondition", "1");
		json.put("searchKeyword", "restTestName");
		
		HttpEntity httpEntity = new StringEntity(json.toString(), "utf-8");
		httpPost.setEntity(httpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity responseEntity = httpResponse.getEntity();
		
		InputStream is = responseEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonObj);
		
		
	}
	
	public static void getListProductTestPost_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/product/json/listProduct";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setSearchCondition("1");
		search.setSearchKeyword("restTestName");
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(search);
		
		HttpEntity httpEntity = new StringEntity(jsonString, "utf-8");
		httpPost.setEntity(httpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity responseEntity = httpResponse.getEntity();
		InputStream is = responseEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonObj);
		
		ObjectMapper  objectMapper2 = new ObjectMapper();
		List<Product> list=objectMapper2.readValue(jsonObj.get("list").toString(), new TypeReference<List<Product>>() {});
		Search returnSearch = objectMapper.readValue(jsonObj.get("search").toString(), Search.class);
		Page resultPage = objectMapper.readValue(jsonObj.get("resultPage").toString(), Page.class);
		int totalCount = Integer.parseInt(jsonObj.get("totalCount").toString());
	}
	
	public static void updateProductTestPost_JsonSimple() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://localhost:8080/product/json/updateProduct";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("prodNo", 10193);
		json.put("prodName", "restControllerUpdateName");
		json.put("price", 1234);
		json.put("prodDetail", "UpdateDetail");
		json.put("manuDate", "20191123");
		json.put("prodQuantity", 1234);
		json.put("fileName", "updateFileName");
		
		HttpEntity httpEntity = new StringEntity(json.toString(),"utf-8");
		httpPost.setEntity(httpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
	}
	
	public static void updateProductTestPost_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://localhost:8080/product/json/updateProduct";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		Product product = new Product();
		product.setFileName("update1234");
		product.setManuDate("20191111");
		product.setPrice(4321);
		product.setProdDetail("updateDetail1234");
		product.setProdName("updateName1234");
		product.setProdNo(10193);
		product.setProdQuantity(4321);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(product);
		
		HttpEntity httpEntity = new StringEntity(jsonString,"utf-8");
		httpPost.setEntity(httpEntity);
		httpClient.execute(httpPost);
	
	}
	
}
