/**
 * Copyright (c) 2016 Dr. Chris Senior.
 * See LICENSE.txt for licensing terms
 */
package com.asteroid.duck.osgi.example.album;

import java.util.List;

/**
 * Interface to a service that provides information about Underworld albums
 */
public interface AlbumInfoService {
    List<Album> getAlbums();
    List<Track> getTracks();
}
