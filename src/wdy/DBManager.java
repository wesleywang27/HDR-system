package wdy;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Eric on 2017/5/13.
 */
class DBManager {
    private String path;
    private String tableName;
    private Connection conn;
    private Statement stmt;

    DBManager(String path, String tableName){
        this.path = path;
        this.tableName = tableName;
    }

    private void connect(){
        String url="jdbc:mysql://localhost:3306/hdr_system";
        try {
            Properties props = new Properties();
            InputStream in = new FileInputStream("config.properties");
            props.load(in);
            in.close();

            this.conn = DriverManager.getConnection(url, props.getProperty("user"),props.getProperty("password"));
            this.stmt = conn.createStatement();
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }

    private void close(){
        try {
            this.stmt.close();
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void upload() throws SQLException {
        this.connect();

        String sql = "CREATE TABLE `" + tableName.split("\\.")[0] +"` ( `id` int(10) NOT NULL AUTO_INCREMENT, `std_num` int(10) NOT NULL, `std_score` int(4) NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
        this.stmt.executeUpdate(sql);

        File file_num = new File(path + "\\HDR-system\\stdNum\\num.txt");
        BufferedReader reader_num = null;
        File file_score = new File(path + "\\HDR-system\\stdScore\\score.txt");
        BufferedReader reader_score = null;

        try {
            reader_num = new BufferedReader(new FileReader(file_num));
            reader_score = new BufferedReader(new FileReader(file_score));
            String tempString_num;
            String tempString_score;

            while (((tempString_num = reader_num.readLine()) != null) && ((tempString_score = reader_score.readLine()) != null)) {
                sql = "INSERT INTO `" + tableName.split("\\.")[0] + "` (`id`, `std_num`, `std_score`) VALUES (NULL, " + tempString_num + ", " + tempString_score +")";
                this.stmt.executeUpdate(sql);
            }
            reader_num.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader_num != null && reader_score != null) {
                try {
                    reader_num.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        this.close();
    }
}
