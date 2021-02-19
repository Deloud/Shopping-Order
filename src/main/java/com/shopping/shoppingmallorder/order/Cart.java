package com.shopping.shoppingmallorder.order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(description = "사용자-상세 정보를 위한 도메인 객체")
public class Cart {

    @ApiModelProperty(notes = "사용자 id") //SWAGGER
//    @Id
//    private Integer order_id;
//    @Id
    private Integer id;

    private Integer total_price;

    @Id
    @GeneratedValue
    private Integer order_id;

    //1대 다 속성 주기
    @OneToMany(mappedBy = "cart")
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Purchase purchase;
}
