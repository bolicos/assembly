package com.analuciabolico.assembly.v1.core.service.client;

import com.analuciabolico.assembly.v1.core.model.AssociatedVoteApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "${user-info-service-api.name}", url = "${user-info-service-api.url}")
public interface IAssociatedVoteClient {

    @GetMapping(value = "/users/{cpf}", consumes = MediaType.APPLICATION_JSON_VALUE)
    AssociatedVoteApi ableToVote(@PathVariable String cpf);
}
