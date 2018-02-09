package com.androidshop.model;

import java.util.List;

/**
 * Created by ${Terry} on 2018/2/9.
 */
public class FilterModel {


    /**
     * status : 0
     * data : {"colorList":[{"id":1,"color":"绿毛","itemId":null},{"id":2,"color":"白色","itemId":null},{"id":3,"color":"棕色","itemId":null},{"id":4,"color":"棕色","itemId":null},{"id":5,"color":"棕色","itemId":null},{"id":6,"color":"棕色","itemId":null},{"id":7,"color":"白色","itemId":null},{"id":8,"color":"棕色","itemId":null},{"id":9,"color":"白色","itemId":null},{"id":10,"color":"白色","itemId":null}],"brandList":[{"id":1,"text":"猫","itemId":null},{"id":2,"text":"猫","itemId":null},{"id":3,"text":"猫","itemId":null},{"id":4,"text":"猫","itemId":null},{"id":5,"text":"狗","itemId":null},{"id":6,"text":"猫","itemId":null},{"id":7,"text":"猫","itemId":null},{"id":8,"text":"猫","itemId":null},{"id":9,"text":"土拔鼠","itemId":null},{"id":10,"text":"狗","itemId":null}]}
     */

    private int status;
    private DataEntity data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * colorList : [{"id":1,"color":"绿毛","itemId":null},{"id":2,"color":"白色","itemId":null},{"id":3,"color":"棕色","itemId":null},{"id":4,"color":"棕色","itemId":null},{"id":5,"color":"棕色","itemId":null},{"id":6,"color":"棕色","itemId":null},{"id":7,"color":"白色","itemId":null},{"id":8,"color":"棕色","itemId":null},{"id":9,"color":"白色","itemId":null},{"id":10,"color":"白色","itemId":null}]
         * brandList : [{"id":1,"text":"猫","itemId":null},{"id":2,"text":"猫","itemId":null},{"id":3,"text":"猫","itemId":null},{"id":4,"text":"猫","itemId":null},{"id":5,"text":"狗","itemId":null},{"id":6,"text":"猫","itemId":null},{"id":7,"text":"猫","itemId":null},{"id":8,"text":"猫","itemId":null},{"id":9,"text":"土拔鼠","itemId":null},{"id":10,"text":"狗","itemId":null}]
         */

        private List<ColorListEntity> colorList;
        private List<BrandListEntity> brandList;

        public void setColorList(List<ColorListEntity> colorList) {
            this.colorList = colorList;
        }

        public void setBrandList(List<BrandListEntity> brandList) {
            this.brandList = brandList;
        }

        public List<ColorListEntity> getColorList() {
            return colorList;
        }

        public List<BrandListEntity> getBrandList() {
            return brandList;
        }

        public static class ColorListEntity {
            /**
             * id : 1
             * color : 绿毛
             * itemId : null
             */

            private int id;
            private String color;
            private Object itemId;

            public void setId(int id) {
                this.id = id;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public void setItemId(Object itemId) {
                this.itemId = itemId;
            }

            public int getId() {
                return id;
            }

            public String getColor() {
                return color;
            }

            public Object getItemId() {
                return itemId;
            }
        }

        public static class BrandListEntity {
            /**
             * id : 1
             * text : 猫
             * itemId : null
             */

            private int id;
            private String text;
            private Object itemId;

            public void setId(int id) {
                this.id = id;
            }

            public void setText(String text) {
                this.text = text;
            }

            public void setItemId(Object itemId) {
                this.itemId = itemId;
            }

            public int getId() {
                return id;
            }

            public String getText() {
                return text;
            }

            public Object getItemId() {
                return itemId;
            }
        }
    }
}
