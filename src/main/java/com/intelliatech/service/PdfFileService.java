package com.intelliatech.service;

import io.micronaut.http.multipart.CompletedFileUpload;

public interface PdfFileService {

    public String uploadFile(CompletedFileUpload file);
}
