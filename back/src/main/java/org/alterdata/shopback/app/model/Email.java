package org.alterdata.shopback.app.model;

import java.io.File;

public class Email {

    private String destino;
    private String corpo;
    private String assunto;
    private File reciboPDF;

    public Email(String destino, String corpo, String assunto, File reciboPDF) {
        this.destino = destino;
        this.corpo = corpo;
        this.assunto = assunto;
        this.reciboPDF = reciboPDF;
    }

    public Email() {
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public File getReciboPDF() {
        return reciboPDF;
    }

    public void setReciboPDF(File reciboPDF) {
        this.reciboPDF = reciboPDF;
    }
}
