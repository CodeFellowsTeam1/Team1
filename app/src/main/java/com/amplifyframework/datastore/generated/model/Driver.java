package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasOne;
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

/** This is an auto generated class representing the Driver type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Drivers", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Driver implements Model {
  public static final QueryField ID = field("Driver", "id");
  public static final QueryField LICENSE_PLATE = field("Driver", "licensePlate");
  public static final QueryField LICENSE_TYPE = field("Driver", "licenseType");
  public static final QueryField DRIVER_USERS_ID = field("Driver", "driverUsersId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="User") @HasOne(associatedWith = "id", type = User.class) User users = null;
  private final @ModelField(targetType="String", isRequired = true) String licensePlate;
  private final @ModelField(targetType="String", isRequired = true) String licenseType;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String driverUsersId;
  public String getId() {
      return id;
  }
  
  public User getUsers() {
      return users;
  }
  
  public String getLicensePlate() {
      return licensePlate;
  }
  
  public String getLicenseType() {
      return licenseType;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getDriverUsersId() {
      return driverUsersId;
  }
  
  private Driver(String id, String licensePlate, String licenseType, String driverUsersId) {
    this.id = id;
    this.licensePlate = licensePlate;
    this.licenseType = licenseType;
    this.driverUsersId = driverUsersId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Driver driver = (Driver) obj;
      return ObjectsCompat.equals(getId(), driver.getId()) &&
              ObjectsCompat.equals(getLicensePlate(), driver.getLicensePlate()) &&
              ObjectsCompat.equals(getLicenseType(), driver.getLicenseType()) &&
              ObjectsCompat.equals(getCreatedAt(), driver.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), driver.getUpdatedAt()) &&
              ObjectsCompat.equals(getDriverUsersId(), driver.getDriverUsersId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getLicensePlate())
      .append(getLicenseType())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getDriverUsersId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Driver {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("licensePlate=" + String.valueOf(getLicensePlate()) + ", ")
      .append("licenseType=" + String.valueOf(getLicenseType()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("driverUsersId=" + String.valueOf(getDriverUsersId()))
      .append("}")
      .toString();
  }
  
  public static LicensePlateStep builder() {
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
  public static Driver justId(String id) {
    return new Driver(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      licensePlate,
      licenseType,
      driverUsersId);
  }
  public interface LicensePlateStep {
    LicenseTypeStep licensePlate(String licensePlate);
  }
  

  public interface LicenseTypeStep {
    BuildStep licenseType(String licenseType);
  }
  

  public interface BuildStep {
    Driver build();
    BuildStep id(String id);
    BuildStep driverUsersId(String driverUsersId);
  }
  

  public static class Builder implements LicensePlateStep, LicenseTypeStep, BuildStep {
    private String id;
    private String licensePlate;
    private String licenseType;
    private String driverUsersId;
    @Override
     public Driver build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Driver(
          id,
          licensePlate,
          licenseType,
          driverUsersId);
    }
    
    @Override
     public LicenseTypeStep licensePlate(String licensePlate) {
        Objects.requireNonNull(licensePlate);
        this.licensePlate = licensePlate;
        return this;
    }
    
    @Override
     public BuildStep licenseType(String licenseType) {
        Objects.requireNonNull(licenseType);
        this.licenseType = licenseType;
        return this;
    }
    
    @Override
     public BuildStep driverUsersId(String driverUsersId) {
        this.driverUsersId = driverUsersId;
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
    private CopyOfBuilder(String id, String licensePlate, String licenseType, String driverUsersId) {
      super.id(id);
      super.licensePlate(licensePlate)
        .licenseType(licenseType)
        .driverUsersId(driverUsersId);
    }
    
    @Override
     public CopyOfBuilder licensePlate(String licensePlate) {
      return (CopyOfBuilder) super.licensePlate(licensePlate);
    }
    
    @Override
     public CopyOfBuilder licenseType(String licenseType) {
      return (CopyOfBuilder) super.licenseType(licenseType);
    }
    
    @Override
     public CopyOfBuilder driverUsersId(String driverUsersId) {
      return (CopyOfBuilder) super.driverUsersId(driverUsersId);
    }
  }
  
}
