package me.thatonedevil.beaconsmp.Database;

import me.thatonedevil.beaconsmp.BeaconSmp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomPlayer {
    private BeaconSmp main;

    private int stars;
    private String beaconLoc;

    private UUID uuid;
    public CustomPlayer(BeaconSmp main, UUID uuid) throws SQLException {
        this.main = main;

        this.uuid = uuid;

        PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("SELECT STARS, BEACONLOC FROM players WHERE UUID = ?;");
        statement.setString(1, uuid.toString());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            beaconLoc = rs.getString("BEACONLOC");
            stars = rs.getInt("STARS");
        } else {
            beaconLoc = "Not placed";
            stars = 0;
            PreparedStatement statement1 = main.getDatabase().getConnection().prepareStatement("INSERT INTO players (ID, UUID, STARS, BEACONLOC) VALUES (" +
                    "DEFAULT," +
                    "'" + uuid + "'," +
                    stars + "," +
                    "'" + beaconLoc + "');");
            statement1.executeUpdate();
        }

    }

    public void setStars(int stars) {
        this.stars = stars;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("UPDATE players SET STARS = " + stars + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBeaconLoc(String beaconLoc) {
        this.beaconLoc = beaconLoc;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("UPDATE players SET BEACONLOC = " + beaconLoc + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlayerData(UUID uuid) {
        this.uuid = uuid;
        try {
            PlayerManager playerManager = new PlayerManager();
            playerManager.removeCustomPlayer(uuid);
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("DELETE from players WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public int getStars() { return stars; }
    public String getBeaconLoc() { return beaconLoc; }
    public UUID getUuid() { return uuid; }


}
