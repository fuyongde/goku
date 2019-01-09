package com.sunflower.goku.webflux.demo.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author fuyongde
 * @date 2019-01-09 22:24
 * @desc TODO add description in here
 */
@Component
public class EchoHandler {

    public Mono<ServerResponse> echo(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromObject("Hello"));
    }
}
