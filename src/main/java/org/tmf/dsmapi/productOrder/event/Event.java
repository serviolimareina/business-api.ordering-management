/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmf.dsmapi.productOrder.event;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.tmf.dsmapi.commons.utils.CustomJsonDateSerializer;
import org.tmf.dsmapi.productOrder.model.ProductOrder;

/**
 *
 * @author pierregauthier
 */
@XmlRootElement
@Entity
@Table(name="Event_ProductOrder")
@JsonPropertyOrder(value = {"event", "reason", "date", "eventType"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private String id;
    private String reason;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Date dateEvent;
    @OneToOne (cascade=CascadeType.MERGE)
    private ProductOrder resource; //check for object
    @Enumerated(value = EnumType.STRING)
    private EventTypeEnum eventType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeEnum eventType) {
        this.eventType = eventType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return dateEvent;
    }

    public void setDate(Date date) {
        this.dateEvent = date;
    }

    public ProductOrder getResource() {
        return resource;
    }

    public void setResource(ProductOrder resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", eventType=" + eventType + ", reason=" + reason + ", dateEvent=" + dateEvent + ", resource=" + resource + "}";
    }
}