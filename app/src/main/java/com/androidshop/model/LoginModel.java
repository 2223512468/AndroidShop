package com.androidshop.model;

/**
 * Created by ${Terry} on 2018/2/1.
 */
public class LoginModel {


    /**
     * status : 0
     * msg : 登录成功
     * data : {"id":6,"username":"admin","password":"","email":"987456321@qq.com","phone":"15267185984","question":"123","answer":"123","role":0,"createTime":1517470615000,"updateTime":1517470615000}
     */

    private int status;
    private String msg;
    private DataEntity data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * id : 6
         * username : admin
         * password :
         * email : 987456321@qq.com
         * phone : 15267185984
         * question : 123
         * answer : 123
         * role : 0
         * createTime : 1517470615000
         * updateTime : 1517470615000
         */

        private int id;
        private String username;
        private String password;
        private String email;
        private String phone;
        private String question;
        private String answer;
        private int role;
        private long createTime;
        private long updateTime;

        public void setId(int id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        public int getRole() {
            return role;
        }

        public long getCreateTime() {
            return createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }
    }
}
