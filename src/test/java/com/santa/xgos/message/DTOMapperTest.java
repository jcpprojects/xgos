package com.santa.xgos.message;


import com.santa.xgos.message.mapper.DTOMapper;
import com.santa.xgos.model.Basket;
import com.santa.xgos.model.BasketStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DTOMapperTest {

    DTOMapper mapper = new DTOMapper();

    @Test
    public void testBasketMap(){
        WishBasketDTO wishBasketDTO = new WishBasketDTO();
        wishBasketDTO.id = "id1";
        wishBasketDTO.kidId = 2L;
        wishBasketDTO.wishList = new ArrayList<>();

        WishDTO wishDTO = new WishDTO();
        wishDTO.quantity = 10;
        wishDTO.productId = 100L;
        wishBasketDTO.wishList.add(wishDTO);

        Basket basket = mapper.convert(wishBasketDTO);
        assertEquals("id1", basket.id);
        assertEquals(2, basket.kidId);
        assertEquals(BasketStatus.NEW, basket.status);
    }

}