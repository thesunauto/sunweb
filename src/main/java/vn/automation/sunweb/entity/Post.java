package vn.automation.sunweb.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "context", nullable = false)
    private String context;

    @Column(name = "datecreated", nullable = false)
    private LocalDateTime datecreated;

    @Column(name = "datepuliced")
    private LocalDateTime datepuliced;

    @Column(name = "dateupdated")
    private LocalDateTime dateupdated;

    @Column(name = "idcategory", nullable = false)
    private String idcategory;

    @Column(name = "image")
    private String image;

    @Column(name = "isdeleted", nullable = false)
    private Integer isdeleted;

    @Column(name = "ispulic", nullable = false)
    private Integer ispulic;

    @Column(name = "isshowindex", nullable = false)
    private Integer isshowindex;

    @Column(name = "metatitle")
    private String metatitle;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "usercreated", nullable = false)
    private String usercreated;

}
