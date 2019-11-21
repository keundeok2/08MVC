package com.model2.mvc.web.product;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public ProductController() {
		System.out.println("::ProductController Default Constructor call");
	}

	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

//	���Ͼ��ε� ���
//	tomcat�� �����ϴ� ���
//	private static final String UPLOAD_PATH_TOMCAT = "C:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\08.Model2MVCShop(RestFul Server)\\images\\uploadFiles";
	
//	git workspace�� �����ϴ� ���
	private static final String UPLOAD_PATH = "C:\\Users\\User\\git\\08MVC\\08.Model2MVCShop(RestFul Server)\\WebContent\\images\\uploadFiles";

//	Original addProduct
//	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
//	public String addProduct(@ModelAttribute("product") Product product) throws Exception {
//		System.out.println("\t\t\t\t\t\t product : " + product);
//		product.setManuDate(product.getManuDate().replace("-", ""));
//		productService.addProduct(product);
//		return "forward:/product/addProduct.jsp";
//	}

	
//		Spring Multiple FileUpload Test
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(@RequestParam("uploadFiles") ArrayList<MultipartFile> fileName, @ModelAttribute("product") Product product) throws Exception {
		String result = "";
		 int i = 0;
		 System.out.println( "addProduct() start...");
		 for (MultipartFile files : fileName) {
			 i++;
			result += saveFile(files);
			if (i != fileName.size()) {
				result += ":";
			}
		}
		    System.out.println("result : " + result);
		    
		    product.setManuDate(product.getManuDate().replace("-", ""));
			product.setFileName(result);
			System.out.println("fileNameList : " + product.getFileNameList());
			
			productService.addProduct(product);
		return "forward:/product/addProduct.jsp";
	}

	
//		Spring fileUpload Test
//	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
//	public String addProduct(MultipartFile fileName, @ModelAttribute("product") Product product) throws Exception {
//		
////		���� ����, ����� ����
//		String uploadFileName = saveFile(fileName);
//		product.setManuDate(product.getManuDate().replace("-", ""));
//		product.setFileName(uploadFileName);
//		
//		productService.addProduct(product);
//		return "forward:/product/addProduct.jsp";
//	}

	
	
