package vn.automation.sunweb.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private String idCategory;
    private String titleCategory;
    private Integer id;
    private String title;
    private String metatitle;
    private Boolean isshowindex;
    private Boolean ispublic;
    private String dateCreated;
    private String dateUpdated;
    private String datepuliced;
    private String image;
    private String content;
    private String user;
}
