package com.pucminas.gestaoquadras.agendamento.quadras.dataprovider;

import com.pucminas.gestaoquadras.agendamento.quadra.Quadra;
import com.pucminas.gestaoquadras.agendamento.quadra.dataprovider.QuadraGateway;
import org.springframework.stereotype.Component;

@Component
public class QuadraGatewayImpl implements QuadraGateway {

    public QuadraGatewayImpl() {
    }

    @Override
    public Quadra getQuadraById(String id) {
        return new Quadra("1", "Quadra X", "AREIA", "teste", "1");
    }
}
