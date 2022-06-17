package com.example;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.ws.rs.client.WebTarget;

public enum ClientEnum {
    INSTANCE;
    private ResteasyClient clientweb;
    private WebTarget target;
    private String url;

    private ClientEnum()
    {

    }



    public WebTarget getTarget() {
        return target;
    }

    public void setTarget(WebTarget target) {
        this.target = target;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public ResteasyClient getClientweb() {
        if(clientweb==null)
        {
            clientweb = new ResteasyClientBuilder()
                    //.register(LoggingFilterRestEasy.class)
                    .build();
        }
        return clientweb;
    }


    public void setClientweb(ResteasyClient clientweb) {
        this.clientweb = clientweb;
    }



}