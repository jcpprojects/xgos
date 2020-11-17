package com.santa.xgos.message;


import com.santa.xgos.message.mapper.DTOMapper;
import com.santa.xgos.model.Basket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DTOMapperTest {

    DTOMapper mapper = new DTOMapper();

    @Test
    public void testWishBasketDTOToBasketConversion(){
        WishBasketDTO wishBasketDTO = new WishBasketDTO();
        String id = "id1";
        wishBasketDTO.setId(id);
        wishBasketDTO.setKidId(2L);
        List<WishDTO> wishDTOList = new ArrayList<>();
        WishDTO wishDTO = new WishDTO();
        wishDTO.setQuantity(10);
        wishDTO.setProductId(100L);
        wishDTOList.add(wishDTO);
        wishBasketDTO.setWishList(wishDTOList);

        Basket basket = mapper.convert(wishBasketDTO);
        assertEquals(id, basket.getId());
        assertEquals(2, basket.getKidId());
        assertEquals(1, basket.getOrders().size());
        assertEquals(10, basket.getOrders().get(0).getQuantity());
        assertEquals(100L, basket.getOrders().get(0).getProductId());
    }

}