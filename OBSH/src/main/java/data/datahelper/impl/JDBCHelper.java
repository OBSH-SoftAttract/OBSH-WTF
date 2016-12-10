package data.datahelper.impl;
/**
 * @author bxh
 * JDBC驱动的启动和关闭
 */
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
  
public class JDBCHelper {  
    public static final String url = "jdbc:mysql://127.0.0.1/obsh?useSSL=true& serverTimezone=UTC& characterEncoding=UTF-8";  
    public static final String name = "com.mysql.cj.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "flutter561802";  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public JDBCHelper(String sql) {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
} 