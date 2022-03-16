package pl.filipnowakdev.gardenmanager.model.device;

import lombok.Data;
import lombok.NonNull;

@Data
public class HttpDeviceConfig implements DeviceTypeConfig {
    @NonNull
    private String url;

    @NonNull
    private String dataType;
}
