package pl.filipnowakdev.gardenmanager.model.device;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "deviceType")
@JsonSubTypes({
        @JsonSubTypes.Type(value=MqttDeviceConfig.class, name = "MQTT"),
        @JsonSubTypes.Type(value=HttpDeviceConfig.class, name = "HTTP")
})
public interface DeviceTypeConfig {}

