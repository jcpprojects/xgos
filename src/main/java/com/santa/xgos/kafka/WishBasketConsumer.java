package com.santa.xgos.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santa.xgos.message.WishBasketDTO;
import com.santa.xgos.message.mapper.DTOMapper;
import com.santa.xgos.service.BasketUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WishBasketConsumer {

    @Autowired
    BasketUpdateService basketUpdateService;

    DTOMapper dtoMapper = new DTOMapper();

    @KafkaListener(topics = "${app.topic.basket}")
    public void receive(String wishBasketString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        WishBasketDTO wishBasketDTO = mapper.readValue(wishBasketString, WishBasketDTO.class);
        basketUpdateService.onNewBasket(dtoMapper.convert(wishBasketDTO));
    }

}