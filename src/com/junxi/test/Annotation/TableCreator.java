package com.junxi.test.Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/*
运行时注解处理器，构造表创建语句
 */
public class TableCreator {

    public static void main(String[] args) {
        try {
            String s = Member.class.getName();
            String result = createTableSql(s);
            System.out.println(result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String createTableSql (String className) throws ClassNotFoundException {
        Class<?> c1 = Class.forName(className); //class com.junxi.test.Annotation.Member
        DBTable dbTable = c1.getAnnotation(DBTable.class);  //@com.junxi.test.Annotation.DBTable(name=MEMBER)

        if(dbTable == null){
            System.out.println("NO DBTable annotations in class "+ className);
            return null;
        }
        String tableName = dbTable.name();  //MEMBER
        if (tableName.length() < 1){
             tableName = c1.getName().toUpperCase();
        }
        Field[] fields = c1.getDeclaredFields();
        /*
        fields:
            private java.lang.String com.junxi.test.Annotation.Member.id
            private java.lang.String com.junxi.test.Annotation.Member.name
            private int com.junxi.test.Annotation.Member.age
            private java.lang.String com.junxi.test.Annotation.Member.description
         */

        List<String> columnDefs = new ArrayList<String>();

        for (Field field:fields) {
            String columnName = null;

            Annotation[] anns = field.getDeclaredAnnotations();
            /*
            anns:
                @com.junxi.test.Annotation.SQLString(name=ID, value=50, constraint=@com.junxi.test.Annotation.Constraints(allowNull=false, unique=false, primaryKey=true))
                @com.junxi.test.Annotation.SQLString(name=NAME, value=30, constraint=@com.junxi.test.Annotation.Constraints(allowNull=false, unique=false, primaryKey=false))
                @com.junxi.test.Annotation.SQLInteger(name=AGE, constraint=@com.junxi.test.Annotation.Constraints(allowNull=false, unique=false, primaryKey=false))
                @com.junxi.test.Annotation.SQLString(name=DESCRIPTION, value=150, constraint=@com.junxi.test.Annotation.Constraints(allowNull=true, unique=false, primaryKey=false))
             */
            if(anns.length <1 ){
                continue;
            }
            if(anns[0] instanceof SQLInteger){
                SQLInteger sInt  = (SQLInteger) anns[0];
                if (sInt.name().length() < 1){
                    columnName = field.getName().toUpperCase();
                }else {
                    columnName = sInt.name();
                }
                columnDefs.add(columnName + " INT" + getConstraints(sInt.constraint()));
            }
            if(anns[0] instanceof SQLString){
                SQLString sStr = (SQLString)anns[0];
                if(sStr.name().length()<1){
                    columnName = field.getName().toUpperCase();
                }else{
                    columnName = sStr.name();
                }
                columnDefs.add(columnName + " VARCHAR(" + sStr.value() + ")" + getConstraints(sStr.constraint()));
            }
//            System.out.println(anns[0]);
        }

        StringBuilder createCommand = new StringBuilder("CREATE TABLE "+ tableName + "(");
        for (String columnDef : columnDefs) {
            createCommand.append("\n "+ columnDef + ",");
        }
        String createTable = createCommand.substring(0,createCommand.length()-1)+"\n);";


//        System.out.println(dbTable);
        return createTable;
    }

    public static String getConstraints(Constraints con){
        String constraints = "";
        if(!con.allowNull()){
            constraints += " NOT NULL";
        }
        if(con.primaryKey()){
            constraints += " PRIMARY KEY";
        }
        if(con.unique()){
            constraints += " UNIQUE";
        }
        return constraints;
    }

}
