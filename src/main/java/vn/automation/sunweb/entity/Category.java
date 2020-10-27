package vn.automation.sunweb.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "datecreated", nullable = false)
    private LocalDateTime datecreated;

    @Column(name = "detail")
    private String detail;

    @ManyToOne
    @JoinColumn(name = "idparent")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

    @Column(name = "isdeleted", nullable = false)
    private Integer isdeleted;

    @Column(name = "metatitle")
    private String metatitle;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "usercreated")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User usercreated;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Category> categories;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Post> posts;
}
