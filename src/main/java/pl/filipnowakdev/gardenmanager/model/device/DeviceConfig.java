package pl.filipnowakdev.gardenmanager.model.device;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
public class DeviceConfig {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private DeviceTypeConfig deviceTypeConfig;
}
