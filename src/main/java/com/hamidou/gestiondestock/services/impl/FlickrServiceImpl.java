package com.hamidou.gestiondestock.services.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.hamidou.gestiondestock.services.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class FlickrServiceImpl implements FlickrService {

    private Flickr flickr;

    public FlickrServiceImpl(Flickr flickr){
        this.flickr = flickr;
    }
    
    @Override
    public String savePhoto(InputStream photo, String title) throws FlickrException {

        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoId = flickr.getUploader().upload(photo, uploadMetaData);

        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }

}
