import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReturnBookDao {
	
	public static int delete(String bookname,int studentid){
		int status=0;
		String bookAuthor=null ,studentName=null,  studentContact=null;
		try{
			Connection con=DB.getConnection();
			
			status=updatebook(bookname);//updating quantity and issue
			
			if(status>0){
			PreparedStatement ps=con.prepareStatement("delete from issuebooks where bookname=? and studentid=?");
			ps.setString(1,bookname);
			ps.setInt(2,studentid);
			status=ps.executeUpdate();
			}
			
			PreparedStatement ps=con.prepareStatement("select bookauthorname from books where bookname=?");
			ps.setString(1,bookname);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				//quantity=rs.getInt("quantity");
				//issue=rs.getInt("issue");
				bookAuthor = rs.getString("bookauthorname");
			}
			
			PreparedStatement ps1=con.prepareStatement("SELECT name,contact FROM librarian where id = ?");
			ps1.setLong(1,studentid);
			ResultSet rs1=ps1.executeQuery();
			
			if(rs1.next()){
				studentName=rs1.getString("name");
				studentContact=rs1.getString("contact");
				//bookAuthor = rs.getString("bookauthorname");
			}
			

			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		         
		        Calendar cal = Calendar.getInstance();  
		             
		        // use add() method to add the days to the given date  
		       // cal.add(Calendar.DAY_OF_MONTH, 7);  
		        String dateAfter = sdf.format(cal.getTime()); 
		        String borrowingReturn="return";

			PreparedStatement ps11=con.prepareStatement("insert into library_transaction(studentId,studentName,studentContact,bookName,bookAuthor,borrowingReturn,dueDate) values(?,?,?,?,?,?,?)");
			ps11.setInt(1,studentid);
			ps11.setString(2,studentName);
			ps11.setString(3,studentContact);
			ps11.setString(4,bookname);
			ps11.setString(5,bookAuthor);
			
			ps11.setString(6,borrowingReturn);
			ps11.setString(7,dateAfter);
			status=ps11.executeUpdate();
			

			
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int updatebook(String bookname){
		int status=0;
		
		int quantity=0,issue=0;
		try{
			Connection con=DB.getConnection();
			
			PreparedStatement ps=con.prepareStatement("select quantity,issue from books where bookname=?");
			ps.setString(1,bookname);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				quantity=rs.getInt("quantity");
				issue=rs.getInt("issue");
		
			}
			
			if(issue>0){
			PreparedStatement ps2=con.prepareStatement("update books set quantity=?,issue=? where bookname=?");
			ps2.setInt(1,quantity+1);
			ps2.setInt(2,issue-1);
			ps2.setString(3,bookname);
			
			status=ps2.executeUpdate();
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
		public static int saveLibraryTrans(int studentId,String studentName,String studentContact,String bookName,String bookAuthor,String borrowingReturn){

			int status=0;
			try{
				Connection con=DB.getConnection();
				System.out.println("inside save LibraryTrans");
				//status=updatebook(bookName);//updating quantity and issue
				
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
			         
			        Calendar cal = Calendar.getInstance();  
			             
			        // use add() method to add the days to the given date  
			        cal.add(Calendar.DAY_OF_MONTH, 7);  
			        String dateAfter = sdf.format(cal.getTime());  

				PreparedStatement ps=con.prepareStatement("insert into library_transaction(studentId,studentName,studentContact,bookName,bookAuthor,borrowingReturn,dueDate) values(?,?,?,?,?,?,?)");
				ps.setInt(1,studentId);
				ps.setString(2,studentName);
				ps.setString(3,studentContact);
				ps.setString(4,bookName);
				ps.setString(5,bookAuthor);
				ps.setString(6,borrowingReturn);
				ps.setString(7,dateAfter);
				status=ps.executeUpdate();
				
				
				con.close();
			}catch(Exception e){System.out.println(e);}
			return status;
		}
	
}
