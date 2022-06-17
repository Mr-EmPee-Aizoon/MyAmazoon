package tk.empee.myamazoon.model.dao;

import tk.empee.myamazoon.model.dto.products.Category;
import tk.empee.myamazoon.model.dto.products.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class ProductsDAO {

    public static List<Product> findProducts() throws SQLException {
        try(DBConnection connection = new DBConnection()) {
            return connection.execute( s -> {
                ResultSet rs = s.executeQuery("SELECT * FROM products");

                ArrayList<Product> products = new ArrayList<>();
                while(rs.next()) {
                    products.add(parseProduct(rs));
                }

                return Collections.unmodifiableList(products);
            });
        }
    }

    public static List<Product> findProductsByCategory(Category category) throws SQLException {
        try(DBConnection connection = new DBConnection()) {
            connection.setPreparedStatement("SELECT * FROM products WHERE category = ?");
            return connection.executePrepared( s -> {
                s.setString(1, category.name());
                ResultSet rs = s.executeQuery();

                ArrayList<Product> filteredProducts = new ArrayList<>();
                while(rs.next()) {
                    filteredProducts.add(parseProduct(rs));
                }

                return filteredProducts;
            });
        }
    }

    /**
     * @return the productID
     **/
    public static synchronized long saveProduct(Product product) throws SQLException {
        try(DBConnection connection = new DBConnection()) {
            connection.setPreparedStatement("INSERT INTO products (title,description,category,price,image) VALUES (?,?,?,?,?)");
            connection.executePrepared( s -> {
                s.setString(1, product.getTitle());
                s.setString(2, product.getDescription());
                s.setString(3, product.getCategory().name());
                s.setDouble(4, product.getPrice());
                s.setString(5, product.getImagePath());
                s.executeUpdate();
            });

            return connection.execute( s -> {
                ResultSet rs = s.executeQuery("select max(id) as max from products");
                rs.next();

                return rs.getLong("max");
            });
        }
    }

    public static int deleteProduct(long id) throws SQLException {
        try(DBConnection connection = new DBConnection()) {
            connection.setPreparedStatement("DELETE FROM products WHERE id = ?");
            return connection.executePrepared( s -> {
                s.setDouble(1, id);
                return s.executeUpdate();
            });
        }
    }

    public static Optional<Product> findProductByID(long id) throws SQLException {
        try(DBConnection connection = new DBConnection()) {
            connection.setPreparedStatement("SELECT * FROM products WHERE id = ?");
            return connection.executePrepared( s -> {
                s.setLong(1, id);
                ResultSet rs = s.executeQuery();

                if(rs.next()) {
                    return Optional.of(parseProduct(rs));
                }

                return Optional.empty();
            });
        }
    }

    public static boolean updateProduct(Product product) throws SQLException {
        try(DBConnection connection = new DBConnection()) {
            connection.setPreparedStatement("UPDATE products SET title = ?, description = ?, category=?, price = ? WHERE id = ?");
            return connection.executePrepared( s -> {
                s.setString(1, product.getTitle());
                s.setString(2, product.getDescription());
                s.setString(3, product.getCategory().name());
                s.setDouble(4, product.getPrice());
                s.setLong(5, product.getId());

                return s.executeUpdate() > 0;
            });
        }
    }

    private static Product parseProduct(ResultSet rs) throws SQLException {
        Product product = new Product(rs.getLong("id"));

        product.setTitle(rs.getString("title"));
        product.setDescription(rs.getString("description"));
        product.setCategory(
                Category.valueOf(rs.getString("category"))
        );
        product.setPrice(rs.getDouble("price"));
        product.setImagePath(rs.getString("image"));
        return product;
    }

}
