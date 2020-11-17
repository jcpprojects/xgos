package com.santa.xgos.message.mapper;

import com.santa.xgos.message.WishBasketDTO;
import com.santa.xgos.model.Basket;
import com.santa.xgos.model.BasketStatus;
import com.santa.xgos.model.Order;
import com.santa.xgos.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class DTOMapper {

    public Basket convert(WishBasketDTO wishBasket){
        Basket basket = new Basket();
        basket.setId(wishBasket.id);
        basket.setKidId(wishBasket.kidId);
        basket.setStatus(BasketStatus.NEW);
        List<Order> orders = new ArrayList<>();
        wishBasket.wishList.forEach(w->{
                    Order order = new Order();
                    order.setBasket(basket);
                    order.setQuantity(w.quantity);
                    order.setProductId(w.productId);
                    order.setStatus(OrderStatus.NEW);
                });
        basket.setOrders(orders);
        return basket;
    }

}
