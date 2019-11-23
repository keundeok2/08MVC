package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;

import org.codehaus.jackson.type.TypeReference;

public class RestHttpClientAppUser {

	// main Method
	public static void main(String[] args) throws Exception {

		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////

//		System.out.println("\n====================================\n");
//		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientAppUser.getUserTest_JsonSimple();

//		System.out.println("\n====================================\n");
//		// 1.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientAppUser.getUserTest_Codehaus();

//		System.out.println("\n====================================\n");
//		// 2.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientAppUser.LoginTest_JsonSimple();

//		System.out.println("\n====================================\n");
//		// 1.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientAppUser.LoginTest_Codehaus();
		
//		RestHttpClientAppUser.addTest_JsonSimple();
		
//		RestHttpClientAppUser.addTest_Codehaus();
		
//		RestHttpClientAppUser.updateUserTest_JsonSimple();
		
//		RestHttpClientAppUser.updateUserGetTest_UseCodehaus();
		
//		RestHttpClientAppUser.updateUserPostTest_Codehaus();
		
//		RestHttpClientAppUser.updateUserPostTest_JsonSimple();
		
//		RestHttpClientAppUser.listUserGetTest_JsonSimple();
		
//		RestHttpClientAppUser.listUserGetTest_Codehaus();
		
		RestHttpClientAppUser.listUserPostTest_Codehaus();
		
//		RestHttpClientAppUser.listUserPostTest_JsonSimple();
		
//		RestHttpClientAppUser.checkDuplicationPost_JsonSimple();
		
//		RestHttpClientAppUser.checkDuplicationPost_Codehaus();
	}

//================================================================//
	// 1.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
	public static void getUserTest_JsonSimple() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);

		// ==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}

	// 1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
	public static void getUserTest_Codehaus() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		// ==> 다른 방법으로 serverData 처리
		// System.out.println("[ Server 에서 받은 Data 확인 ] ");
		// String serverData = br.readLine();
		// System.out.println(serverData);

		// ==> API 확인 : Stream 객체를 직접 전달
		JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
		System.out.println(jsonobj);

		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
	}
//================================================================//	

//================================================================//
	// 2.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void LoginTest_JsonSimple() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

		// [ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

		// [ 방법 2 : JSONObject 사용]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(), "utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);

		// ==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonobj);

	}

	// 2.2 Http Protocol POST 방식 Request : FromData전달
	// ==> JsonSimple + codehaus 3rd party lib 사용
	public static void LoginTest_Codehaus() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

//		//[ 방법 1 : String 사용]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

