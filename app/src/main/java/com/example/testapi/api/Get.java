package com.example.testapi.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;

public class Get {

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("data")
    @Expose
    private List<Item> data;



    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public List<Get.Item> getData() {
        return data;
    }

    public void setData(List<Get.Item> data) {
        this.data = data;
    }


    public class Item {

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("actual_time")
        @Expose
        private int actual_time;

        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("location")
        @Expose
        private String location;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getActualTime() {
            return this.actual_time;
        }

        public void setActual_time(int actual_time) {
            this.actual_time = actual_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
