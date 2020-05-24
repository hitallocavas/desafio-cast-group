package br.ufpe.cin.hcs3.documentmanager.v1.rest;

import br.ufpe.cin.hcs3.documentmanager.v1.model.enums.DocumentType;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.request.DocumentRequest;
import br.ufpe.cin.hcs3.documentmanager.v1.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/diff")
public @RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DocumentController {

    private final DocumentService documentService;

    @Validated
    @PostMapping("{id}/left")
    public ResponseEntity<Object> saveLeftDocument(@PathVariable("id") Long id,
                                                   @Valid @RequestBody DocumentRequest documentRequest) {
        documentService.saveDocument(documentService.getEntity(id, documentRequest, DocumentType.LEFT));
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @Validated
    @PostMapping("{id}/right")
    public ResponseEntity<Object> saveRightDocument(@PathVariable("id") Long id,
                                                    @Valid @RequestBody DocumentRequest documentRequest) {

        documentService.saveDocument(documentService.getEntity(id, documentRequest, DocumentType.RIGHT));
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<String> evaluate(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.documentService.evaluate(id));
    }

}
