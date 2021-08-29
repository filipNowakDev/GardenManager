package pl.filipnowakdev.gardenmanager.model.device;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class Device {

    @Id
    private String id;
    private String name;
    private DeviceType type;

    public Device(String name, DeviceType type) {
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }
}
