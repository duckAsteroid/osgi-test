/**
 * Copyright (c) 2017 Ford Motor Co.
 */
package com.asteroid.duck.osgi.example.underworld.impl;

import com.asteroid.duck.osgi.example.underworld.AlbumInfoService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * An OSGi activator for this service
 */
public class Activator implements BundleActivator {

    private ServiceRegistration<AlbumInfoService> serviceRegistration;

    @Override
    public void start(final BundleContext context) throws Exception {
        serviceRegistration = context.registerService(AlbumInfoService.class, new AlbumInfoServiceImpl(), null);
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        serviceRegistration.unregister();
    }
}
