package com.analuciabolico.assembly.v1.api.vote;

import com.analuciabolico.assembly.v1.core.annotations.SwaggerDocumentation;
import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import com.analuciabolico.assembly.v1.vote.dto.VoteDto;
import com.analuciabolico.assembly.v1.vote.service.interfaces.IVoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SwaggerDocumentation
@Api(value = "Vote")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/vote")
public class VoteController {

    private final IVoteService voteService;

    @ApiOperation(value = "Save a vote")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceCreated> save(@RequestBody VoteDto voteDto) {
        return new ResponseEntity<>(voteService.save(voteDto), HttpStatus.CREATED);
    }
}
