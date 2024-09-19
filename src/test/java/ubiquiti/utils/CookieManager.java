package ubiquiti.utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CookieManager {
    private static CookieManager instance;
    private String csrfToken;
    private String unifises;

    private CookieManager() {
    }

    public static CookieManager getInstance() {
        if (instance == null) {
            instance = new CookieManager();
        }
        return instance;
    }

}
