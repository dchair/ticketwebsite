package com.mycompany.ticketwebsite.service;

import com.mycompany.ticketwebsite.model.ShoppingCart;
import com.mycompany.ticketwebsite.model.TicketInfoModel;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Service
public class ShoppingCartService {

    private final ShoppingCart shoppingCart = new ShoppingCart();
    private final Map<Integer, String> dateandlocationToProductName = createProductIdToProductNameMap();
    List<String> productNames = Arrays.asList(
            "1/13台北兩廳院(1)","1/13台中歌劇院(1)","1/13高雄衛武營(1)",
            "1/20台北兩廳院(1)","1/20台中歌劇院(1)","1/20高雄衛武營(1)",
            "1/13台北兩廳院(2)","1/13台中歌劇院(2)","1/13高雄衛武營(2)",
            "1/20台北兩廳院(2)","1/20台中歌劇院(2)","1/20高雄衛武營(2)",
            "1/13台北兩廳院(3)","1/13台中歌劇院(3)","1/13高雄衛武營(3)",
            "1/20台北兩廳院(3)","1/20台中歌劇院(3)","1/20高雄衛武營(3)");

    private Map<Integer, String> createProductIdToProductNameMap() {
        Map<Integer, String> map = new HashMap<>();
        if (productNames != null) {
            for (int i = 1; i <= 18; i++) {
                map.put(i, productNames.get(i - 1));
            }
        }
        // 添加其他 productId 到商品名稱的映射
        return map;
    }


    public void addToCart(ShoppingCart cart, TicketInfoModel ticketInfo, int quantity) {
        // 實現加入購物車的邏輯
        // 更新 ShoppingCart 中的 cartItems

        int dateandlocation = ticketInfo.getDateandlocation(); // 假設使用 dateandlocation 作為商品 ID
        String productName = dateandlocationToProductName.get(dateandlocation); // 根據商品 ID 獲取商品名稱

        // 檢查購物車中是否已存在相同商品，如果存在，則增加數量而不是新增
        for (TicketInfoModel item : cart.getTicketInfoModels()) {
            if (item.getDateandlocation() == dateandlocation) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        TicketInfoModel newItem = new TicketInfoModel();
        newItem.setDateandlocation(dateandlocation);
        newItem.setProductName(productName);
        newItem.setPrice(ticketInfo.getPrice()); // 假設 ticketInfo 包含商品價格
        newItem.setQuantity(quantity);

        cart.getTicketInfoModels().add(newItem);
    }


    public void removeFromCart(ShoppingCart cart, int dateandlocation) {
        // 實現從購物車中移除商品的邏輯
        // 更新 ShoppingCart 中的 cartItems

        // 找到相應的 CartItem
        TicketInfoModel itemToRemove = null;
        for (TicketInfoModel item : cart.getTicketInfoModels()) {
            if (item.getDateandlocation() == dateandlocation) {
                itemToRemove = item;
                break;
            }
        }

        // 移除相應的 CartItem
        if (itemToRemove != null) {
            cart.getTicketInfoModels().remove(itemToRemove);
        }
    }


    public void checkout(ShoppingCart cart) {
        // 實現結帳的邏輯
        // 清空 ShoppingCart 中的 cartItems
        cart.getTicketInfoModels().clear();

    }
}
