package com.santa.xgos.service;

import com.santa.xgos.model.Basket;
import com.santa.xgos.model.Order;

/**
 * Interface to handle
 */
public interface BasketUpdateService {

    void onNewBasket(Basket basket);

    void onConfirmationReceived(Basket basket);

    void onOrderUpdate(Order order);

}
