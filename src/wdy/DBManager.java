package wdy;

import java.io.*;
import java.sql.*;
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
        try {
            Properties props = new Properties();
            InputStream in = new FileInputStream("config.properties");
            props.load(in);
            in.close();

            String url="jdbc:mysql://" + props.getProperty("ip") + ":3306/" + props.getProperty("db");

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
            reader_score.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader_num != null && reader_score != null) {
                try {
                    reader_num.close();
                    reader_score.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        sql = "SELECT * FROM `" + tableName.split("\\.")[0] +"`";
        ResultSet resultSet =  this.stmt.executeQuery(sql);

        int num = 0;
        int max = 0;
        int min = 100;
        int score_over_60 = 0;
        int s_60_70 = 0;
        int s_70_80 = 0;
        int s_80_90 = 0;
        int s_90_100 = 0;
        int sum = 0;
        int score;

        for(; resultSet.next(); num++){
            score = resultSet.getInt("std_score");
            if(score > max)
                max = score;
            if(score < min)
                min = score;
            if(score >= 60){
                if(score < 70)
                    s_60_70++;
                else if(score < 80)
                    s_70_80++;
                else if(score < 90)
                    s_80_90++;
                else
                    s_90_100++;
                score_over_60++;
            }
            sum += score;
        }

        sql = "INSERT INTO `tables`(`id`, `name`, `total_num`, `over_60_num`, `max_score`, `min_score`, `total_score`, `s_60_70`, `s_70_80`, `s_80_90`, `s_90_100`) VALUES (NULL,'" + tableName.split("\\.")[0] + "'," + num+ "," + score_over_60 + "," + max + "," + min + "," + sum + "," + s_60_70 + "," + s_70_80 + "," + s_80_90 + "," + s_90_100 + ")";
        this.stmt.executeUpdate(sql);

        this.close();
    }
}
