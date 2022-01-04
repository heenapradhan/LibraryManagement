import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
public class IssueBookDao {
	
public static boolean checkBook(String bookname){
	boolean status=false;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from books where bookname=?");
		ps.setString(1,bookname);
	    ResultSet rs=ps.executeQuery();
		status=rs.next();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}

public static int save(String bookname,int studentid,String studentname,String studentcontact){
	int status=0, libtransStatus=0;
	String bookAuthor=null;
	try{
		Connection con=DB.getConnection();
	
		String borrowingReturn ="borrow";
		status=updatebook(bookname);//updating quantity and issue
		System.out.println("Book updated");
		
		if(status>0){
		PreparedStatement ps1=con.prepareStatement("insert into issuebooks(bookname,studentid,studentname,studentcontact) values(?,?,?,?)");
		ps1.setString(1,bookname);
		ps1.setInt(2,studentid);
		ps1.setString(3,studentname);
		ps1.setString(4,studentcontact);
		status=ps1.executeUpdate();
		}
		PreparedStatement ps=con.prepareStatement("select bookauthorname from books where bookname=?");
		ps.setString(1,bookname);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			//quantity=rs.getInt("quantity");
			bookAuthor = rs.getString("bookauthorname");
			System.out.println("bookAuthor:" +bookAuthor);
		}
		
		libtransStatus= saveLibraryTrans(studentid, studentname, studentcontact, bookname, bookAuthor, borrowingReturn);
		
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
		
		if(quantity>0){
		PreparedStatement ps2=con.prepareStatement("update books set quantity=?,issue=? where bookname=?");
		ps2.setInt(1,quantity-1);
		ps2.setInt(2,issue+1);
		ps2.setString(3,bookname);
		
		status=ps2.executeUpdate();
		}
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
}
