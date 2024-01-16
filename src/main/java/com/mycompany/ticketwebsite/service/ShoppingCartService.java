package com.mycompany.ticketwebsite.service;

import com.mycompany.ticketwebsite.DAO.TicketDao;
import com.mycompany.ticketwebsite.model.ShoppingCart;
import com.mycompany.ticketwebsite.model.TicketInfoModel;
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

    private final Map<Integer, String> ticketTypeMap = createTicketTypeMap();
    public String getTicketTypeDescription(int tickettype) {
        return ticketTypeMap.get(tickettype);
    }

    private Map<Integer, String> createTicketTypeMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "敬老票");
        map.put(2, "學生票");
        map.put(3, "全票");
        return map;
    }
    private final Map<Integer, String> paymentMap = createPaymentMap();

    public String getPaymentDescription(int payment) {
        return paymentMap.get(payment);
    }
    private Map<Integer, String> createPaymentMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "信用卡");
        map.put(2, "超商付款");
        map.put(3, "轉帳");
        return map;
    }


    private final Map<Integer, String> collectionMap = createCollectionMap();
    public String getCollectionDescription(int collection) {
        return collectionMap.get(collection);
    }
    private Map<Integer, String> createCollectionMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "超商取票");
        map.put(2, "現場取票");
        map.put(3, "電子票券");
        return map;
    }


    public void addToCart(ShoppingCart cart, int dateandlocation, String seat, int tickettype, int payment, int collection, int quantity, int price) {
        // 實現加入購物車的邏輯

        if (cart.getTicketInfoModels().stream().anyMatch(item -> item.getDateandlocation() == dateandlocation)) {
            cart.getTicketInfoModels().stream()
                    .filter(item -> item.getDateandlocation() == dateandlocation)
                    .findFirst()
                    .ifPresent(item -> item.setQuantity(item.getQuantity() + quantity));
        } else {

            // 如果購物車中不存在相同商品，創建新的 TicketInfoModel 物件並添加到購物車中
            String productName = dateandlocationToProductName.get(dateandlocation);
            TicketInfoModel newItem = new TicketInfoModel();
            newItem.setProductName(productName);
            newItem.setDateandlocation(dateandlocation);
            newItem.setSeat(seat);
            newItem.setTickettype(tickettype);
            newItem.setPayment(payment);
            newItem.setCollection(collection);
            newItem.setQuantity(quantity);
            newItem.setPrice(price);

            cart.getTicketInfoModels().add(newItem);
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


    public void checkout(ShoppingCart cart) {
        // 實現結帳的邏輯
        // 清空 ShoppingCart 中的 ticketinfomodels
        cart.getTicketInfoModels().clear();
    }
}
