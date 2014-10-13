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
		st = (Statement)conn.createStatement(); // 创建用于执行静态sql语句的Statement对象  
		
		int count = st.executeUpdate(sql);//number of insert data
		int count2 = st.executeUpdate(sql2);
		System.out.println("向dictionary表中插入 " + count + " 条数据"); //输出插入操作的处理结果  
		System.out.println("向dictionary表中插入 " + count2 + " 条数据");
		conn.close();
		}catch (SQLException e) {  
			     System.out.println("插入数据失败" + e.getMessage());  
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
					+"," +"PublishDate = '"+publishdate+"'"+"," +"Price = '"+price+"'" +" where name='"+ name +"'";// 更新数据的sql语句  
			temp[0]=name;temp[1]=writer;temp[2]=isbn;temp[3]=publisher;temp[4]=publishdate;temp[5]=price;       
		    st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  
            int count = st.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数  
            System.out.println("dictionary表中更新 " + count + " 条数据");      //输出更新操作的处理结果  
	              
		    conn.close();   //关闭数据库连接  
	         } catch (SQLException e) {  
		            System.out.println("更新数据失败");  
	        }  
			mylist.add(temp);
			return mylist;
	}
	public static String delete(String table,String index,String name){ 
		 conn = getConnection(); //同样先要获取连接，即连接到数据库  
		        
		 try {  
		           String sql = "delete from "+table+" where "+index+" = '"+name+"'";// 删除数据的sql语句  
		           st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  
		            
		          int count = st.executeUpdate(sql);// 执行sql删除语句，返回删除数据的数量  
		             
		           System.out.println("dictionary表中删除 " + count + " 条数据\n");    //输出删除操作的处理结果  
		            
		          conn.close();   //关闭数据库连接  
		          return "success";
	             
		        } catch (SQLException e) {  
		             System.out.println("删除数据失败");
		            return "failed";
		       }
		
	}
	public static List<String[]> query(String index,String val,String table){
		conn = getConnection(); //同样先要获取连接，即连接到数据库     
		List<String[]> mylist= new ArrayList<String[]>();
		String []temp = new String[6];
		try {
	    	   String sql = "select * from "+table+" where "+ index +" = '"+ val +"';";     // 查询数据的sql语句  
	           st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  
		              
	            ResultSet rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集  
	            System.out.println("最后的查询结果为：");  
	            while (rs.next()) { // 判断是否还有下一个数据  
		                  
	               // 根据字段名获取相应的值  
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
		                //输出查到的记录的各个字段的值  
		       }  
	          conn.close();   //关闭数据库连接 
		        } catch (SQLException e) {  
		            System.out.println("查询数据失败");  
	      }
		return mylist;
	}
	public static Connection getConnection() {  
		Connection con = null;  //创建用于连接数据库的Connection对象  
			try {  
				Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动      
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "123456");// 创建数据连接  
				if(con==null)System.out.println("数据库接收为空！");
	       } catch (Exception e) {  
	            System.out.println("数据库连接失败" + e.getMessage());  
	        }  
		        return con; //返回所建立的数据库连接  
	   } 

}
