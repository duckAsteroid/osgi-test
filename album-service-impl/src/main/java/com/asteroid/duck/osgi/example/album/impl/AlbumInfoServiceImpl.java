/**
 * Copyright (c) 2016 Dr. Chris Senior.
 * See LICENSE.txt for licensing terms
 */
package com.asteroid.duck.osgi.example.album.impl;

import com.asteroid.duck.osgi.example.album.Album;
import com.asteroid.duck.osgi.example.album.AlbumInfoService;
import com.asteroid.duck.osgi.example.album.Track;
import org.apache.commons.io.LineIterator;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple implementation of the album info service (uses a CSV file)
 */
public class AlbumInfoServiceImpl implements AlbumInfoService {

    public static AlbumInfoServiceImpl loadFrom(InputStream albumData, InputStream trackData) {
        List<Album> albums = loadData(albumData, new DataFactory<Album>() {
            @Override
            public Album parse(final String line) {
                return Album.parse(line);
            }
        });
        List<Track> tracks = loadData(trackData, new DataFactory<Track>() {
            @Override
            public Track parse(final String line) {
                return Track.parse(line);
            }
        });
        return new AlbumInfoServiceImpl(albums, tracks);
    }

    /**
     * Parses lines in a file and creates objects of type T
     * @param <T>
     */
    private interface DataFactory<T> {
        /**
         * Parse a single line into an instance
         * @param line the line to parse
         * @return the instance
         */
        T parse(String line);
    }

    /**
     * Load data from a stream. Each line is passed to the factory to parse.
     * @param stream The data stream to read
     * @param factory the factory that parses each line
     * @param <T> the type of object to parse
     * @return the list of parsed objects
     */
    private static <T> List<T> loadData(InputStream stream, DataFactory<T> factory) {
        List<T> results = new ArrayList<>();
        InputStreamReader reader = new InputStreamReader(stream);
        LineIterator lines = new LineIterator(reader);
        while(lines.hasNext()) {
            String line = lines.nextLine();
            if (line.startsWith("#") || line.length() == 0) {
                continue;
            }
            T data = factory.parse(line);
            results.add(data);
        }
        return Collections.unmodifiableList(results);
    }

    private final List<Album> albums;
    private final List<Track> tracks;

    private AlbumInfoServiceImpl(List<Album> albums, List<Track> tracks) {
        this.albums = albums;
        this.tracks = tracks;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
