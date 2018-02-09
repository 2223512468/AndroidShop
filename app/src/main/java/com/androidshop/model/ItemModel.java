package com.androidshop.model;

import java.util.List;

/**
 * Created by ${Terry} on 2018/2/7.
 */
public class ItemModel {


    /**
     * status : 0
     * data : [{"id":1,"price":"200","text":"猫","uri":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2171073523,1991282945&fm=27&gp=0.jpg"},{"id":2,"price":"200","text":"猫","uri":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2688905660,388396164&fm=27&gp=0.jpg"},{"id":3,"price":"200","text":"猫","uri":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2777081166,85078603&fm=27&gp=0.jpg"},{"id":4,"price":"300","text":"猫","uri":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2801782160,3456854724&fm=27&gp=0.jpg"},{"id":5,"price":"600","text":"狗","uri":"http://img2.imgtn.bdimg.com/it/u=1473558171,4053913881&fm=27&gp=0.jpg"},{"id":6,"price":"200","text":"猫","uri":"http://img0.imgtn.bdimg.com/it/u=2983358032,1731292116&fm=27&gp=0.jpg"},{"id":7,"price":"100","text":"猫","uri":"http://img1.imgtn.bdimg.com/it/u=755328260,2744743889&fm=27&gp=0.jpg"},{"id":8,"price":"60","text":"猫","uri":"http://img5.imgtn.bdimg.com/it/u=2214814307,4288719040&fm=27&gp=0.jpg"},{"id":9,"price":"200","text":"土拔鼠","uri":"http://img2.imgtn.bdimg.com/it/u=1281781512,2150938712&fm=27&gp=0.jpg"},{"id":10,"price":"100","text":"狗","uri":"http://img2.imgtn.bdimg.com/it/u=2475481552,705307258&fm=27&gp=0.jpg"}]
     */

    private int status;
    private List<DataEntity> data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * id : 1
         * price : 200
         * text : 猫
         * uri : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2171073523,1991282945&fm=27&gp=0.jpg
         */

        private int id;
        private String price;
        private String text;
        private String uri;

        public void setId(int id) {
            this.id = id;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public int getId() {
            return id;
        }

        public String getPrice() {
            return price;
        }

        public String getText() {
            return text;
        }

        public String getUri() {
            return uri;
        }
    }
}
