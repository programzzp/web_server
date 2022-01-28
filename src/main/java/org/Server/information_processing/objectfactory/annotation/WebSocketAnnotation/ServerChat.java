package org.Server.information_processing.objectfactory.annotation.WebSocketAnnotation;

public interface ServerChat {
    public void onOpen(String username);
    public void onMessage(String message);
    public void onClose(String username);
}
