package vn.automation.sunweb.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private String id;
    private String title;
    private String image;
    private File imageBytes;
    private String detail;
    private String parentTitle;
    private String idParent;
    private Boolean hasParent;
    private String isShowIndex;
    private Boolean isMax;
}
