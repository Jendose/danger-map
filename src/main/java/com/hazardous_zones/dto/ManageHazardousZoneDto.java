package com.hazardous_zones.dto;

public class ManageHazardousZoneDto {
    private String name;
    private Double latitude;
    private Double longitude;
    private String hazardousZoneType;
    private Double nuclearWasteAmount;
    private Double recycledWasteAmount;
    private Double unrecyclableWasteAmount;
    private Double averageTemperature;
    private Double averageAttendance;
    private Double waterPollutionRate;
    private Double airPollutionRate;
    private Double soilPollutionRate;
    private Double rockDestructionLevel;
    private Double industrialEnterprisesNumber;
    private Double reservoirExist;
    private Double flammableSubstancesExist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getHazardousZoneType() {
        return hazardousZoneType;
    }

    public void setHazardousZoneType(String hazardousZoneType) {
        this.hazardousZoneType = hazardousZoneType;
    }

    public Double getNuclearWasteAmount() {
        return nuclearWasteAmount;
    }

    public void setNuclearWasteAmount(Double nuclearWasteAmount) {
        this.nuclearWasteAmount = nuclearWasteAmount;
    }

    public Double getRecycledWasteAmount() {
        return recycledWasteAmount;
    }

    public void setRecycledWasteAmount(Double recycledWasteAmount) {
        this.recycledWasteAmount = recycledWasteAmount;
    }

    public Double getUnrecyclableWasteAmount() {
        return unrecyclableWasteAmount;
    }

    public void setUnrecyclableWasteAmount(Double unrecyclableWasteAmount) {
        this.unrecyclableWasteAmount = unrecyclableWasteAmount;
    }

    public Double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(Double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public Double getAverageAttendance() {
        return averageAttendance;
    }

    public void setAverageAttendance(Double averageAttendance) {
        this.averageAttendance = averageAttendance;
    }

    public Double getWaterPollutionRate() {
        return waterPollutionRate;
    }

    public void setWaterPollutionRate(Double waterPollutionRate) {
        this.waterPollutionRate = waterPollutionRate;
    }

    public Double getAirPollutionRate() {
        return airPollutionRate;
    }

    public void setAirPollutionRate(Double airPollutionRate) {
        this.airPollutionRate = airPollutionRate;
    }

    public Double getSoilPollutionRate() {
        return soilPollutionRate;
    }

    public void setSoilPollutionRate(Double soilPollutionRate) {
        this.soilPollutionRate = soilPollutionRate;
    }

    public Double getRockDestructionLevel() {
        return rockDestructionLevel;
    }

    public void setRockDestructionLevel(Double rockDestructionLevel) {
        this.rockDestructionLevel = rockDestructionLevel;
    }

    public Double getIndustrialEnterprisesNumber() {
        return industrialEnterprisesNumber;
    }

    public void setIndustrialEnterprisesNumber(Double industrialEnterprisesNumber) {
        this.industrialEnterprisesNumber = industrialEnterprisesNumber;
    }

    public Double getReservoirExist() {
        return reservoirExist;
    }

    public void setReservoirExist(Double reservoirExist) {
        this.reservoirExist = reservoirExist;
    }

    public Double getFlammableSubstancesExist() {
        return flammableSubstancesExist;
    }

    public void setFlammableSubstancesExist(Double flammableSubstancesExist) {
        this.flammableSubstancesExist = flammableSubstancesExist;
    }
}
