package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionDB {
    
    static String db = "rest_acai_db";
    static String login = "root";
    static String password = "";
    static String url = "jdbc:mysql://127.0.0.1:3306/" + db;
    Connection con = null;
    Statement stm;
    ResultSet rs;

    private void conectar() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, login, password);
        this.stm = con.createStatement();
    }
    
    public Connection conexion() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, login, password);
        return con;
    }

    public void desconectar() throws Exception {
        this.con.close();
        this.stm.close();
    }

    public ResultSet mostrar(String sql) {
        try {
            conectar();
            rs = stm.executeQuery(sql);
        } catch (Exception e) {
            System.err.println("Hubo un error en mostrar datos de la DB");
        }
        return rs;
    }
    
    public void ejecutar(String sql) {
        try {
            conectar();
            stm.execute(sql);
            desconectar();
        } catch (Exception e) {
        }
    }
}
