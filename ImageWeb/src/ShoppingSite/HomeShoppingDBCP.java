package ShoppingSite;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;

import javax.sql.*;
import javax.naming.*;

import sun.security.krb5.internal.crypto.RsaMd5CksumType;


public class HomeShoppingDBCP {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private DataSource ds = null;
	
	public HomeShoppingDBCP(){
		try{
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void connect(){
		try{
			con = ds.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}			
		}
		if(con!=null){
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	//아이디와 비번이 맞는지 확인하는 함수
	public boolean isValid(String member_id, String password){
		boolean success = false;
		connect();
		String SQL = "select password from member where member_id = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String resultPassword = rs.getString("password");
			if(password.equals(resultPassword)) 
				success = true;
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
			return success;
		}finally{
			disconnect();
		}
		return success;			
	}
	//해당 회원 정보를 얻는 함수
	public MemberEntity getLoginfo(String member_id, String password){
		connect();
		String SQL = "select * from member where member_id = ?";
		MemberEntity lie = new MemberEntity();
		
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			lie.setMember_id(rs.getString("member_id"));
			lie.setPassword(rs.getString("password"));
			lie.setName(rs.getString("name"));
			lie.setAddress(rs.getString("address"));
			lie.setProvince(rs.getString("province"));
			lie.setTown(rs.getString("town"));
			lie.setMail(rs.getString("mail"));
			lie.setPhone_number(rs.getInt("phone_number"));
			
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}		
		return lie;
	}
	//상풍 타입 게시판에 표시할 상품 정보를 얻는 함수
	public ArrayList<ProductEntity> getListOfProduct(String type){
		ArrayList<ProductEntity> pdList = new ArrayList<ProductEntity>();
		connect();
		String SQL = "select product_id, name, price from product where type = ?";
		
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, type);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				ProductEntity pe = new ProductEntity();
				pe.setProduct_id(rs.getString("product_id"));
				pe.setName(rs.getString("name"));
				pe.setPrice(rs.getInt("price"));				
				pdList.add(pe);
			}
			rs.close();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
		return pdList;
	}
	
	//장바구니 게시판에 회이이 추가한 상품 보는 함수
	public ArrayList<CartBoardEntity> getProductsOfCart(String member_id){
		ArrayList<CartBoardEntity> cbeList = new ArrayList<CartBoardEntity>();
		connect();
		String SQL = "select member_id, product_id, name, color, size, price, amount  from cart natural join product where member_id = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				CartBoardEntity cbe = new CartBoardEntity();
				cbe.setMember_id(rs.getString("member_id"));
				cbe.setProduct_id(rs.getString("product_id"));
				cbe.setName(rs.getString("name"));
				cbe.setColor(rs.getString("color"));
				cbe.setSize(rs.getString("size"));
				cbe.setPrice(rs.getInt("price"));
				cbe.setAmount(rs.getInt("amount"));
				cbeList.add(cbe);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
		return cbeList;
	}
	
	//삼품의 게시판에 표시할 상품 정보 얻는 함수
	public ProductEntity getProductInfomation(String product_id){
		connect();
		String SQL = "select * from product where product_id = ?";
		ProductEntity pe = new ProductEntity();
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, product_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();		
			pe.setProduct_id(rs.getString("product_id"));
			pe.setType(rs.getString("type"));
			pe.setName(rs.getString("name"));
			pe.setPrice(rs.getInt("price"));
			pe.setContent(rs.getString("content"));
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return pe;
	}
	
	//지역당 판매량 함수
	public AreaEntity getSalesInformationForArea(String product_id,String province, String town) {
		AreaEntity ae = new AreaEntity();
		connect();
		String SQL = "select * from area where product_id = ? and province = ? and town = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, product_id);
			pstmt.setString(2, province);
			pstmt.setString(3, town);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			ae.setProduct_id(rs.getString("product_id"));
			ae.setProvince(rs.getString("province"));
			ae.setTown(rs.getString("town"));
			ae.setAmount(rs.getInt("amount"));
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return ae;	
	}
	//상품 판매량 정보 함수
	public SalesVolumeEntity getSalesVolumeInfomation(String product_id){
		SalesVolumeEntity sv = new SalesVolumeEntity();
		connect();
		String SQL = "select * from sales_voluem where product_id = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, product_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			sv.setProduct_id(rs.getString("product_id"));
			sv.setTotal_sale(rs.getInt("total_sale"));
			sv.setAmount(rs.getInt("amount"));
			rs.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
		return sv;
	}
	// 상품 의견 정보를 받는 함수
	public ArrayList<OpinionEntity> getOpinionInformation(String product_id){
		ArrayList<OpinionEntity> oeList = new ArrayList<OpinionEntity>();
		connect();
		String SQL = "select * from opinion where product_id = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, product_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				OpinionEntity oe = new OpinionEntity();
				oe.setProduct_id(rs.getString("product_id"));
				oe.setMember_id(rs.getString("member_id"));
				oe.setComment(rs.getString("comment"));
				oe.setGoodbad(rs.getString("goodbad"));
				oeList.add(oe);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}		
		return oeList;
	}
	//상품 정보 게시판에 상품 옵션 정보를 받는 함수
	public ArrayList<ProductOptionEntity> getProductOption(String product_id){
		ArrayList<ProductOptionEntity> opList = new ArrayList<ProductOptionEntity>();
		connect();
		String SQL = "select * from product_options where product_id = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, product_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				ProductOptionEntity op = new ProductOptionEntity();
				op.setProduct_id(rs.getString("product_id"));
				op.setColor(rs.getString("color"));
				op.setSize(rs.getString("size"));
				op.setAmount(rs.getInt("amount"));
				opList.add(op);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
		return opList;
	}

	public boolean setProductIntoCart(String member_id, String product_id, String color, String size,int amount){
		boolean success = false;
		connect();
		String SQL = "insert into cart values(?, ?, ?, ?, ?)";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member_id);
			pstmt.setString(2, product_id);
			pstmt.setString(3, color);
			pstmt.setString(4, size);
			pstmt.setInt(5, amount);
			pstmt.executeUpdate();
			success = true;			
		}catch(Exception e){
			e.printStackTrace();
			return success;
		}finally{
			disconnect();
		}		
		return success;
	}
	//코멘트 의견 달기
	public boolean setProductOpnion(String product_id, String member_id, String comment, String goodbad){
		boolean success = false;
		connect();
		String SQL = "insert into opinion values(?, ?, ?, ?)";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, product_id);
			pstmt.setString(2, member_id);
			pstmt.setString(3, comment);
			pstmt.setString(4, goodbad);
			pstmt.executeUpdate();
			success = true;
		}catch(Exception e){
			e.printStackTrace();
			return success;
		}finally{
			disconnect();
		}
		
		return success;		
	}
	
	public ArrayList<SalesVolumeBoardEntity> getTop4Product(){
		ArrayList<SalesVolumeBoardEntity> svbeList = new ArrayList<SalesVolumeBoardEntity>();
		connect();
		String SQL  = "select product_id, name, price, total_sale, amount from product natural join sales_volume order by `total_sale` DESC";
		try{
			pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				SalesVolumeBoardEntity svbe = new SalesVolumeBoardEntity();
				svbe.setProduct_id(rs.getString("product_id"));
				svbe.setName(rs.getString("name"));
				svbe.setPrice(rs.getInt("price"));
				svbe.setTotal_sale(rs.getInt("total_sale"));
				svbe.setAmount(rs.getInt("amount"));
				svbeList.add(svbe);
				}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
		return svbeList;
	}
	//카트에 있는 상품 정보 삭제
	public boolean deleteProductInCart(String product_id, String member_id, String color, String size){
		boolean success = false;
		connect();
		String SQL = "delete from cart where product_id = ? and member_id = ? and color = ? and size = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, product_id);
			pstmt.setString(2, member_id);
			pstmt.setString(3, color);
			pstmt.setString(4, size);
			pstmt.executeUpdate();
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return success;
	}
	
	public boolean setProduct(String product_id,String type, String name, int price, String path, String content){
		boolean success = false;
		connect();
		String SQL =  "insert into product values(?, ?, ?, ?, ?, ?)";
		try{
            File imgfile = new File(path);
            FileInputStream fin = new FileInputStream(imgfile);
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, product_id);
            pstmt.setString(2, type);
            pstmt.setString(3, name);
            pstmt.setInt(4, price);
            pstmt.setBinaryStream(5, fin, (int)imgfile.length());
            pstmt.setString(6, content);
            pstmt.executeUpdate();
            success = true;
            fin.close();
            SQL = "insert into sales_volume values(?,?,?)";
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, product_id);
            pstmt.setInt(2, 0);
            pstmt.setInt(3, 300);    
            pstmt.executeUpdate();
            SQL = "insert into product_options value(?,?,?,?)";
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, product_id);
            pstmt.setString(2, "파랑");
            pstmt.setString(3, "S");
            pstmt.setInt(4, 100);
            pstmt.executeUpdate();
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, product_id);
            pstmt.setString(2, "파랑");
            pstmt.setString(3, "M");
            pstmt.setInt(4, 100);
            pstmt.executeUpdate();           
            SQL = "insert into product_options value(?,?,?,?)";
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, product_id);
            pstmt.setString(2, "파랑");
            pstmt.setString(3, "X");
            pstmt.setInt(4, 100);
            pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
			
		}
		return success;
	}
	
	public boolean setSalesVolume(String product_id, int total_sale, int amount){
		boolean success = false;
		connect();
		String SQL = "insert into sales_volume values(?,?,?)";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, product_id);
			pstmt.setInt(2, total_sale);
			pstmt.setInt(3, amount);
			pstmt.executeUpdate();
			success = true;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
		return success;
	}
	//주문자 테이블에 주문자 정보를 넣는 함수
	public boolean doOrders(String member_id, String receiver, String receiver_address, String receiver_province, String receiver_town, int phone_number, String notice, String deposit_confirmation, int payment){
		boolean success = false;
		Date payment_day = new Date(System.currentTimeMillis());
		connect();
		String SQL = "insert into orders values(null,?,?,?,?,?,?,?,?,?,?)";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1,member_id);
			pstmt.setString(2,receiver);
			pstmt.setString(3, receiver_address);
			pstmt.setString(4, receiver_province);
			pstmt.setString(5, receiver_town);
			pstmt.setInt(6, phone_number);
			pstmt.setString(7, notice);
			pstmt.setString(8, deposit_confirmation);
			pstmt.setInt(9, payment);
			pstmt.setDate(10, payment_day);
			pstmt.executeUpdate();
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return success;
	}
	//주문자 정보 입력 후 주문 상품테이블에 넣기위한 주문번호를 얻어오는 함수
	public int getOrderNumber(){
		int number = 0;
		connect();
		String SQL = "select order_number from orders order by `order_number` DESC";
		try{
			pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			number = rs.getInt("order_number");
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return number;
	}
	//해당 멤버의 카트 테이블에 있는 상품정보를 받아오는 함수
	public ArrayList<CartEntity> getCartEntityByMemberId(String member_id){
		
		ArrayList<CartEntity> ceList = new ArrayList<CartEntity>();
		connect();
		String SQL = "select * from cart where member_id = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				CartEntity ce = new CartEntity();
				ce.setProduct_id(rs.getString("product_id"));
				ce.setMember_id(rs.getString("member_id"));
				ce.setColor(rs.getString("color"));
				ce.setSize(rs.getString("size"));
				ce.setAmount(rs.getInt("amount"));
				ceList.add(ce);				
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return ceList;
	}
   //주문 상품 테이블에 주문 하려는 상품 정보 넣는 함수
	public boolean setOrderProduct(int order_number, ArrayList<CartEntity> ceList,String province, String town){
		boolean success = false;
		connect();
		String SQL = "insert into order_product values(?, ?, ?, ?, ?, ?, ?)";
		int count = ceList.size();		
		try{
			if(count>0){
			for(CartEntity ce : ceList){
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, order_number);
				pstmt.setString(2, ce.getMember_id());
				pstmt.setString(3, "배송준비중");
				pstmt.setString(4, ce.getProduct_id());
				pstmt.setString(5, ce.getColor());
				pstmt.setString(6, ce.getSize());
				pstmt.setInt(7, ce.getAmount());
				pstmt.executeUpdate();
				updateArea(ce.getProduct_id(), province, town, ce.getAmount());
				updateProductOptions(ce.getProduct_id(), ce.getColor(), ce.getSize(), ce.getAmount());
				updateSalesVolume(ce.getProduct_id(), ce.getAmount());
			}
			}
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
		return success;
	}
	// 주문후 장바구니에 있는 해당 회원 상품정보 삭제
	public boolean deleteProductInCartByMember(String member_id){
		boolean success = false;
		connect();
		String SQL = "delete from cart where member_id = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member_id);
			pstmt.executeUpdate();
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
		return success;
	}
	public boolean updateProductOptions(String product_id,String color, String size,int amount){
		boolean success = false;
	//	connect();
		String SQL = "update product_options set amount = amount - ? where product_id = ? and color = ? and size = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, amount);
			pstmt.setString(2, product_id);
			pstmt.setString(3, color);
			pstmt.setString(4, size);
			pstmt.executeUpdate();
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
	//		disconnect();
		}
		 
		return success;
	}
	
	public boolean updateArea(String product_id, String province, String town,int amount){
		boolean success = false;
	//	connect();
		String SQL = "select count(product_id) as count from area where product_id = ? and province = ? and town = ?";		
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, product_id);
			pstmt.setString(2, province);
			pstmt.setString(3, town);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt("count")!=0){//결과값이 존재하면 해당 지방과 타운 정보가 있으므로 수량만 더해준다.
				rs.close();
				SQL = "update area set amount = amount + ? where product_id = ? and province = ? and town = ?";
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, amount);
				pstmt.setString(2, product_id);
				pstmt.setString(3, province);
				pstmt.setString(4, town);
				pstmt.executeUpdate();
			}else{//해당 지역 정보가 지역 테이블에 없으므로 값을 삽입한다.
				rs.close();
				SQL = "insert into area values(?,?,?,?)";
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1, product_id);
				pstmt.setString(2, province);
				pstmt.setString(3, town);
				pstmt.setInt(4, amount);
				pstmt.executeUpdate();				
			}
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
	//		disconnect();
		}
		 
		return success;
	}
	
	public boolean updateSalesVolume(String product_id, int amount){
		boolean success = false;
	//	connect();
		String SQL = "update sales_volume set amount = amount - ? , total_sale = total_sale + ? where product_id = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, amount);
			pstmt.setString(3, product_id);
			pstmt.executeUpdate();
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
	//		disconnect();
		}
		 
		return success;
	}
	
	
	public boolean insertMember(MemberEntity member) {
		boolean cnt = false;
		connect();
		String SQL = "insert into member values (?, ?, ?, ?, ?, ?, ?, ?)";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getProvince());
			pstmt.setString(6, member.getTown());
			pstmt.setInt(7, member.getPhone_number());
			pstmt.setString(8, member.getMail());
			int re = pstmt.executeUpdate();
			if(re>0) cnt=true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}
	

	public MemberEntity selectMember(String member_id){
		connect();
		MemberEntity member = new MemberEntity();
		ResultSet rs = null;
		String SQL = "select * from member where member_id=?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				member.setMember_id(rs.getString("member_id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setProvince(rs.getString("province"));
				member.setTown(rs.getString("town"));				
				member.setAddress(rs.getString("address"));
				member.setPhone_number(rs.getInt("phone_number"));
				member.setMail(rs.getString("mail"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return member;
	}
	public boolean updateMember(MemberEntity member) {
		boolean cnt = false;
		connect();
		String SQL = "update member set password=?,name=?,province=?,town=?,address=?,phone_number=?,mail=? "+ "where member_id=?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getProvince());
			pstmt.setString(5, member.getTown());
			pstmt.setInt(6, member.getPhone_number());
			pstmt.setString(7, member.getMail());
			pstmt.setString(8, member.getMember_id());
			int re = pstmt.executeUpdate();
			if(re>0) cnt=true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return cnt;
	}
	
	public boolean deleteMember(String member_id) {
		boolean cnt = false;
		connect();
	
		String SQL = "delete from member where member_id=?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member_id);
			int re = pstmt.executeUpdate();
			if(re>0) cnt=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return cnt;
	}
	
	public boolean checkId(String member_id) {
		boolean chk = false;
		connect();
		String SQL = "select * from member where member_id='"+member_id+"'";
		try{
			pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				chk = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return chk;	
	}
	
	public ArrayList<ProductEntity> searchingProductForPants(String type, String textForSearch){//바지
		ArrayList<ProductEntity> pdList = new ArrayList<ProductEntity>();
		connect();
		String SQL = "select product_id, name,type, price from product where type = ? and name like ?";
		
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, type);
			pstmt.setString(2, "%"+textForSearch+"%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				ProductEntity pt = new ProductEntity();
				
				pt.setProduct_id(rs.getString("product_id"));
				pt.setType(rs.getString("type"));
				pt.setName(rs.getString("name"));
				pt.setPrice(rs.getInt("price"));
				pdList.add(pt);
			}
			rs.close();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
		return pdList;
	}

	public ArrayList<OrderBoardEntity> getProductsInOrder(String member_id){
		ArrayList<OrderBoardEntity> obeList = new ArrayList<OrderBoardEntity>();
		connect();
		String SQL = "select order_number,member_id, product_id, name, color, size, price, amount,delivery_info from order_product natural join product where member_id = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				OrderBoardEntity obe = new OrderBoardEntity();
				obe.setProduct_id(rs.getString("product_id"));
				obe.setMember_id(rs.getString("member_id"));
				obe.setOrder_number(rs.getInt("order_number"));
				obe.setColor(rs.getString("color"));
				obe.setSize(rs.getString("size"));
				obe.setPrice(rs.getInt("price"));
				obe.setAmount(rs.getInt("amount"));
				obe.setDelivery_info(rs.getString("delivery_info"));
				obe.setName(rs.getString("name"));
				obeList.add(obe);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return obeList;
	}
	
	public ArrayList<OrdersEntity> getOrderInformation(String member_id){
		ArrayList<OrdersEntity> oeList = new ArrayList<OrdersEntity>();
		connect();
		String SQL = "select * from orders where member_id = ?";
		try{
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				OrdersEntity oe = new OrdersEntity();
				oe.setOrder_number(rs.getInt("order_number"));
				oe.setMember_id(rs.getString("member_id"));
				oe.setReceiver(rs.getString("receiver"));
				oe.setReceiver_address(rs.getString("receiver_address"));
				oe.setReceiver_province(rs.getString("receiver_province"));
				oe.setReceiver_town(rs.getString("receiver_town"));
				oe.setPhone_number(rs.getInt("phone_number"));
				oe.setNotice(rs.getString("notice"));
				oe.setDeposit_confirmation(rs.getString("deposit_confirmation"));
				oe.setPayment(rs.getInt("payment"));
				oe.setPayment_day(rs.getDate("payment_day"));	
				oeList.add(oe);
			}
			rs.close();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
		return oeList;
	}
}
