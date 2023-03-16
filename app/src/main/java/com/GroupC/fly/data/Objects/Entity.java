package com.GroupC.fly.data.Objects;

/**
 * This class handles the abstract entity class, which is the "father" of the different types of users we have
 * like: person, page etc.
 * **/

abstract class Entity {

        /* Data Members */

        private String m_name;
        private int m_id;
        private Address m_address;
        //Post posts[];
        //img profile_picture;
        private Entity blocked[];

        /* Functions */
        abstract public void createEntityInDB();
        public String getName() {
                return m_name;
        }

        public void setName(String i_name) {
                this.m_name = i_name;
        }

        public int getId() {
                return m_id;
        }

        public void setId(int i_id) {
                this.m_id = i_id;
        }

        public Address getAddress() {
                return m_address;
        }

        public void setAddress(Address i_address) {
                this.m_address = i_address;
        }

        public Entity[] getBlocked() {
                return blocked;
        }

        public void setBlocked(Entity[] blocked) {
                this.blocked = blocked;
        }
}

