package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Trip type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Trips", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Trip implements Model {
  public static final QueryField ID = field("Trip", "id");
  public static final QueryField WHERE = field("Trip", "where");
  public static final QueryField MILES = field("Trip", "miles");
  public static final QueryField HOURS = field("Trip", "hours");
  public static final QueryField DROP_OFF = field("Trip", "dropOff");
  public static final QueryField DEAD_HEAD = field("Trip", "deadHead");
  public static final QueryField RATE = field("Trip", "rate");
  public static final QueryField DELIVERY_NOTES = field("Trip", "deliveryNotes");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String where;
  private final @ModelField(targetType="Float") Double miles;
  private final @ModelField(targetType="String") String hours;
  private final @ModelField(targetType="String", isRequired = true) String dropOff;
  private final @ModelField(targetType="Float") Double deadHead;
  private final @ModelField(targetType="Float") Double rate;
  private final @ModelField(targetType="String") String deliveryNotes;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getWhere() {
      return where;
  }
  
  public Double getMiles() {
      return miles;
  }
  
  public String getHours() {
      return hours;
  }
  
  public String getDropOff() {
      return dropOff;
  }
  
  public Double getDeadHead() {
      return deadHead;
  }
  
  public Double getRate() {
      return rate;
  }
  
  public String getDeliveryNotes() {
      return deliveryNotes;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Trip(String id, String where, Double miles, String hours, String dropOff, Double deadHead, Double rate, String deliveryNotes) {
    this.id = id;
    this.where = where;
    this.miles = miles;
    this.hours = hours;
    this.dropOff = dropOff;
    this.deadHead = deadHead;
    this.rate = rate;
    this.deliveryNotes = deliveryNotes;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Trip trip = (Trip) obj;
      return ObjectsCompat.equals(getId(), trip.getId()) &&
              ObjectsCompat.equals(getWhere(), trip.getWhere()) &&
              ObjectsCompat.equals(getMiles(), trip.getMiles()) &&
              ObjectsCompat.equals(getHours(), trip.getHours()) &&
              ObjectsCompat.equals(getDropOff(), trip.getDropOff()) &&
              ObjectsCompat.equals(getDeadHead(), trip.getDeadHead()) &&
              ObjectsCompat.equals(getRate(), trip.getRate()) &&
              ObjectsCompat.equals(getDeliveryNotes(), trip.getDeliveryNotes()) &&
              ObjectsCompat.equals(getCreatedAt(), trip.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), trip.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getWhere())
      .append(getMiles())
      .append(getHours())
      .append(getDropOff())
      .append(getDeadHead())
      .append(getRate())
      .append(getDeliveryNotes())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Trip {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("where=" + String.valueOf(getWhere()) + ", ")
      .append("miles=" + String.valueOf(getMiles()) + ", ")
      .append("hours=" + String.valueOf(getHours()) + ", ")
      .append("dropOff=" + String.valueOf(getDropOff()) + ", ")
      .append("deadHead=" + String.valueOf(getDeadHead()) + ", ")
      .append("rate=" + String.valueOf(getRate()) + ", ")
      .append("deliveryNotes=" + String.valueOf(getDeliveryNotes()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static WhereStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Trip justId(String id) {
    return new Trip(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      where,
      miles,
      hours,
      dropOff,
      deadHead,
      rate,
      deliveryNotes);
  }
  public interface WhereStep {
    DropOffStep where(String where);
  }
  

  public interface DropOffStep {
    BuildStep dropOff(String dropOff);
  }
  

  public interface BuildStep {
    Trip build();
    BuildStep id(String id);
    BuildStep miles(Double miles);
    BuildStep hours(String hours);
    BuildStep deadHead(Double deadHead);
    BuildStep rate(Double rate);
    BuildStep deliveryNotes(String deliveryNotes);
  }
  

  public static class Builder implements WhereStep, DropOffStep, BuildStep {
    private String id;
    private String where;
    private String dropOff;
    private Double miles;
    private String hours;
    private Double deadHead;
    private Double rate;
    private String deliveryNotes;
    @Override
     public Trip build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Trip(
          id,
          where,
          miles,
          hours,
          dropOff,
          deadHead,
          rate,
          deliveryNotes);
    }
    
    @Override
     public DropOffStep where(String where) {
        Objects.requireNonNull(where);
        this.where = where;
        return this;
    }
    
    @Override
     public BuildStep dropOff(String dropOff) {
        Objects.requireNonNull(dropOff);
        this.dropOff = dropOff;
        return this;
    }
    
    @Override
     public BuildStep miles(Double miles) {
        this.miles = miles;
        return this;
    }
    
    @Override
     public BuildStep hours(String hours) {
        this.hours = hours;
        return this;
    }
    
    @Override
     public BuildStep deadHead(Double deadHead) {
        this.deadHead = deadHead;
        return this;
    }
    
    @Override
     public BuildStep rate(Double rate) {
        this.rate = rate;
        return this;
    }
    
    @Override
     public BuildStep deliveryNotes(String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String where, Double miles, String hours, String dropOff, Double deadHead, Double rate, String deliveryNotes) {
      super.id(id);
      super.where(where)
        .dropOff(dropOff)
        .miles(miles)
        .hours(hours)
        .deadHead(deadHead)
        .rate(rate)
        .deliveryNotes(deliveryNotes);
    }
    
    @Override
     public CopyOfBuilder where(String where) {
      return (CopyOfBuilder) super.where(where);
    }
    
    @Override
     public CopyOfBuilder dropOff(String dropOff) {
      return (CopyOfBuilder) super.dropOff(dropOff);
    }
    
    @Override
     public CopyOfBuilder miles(Double miles) {
      return (CopyOfBuilder) super.miles(miles);
    }
    
    @Override
     public CopyOfBuilder hours(String hours) {
      return (CopyOfBuilder) super.hours(hours);
    }
    
    @Override
     public CopyOfBuilder deadHead(Double deadHead) {
      return (CopyOfBuilder) super.deadHead(deadHead);
    }
    
    @Override
     public CopyOfBuilder rate(Double rate) {
      return (CopyOfBuilder) super.rate(rate);
    }
    
    @Override
     public CopyOfBuilder deliveryNotes(String deliveryNotes) {
      return (CopyOfBuilder) super.deliveryNotes(deliveryNotes);
    }
  }
  
}
