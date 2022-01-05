package fudan.se.lab2.controller.response;

import fudan.se.lab2.domain.User;

public class AllSickroomResponse {
    Iterable<User> sickrooms;

    public Iterable<User> getSickrooms() {
        return sickrooms;
    }

    public void setSickrooms(Iterable<User> sickrooms) {
        this.sickrooms = sickrooms;
    }
}
