package project.models.userModels;

import java.util.Objects;

public class Geo {
    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Geo)) return false;
        Geo geo = (Geo) o;
        return Objects.equals(getLat(), geo.getLat()) && Objects.equals(getLng(), geo.getLng());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLat(), getLng());
    }
}
