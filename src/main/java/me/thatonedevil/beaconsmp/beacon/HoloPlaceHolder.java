package me.thatonedevil.beaconsmp.beacon;

import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;

public class HoloPlaceHolder implements PlaceholderReplacer {

    private final double stars;

    public HoloPlaceHolder(double stars) {
        this.stars = stars;
    }

    @Override
    public String update() {
        return String.valueOf(stars);
    }
}
