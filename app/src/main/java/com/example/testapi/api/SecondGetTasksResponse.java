package com.example.testapi.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SecondGetTasksResponse {

    private boolean status;
    private SecondItem data;
    private String error;



    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public SecondItem getData() {
        return data;
    }

    public void setData(SecondItem data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public class SecondItem {

        private int id;
        private String title;
        private int actual_time;
        private String status;
        private String location;
        private String description;
        private Specialist specialist;


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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Specialist getSpecialist() {
            return specialist;
        }

        public void setSpecialist(Specialist specialist) {
            this.specialist = specialist;
        }


        public class Specialist {


            private String first_name;

            private String last_name;

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }
        }


    }
}
