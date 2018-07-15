package com.springAprendendo.br.aprendendo.domain.domain.enums;

public enum  EstadoPagamento {
    PENDENTE(1,"Pagamento pendente"),
    QUITADO(2,"Quitado"),
    CANCELADO(3,"Intem foi cancelado");

    private Integer cod;
    private  String descricao;

    private EstadoPagamento(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnmum(Integer cod){
        if(cod == null){
            return null;
        }
        for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
            if(cod.equals(estadoPagamento.getCod())){
                return estadoPagamento;
            }
        }
        throw new IllegalArgumentException("Id invãlido: "+ cod);
    }
}
