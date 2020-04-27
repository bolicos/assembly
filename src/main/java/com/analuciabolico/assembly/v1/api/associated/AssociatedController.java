package com.analuciabolico.assembly.v1.api.associated;

import com.analuciabolico.assembly.v1.core.annotations.SwaggerDocumentation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.analuciabolico.assembly.v1.associated.dto.AssociatedDto;
import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.associated.service.interfaces.IAssociatedService;
import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@SwaggerDocumentation
@Api(value = "Associated")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/associated")
public class AssociatedController implements Serializable {

    private final IAssociatedService associatedService;

    @ApiOperation(value = "Find an associate by ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Associated> findById(@PathVariable Long id) {
        return new ResponseEntity<>(associatedService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Saves the data of an associate")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceCreated> save(@RequestBody AssociatedDto associatedDto) {
        return new ResponseEntity<>(associatedService.save(associatedDto), HttpStatus.CREATED);
    }
}
