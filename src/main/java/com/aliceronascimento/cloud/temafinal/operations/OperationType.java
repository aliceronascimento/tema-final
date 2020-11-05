package com.aliceronascimento.cloud.temafinal.operations;

public enum OperationType {

    SUM("SUM"), SUB("SUB"), DIV("DIV"), POW("POW"), MULT("MULT");

    private String operationType;

    private OperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationType() {
        return operationType;
    }
}
