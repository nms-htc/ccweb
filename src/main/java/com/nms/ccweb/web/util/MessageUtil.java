/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.web.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Nguyen Trong Cuong
 * @since 08/26/2014
 * @version 1.0
 */
public class MessageUtil {

    public static final String APP_RESOURCE_BUNDLE_NAME = "Message";
    private static final Logger LOGGER = Logger.getLogger(MessageUtil.class.getName());

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle(APP_RESOURCE_BUNDLE_NAME);
    }

    /**
     * Get a string for given key from application resource bundle.
     *
     * @param key the key for the desired string 
     * @return the string for the given key
     */
    public static String getBundleMessage(String key) {
        String ret = key;
        try {
            ret = getResourceBundle().getString(key);
        } catch (MissingResourceException e) {
            LOGGER.log(Level.WARNING, "[MessageUtil] resource with key {0} not found in application "
                    + "resource bundle, exception message : {1}", new Object[]{key, e.toString()});
        }
        return ret;
    }
    
    public static void addGlobalInfoMessage(String message) {
        addGlobalMessage(FacesMessage.SEVERITY_INFO, message);
    }
    
    public static void addGlobalErrorMessage(String message) {
        addGlobalMessage(FacesMessage.SEVERITY_ERROR, message);
        JsfUtil.isValidationFailed();
    }
    
    @SuppressWarnings("ThrowableResultIgnored")
    public static void addGlobalErrorMessage(Throwable t) {
        addGlobalMessage(FacesMessage.SEVERITY_ERROR, JsfUtil.getRootCause(t).getLocalizedMessage());
    }
    
    public static void addGlobalWarnMessage(String message) {
        addGlobalMessage(FacesMessage.SEVERITY_WARN, message);
    }
    
    public static void addGlobalFatalMessage(String message) {
        addGlobalMessage(FacesMessage.SEVERITY_FATAL, message);
    }
    
    public static void addGlobalMessage(FacesMessage.Severity severity, String message) {
        FacesMessage msg = new FacesMessage(severity, getBundleMessage(message), getBundleMessage(message));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
