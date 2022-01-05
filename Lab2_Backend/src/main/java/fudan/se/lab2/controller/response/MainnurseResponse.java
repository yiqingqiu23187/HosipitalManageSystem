package fudan.se.lab2.controller.response;

import fudan.se.lab2.domain.User;

public class MainnurseResponse {
    Iterable<User> mainnurses;

    public Iterable<User> getMainnurses() {
        return mainnurses;
    }

    public void setMainnurses(Iterable<User> mainnurses) {
        this.mainnurses = mainnurses;
    }
}
