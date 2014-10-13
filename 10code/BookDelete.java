package lab2;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class BookDelete extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String bookname;
	
	public  String execute() throws Exception{
		List<String[]> mylist= new ArrayList<String[]>();
		
		bookname = getBookname();
		System.out.println("git1");
		String message = DBConnection.delete("dictionary","name",bookname);
		mylist = DBConnection.query("name",bookname,"dictionary");
		String message2 = DBConnection.delete("author","Name",bookname);
		if(message=="success")return "success";
		else return "failed";
	}
	
	
	
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
}
