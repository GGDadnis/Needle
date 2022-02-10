package org.alterdata.shopback.app.model.enums;

public enum TipoLayoutReciboEnum {
    LAYOUT_1(1),
    LAYOUT_2(2);

    private Integer tipo;

    TipoLayoutReciboEnum(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public static TipoLayoutReciboEnum getTipoLayout(Integer tipo) {

        for (TipoLayoutReciboEnum tipoEnum : TipoLayoutReciboEnum.values()) {
            if (tipoEnum.getTipo().equals(tipo)) {
                return tipoEnum;
            }
        }
        return TipoLayoutReciboEnum.LAYOUT_1;
    }
}
