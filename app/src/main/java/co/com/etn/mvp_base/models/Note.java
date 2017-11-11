package co.com.etn.mvp_base.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * co.com.etn.mvp_base.models
 * MVP_Base
 * Created by alexander.vasquez on 7/11/2017.8:18 PM
 */

@Root(name="note")
public class Note {

    @Element(name="to")
    private String to;
    @Element(name="from")
    private String from;
    @Element(name="header")
    private String header;
    @Element(name="body")
    private String body;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
