package vn.automation.sunweb.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private String id;
    private String title;
    private String metatitle;
    private String detail;
    private String idparent;
}
