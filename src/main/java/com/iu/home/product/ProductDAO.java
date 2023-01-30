package com.iu.home.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.home.member.MemberDAO;
import com.iu.home.member.MemberDTO;
import com.iu.home.util.DBConnection;

public class ProductDAO {
	
	
	//---getMax
	public int getProductNum() throws Exception{
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT PRODUCT_SEQ.NEXTVAL FROM DUAL";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		int num = rs.getInt(1);
		
		DBConnection.disConnection(st, con, rs);
		
		return num;
	
	}
	
	
	
	
	
	//---------------------------ProductOption
	
	public List<ProductOptionDTO> getProductOptionDTOList() throws Exception {
		List<ProductOptionDTO> ar = new ArrayList<ProductOptionDTO>();
		
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT * FROM PRODUCTOPTION";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductOptionDTO productOptionDTO = new ProductOptionDTO();
			productOptionDTO.setOptionNum(rs.getInt("OPTIONNUM"));
			productOptionDTO.setProductNum(rs.getInt("PRODUCTNUM"));
			productOptionDTO.setOptionName(rs.getString("OPTIONMANE"));
			productOptionDTO.setOptionPrice(rs.getInt("OPTIONPRICE"));
			productOptionDTO.setOptionStock(rs.getInt("OPTIONSTOCK"));
			ar.add(productOptionDTO);
			
		}
		DBConnection.disConnection(st, con, rs);
		
		return ar;
	}
	
	
	public int setAddProductOption(ProductOptionDTO productOptionDTO) throws Exception {
		Connection con = DBConnection.getConnection();
		
		String sql ="INSERT INTO PRODUCTOPTION(OPTIONNUM, PRODUCTNUM, OPTIONNAME, OPTIONPRICE OPTIONSTOCK) "
				+ "VALUEX(PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, productOptionDTO.getOptionNum());
		st.setInt(2, productOptionDTO.getProductNum());
		st.setString(3, productOptionDTO.getOptionName());
		st.setInt(4, productOptionDTO.getOptionPrice());
		st.setInt(5, productOptionDTO.getOptionStock());
		
		int result = st.executeUpdate();

		DBConnection.disConnect(st, con);
		
		return result;
		
	}
	
	
	
	
	//---------------------------------- Product
	
	public List<ProductDTO> getProductList() throws Exception {
		
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
		
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT PRODUCTNUM, PRODUCTNAME, PRODUCTJUMSU "
				+ "FROM PRODUCT ORDER BY PRODUCTJUMSU DESC";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductnum(rs.getInt("PRODUCTNUM"));
			productDTO.setProductname(rs.getString("PRODUCTNAME"));
			productDTO.setProductjumsu(rs.getDouble("PRODUCTJUMSU"));
			
			ar.add(productDTO);
			
		}
		
		DBConnection.disConnection(st, con, rs);
		
		return ar;
	}
	

	
	
	
	
	//SetAddProduct
	public int SetAddProduct(ProductDTO productDTO) throws Exception {
		
		Connection con = DBConnection.getConnection();
		
		String sql = "INSERT INTO PRODUCT3(PRODUCTNUM, PRODUCTNAME, PRODUCTDETAIL, PRODUCTJUMSU) "
				+ "VALUES(?, ?, ?, 0.0) ";
	
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, productDTO.getProductnum());
		st.setString(2, productDTO.getProductname());
		st.setString(3, productDTO.getProductdetail());
		//st.setDouble(4, productDTO.getProductjumsu());
	
		
		int result = st.executeUpdate();
		
		DBConnection.disConnect(st, con);
		
		return result;
		
	}
	
	private static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		try {
		
			List<ProductDTO> ar = productDAO.getProductList();
		
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

}
