package com.sa.programacionaplicada.procesamientoDatos.presentacion;/*Author:sfeli*/

import javafx.beans.property.*;

public class DivisionEmpresa {
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final IntegerProperty devices = new SimpleIntegerProperty(this,"devices");
    private final IntegerProperty operators = new SimpleIntegerProperty(this,"operators");
    private final ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this, "id");
    public DivisionEmpresa(int id){
        this.id.set(id);
        name.set("");
        devices.set(0);
    }

    public DivisionEmpresa(int id,String name, int devices){
        this.id.set(id);
        this.name.set(name);
        this.devices.set(devices);
    }
    public int getId(){
        return id.get();
    }
    public ReadOnlyIntegerProperty idProperty(){
        return id.getReadOnlyProperty();
    }
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getDevices() {
        return devices.get();
    }

    public IntegerProperty devicesProperty() {
        return devices;
    }

    public void setDevices(int devices) {
        this.devices.set(devices);
    }

    public int getOperators() {
        return operators.get();
    }

    public IntegerProperty operatorsProperty() {
        return operators;
    }

    public void setOperators(int operators){
        this.operators.set(operators);
    }

    @Override
    public String toString() {
        return name.get() + "(Equipos:" + devices.get() + ")(Operarios:" + operators.get() + ")";
    }
}
