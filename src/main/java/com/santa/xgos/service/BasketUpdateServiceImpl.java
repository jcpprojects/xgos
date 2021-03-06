package com.santa.xgos.service;

import com.santa.xgos.model.Basket;
import com.santa.xgos.model.BasketStatus;
import com.santa.xgos.model.Order;
import com.santa.xgos.model.OrderStatus;
import com.santa.xgos.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class BasketUpdateServiceImpl implements BasketUpdateService {

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    DeedsAndDontsService deedsAndDontsService;

    @Override
    public void onNewBasket(Basket basket) {
        basket.setStatus(BasketStatus.NEW);
        basket.getOrders().forEach(o->o.setStatus(OrderStatus.NEW));
        basketRepository.save(basket);
        deedsAndDontsService.requestConfirmation(basket);
    }

    @Override
    public void onConfirmationReceived(Basket basket) {
        //TODO
    }

    @Override
    public void onOrderUpdate(Order order) {
        //TODO
    }

}
