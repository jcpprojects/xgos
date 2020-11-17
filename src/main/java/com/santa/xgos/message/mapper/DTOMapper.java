package com.santa.xgos.message.mapper;

import com.santa.xgos.message.WishBasketDTO;
import com.santa.xgos.model.Basket;
import com.santa.xgos.model.Order;

public class DTOMapper {

    public Basket convert(WishBasketDTO wishBasket) {
        Basket basket = new Basket();
        basket.setId(wishBasket.getId());
        basket.setKidId(wishBasket.getKidId());
        wishBasket.getWishList().forEach(w -> {
            Order order = new Order();
            order.setBasket(basket);
            order.setQuantity(w.getQuantity());
            order.setProductId(w.getProductId());
            basket.addOrder(order);
        });
        return basket;
    }

}
