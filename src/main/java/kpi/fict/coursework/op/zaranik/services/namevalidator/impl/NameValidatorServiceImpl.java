package kpi.fict.coursework.op.zaranik.services.namevalidator.impl;


import kpi.fict.coursework.op.zaranik.services.namevalidator.NameValidatorService;

public class NameValidatorServiceImpl implements NameValidatorService {
    public  boolean isValidName(String name){
        return (name != null && !name.trim().isEmpty());
    }
}
