package org.example.websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.inject.Inject;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.example.model.Device;

@ApplicationScoped
@ServerEndpoint("/actions")
public class DeviceWebSocketServer {

//    @Inject
    private DeviceSessionHandler sessionHandler = new DeviceSessionHandler();


    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(DeviceWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }


    @OnMessage
    public void handleMessage(String message, Session session) {

        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

        if ("add".equals(jsonMessage.getString("action"))) {
            Device device = new Device();
            device.setName(jsonMessage.getString("name"));
            device.setDescription(jsonMessage.getString("description"));
            device.setType(jsonMessage.getString("type"));
            device.setStatus("Off");
            System.out.println("adding device");
            sessionHandler.addDevice(device);
        }

        if ("remove".equals(jsonMessage.getString("action"))) {
            int id = (int) jsonMessage.getInt("id");
            System.out.println("removing device");
            sessionHandler.removeDevice(id);
        }

        if ("toggle".equals(jsonMessage.getString("action"))) {
            int id = (int) jsonMessage.getInt("id");
            System.out.println("toggling device");
            sessionHandler.toggleDevice(id);
        }
        }
    }
}
