package com.tsk.ecommerce.entities.models;

import com.tsk.ecommerce.entities.enumerations.UploadedFileType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploaded {

    private String name;
    private String link;
    private UploadedFileType type;

    public static UploadedFileType getType(String contentType) {
        if (contentType.startsWith("audio")) {
            return UploadedFileType.AUDIO;
        }
        if (contentType.startsWith("video")) {
            return UploadedFileType.VIDEO;
        }
        if (contentType.startsWith("image")) {
            return UploadedFileType.IMAGE;
        }
        if (contentType.startsWith("application/pdf")) {
            return UploadedFileType.PDF;
        }
        return UploadedFileType.UNKNOWN;
    }
}
