package lab2;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.util.ArrayList;
import java.util.List;
 
public class DBConnection {
	static Connection conn;
	static Statement st;
	
	public DBConnection(){
		 conn = null;
		 st = null;
	}

	public static List<String[]> insert(String name,String writer,String isbn,String publisher,String publishdate,String price,String age,String country) {//different
		String value = null;
		System.out.println("git1");
		String []temp = new String[6];
		List<String[]> mylist= new ArrayList<String[]>();
		conn = getConnection();
		try{
		value ="'"+name +"'"+","+ "'"+writer+"'"+"," +"'"+ isbn+"'"+","+"'"+publisher +"'"+","+ "'"+publishdate+"'"+"," +"'"+ price+"'";
		String sql = "INSERT INTO dictionary (name,writer,isbn,Publisher,PublishDate,Price)" + "VALUES("+value+")";
		value ="'"+writer +"'"+","+ "'"+age+"'"+"," +"'"+ country+"'";
		String sql2 = "INSERT INTO author (Name,Age,Country)" + "VALUES("+value+")";
		st = (Statement)conn.createStatement(); // ��������ִ�о�̬sql����Statement����  
		
		int count = st.executeUpdate(sql);//number of insert data
		int count2 = st.executeUpdate(sql2);
		System.out.println("��dictionary���в��� " + count + " ������"); //�����������Ĵ�����  
		System.out.println("��dictionary���в��� " + count2 + " ������");
		conn.close();
		}catch (SQLException e) {  
			     System.out.println("��������ʧ��" + e.getMessage());  
			}  
		temp[0]=name;temp[1]=writer;temp[2]=isbn;temp[3]=publisher;temp[4]=publishdate;temp[5]=price;
		mylist.add(temp);
		return mylist;
	}
	public static List<String[]> update(String name,String writer,String isbn,String publisher,String publishdate,String price){	
			String sql = null;
			String []temp = new String[6];
			List<String[]> mylist= new ArrayList<String[]>();
			conn = getConnection();
			try{
			sql = "update dictionary set isbn = '"+ isbn +"'"+"," +"writer = '"+writer+"'"+"," +"Publisher = '"+publisher+"'"
					+"," +"PublishDate = '"+publishdate+"'"+"," +"Price = '"+price+"'" +" where name='"+ name +"'";// �������ݵ�sql���  
			temp[0]=name;temp[1]=writer;temp[2]=isbn;temp[3]=publisher;temp[4]=publishdate;temp[5]=price;       
		    st = (Statement) conn.createStatement();    //��������ִ�о�̬sql����Statement����st���ֲ�����  
            int count = st.executeUpdate(sql);// ִ�и��²�����sql��䣬���ظ������ݵĸ���  
            System.out.println("dictionary���и��� " + count + " ������");      //������²����Ĵ�����  
	              
		    conn.close();   //�ر����ݿ�����  
	         } catch (SQLException e) {  
		            System.out.println("��������ʧ��");  
	        }  
			mylist.add(temp);
			return mylist;
	}
	public static String delete(String table,String index,String name){ 
		 conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
		        
		 try {  
		           String sql = "delete from "+table+" where "+index+" = '"+name+"'";// ɾ�����ݵ�sql���  
		           st = (Statement) conn.createStatement();    //��������ִ�о�̬sql����Statement����st���ֲ�����  
		            
		          int count = st.executeUpdate(sql);// ִ��sqlɾ����䣬����ɾ�����ݵ�����  
		             
		           System.out.println("dictionary����ɾ�� " + count + " ������\n");    //���ɾ�������Ĵ�����  
		            
		          conn.close();   //�ر����ݿ�����  
		          return "success";
	             
		        } catch (SQLException e) {  
		             System.out.println("ɾ������ʧ��");
		            return "failed";
		       }
		
	}
	public static List<String[]> query(String index,String val,String table){
		conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�     
		List<String[]> mylist= new ArrayList<String[]>();
		String []temp = new String[6];
		try {
	    	   String sql = "select * from "+table+" where "+ index +" = '"+ val +"';";     // ��ѯ���ݵ�sql���  
	           st = (Statement) conn.createStatement();    //��������ִ�о�̬sql����Statement����st���ֲ�����  
		              
	            ResultSet rs = st.executeQuery(sql);    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
	            System.out.println("���Ĳ�ѯ���Ϊ��");  
	            while (rs.next()) { // �ж��Ƿ�����һ������  
		                  
	               // �����ֶ�����ȡ��Ӧ��ֵ  
	                temp = new String[6];
	            	if(table=="dictionary"){
	            		temp[0] = rs.getString("name");  
	            		temp[1] = rs.getString("writer");
	            		temp[2] = rs.getString("isbn");
	            		temp[3] = rs.getString("Publisher");
	            		temp[4] = rs.getString("PublishDate");
	            		temp[5] = rs.getString("Price");
	            		
	            	}
	            	else if(table=="author"){
	            		temp[0] = rs.getString("Name");  
	            		temp[1] = rs.getString("Age");
	            		temp[2] = rs.getString("Country");
	            	}
	            	mylist.add(temp); 
		            //System.out.println(result);  
		                //����鵽�ļ�¼�ĸ����ֶε�ֵ  
		       }  
	          conn.close();   //�ر����ݿ����� 
		        } catch (SQLException e) {  
		            System.out.println("��ѯ����ʧ��");  
	      }
		return mylist;
	}
	public static Connection getConnection() {  
		Connection con = null;  //���������������ݿ��Connection����  
			try {  
				Class.forName("com.mysql.jdbc.Driver");// ����Mysql��������      
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "123456");// ������������  
				if(con==null)System.out.println("���ݿ����Ϊ�գ�");
	       } catch (Exception e) {  
	            System.out.println("���ݿ�����ʧ��" + e.getMessage());  
	        }  
		        return con; //���������������ݿ�����  
	   } 

}
