package show;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

/**
 * Servlet implementation class disPlayBlobExample
 */
public class disPlayBlobExample extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public disPlayBlobExample() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	     
		  Blob image = null;
		  Connection con = null;
		  Statement stm = null;
		  ResultSet rs = null;
		  ServletOutputStream out = null;
		   response.setContentType("image/jpg");
		   out = response.getOutputStream();
		  try {
		   Class.forName("com.mysql.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/image","root","answndi123");
		   stm = con.createStatement();
		   rs = stm.executeQuery("select image from tbl_test where ID = '5'");
		   if (rs.next()){
		    image = rs.getBlob("FILE");
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
		   in.close();
		   out.flush();
		  }
		  catch (Exception e){
		   response.setContentType("TEXT/HTML");
		   out.println("<html><head><title>Unable To Display image</title></head>");
		   out.println("<body><h4><font color='red'>Image Display Error=" + e.getMessage() + "</font></h4></body></html>");
		   return;
		  }
		  finally {
		   
			  try{if (rs != null) rs.close();} catch (Exception ex) {}
			  try{if (stm != null) stm.close();} catch (Exception ex) {}
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
