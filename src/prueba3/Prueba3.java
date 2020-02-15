package prueba3;

import com.sun.org.apache.xpath.internal.res.XPATHErrorResources_pt_BR;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Prueba3 {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/tienda";
            String user = "root";
            String pwd = "mysqladmin";
            try (Connection conect = DriverManager.getConnection(url, user, pwd); Statement statement = conect.createStatement()) {
                String sql = "select * from productos";
                try (ResultSet resultset = statement.executeQuery(sql)) {
                    while (resultset.next()) {
                        int id_prod = resultset.getInt("id_producto");
                        String nombre = resultset.getString("nombre");
                        int precio = resultset.getInt("precio");
                        System.out.println("id " + id_prod);
                        System.out.println("nombre" + nombre);
                        System.out.println("precio" + precio);
                        System.out.println("==============================");
                    }
                    Scanner scan = new Scanner(System.in);
                    System.out.println("¿que desas hacer : insertar / actualizar /eliminar");
                    String accion = scan.nextLine();
                    if (accion.equals("insertar")) {
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el producto");
                        String idprod = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el nombre");
                        String nombre = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el precio");
                        String precio = scan.nextLine();
                        //
                        String query = "insert into productos values(?,?,?)";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setInt(1, Integer.parseInt(idprod));
                        ps.setString(2, nombre);
                        ps.setInt(3, Integer.parseInt(precio));
                        ps.executeUpdate();

                    }
                    if (accion.equals("borrar")) {
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el id_producto");
                        String idprod = scan.nextLine();
                        String query = "delete from productos where id_producto=?";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setInt(1, Integer.parseInt(idprod));
                        ps.executeUpdate();
                    }
                    if (accion.equals("actualizar")) {
                        scan = new Scanner(System.in);
                        System.out.println("ingresa el producto");
                        String idprod = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el nombre");
                        String nombre = scan.nextLine();

                        scan = new Scanner(System.in);
                        System.out.println("ingresa el precio");
                        String precio = scan.nextLine();
                        //
                        String query = "update productos set nombre=?, precio=? where id_producto=?";
                        PreparedStatement ps = conect.prepareStatement(query);
                        ps.setString(1, nombre);
                        ps.setInt(2, Integer.parseInt(precio));
                        ps.setInt(3, Integer.parseInt(idprod));
                        ps.executeUpdate();
                    }
                    resultset.close();
                    statement.close();
                    conect.close();
                }
            }
            //

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
