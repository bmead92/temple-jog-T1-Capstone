package com.game.templejog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room implements java.io.Serializable {
    //    TODO private fields
    Integer number;
    String name;
    String description, shortDescription, west, south, north, east;
    List<String> items = new ArrayList<>();
    List<String> encounters_to = new ArrayList<>();
    List<String> encounters_from = new ArrayList<>();
    Boolean hasBeenVisited;
    Boolean isLocked;
    String sound;
    String image;

    public Room() {
    }

    public Room(Integer number, String name, String description, String shortDescription, String west, String south, String north, String east, List<String> items, List<String> encounters_to, List<String> encounters_from, Boolean hasBeenVisited, String sound, String backgroungImage) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.west = west;
        this.south = south;
        this.north = north;
        this.east = east;
        this.encounters_to = encounters_to;
        this.encounters_from = encounters_from;
        this.hasBeenVisited = hasBeenVisited;
        this.items = items;
        this.sound = sound;
        this.image = backgroungImage;
    }

    //  HELPER METHODS
    public String checkDirection(String noun) {
        HashMap<String, String> directions = new HashMap<>();
        directions.put("west", getWest());
        directions.put("north", getNorth());
        directions.put("south", getSouth());
        directions.put("east", getEast());
        String dir = directions.get(noun);
        return dir;
    }

    public Boolean directionBlockedByDoor() {
        return getEncounters_to().contains("locked door");
    }

    public Boolean removeEncounter(String targetEncounter) {
        if (getEncounters_to().contains(targetEncounter)) {
            Boolean removedEncounterTo = getEncounters_to().remove(targetEncounter);
            return removedEncounterTo;
        }
//        if( getEncounters_from().contains(targetEncounter) ){
//            Boolean removedEncounterFrom = getEncounters_from().remove(targetEncounter);
//            return removedEncounterFrom;
//        }
        // else return false // if rooms encounter_to length is 0
        return false;
    }

    //  ACCESSOR METHODS
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<String> getEncounters_to() {
        return encounters_to;
    }

    public void setEncounters_to(List<String> encounters_to) {
        this.encounters_to = encounters_to;
    }

    public List<String> getEncounters_from() {
        return encounters_from;
    }

    public void setEncounters_from(List<String> encounters_from) {
        this.encounters_from = encounters_from;
    }

    public boolean getHasBeenVisited() {
        return hasBeenVisited;
    }

    public void setHasBeenVisited(Boolean hasBeenVisited) {
        this.hasBeenVisited = hasBeenVisited;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean locked) {
        isLocked = locked;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}