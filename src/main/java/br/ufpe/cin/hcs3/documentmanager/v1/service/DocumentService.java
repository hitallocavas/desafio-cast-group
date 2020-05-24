package br.ufpe.cin.hcs3.documentmanager.v1.service;

import br.ufpe.cin.hcs3.documentmanager.v1.model.builders.DocumentBuilder;
import br.ufpe.cin.hcs3.documentmanager.v1.model.entity.Document;
import br.ufpe.cin.hcs3.documentmanager.v1.model.enums.DocumentType;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message.ResponseMessage;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.request.DocumentRequest;
import br.ufpe.cin.hcs3.documentmanager.v1.repository.DocumentRepository;
import br.ufpe.cin.hcs3.documentmanager.v1.util.DocumentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        if (Boolean.TRUE.equals(DocumentUtil.hasNullPosition(document))) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    DocumentUtil.getNullPositionsMessage(document));
        }

        return DocumentUtil.checkDifferences(document);
    }

    public Document getEntity(Long id, DocumentRequest request, DocumentType documentType) {
        var documentOpt = this.findById(id);

        if (documentType.equals(DocumentType.LEFT)) {
            return DocumentBuilder.mountLeftDocument(id, request.getData(), documentOpt);
        }

        return DocumentBuilder.mountRightDocument(id, request.getData(), documentOpt);

    }

}