// 		1EA FileUpload
//	@RequestMapping("addProduct")
//	public String addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		if (FileUpload.isMultipartContent(request)) {
//
//			// ���� ��� �� addProduct.jsp���� �̹��� �ٷ� �ȳ��� �������� ���ѵ� �ȳ���. ���������ϴµ��� �ð��� ���� �ɸ��� ��.
//			// String temDir ="C:\\Users\\User\\git\\07MVC\\07.Model2MVCShop(URI,pattern)\\WebContent\images\\uploadFiles";
//			// tomcat root�� �ִ� workspace�� ��η� �����ϸ� �������þ��� �ٷ� ������. // �� repository���� ���� ���� �ȵ�.
//			String temDir = "C:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\07.Model2MVCShop(URI,pattern)\\images\\uploadFiles";
//
//			DiskFileUpload fileUpload = new DiskFileUpload();
//			fileUpload.setRepositoryPath(temDir); // sizeThreshold�� ũ�⸦ ����� ������ ��ġ�� �ӽ÷�����
//			fileUpload.setSizeMax(1024 * 1024 * 10); // �ѹ��� �ִ� 1MB
//			fileUpload.setSizeThreshold(1024 * 100); // �ѹ��� 100k������ �޸𸮿� ����
//
//			if (request.getContentLength() < fileUpload.getSizeMax()) {
//				Product product = new Product();
//
//				StringTokenizer token = null;
//
//				List fileItemList = fileUpload.parseRequest(request);
//				int Size = fileItemList.size();
//				for (int i = 0; i < Size; i++) {
//					FileItem fileItem = (FileItem) fileItemList.get(i);
//					if (fileItem.isFormField()) {
//						if (fileItem.getFieldName().equals("manuDate")) {
//							token = new StringTokenizer(fileItem.getString("euc-kr"), "-");
//							String manuDate = token.nextToken() + token.nextToken() + token.nextToken();
//							product.setManuDate(manuDate);
//						} else if (fileItem.getFieldName().equals("prodName"))
//							product.setProdName(fileItem.getString("euc-kr"));
//						else if (fileItem.getFieldName().equals("prodDetail"))
//							product.setProdDetail(fileItem.getString("euc-kr"));
//						else if (fileItem.getFieldName().equals("price"))
//							product.setPrice(Integer.parseInt(fileItem.getString("euc-kr")));
//						else if (fileItem.getFieldName().equals("prodQuantity"))
//							product.setProdQuantity(Integer.parseInt(fileItem.getString("euc-kr")));
//					} else {
//						if (fileItem.getSize() > 0) {
//							int idx = fileItem.getName().lastIndexOf("\\");
//
//							if (idx == -1) {
//								idx = fileItem.getName().lastIndexOf("/");
//							}
//							String fileName = fileItem.getName().substring(idx + 1);
//							product.setFileName(fileName);
//							try {
//								File uploadedFile = new File(temDir, fileName);
//								fileItem.write(uploadedFile);
//							} catch (IOException e) {
//								System.out.println(e);
//							}
//						} else {
//							product.setFileName("../../images/empty.GIF");
//						}
//					}
//				} // for
//
//				productService.addProduct(product);
//				request.setAttribute("product", product);
//			} else {
//				int overSize = (request.getContentLength() / 1000000);
//				System.out.println("<script>alert('������ ũ��� 1MB�����Դϴ�. �ø��� ���� �뷮�� " + overSize + "MB�Դϴ�.");
//				System.out.println("history.back();</script>");
//			}
//		} else {
//			System.out.println("���ڵ� Ÿ���� multipart/form-data�� �ƴմϴ�.");
//		}
//
//		return "forward:/product/addProduct.jsp";
//	}

	@RequestMapping("getProduct")
	public String getProduct(@RequestParam("menu") String menu, @RequestParam("prodNo") int prodNo,
			HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		this.addHistory(request, response);

		if (menu.equals("manage")) {
			return "forward:/product/updateProductView.jsp";
		}
		return "forward:/product/readProduct.jsp";
	}

	@RequestMapping("listProduct")
	public String listProduct(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = productService.getProductList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/product/listProduct.jsp";
	}

	@RequestMapping("updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product) throws Exception {
		productService.updateProduct(product);

		return "forward:/product/readProduct.jsp?prodNo=" + product.getProdNo() + "&menu=manage";
	}

	public String deleteProduct(@ModelAttribute Product product) throws Exception{
		
		return "forward:/product/listProduct";
	}
	
	@RequestMapping("getHistory")
	public String getHistory(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String history = null;
		String[] h = null;
		Cookie[] cookies = request.getCookies();
		
		if (cookies!=null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				System.out.println("\t\t\t\t Cookie : " + cookie.getName());
				if (cookie.getName().equals("history")) {
					history = cookie.getValue();
					System.out.println("\t\t\t\t getHistory cookie : " + history);
				}
			}
		}
		
		if (history != null) {
			h = history.split(",");
		}
		
		model.addAttribute("h", h);
		return "forward:/history.jsp";
	}
	
	
	
	private void addHistory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Cookie[] cookies = request.getCookies();
		String tempValue = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("history")) {
					String cValue = (String) cookies[i].getValue();
					tempValue += cValue + ",";
				}
			}
		}
		tempValue += request.getParameter("prodNo");
		Cookie cookie = new Cookie("history", tempValue);
		System.out.println("\t\t\t\t\t controller history : " + tempValue);
		response.addCookie(cookie);
	}
	
	private String saveFile(MultipartFile file) {

//		�̸� �ߺ����� �ʰ� �����̸� ����
		UUID uuid = UUID.randomUUID();
		String saveName = uuid + "_" + file.getOriginalFilename();

		System.out.println("File Save Name : " + saveName);

		File saveFile = new File(UPLOAD_PATH, saveName);
//		File saveFileTomcat = new File(UPLOAD_PATH_TOMCAT, saveName);
		
		try {
			file.transferTo(saveFile);
//			file.transferTo(saveFileTomcat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return saveName;
	}
	
}
