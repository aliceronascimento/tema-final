package com.aliceronascimento.cloud.temafinal.controller;

import com.aliceronascimento.cloud.temafinal.exception.RxNettyHandlerException;
import com.aliceronascimento.cloud.temafinal.operations.Operation;
import com.aliceronascimento.cloud.temafinal.operations.OperationType;
import com.aliceronascimento.cloud.temafinal.service.Calculator;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import rx.Observable;

public class RxNettyHandler implements RequestHandler<ByteBuf, ByteBuf> {

    private Calculator calculator;
    private final String uri;
    private final HealthCheckEndpoint endpoint;
    public RxNettyHandler(String uri, HealthCheckEndpoint endpoint, Calculator calculator) {
        this.uri = uri;
        this.endpoint = endpoint;
        this.calculator = calculator;
    }

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        if(request.getUri().startsWith(uri)){
            return endpoint.handle(request, response);
        }
        if( request.getUri().startsWith("/history")){
            response.setStatus(HttpResponseStatus.OK);
            return response.writeStringAndFlush(calculator.getHistory().toString());
        }

        String[] message = request.getPath().substring(1).split("/", 0);
        if(message.length != 3){
            response.setStatus(HttpResponseStatus.NOT_FOUND);
            throw new RxNettyHandlerException("Incorrect uri! Please, enter the corect uri: /num1/operation/num2");
        }

        Double num1= Double.parseDouble(message[0]);
        OperationType operation = OperationType.valueOf(message[1].toUpperCase());
        Double num2 = Double.parseDouble(message[2]);

        Operation answer = calculator.calculate(num1, operation, num2);

        response.setStatus(HttpResponseStatus.OK);
        return response.writeStringAndFlush(answer.toString());
    }
}
