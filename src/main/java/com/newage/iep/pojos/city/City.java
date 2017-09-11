package com.newage.iep.pojos.city;

/**
 * Created by a1996_000 on 2017/9/11.
 */
//城市表
public class City {

    private Integer id; //int(11) unsigned NOT NULL AUTO_INCREMENT,
    private String code;// varchar(50) NOT NULL DEFAULT '' COMMENT '行政代码',
    private String name; //varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
    private Integer parent_id;//int(11) NOT NULL COMMENT '父id',
    private String first_letter; //varchar(10) NOT NULL DEFAULT '' COMMENT '首字母',
    private Integer level; //int(11) NOT NULL COMMENT '城市等级',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getFirst_letter() {
        return first_letter;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
