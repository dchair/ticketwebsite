package com.mycompany.ticketwebsite.service;

import com.mycompany.ticketwebsite.DAO.TicketDao;
import com.mycompany.ticketwebsite.model.ShoppingCart;
import com.mycompany.ticketwebsite.model.TicketInfoModel;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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
            "1/13台北兩廳院(1)", "1/13台中歌劇院(1)", "1/13高雄衛武營(1)",
            "1/20台北兩廳院(1)", "1/20台中歌劇院(1)", "1/20高雄衛武營(1)",
            "1/13台北兩廳院(2)", "1/13台中歌劇院(2)", "1/13高雄衛武營(2)",
            "1/20台北兩廳院(2)", "1/20台中歌劇院(2)", "1/20高雄衛武營(2)",
            "1/13台北兩廳院(3)", "1/13台中歌劇院(3)", "1/13高雄衛武營(3)",
            "1/20台北兩廳院(3)", "1/20台中歌劇院(3)", "1/20高雄衛武營(3)");

    private Map<Integer, String> createProductIdToProductNameMap() {
        Map<Integer, String> map = new HashMap<>();
        if (productNames != null) {
            for (int i = 1; i <= 18; i++) {
                map.put(i, productNames.get(i - 1));
            }
        }
        return map;
    }

    @Autowired
    private TicketDao ticketDao;

    public TicketInfoModel getTicketInfoByDateandlocation(int dateandlocation) {
        List<TicketInfoModel> ticketList = ticketDao.getTicketinfoByDateandlocation(Integer.toString(dateandlocation));

        // 假設您希望返回唯一的TicketInfoModel，您可以進行進一步的邏輯處理
        // 這裡的示例是返回列表的第一個元素，您可能需要根據實際情況進行處理
        return ticketList.isEmpty() ? null : ticketList.get(0);
    }

    public void addToCart(int dateandlocation, String productName, String price, String payment, String collection, int quantity) {
        // 根据日期和地点查找相应的票务信息
        TicketInfoModel ticketInfo = this.getTicketInfoByDateandlocation(dateandlocation);

        if (ticketInfo != null) {
            // 如果购物车中已经存在相同的商品，更新数量等信息
            ticketInfo.setQuantity(ticketInfo.getQuantity() + quantity);
        } else {
            // 如果购物车中不存在相同商品，创建新的 TicketInfoModel 对象并添加到购物车中
            TicketInfoModel newItem = new TicketInfoModel();
            newItem.setProductName(productName);
            newItem.setDateandlocation(dateandlocation);
            newItem.setPrice(price);
            newItem.setPayment(payment);
            newItem.setCollection(collection);

            shoppingCart.getTicketInfoModels().add(newItem);
        }
    }


    public void removeFromCart(ShoppingCart cart, int dateandlocation) {
        // 實現從購物車中移除商品的邏輯

        // 找到相應的 ticketinfomodels
        TicketInfoModel itemToRemove = null;
        for (TicketInfoModel item : cart.getTicketInfoModels()) {
            if (item.getDateandlocation() == dateandlocation) {
                itemToRemove = item;
                break;
            }
        }

        // 移除相應的 ticketinfomodels
        if (itemToRemove != null) {
            cart.getTicketInfoModels().remove(itemToRemove);
        }
    }
    public ShoppingCart getOrCreateShoppingCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }


    public void checkout(ShoppingCart cart) {
        // 實現結帳的邏輯
        // 清空 ShoppingCart 中的 ticketinfomodels
        cart.getTicketInfoModels().clear();
    }
}
