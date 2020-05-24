package br.ufpe.cin.hcs3.documentmanager.v1.service;

import br.ufpe.cin.hcs3.documentmanager.v1.model.builders.DocumentBuilder;
import br.ufpe.cin.hcs3.documentmanager.v1.model.entity.Document;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message.ResponseMessage;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.request.DocumentRequest;
import br.ufpe.cin.hcs3.documentmanager.v1.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.ufpe.cin.hcs3.documentmanager.v1.util.DocumentUtil.*;

import java.util.Optional;

@Service
public @RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DocumentService {

    private final DocumentRepository documentRepository;

    public Document saveDocument(Document document) {
       return this.documentRepository.save(document);
    }

    private Optional<Document> findById(Long id) {
        return this.documentRepository.findById(id);
    }

    public String evaluate(Long id) {
        var document = this.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.DOCUMENT_NOT_FOUND));

        if (hasNullPosition(document)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    getNullPositionsMessage(document));
        }

        return checkDifferences(document);
    }

    public Document getLeftEntity(Long id, DocumentRequest request) {
        var documentOpt = this.findById(id);
        if (documentOpt.isPresent()) {
            var document = documentOpt.get();
            document.setLeftDoc(request.getData());
            return document;
        } else {
            return DocumentBuilder.mountLeftDocument(id, request.getData());
        }
    }

    public Document getRightEntity(Long id, DocumentRequest request) {
        var documentOpt = this.findById(id);
        if (documentOpt.isPresent()) {
            var document = documentOpt.get();
            document.setRightDoc(request.getData());
            return document;
        } else {
            return DocumentBuilder.mountRightDocument(id, request.getData());
        }
    }
}
