package com.asteroid.duck.osgi.example.album.impl;

import com.asteroid.duck.osgi.example.album.Album;
import com.asteroid.duck.osgi.example.album.Track;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class AlbumInfoServiceImplTest {
    private AlbumInfoServiceImpl subject;

    @Before
    public void setup() {
        InputStream albumStream = AlbumInfoServiceImplTest.class.getResourceAsStream("/album-data.csv");
        InputStream trackStream = AlbumInfoServiceImplTest.class.getResourceAsStream("/track-data.csv");
        subject = AlbumInfoServiceImpl.loadFrom(albumStream, trackStream);
    }
    @Test
    public void testAlbums() {
        List<Album> albums = subject.getAlbums();
        assertNotNull(albums);
        assertEquals(2, albums.size());
    }

    @Test
    public void testTracks() {
        List<Track> tracks = subject.getTracks();
        assertNotNull(tracks);
        assertEquals(17, tracks.size());
    }
}