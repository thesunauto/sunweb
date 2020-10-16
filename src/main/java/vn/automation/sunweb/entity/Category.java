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
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "datecreated", nullable = false)
    private LocalDateTime datecreated;

    @Column(name = "detail")
    private String detail;

    @Column(name = "idparent")
    private String idparent;

    @Column(name = "isdeleted", nullable = false)
    private Integer isdeleted;

    @Column(name = "metatitle")
    private String metatitle;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "usercreated", nullable = false)
    private String usercreated;

}
