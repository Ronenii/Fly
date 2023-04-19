package com.GroupC.fly.data.Objects;

import java.util.Vector;

/**
 * This class handles the abstract entity class, which is the "father" of the different types of users we have
 * like: person, page etc.
 * **/

abstract class Entity {

        /** DATA MEMBERS **/

        private String name;
        private int id;
        private Address address;
        //Post posts[];
        //img profile_picture;
        private Vector<Integer> blocked;

        /** ABSTRACT METHODS **/
        abstract public void createEntityInDB();

        abstract public void deleteEntityInDB();

        abstract public void updateEntityInDB();


        /** METHODS **/

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public Address getAddress() {
                return address;
        }

        public void setAddress(Address address) {
                this.address = address;
        }

        public Vector<Integer> getBlocked() {
                return blocked;
        }

        public void setBlocked(Vector<Integer> blocked) {
                this.blocked = blocked;
        }
}