//		//[ 방법 2 : JSONObject 사용]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		// [ 방법 3 : codehaus 사용]
		User user01 = new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		// Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue, "utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		// ==> 다른 방법으로 serverData 처리
		// System.out.println("[ Server 에서 받은 Data 확인 ] ");
		// String serverData = br.readLine();
		// System.out.println(serverData);

		// ==> API 확인 : Stream 객체를 직접 전달
		JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
		System.out.println(jsonobj);

		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
	}

	public static void addTest_JsonSimple() throws Exception {
		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/addUser";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

//		 [ 방법 1 : String 사용]
//					String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//					HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

		// [ 방법 2 : JSONObject 사용]
		JSONObject json = new JSONObject();
		json.put("userId", "restTest");
		json.put("userName", "restName");
		json.put("password", "1234");
		
		HttpEntity httpEntity01 = new StringEntity(json.toString(), "utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);

	}

	public static void addTest_Codehaus() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/addUser";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

		User user01 = new User();
		user01.setUserId("testuser001");
		user01.setUserName("testuserRestName");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		// Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue, "utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		// ==> 다른 방법으로 serverData 처리
		// System.out.println("[ Server 에서 받은 Data 확인 ] ");
		// String serverData = br.readLine();
		// System.out.println(serverData);

		// ==> API 확인 : Stream 객체를 직접 전달
		JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
		System.out.println(jsonobj);

	}
	
	
	public static void updateUserGetTest_JsonSimple() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/updateUser/point";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);

		// ==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	public static void updateUserGetTest_UseCodehaus() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/updateUser/point";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);

		// ==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonobj);
		
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
	}

	public static void updateUserPostTest_Codehaus() throws Exception {

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url = "http://127.0.0.1:8080/user/json/updateUser";

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		User user01 = new User();
		user01.setUserId("restTest");
		user01.setUserName("RT");
		user01.setPhone("010-1234-5678");
		user01.setEmail("1@2.3");
		user01.setAddr("RT");
		
		ObjectMapper objectMapper01 = new ObjectMapper();
		// Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue, "utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// ==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		// ==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		// ==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);

		// ==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	public static void updateUserPostTest_JsonSimple() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/user/json/updateUser";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userId", "restTest");
		jsonObj.put("phone", 01012341234);
		jsonObj.put("userName", "1234");
		jsonObj.put("addr","hello");
		jsonObj.put("email", "1@2.3");
		
		HttpEntity httpEntity = new StringEntity(jsonObj.toString(), "utf-8");
		httpPost.setEntity(httpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		HttpEntity httpEntity01 = httpResponse.getEntity();
		
		InputStream is = httpEntity01.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		JSONObject json = (JSONObject)JSONValue.parse(br);
		System.out.println(json);
		
	}
	
	public static void listUserGetTest_JsonSimple() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/listUser";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type","application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		System.out.println(httpResponse);
		
		HttpEntity httpEntity = httpResponse.getEntity();
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br.readLine());
		System.out.println(jsonObj);
		
	}
	public static void listUserGetTest_Codehaus() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/listUser";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type","application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		System.out.println(httpResponse);
		
		HttpEntity httpEntity = httpResponse.getEntity();
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br.readLine());
		System.out.println(jsonObj);
		
	}
	
	
	
	public static void listUserPostTest_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient(); 
		String url = "http://localhost:8080/user/json/listUser";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Content-Type","application/json");
		
		Search search = new Search();
		search.setCurrentPage(2);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonValue = objectMapper.writeValueAsString(search);
		
		HttpEntity httpEntity = new StringEntity(jsonValue,"utf-8");
		httpPost.setEntity(httpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity entity = httpResponse.getEntity();
		InputStream is =  entity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonObj);
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		
		List<User> userList = objectMapper2.readValue(jsonObj.get("list").toString(), new TypeReference<List<User>>() {});
		Search returnSearch = objectMapper2.readValue(jsonObj.get("search").toString(), Search.class);
		Page returnPage = objectMapper2.readValue(jsonObj.get("resultPage").toString(), Page.class);
		int totalCount = objectMapper2.readValue(jsonObj.get("totalCount").toString(), Integer.class);
		
	}
	
	public static void listUserPostTest_JsonSimple() throws Exception{
		HttpClient httpClient = new DefaultHttpClient(); 
		String url = "http://localhost:8080/user/json/listUser";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Content-Type","application/json");
		
		
		JSONObject json = new JSONObject();
		json.put("currentPage", 1);
		
		HttpEntity httpEntity = new StringEntity(json.toString(), "utf-8");
		httpPost.setEntity(httpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		HttpEntity responseHttpEntity = httpResponse.getEntity();

		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		System.out.println("[Server에서 받은 Data 확인]");
		String serverData = br.readLine();
		System.out.println(serverData);

		JSONObject jsonObj = (JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonObj);
		
	}
	
	public static void checkDuplicationPost_JsonSimple() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/user/json/checkDuplication";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Content-Type","application/json");
		
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		
		HttpEntity httpEntity = new StringEntity(json.toString(),"utf-8");
		httpPost.setEntity(httpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println(httpResponse);
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonObj);
		boolean result = (Boolean)jsonObj.get("result");
		System.out.println(result);
		
	}
	
	public static void checkDuplicationPost_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/user/json/checkDuplication";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Content-Type","application/json");
		
		User user = new User();
		user.setUserId("abaccdas");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonValue = objectMapper.writeValueAsString(user);
		
		HttpEntity httpEntity = new StringEntity(jsonValue,"utf-8");
		httpPost.setEntity(httpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println(httpResponse);
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonObj);
		
	}
}