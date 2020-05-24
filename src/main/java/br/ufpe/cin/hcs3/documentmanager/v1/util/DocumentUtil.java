package br.ufpe.cin.hcs3.documentmanager.v1.util;

import br.ufpe.cin.hcs3.documentmanager.v1.model.entity.Document;
import br.ufpe.cin.hcs3.documentmanager.v1.model.representation.message.ResponseMessage;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

import static java.util.Base64.*;

import java.util.List;

@UtilityClass
public class DocumentUtil {
    public static String getNullPositionsMessage(Document document) {
        List<String> messages = new ArrayList<>();
        if (StringUtils.isEmpty(document.getLeftDoc()))
            messages.add(ResponseMessage.LEFT_DOCUMENT_IS_NULL);
        if (StringUtils.isEmpty(document.getRightDoc()))
            messages.add(ResponseMessage.RIGHT_DOCUMENT_IS_NULL);
        return String.join(",", messages);
    }

    public static Boolean hasNullPosition(Document document) {
        return StringUtils.isEmpty(document.getRightDoc()) ||
                StringUtils.isEmpty(document.getLeftDoc());
    }

    public static String checkDifferences(Document document) {
        var left = getMimeDecoder().decode(document.getLeftDoc());
        var right = getMimeDecoder().decode(document.getRightDoc());

        if (Boolean.TRUE.equals(BytesUtil.hasEqualSize(left, right))) {
            if (Boolean.TRUE.equals(BytesUtil.equals(left, right))) {
                return String.format(ResponseMessage.DOCUMENTS_WITH_SAME_SIZE, document.getId());
            }
            return compareBytesEquality(right, left);
        } else {
            return String.format(ResponseMessage.DOCUMENTS_WITH_DIFFERENT_SIZE, document.getId());
        }
    }

    private static String compareBytesEquality(byte[] right, byte[] left) {
       return BytesUtil.differentOffset(left, right).toString();
    }
}
