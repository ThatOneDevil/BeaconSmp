package me.thatonedevil.beaconsmp.beacon;

import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;

public class HoloPlaceHolder implements PlaceholderReplacer {

    private final String stars;

    public HoloPlaceHolder(String stars) {
        this.stars = stars;
    }

    @Override
    public String update() {
        return stars;
    }
}
