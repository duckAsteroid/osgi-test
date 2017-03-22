/**
 * Copyright (c) 2017 Ford Motor Co.
 */
package com.asteroid.duck.osgi.example.tangerinedream.impl;

import com.asteroid.duck.osgi.example.album.AlbumInfoService;
import com.asteroid.duck.osgi.example.album.impl.AlbumInfoServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.io.InputStream;

/**
 * An OSGi activator for this service
 */
public class Activator implements BundleActivator {

    private ServiceRegistration<AlbumInfoService> serviceRegistration;

    @Override
    public void start(final BundleContext context) throws Exception {
        InputStream albums = Activator.class.getResourceAsStream("/album-data.csv");
        InputStream tracks = Activator.class.getResourceAsStream("/track-data.csv");
        AlbumInfoServiceImpl albumService = AlbumInfoServiceImpl.loadFrom(albums, tracks);
        serviceRegistration = context.registerService(AlbumInfoService.class, albumService, null);
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        serviceRegistration.unregister();
    }
}
