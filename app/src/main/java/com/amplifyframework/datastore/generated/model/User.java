package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byFirm", fields = {"firmID"})
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField NAME = field("User", "name");
  public static final QueryField AUTH_ID = field("User", "authId");
  public static final QueryField LAT = field("User", "lat");
  public static final QueryField LON = field("User", "lon");
  public static final QueryField FIRM = field("User", "firmID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String name;
  private final @ModelField(targetType="String") String authId;
  private final @ModelField(targetType="Float") Double lat;
  private final @ModelField(targetType="Float") Double lon;
  private final @ModelField(targetType="Firm") @BelongsTo(targetName = "firmID", type = Firm.class) Firm firm;
  private final @ModelField(targetType="Trip") @HasMany(associatedWith = "user", type = Trip.class) List<Trip> trips = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getAuthId() {
      return authId;
  }
  
  public Double getLat() {
      return lat;
  }
  
  public Double getLon() {
      return lon;
  }
  
  public Firm getFirm() {
      return firm;
  }
  
  public List<Trip> getTrips() {
      return trips;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private User(String id, String name, String authId, Double lat, Double lon, Firm firm) {
    this.id = id;
    this.name = name;
    this.authId = authId;
    this.lat = lat;
    this.lon = lon;
    this.firm = firm;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getName(), user.getName()) &&
              ObjectsCompat.equals(getAuthId(), user.getAuthId()) &&
              ObjectsCompat.equals(getLat(), user.getLat()) &&
              ObjectsCompat.equals(getLon(), user.getLon()) &&
              ObjectsCompat.equals(getFirm(), user.getFirm()) &&
              ObjectsCompat.equals(getCreatedAt(), user.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), user.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getAuthId())
      .append(getLat())
      .append(getLon())
      .append(getFirm())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("authId=" + String.valueOf(getAuthId()) + ", ")
      .append("lat=" + String.valueOf(getLat()) + ", ")
      .append("lon=" + String.valueOf(getLon()) + ", ")
      .append("firm=" + String.valueOf(getFirm()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
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
  public static User justId(String id) {
    return new User(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      authId,
      lat,
      lon,
      firm);
  }
  public interface BuildStep {
    User build();
    BuildStep id(String id);
    BuildStep name(String name);
    BuildStep authId(String authId);
    BuildStep lat(Double lat);
    BuildStep lon(Double lon);
    BuildStep firm(Firm firm);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String name;
    private String authId;
    private Double lat;
    private Double lon;
    private Firm firm;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          name,
          authId,
          lat,
          lon,
          firm);
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep authId(String authId) {
        this.authId = authId;
        return this;
    }
    
    @Override
     public BuildStep lat(Double lat) {
        this.lat = lat;
        return this;
    }
    
    @Override
     public BuildStep lon(Double lon) {
        this.lon = lon;
        return this;
    }
    
    @Override
     public BuildStep firm(Firm firm) {
        this.firm = firm;
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
    private CopyOfBuilder(String id, String name, String authId, Double lat, Double lon, Firm firm) {
      super.id(id);
      super.name(name)
        .authId(authId)
        .lat(lat)
        .lon(lon)
        .firm(firm);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder authId(String authId) {
      return (CopyOfBuilder) super.authId(authId);
    }
    
    @Override
     public CopyOfBuilder lat(Double lat) {
      return (CopyOfBuilder) super.lat(lat);
    }
    
    @Override
     public CopyOfBuilder lon(Double lon) {
      return (CopyOfBuilder) super.lon(lon);
    }
    
    @Override
     public CopyOfBuilder firm(Firm firm) {
      return (CopyOfBuilder) super.firm(firm);
    }
  }
  
}
