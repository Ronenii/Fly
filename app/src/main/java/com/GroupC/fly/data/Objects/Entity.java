package com.GroupC.fly.data.Objects;

import java.util.Vector;

/**
 * This class handles the abstract entity class, which is the "father" of the different types of users we have
 * like: person, page etc.
 * **/

abstract class Entity {

        /** DATA MEMBERS **/

        private Address address;
        //Post posts[];
        //img profilePicture;
        private Vector<Integer> blocked;


        /** METHODS **/

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

