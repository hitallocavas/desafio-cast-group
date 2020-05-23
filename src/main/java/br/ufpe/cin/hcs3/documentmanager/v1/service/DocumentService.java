package br.ufpe.cin.hcs3.documentmanager.v1.service;

import br.ufpe.cin.hcs3.documentmanager.v1.model.builders.DocumentBuilder;
import br.ufpe.cin.hcs3.documentmanager.v1.model.entity.Document;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message.ResponseMessage;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.request.DocumentRequest;
import br.ufpe.cin.hcs3.documentmanager.v1.repository.DocumentRepository;
import br.ufpe.cin.hcs3.documentmanager.v1.util.BytesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public @RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DocumentService {

    private final DocumentRepository documentRepository;

    public void saveDocument(Document document) {
        this.documentRepository.save(document);
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

    private Optional<Document> findById(Long id) {
        return this.documentRepository.findById(id);
    }

    public String getNullPositionsMessage(Document document) {
        List<String> messages = new ArrayList<>();
        if (StringUtils.isEmpty(document.getLeftDoc()))
            messages.add(ResponseMessage.LEFT_DOCUMENT_IS_NULL);
        if (StringUtils.isEmpty(document.getRightDoc().isEmpty()))
            messages.add(ResponseMessage.RIGHT_DOCUMENT_IS_NULL);
        return String.join(",", messages);
    }

    public boolean hasNullPosition(Document document) {
        return StringUtils.isEmpty(document.getRightDoc()) ||
                StringUtils.isEmpty(document.getLeftDoc());
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

    private String checkDifferences(Document document) {
        var left = Base64.getDecoder().decode(document.getLeftDoc());
        var right = Base64.getDecoder().decode(document.getRightDoc());

        if (Boolean.TRUE.equals(BytesUtil.hasEqualSize(left, right))) {
            if (Boolean.TRUE.equals(BytesUtil.equals(left, right))) {
                return ResponseMessage.
                        getMessageReplaceId(ResponseMessage.DOCUMENTS_WITH_SAME_SIZE, document.getId());
            }
            return compareBytesEquality(document, right, left);

        } else {
            return ResponseMessage.
                    getMessageReplaceId(ResponseMessage.DOCUMENTS_WITH_DIFFERENT_SIZE, document.getId());
        }
    }

    private String compareBytesEquality(Document document, byte[] right, byte[] left) {
        var differentOffsetOpt = BytesUtil.differentOffset(left, right);
        if (differentOffsetOpt.isPresent()) {
            int offset = differentOffsetOpt.getAsInt();
            return "" + offset;
        }
        return ResponseMessage.getMessageReplaceId(ResponseMessage.DOCUMENTS_WITH_SAME_SIZE, document.getId());
    }


}
