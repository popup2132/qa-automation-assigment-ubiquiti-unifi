package ubiquiti.models;

import java.util.List;
import java.util.Map;

public record AdminConfig(
        Meta meta,
        List<Data> data
) {

    public record Meta(
            String rc
    ) {
    }

    public record Data(
            String admin_id,
            String device_id,
            String email,
            boolean email_alert_enabled,
            int email_alert_grouping_delay,
            boolean email_alert_grouping_enabled,
            boolean html_email_enabled,
            boolean is_owner,
            boolean is_professional_installer,
            boolean is_super,
            String last_site_name,
            String name,
            boolean push_alert_enabled,
            boolean requires_new_password,
            List<String> super_site_permissions,
            Map<String, Object> ui_settings,
            long last_login_timestamp
    ) {
    }
}