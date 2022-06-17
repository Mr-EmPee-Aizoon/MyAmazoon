package tk.empee.myamazoon.model.utils.mappers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;

public final class ControllerMappers {


    private static HashMap<String, String> urls = new HashMap<>();

    static {
        urls.put("index", "/index");

        urls.put("createProduct", "/personalArea/administration/products/createProduct");
        urls.put("updateProduct", "/personalArea/administration/products/updateProduct");
        urls.put("productsList", "/personalArea/administration/products/productsList");

        urls.put("updateUser", "/personalArea/administration/users/updateUser");
        urls.put("usersList", "/personalArea/administration/users/usersList");

        urls.put("login", "/personalArea/login");
        urls.put("logout", "/personalArea/logout");

        urls.put("adminDashboard", "/personalArea/administration/dashboard");
    }

    public static String getAbsoluteURL(String key, HttpServlet servlet) {
        return servlet.getServletContext().getContextPath() + urls.get(key);
    }

    public static String getAbsoluteURL(String key, ServletContext context) {
        return context.getContextPath() + urls.get(key);
    }

    public static String getURL(String key) {
        return urls.get(key);
    }


}
