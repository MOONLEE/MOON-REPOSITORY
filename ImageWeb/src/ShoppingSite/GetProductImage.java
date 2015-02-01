package ShoppingSite;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.sql.*;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;
/**
 * Servlet implementation class GetProductImage
 */
public class GetProductImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private java.sql.PreparedStatement pstmt = null;
	private DataSource ds = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	     
		  Blob image = null;
		  ServletOutputStream out = null;
		  
		  String sql = "select picture from product where product_id = ?";
		  response.setContentType("image/jpg");
		  out = response.getOutputStream();
		  try {	
			  InitialContext ctx = new InitialContext();
			  ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
			  con = ds.getConnection();
			  pstmt = con.prepareStatement(sql);
			  pstmt.setString(1, request.getParameter("product_id"));
			  ResultSet rs = pstmt.executeQuery();
			  if (rs.next()){
				  image = rs.getBlob("picture");
			  }
			  else{
				  response.setContentType("TEXT/HTML");
				  out.println("<html><head><title>Display out the blob image</title></head>");
				  out.println("<body><h4><font color = 'red'>Image not founde for the given id</font></h4></body></html>");
				  return;
			  }		  
		
			  InputStream in = image.getBinaryStream();
			  int length = (int) image.length();
			  int bufferSize = 1024;
			  byte[] buffer = new byte[bufferSize];
			  while ((length = in.read(buffer)) != -1) {
				  out.write(buffer, 0, length);
			  }
			  rs.close();
			  in.close();
			  out.flush();
		  }catch (Exception e){
			  response.setContentType("TEXT/HTML");
			  out.println("<html><head><title>Unable To Display image</title></head>");
			  out.println("<body><h4><font color='red'>Image Display Error=" + e.getMessage() + "</font></h4></body></html>");
			  return;
		  }finally {
			 
			  try{if (pstmt != null) pstmt.close();} catch (Exception ex) {}
			  try {if (con != null) con.close();} catch (Exception ex) {}
		  
		  }	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
