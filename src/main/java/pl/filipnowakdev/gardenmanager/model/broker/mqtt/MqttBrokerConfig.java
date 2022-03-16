package pl.filipnowakdev.gardenmanager.model.broker.mqtt;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MqttBrokerConfig {
    @Id
    String id;

    String username;

    String password;

    String address;
}
