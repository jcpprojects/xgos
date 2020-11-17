package com.santa.xgos.integration;

import com.santa.xgos.model.Basket;
import com.santa.xgos.repository.BasketRepository;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


@SpringBootTest
@EmbeddedKafka(ports = 9092)
public class IntegrationTest {

//TODO replace kafka port with random test port

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    BasketRepository basketRepository;

    Producer<String, String> producer;
    private final String id1 = "id1";

    @BeforeEach
    public void setUp() {
        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafkaBroker.getBrokersAsString());
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        this.producer = new DefaultKafkaProducerFactory<String, String>(configs, new StringSerializer(), new StringSerializer()).createProducer();
    }

    @Test
    public void test() throws InterruptedException {

        String jsonString = "{\"id\":\"" + id1 + "\",\"kidId\":100, \"wishList\":[{\"quantity\":1,\"productId\":1000}]}";

        producer.send(new ProducerRecord<>("digital-wish-basket", id1, jsonString));
        producer.flush();
        assertBasketCreated();
    }

    void assertBasketCreated() throws InterruptedException {
        int attempts = 5;
        while (attempts-- > 0) {
            Optional<Basket> optionalBasket = basketRepository.findById(id1);
            if (optionalBasket.isPresent()) {
                assertTrue(optionalBasket.isPresent());
                Basket basket = optionalBasket.get();
                assertEquals(id1, basket.getId());
                assertEquals(100L, basket.getKidId().longValue());
                assertEquals(1, basket.getOrders().size());
                assertEquals(1, basket.getOrders().get(0).getQuantity().longValue());
                assertEquals(1000L, basket.getOrders().get(0).getProductId().longValue());
                return;
            }
            Thread.sleep(1000);
        }
        fail("Basket not created");
    }

}