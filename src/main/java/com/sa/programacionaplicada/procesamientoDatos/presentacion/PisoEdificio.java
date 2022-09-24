package com.sa.programacionaplicada.procesamientoDatos.presentacion;/*Author:sfeli*/

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PisoEdificio {

    private final ObservableList<SimpleIntegerProperty> devicesPerOffice = FXCollections.observableArrayList();
    private final ReadOnlyStringWrapper id = new ReadOnlyStringWrapper(this,"id");
    private final ReadOnlyIntegerWrapper total = new ReadOnlyIntegerWrapper(this,"total");
    private final ReadOnlyStringWrapper percentage = new ReadOnlyStringWrapper(this,"percentage");
    private final ReadOnlyIntegerWrapper budget = new ReadOnlyIntegerWrapper(this, "budget");
    public PisoEdificio(int id) {
        this(id, 0);
    }
    public PisoEdificio(int id, int numberOfOffices){
        this.id.set("Piso " + id);
        for (int i = 0; i < numberOfOffices; i++) {
            SimpleIntegerProperty devicesInOffice = new SimpleIntegerProperty(this,"officeDevicesProperty");
            devicesPerOffice.add(devicesInOffice);
        }
        this.percentage.set("0%");

    }
    public ReadOnlyStringProperty idProperty(){
        return id.getReadOnlyProperty();
    }
    public String getId() {
        return id.get();
    }
    public IntegerProperty officeDevicesProperty(int office){
        return devicesPerOffice.get(office);
    }
    public int getOfficeDevices(int office){
        return devicesPerOffice.get(office).get();
    }
    public void setOfficeDevices(Number devices, int office){
        devicesPerOffice.get(office).set(devices.intValue());
    }
    public ReadOnlyIntegerProperty totalProperty(){
        return total.getReadOnlyProperty();
    }
    public void setTotal(int total){
        this.total.set(total);
    }
    public ReadOnlyStringProperty percentageProperty(){
        return percentage.getReadOnlyProperty();
    }
    public void setPercentage(double raw_percentage){
        String number = "%.2f".formatted(raw_percentage*100);
        percentage.set(number + "%");
    }
    public ReadOnlyIntegerProperty budgetProperty(){
        return budget.getReadOnlyProperty();
    }
    public void setBudget(int budget){
        this.budget.set(budget);
    }
    public int sizeOffices(){
        return devicesPerOffice.size();
    }
    public void setOffices(int numberOfOffices){
        for (int i = 0; i < numberOfOffices; i++) {
            SimpleIntegerProperty devicesInOffice = new SimpleIntegerProperty(this,"officeDevicesProperty");
            devicesPerOffice.add(devicesInOffice);
        }
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getId() + " ");
        for (IntegerProperty office:
             devicesPerOffice) {
            stringBuilder.append("[").append(office.get()).append("] ");
        }
        return stringBuilder.toString();
    }
}
