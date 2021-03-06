package com.santa.xgos.message;

import java.util.List;

public class WishBasketDTO {

    private String id;
    private Long kidId;
    private List<WishDTO> wishList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getKidId() {
        return kidId;
    }

    public void setKidId(Long kidId) {
        this.kidId = kidId;
    }

    public List<WishDTO> getWishList() {
        return wishList;
    }

    public void setWishList(List<WishDTO> wishList) {
        this.wishList = wishList;
    }
}
