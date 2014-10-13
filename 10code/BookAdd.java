package lab2;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
public class BookAdd extends ActionSupport{
	
	private static final long serialVersionUID = -4519118930462496483L;
	private String bookname;
	private String writername;
	private String isbn;
	private String publisher;
	private String publishdate;
	private String price;
	private String age;
	private String country;
	private List<String[]> mylist= new ArrayList<String[]>();
	
	public  String execute() throws Exception
	{
		bookname = getBookname();
		writername = getWritername();
		isbn = getIsbn();
		publisher = getPublisher();
		publishdate = getPublishdate();
		price = getPrice();
		age = getAge();
		country = getCountry();
		mylist = DBConnection.insert(bookname,writername,isbn,publisher,publishdate,price,age,country);//change it a func can carry args
		System.out.println("git1");
		return SUCCESS;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getWritername() {
		return writername;
	}

	public void setWritername(String writername) {
		this.writername = writername;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String[]> getMylist() {
		return mylist;
	}

	public void setMylist(List<String[]> mylist) {
		this.mylist = mylist;
	}
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
