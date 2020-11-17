package com.santa.xgos.kafka;

import com.santa.xgos.message.WishBasketDTO;
import com.santa.xgos.message.mapper.DTOMapper;
import com.santa.xgos.service.BasketUpdateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class WishBasketConsumer {

    @Autowired
    BasketUpdateService basketUpdateService;

    DTOMapper dtoMapper = new DTOMapper();

    private static final Logger LOG = LoggerFactory.getLogger(WishBasketConsumer.class);

    @KafkaListener(topics = "${app.topic.basket}")
    public void receive(@Payload String wishBasketString,
                        @Headers MessageHeaders headers) throws JsonProcessingException {
        //TODO to be replaced by message converter
        ObjectMapper mapper = new ObjectMapper();
        WishBasketDTO wishBasketDTO = mapper.readValue(wishBasketString, WishBasketDTO.class);
        LOG.info("received data='{}'", wishBasketString);
        basketUpdateService.onNewBasket(dtoMapper.convert(wishBasketDTO));
    }

}
