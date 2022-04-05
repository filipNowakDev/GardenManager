package pl.filipnowakdev.gardenmanager.model.device;

import lombok.Data;
import lombok.NonNull;

@Data
public class MqttDeviceConfig implements DeviceTypeConfig {

    @NonNull
    private String brokerId;

    @NonNull
    private String topic;

    @NonNull
    private String dataType;
}
