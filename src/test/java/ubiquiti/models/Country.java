package ubiquiti.models;

import lombok.Data;

import java.util.List;

@Data
public class Country {
    private Meta meta;
    private List<DataElement> data;

    @Data
    public static class Meta {
        private String rc;
    }

    @Data
    public static class DataElement {
        private String code;
        private String site_id;
        private String _id;
        private String key;
    }
}