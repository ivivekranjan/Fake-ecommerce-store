package utils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayloadUtils {

    public static Map<String, Object> loginPayload(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return map;
    }

    public static Map<String, Object> cartCreatePayload(int userId, List<Map<String, Object>> products) {
        Map<String, Object> cart = new HashMap<>();
        cart.put("userId", userId);
        cart.put("date", LocalDate.now().toString());
        cart.put("products", products);
        return cart;
    }

    public static Map<String, Object> cartProduct(int productId, int quantity) {
        Map<String, Object> item = new HashMap<>();
        item.put("productId", productId);
        item.put("quantity", quantity);
        return item;
    }
}
