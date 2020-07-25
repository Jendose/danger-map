package com.hazardous_zones.entity;

import com.hazardous_zones.entity.enums.HazardousZoneType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "hazardous_zone")
public class HazardousZoneEntity {
    @Id
    @Column(name = "zone_id", unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    private Long id;

    // НАЗВАНИЕ
    private String name;

    // КООРДИНАТЫ ЗОНЫ
    private Double latitude;
    private Double longitude;

    // ТИП ЗОНЫ
    private HazardousZoneType hazardousZoneType;

    // ХАРАКТЕРИСТИКИ ЗОНЫ
    private Double nuclearWasteAmount;          // Количество радиоактивных отходов
    private Double recycledWasteAmount;         // Количество перерабатываемых отходов
    private Double unrecyclableWasteAmount;     // Количество неперерабатываемых отходов
    private Double averageTemperature;          // Средняя температура
    private Double averageAttendance;           // Средняя посещаемость
    private Double waterPollutionRate;          // Процент загрязнение воды
    private Double airPollutionRate;            // Процент загрязнение воздуха
    private Double soilPollutionRate;           // Процент загрязнение почвы
    private Double rockDestructionLevel;        // Степень разрушенности пород
    private Double industrialEnterprisesNumber; // Количество промышленных предприятий на территории
    private Double reservoirExist;              // Наличие водоема
    private Double flammableSubstancesExist;    // Работа с легковоспламеняющимися веществами

    public HazardousZoneEntity() {
    }

    public HazardousZoneEntity(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public HazardousZoneEntity(String name, Double latitude, Double longitude, HazardousZoneType hazardousZoneType, Double nuclearWasteAmount, Double recycledWasteAmount, Double unrecyclableWasteAmount, Double averageTemperature, Double averageAttendance, Double waterPollutionRate, Double airPollutionRate, Double soilPollutionRate, Double rockDestructionLevel, Double industrialEnterprisesNumber, Double reservoirExist, Double flammableSubstancesExist) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hazardousZoneType = hazardousZoneType;
        this.nuclearWasteAmount = nuclearWasteAmount;
        this.recycledWasteAmount = recycledWasteAmount;
        this.unrecyclableWasteAmount = unrecyclableWasteAmount;
        this.averageTemperature = averageTemperature;
        this.averageAttendance = averageAttendance;
        this.waterPollutionRate = waterPollutionRate;
        this.airPollutionRate = airPollutionRate;
        this.soilPollutionRate = soilPollutionRate;
        this.rockDestructionLevel = rockDestructionLevel;
        this.industrialEnterprisesNumber = industrialEnterprisesNumber;
        this.reservoirExist = reservoirExist;
        this.flammableSubstancesExist = flammableSubstancesExist;
    }

    // ПОЛУЧИТЬ ЗНАЧЕНИЕ КОЛИЧЕСТВА ПАРАМЕТРОВ ЗОНЫ
    static int getPropertiesLength() {
        return 12;
    }

    // ПОЛУЧИТЬ МАССИВ ПАРАМЕТРОВ ЗОНЫ
    public double[] getProperties() {
        return new double[]{
                nuclearWasteAmount,
                recycledWasteAmount,
                unrecyclableWasteAmount,
                averageTemperature,
                averageAttendance,
                waterPollutionRate,
                airPollutionRate,
                soilPollutionRate,
                rockDestructionLevel,
                industrialEnterprisesNumber,
                reservoirExist,
                flammableSubstancesExist};
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public HazardousZoneType getHazardousZoneType() {
        return hazardousZoneType;
    }

    public void setHazardousZoneType(HazardousZoneType hazardousZoneType) {
        this.hazardousZoneType = hazardousZoneType;
    }
}