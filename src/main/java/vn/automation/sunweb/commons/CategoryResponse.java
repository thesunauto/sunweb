package vn.automation.sunweb.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private String id;
    private String title;
    private String metatitle;
    private String detail;
    private String parentTitle;
    private String idParent;
    private Boolean hasParent;
    private Boolean isMax;
}
