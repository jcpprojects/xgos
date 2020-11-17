package com.santa.xgos.service;

import com.santa.xgos.model.Basket;
import org.springframework.stereotype.Service;

@Service
public interface DeedsAndDontsService {

    void requestConfirmation(Basket basket);

}
