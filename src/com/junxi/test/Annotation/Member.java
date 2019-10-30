package com.junxi.test.Annotation;

/*
    数据库表Member对应实例类bean
 */
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(name = "ID",value = 50,constraint = @Constraints(primaryKey = true))
    private String id;

    @SQLString(name = "NAME",value = 30)
    private String name;

    @SQLInteger(name="AGE")
    private int age;

    //个人描述
    @SQLString(name = "DESCRIPTION",value = 150,constraint = @Constraints(allowNull = true))
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
