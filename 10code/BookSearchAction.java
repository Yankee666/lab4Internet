
package lab2;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class BookSearchAction extends ActionSupport{
	private static final long serialVersionUID = 6947274097672896796L;
	private String bookname;
	private String writername;
	private List<String[]> mylist= new ArrayList<String[]>();
	private List<String[]> smallist= new ArrayList<String[]>();
	private List<String[]> authorlist= new ArrayList<String[]>();
	
	

	public  String execute() throws Exception
	{
		bookname = getBookname();
		writername = getWritername();
		String index;
		String load = null;
		String []temp = new String[2];
		if(bookname.length()!=0||(bookname.length()!=0&&writername.length()!=0)){
			index="name";
			mylist = DBConnection.query(index,bookname,"dictionary");
			if(mylist.size()!=0){
				for(int i = 0;i<mylist.size();i++){
					temp = new String[2];
					temp[0]=(mylist.get(i))[0];
					temp[1]=(mylist.get(i))[1];
					smallist.add(temp);
			}
			bookname = temp[0];
			writername = temp[1];
			authorlist = DBConnection.query("name",writername,"author");
			load = "success";
			}
			else return load = "failed";
		}
		else if (writername.length()!=0){
			index="writer";
			mylist = DBConnection.query(index,writername,"dictionary");
			if(mylist.size()!=0){
				for(int i = 0;i<mylist.size();i++){
					temp = new String[2];
					temp[0]=(mylist.get(i))[0];
					temp[1]=(mylist.get(i))[1];
					smallist.add(temp);
			}
			bookname = temp[0];
			authorlist = DBConnection.query("name",writername,"author");
			load = "success";
			}
			else load = "failed";
		}
		return load;
	}

	public List<String[]> getAuthorlist() {
		return authorlist;
	}

	public void setAuthorlist(List<String[]> authorlist) {
		this.authorlist = authorlist;
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



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<String[]> getMylist() {
		return mylist;
	}
	public void setMylist(List<String[]> mylist) {
		this.mylist = mylist;
	}
	public List<String[]> getSmallist() {
		return smallist;
	}

	public void setSmallist(List<String[]> smallist) {
		this.smallist = smallist;
	}
}
