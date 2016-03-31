package com.goeuro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.goeuro.utils.RestApiPath;

@JsonIgnoreProperties(ignoreUnknown = true)
@RestApiPath("position/suggest/en")
public class Location implements Data {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class GeoPosition {
        @JsonProperty
        private double latitude;
        @JsonProperty
        private double longitude;

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
    }
    @JsonProperty("_id")
    private long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String type;
    @JsonProperty(value = "geo_position")
    private GeoPosition geoPosition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLatitude() {
        return this.geoPosition.getLatitude();
    }

    public double getLongitude() {
        return this.geoPosition.getLongitude();
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }
}
