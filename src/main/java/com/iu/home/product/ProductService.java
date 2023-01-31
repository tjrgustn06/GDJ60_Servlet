package com.iu.home.product;

import java.util.List;

public class ProductService {

	//결합도가 높다(강하다)
	
	private ProductDAO productDAO = new ProductDAO(); 
	
	//인스턴스 초기화블럭
	{
		
		this.productDAO = new ProductDAO();
	}
	
	//생성자
	public ProductService () {
		this.productDAO = new ProductDAO();
	}
	
	// 세터: 결합도가 낮다(약하다)
	public void setProductDAO (ProductDAO productDAO) {
		this.productDAO  = productDAO;
	}
	
	
	public int setAddProduct (ProductDTO productDTO, List<ProductOptionDTO> ar) throws Exception {
		//prdoct , option 등록
		int productNum = productDAO.getProductNum();
		productDTO.setProductnum(productNum);
		int result = productDAO.SetAddProduct(productDTO);
		
		for(ProductOptionDTO productOptionDTO:ar) {
			productOptionDTO.setProductNum(productNum);
			result = productDAO.setAddProductOption(productOptionDTO);		
		}
		
		return result;	
	}
	
	

	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductname("product1");
		productDTO.setProductdetail("detail1");
		
		ProductOptionDTO productOptionDTO = new ProductOptionDTO();
		productOptionDTO.setOptionName("OptionName1");
		productOptionDTO.setOptionPrice(100);
		productOptionDTO.setOptionStock(10);
		productOptionDTO.setProductNum(null);
		
		ProductOptionDTO productOptionDTO2 = new ProductOptionDTO();
		productOptionDTO2.setOptionName("OptionName2");
		productOptionDTO2.setOptionPrice(200);
		productOptionDTO2.setOptionStock(20);
		productOptionDTO2.setProductNum(null);
		
		try {
			int num = productDAO.getProductNum();
			
			productOptionDTO.setProductNum(num);
		
			int result = productDAO.SetAddProduct(productDTO);
			
			productOptionDTO.setProductNum(num);
			
			if(result>0) {
				productDAO.setAddProductOption(productOptionDTO);
			}
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
