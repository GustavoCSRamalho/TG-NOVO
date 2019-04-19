package com.example.tg_novo.utils;

import com.example.tg_novo.models.Coordenate;

public class SupportDataFireDB {
    public String getDatabaseNameCoordenate() {
        return "coordenates";
    }

    public String getDatabaseNameNotifications() {
        return "notifications";
    }

    public String getDatabaseNamePedidos() {
        return "pedidos";
    }

    public String getApiKey() {
        return null;
    }

    public String getBaseUrl() {
        return "https://tcc01-6e0dd.firebaseio.com/" + getDatabaseNameCoordenate() + ".json";
    }

    public String apiKeyUrl() {
        return "?apiKey=" + getApiKey();
    }

    public String collectionName() {
        return "coordenates";
    }

    public String buildContactsSaveURL() {
        return getBaseUrl();
    }

    public String buildContactsFetchURL() {
        return getBaseUrl() + collectionName() + apiKeyUrl();
    }

    public String createCoordenate(Coordenate coordenate) {
        return String
                .format("{\"latitude\": \"%s\", "
                                + "\"longitude\": \"%s\", " + "\"tipo\": \"%s\"}",
                        coordenate.getLatitude(), coordenate.getLongitude(), coordenate.getusuario());
    }


}
