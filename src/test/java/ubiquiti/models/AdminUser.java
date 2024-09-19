package ubiquiti.models;

public record AdminUser(
        String cmd,
        String name,
        String email,
        String x_password
) {
}
