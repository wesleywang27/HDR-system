package wdy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Eric on 2017/5/13.
 */
public class DBManager {
    private String tableName;
    private Connection conn;
    private Statement stmt;

    DBManager(String tableName){
        this.tableName = tableName;
    }

    void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e1){
            e1.printStackTrace();
        }

        String url="jdbc:mysql://localhost:3306/hdr_system";
        try {
            this.conn = DriverManager.getConnection(url, "root","");
            this.stmt = conn.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    void close(){
        try {
            this.stmt.close();
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void upload() throws SQLException {
        this.connect();

        String sql = "CREATE TABLE `" + tableName.split("\\.")[0] +"` ( `id` int(10) NOT NULL, `std_num` int(10) NOT NULL, `std_score` int(4) NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
        this.stmt.executeUpdate(sql);


        this.close();
    }
}
