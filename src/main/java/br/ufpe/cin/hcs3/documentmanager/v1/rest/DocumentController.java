package br.ufpe.cin.hcs3.documentmanager.v1.rest;

import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message.ValidationMessages;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.request.DocumentRequest;
import br.ufpe.cin.hcs3.documentmanager.v1.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/diff")
public @RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DocumentController {

    private final DocumentService documentService;

    @Validated
    @PostMapping("{id}/left")
    public ResponseEntity<Object> saveLeftDocument(@PathVariable("id")
                                                   @NotBlank(message = ValidationMessages.ID_IS_REQUIRED)
                                                           Long id,
                                                   @Valid @RequestBody DocumentRequest documentRequest) {
        try {
            documentService.saveDocument(documentService.getLeftEntity(id, documentRequest));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Validated
    @PostMapping("{id}/right")
    public ResponseEntity<Object> saveRightDocument(@PathVariable("id")
                                                    @NotBlank(message = ValidationMessages.ID_IS_REQUIRED)
                                                            Long id,
                                                    @Valid @RequestBody DocumentRequest documentRequest) {
        try {
            documentService.saveDocument(documentService.getRightEntity(id, documentRequest));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> evaluate(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.documentService.evaluate(id));
    }

}
