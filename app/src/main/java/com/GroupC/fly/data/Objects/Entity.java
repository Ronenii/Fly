package com.GroupC.fly.data.Objects;

/**
 * This class handles the abstract entity class, which is the "father" of the different types of users we have
 * like: person, page etc.
 * **/

abstract class Entity {

        /* Data Members */

        private String name;
        private int id;
        private Address address;
        //Post posts[];
        //img profile_picture;
        private Entity blocked[];

        /* Functions */
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

        public Entity[] getBlocked() {
                return blocked;
        }

        public void setBlocked(Entity[] blocked) {
                this.blocked = blocked;
        }
}

