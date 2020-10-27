package vn.automation.sunweb.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "context", nullable = false)
    private String context;

    @Column(name = "datecreated", nullable = false)
    private LocalDateTime datecreated;

    @Column(name = "datepuliced")
    private LocalDateTime datepuliced;

    @Column(name = "dateupdated")
    private LocalDateTime dateupdated;

    @ManyToOne
    @JoinColumn(name = "idcategory")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

    @Column(name = "image")
    private String image;

    @Column(name = "isdeleted", nullable = false)
    private Boolean isdeleted;

    @Column(name = "ispulic", nullable = false)
    private Boolean ispulic;

    @Column(name = "isshowindex", nullable = false)
    private Boolean isshowindex;

    @Column(name = "metatitle")
    private String metatitle;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "usercreated")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User usercreated;

}
